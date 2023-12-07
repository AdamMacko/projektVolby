package com.example.projektvolby.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.Strana;
import javafx.scene.text.Text;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;


public class MysqlStranaDao implements StranaDao{
    private JdbcTemplate jdbcTemplate;
    private KandidatDao kandidatDao = DaoFactory.INSTANCE.getKandidatiDao();
    public MysqlStranaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Strana> getAll() {
        String query = "SELECT id, nazov, volebny_plan FROM strany ";
        List<Strana> result = jdbcTemplate.query(query, new RowMapper<Strana>() {

            @Override
            public Strana mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String nazov = rs.getString("nazov");
                String sqlvolebnyPlan = rs.getString("volebny_plan");
                String volebnyPlan = new String(sqlvolebnyPlan);
                return new Strana(id, nazov, volebnyPlan, null);
            }
        });
        for (Strana str: result) {
          str.setKandidat(kandidatDao.getAllByStranaId(str.getId()));
        }
        return result;
    }
    @Override
    public Strana save(Strana strana) throws EntityNotFoundException {
        Objects.requireNonNull(strana, "Strana nemôže byť prázdna");
        Objects.requireNonNull(strana.getKandidati(),
                "List kandidatov nemôže byť prázdny");
        Objects.requireNonNull(strana.getNazov(),
                "Meno strany nemôže byť prázdne");

        if (strana.getId() == null) { //INSERT
            String query = "INSERT INTO strany (nazov,volebny_plan) "
                    + "VALUES (?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, strana.getNazov());
                String volebnyPlanText = strana.getVolebny_plan();
                String volebnyPlanValue =volebnyPlanText;
                statement.setString(2, volebnyPlanValue);
                return statement;
            }, keyHolder);
            long id = keyHolder.getKey().longValue();
            for (Kandidat kandidat: strana.getKandidati()) {
                kandidatDao.save(kandidat,id);
            }
            return new Strana(id,
                    strana.getNazov(),
                    strana.getVolebny_plan(),
                    strana.getKandidati()
                            .stream()
                            .map(kandidat-> Kandidat.clone(kandidat))
                            .toList());

        } else {	//UPDATE
            String query = "UPDATE strany SET nazov=?, volebny_plan=? "
                    + "WHERE id = ?";
            int count = jdbcTemplate.update(query, strana.getNazov(),
                    strana.getVolebny_plan(),
                    strana.getId());
            if (count == 0) {
                throw new EntityNotFoundException(
                        "Strana s  id " + strana.getId() + " neexistuje");
            }

            for (Kandidat novyKan: strana.getKandidati()) {
                if (novyKan.getId() == 0) {
                    kandidatDao.save(novyKan, strana.getId());
                }
            }
            List<Kandidat> minuly = kandidatDao.getAllByStranaId(strana.getId());
            for (Kandidat minKan: minuly) {
                if (! strana.getKandidati().contains(minKan)) {
                    kandidatDao.delete(minKan.getId());
                }
            }
            return strana;
        }
    }
    @Override
    public void delete(long stranaId) throws EntityNotFoundException {
        String query = "DELETE FROM kandidati WHERE strany_id=?";
        jdbcTemplate.update(query,stranaId);
        query = "DELETE FROM strany WHERE id=?";
        int count = jdbcTemplate.update(query,stranaId);
        if (count == 0) {
            throw new EntityNotFoundException(
                    "Strana s id " + stranaId + " neexistuje");
        }
    }
}
