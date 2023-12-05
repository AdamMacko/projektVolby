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
        String query = "SELECT id, name, study_year FROM subject "
                + "ORDER BY study_year DESC, name ASC";
        List<Strana> result = jdbcTemplate.query(query, new RowMapper<Strana>() {

            @Override
            public Strana mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String nazov = rs.getString("nazov");
                String sqlvolebnyPlan = rs.getString("volebny_plan");
                Text volebnyPlan = new Text(sqlvolebnyPlan);
//				List<Student> students = studentDao.getAllBySubjectId(id);
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
                Text volebnyPlanText = strana.getVolebny_plan();
                String volebnyPlanValue =volebnyPlanText.getText();
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
            String query = "UPDATE subject SET nazov=?, volebny_plan=? "
                    + "WHERE id = ?";
            int count = jdbcTemplate.update(query, strana.getNazov(),
                    strana.getVolebny_plan(),
                    strana.getId());
            if (count == 0) {
                throw new EntityNotFoundException(
                        "Subject with id " + strana.getId() + " not found");
            }
//			String deleteStudentsQuery = "DELETE FROM student where subject_id=?";
//			jdbcTemplate.update(deleteStudentsQuery, subject.getId());
//			for (Student student: subject.getStudents()) {
//				studentDao.save(student, subject.getId());
//			}
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
