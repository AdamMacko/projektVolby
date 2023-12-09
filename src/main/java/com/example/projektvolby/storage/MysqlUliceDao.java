package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.Strana;
import com.example.projektvolby.Ulica;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class MysqlUliceDao implements UliceDao {
    private JdbcTemplate jdbcTemplate;

    public MysqlUliceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Ulica> ulicaRM(){
        return new RowMapper<Ulica>() {

            @Override
            public Ulica mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String nazov = rs.getString("nazov");
                int popisneCislo=rs.getInt("popisne_cislo");
                String PSC = rs.getString("PSC");


                Ulica ulica = new Ulica(id, nazov, popisneCislo,PSC);
                return ulica;
            }
        };
    }
    @Override
    public List<Ulica> getAll() {
        String query = "SELECT id, nazov, popisne_cislo, PSC FROM ulica ";
        return jdbcTemplate.query(query, ulicaRM());
    }
    @Override
    public Ulica save(Ulica ulica) throws EntityNotFoundException {
        Objects.requireNonNull(ulica, "Kandidat nemôže byť prázdny");
        Objects.requireNonNull(ulica.getNazov(),"Názov ulice nemôže byť prázdny");
        Objects.requireNonNull(ulica.getPopisne_cislo(),"Popisné číslo ulice nemôže byť prázdny");
        Objects.requireNonNull(ulica.getPSC(),"PSC ulice nemôže byť prázdne");
        if (ulica.getId() == 0) { //INSERT
            String query = "INSERT INTO ulica (nazov, popisne_cislo, PSC) "
                    + "VALUES (?,?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, ulica.getNazov());
                    statement.setInt(2, ulica.getPopisne_cislo());
                    statement.setString(3,ulica.getPSC());
                    return statement;
                }
            }, keyHolder);
            long id = keyHolder.getKey().longValue();
            Ulica saved = Ulica.clone(ulica);
            saved.setId(id);
            return saved;
        } else {	//UPDATE
            String query = "UPDATE ulica SET nazov=?, popisne_cislo=?, PSC=? "
                    + "WHERE id = ?";
            int count = jdbcTemplate.update(query, ulica.getNazov(),
                    ulica.getPopisne_cislo(),
                    ulica.getPSC(),
                    ulica.getId());
            if (count == 0) {
                throw new EntityNotFoundException(
                        "ulica s id " + ulica.getId() + " sa nenašiel");
            }
            return ulica;
        }
    }
    @Override
    public void delete(long ulicaid) throws EntityNotFoundException {
        String query = "DELETE FROM volici WHERE ulica_id=?";
        jdbcTemplate.update(query,ulicaid);
        query = "DELETE FROM ulica WHERE id=?";
        int count = jdbcTemplate.update(query,ulicaid);
        if (count == 0) {
            throw new EntityNotFoundException(
                    "Ulica s id " + ulicaid + " neexistuje");
        }
    }
    @Override
    public void deleteall() {
        String query = "DELETE FROM volici WHERE ulica_id IS NOT null";
        jdbcTemplate.update(query);
        query="DELETE FROM ulica WHERE id IS NOT null";
        jdbcTemplate.update(query);

    }

    @Override
    public int getIdByPSC(String psc) {
        String query = "SELECT id FROM ulica WHERE PSC = ?";
        return jdbcTemplate.queryForObject(query,Integer.class,psc);
    }


}
