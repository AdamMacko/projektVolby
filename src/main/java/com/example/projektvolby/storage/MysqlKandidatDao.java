package com.example.projektvolby.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import com.example.projektvolby.Kandidat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;



public class MysqlKandidatDao implements KandidatDao{
    private JdbcTemplate jdbcTemplate;

    public MysqlKandidatDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Kandidat> kandidatRM(){
        return new RowMapper<Kandidat>() {

            @Override
            public Kandidat mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String meno = rs.getString("meno");
                String priezvisko = rs.getString("priezvisko");
                int vek=rs.getInt("vek");

                Kandidat kandidat = new Kandidat(id, meno, priezvisko,vek);
                return kandidat;
            }
        };
    }

    @Override
    public List<Kandidat> getAllByStranaId(long StranaId) {
        String sql = "SELECT id, meno, priezvisko,vek FROM evolby"
                + " WHERE strany_id = " + StranaId
                + " ORDER BY priezvisko";
        return jdbcTemplate.query(sql, kandidatRM());
    }
    @Override
    public Kandidat getById(long id) throws EntityNotFoundException {
        String sql = "SELECT id, meno, priezvisko, vek FROM kandidati"
                + " WHERE id = " + id;
        return jdbcTemplate.queryForObject(sql, kandidatRM());
    }
    @Override
    public Kandidat save(Kandidat kandidat, long StranaId) throws EntityNotFoundException {
        Objects.requireNonNull(kandidat, "Kandidat nemôže byť prázdny");
        Objects.requireNonNull(kandidat.getMeno(),"Meno nemôže byť prázdne");
        Objects.requireNonNull(kandidat.getPriezvisko(),"Priezvisko nemôže byť prázdne");
        Objects.requireNonNull(kandidat.getVek(),"Vek nemôže byť prázdny");
        if (kandidat.getId() == 0) { //INSERT
            String query = "INSERT INTO kandidati (meno, priezvisko, vek, strany_id) "
                    + "VALUES (?,?,?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, kandidat.getMeno());
                    statement.setString(2, kandidat.getPriezvisko());
                    statement.setInt(3,kandidat.getVek());
                    statement.setLong(4, StranaId);
                    return statement;
                }
            }, keyHolder);
            long id = keyHolder.getKey().longValue();
            Kandidat saved = Kandidat.clone(kandidat);
            saved.setId(id);
            return saved;
        } else {	//UPDATE
            String query = "UPDATE kandidati SET meno=?, priezvisko=?, vek=?, strany_id=? "
                    + "WHERE id = ?";
            int count = jdbcTemplate.update(query, kandidat.getMeno(),
                    kandidat.getPriezvisko(),
                    kandidat.getVek(),
                    StranaId,
                    kandidat.getId());
            if (count == 0) {
                throw new EntityNotFoundException(
                        "Kandidat s id " + kandidat.getId() + " sa nenašiel");
            }
            return kandidat;
        }
    }
    @Override
    public void delete(long id) throws EntityNotFoundException {
        String query = "DELETE FROM kandidati WHERE id = ?";
        int count = jdbcTemplate.update(query, id);
        if (count == 0) {
            throw new EntityNotFoundException(
                    "Kandidat s id " + id + " sa nenašiel");
        }
    }
}