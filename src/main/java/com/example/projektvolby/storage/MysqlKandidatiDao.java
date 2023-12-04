package com.example.projektvolby.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import com.example.projektvolby.Kandidati;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;


public class MysqlKandidatiDao implements KandidatiDao{
    private JdbcTemplate jdbcTemplate;

    public MysqlKandidatiDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Kandidati> kandidatiRM(){
        return new RowMapper<Kandidati>() {

            @Override
            public Kandidati mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String meno = rs.getString("meno");
                String priezvisko = rs.getString("priezvisko");
                int vek=rs.getInt("vek");

                Kandidati kandidat = new Kandidati(id, meno, priezvisko,vek);
                return kandidat;
            }
        };
    }

    @Override
    public List<Kandidati> getAllBySubjectId(long StranaId) {
        String sql = "SELECT id, meno, priezvisko,vek FROM evolby"
                + " WHERE strana_id = " + StranaId
                + " ORDER BY priezvisko";
        return jdbcTemplate.query(sql, kandidatiRM());
    }
    @Override
    public Kandidati getById(long id) throws EntityNotFoundException {
        String sql = "SELECT id, meno, priezvisko, vek FROM kandidati"
                + " WHERE id = " + id;
        return jdbcTemplate.queryForObject(sql, kandidatiRM());
    }
    @Override
    public Kandidati save(Kandidati kandidat, long StranaId) throws EntityNotFoundException {
        Objects.requireNonNull(kandidat, "Kandidat nemôže byť prázdny");
        Objects.requireNonNull(kandidat.getMeno(),"Meno nemôže byť prázdne");
        Objects.requireNonNull(kandidat.getPriezvisko(),"Priezvisko nemôže byť prázdne");
        Objects.requireNonNull(kandidat.getVek(),"Vek nemôže byť prázdny");
        if (kandidat.getId() == null) { //INSERT
            String query = "INSERT INTO kandidati (meno, priezvisko, vek, strana_id) "
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
            Kandidati saved = Kandidati.clone(kandidat);
            saved.setId(id);
            return saved;
        } else {	//UPDATE
            String query = "UPDATE student SET meno=?, priezvisko=?, vek=?, strany_id=? "
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
