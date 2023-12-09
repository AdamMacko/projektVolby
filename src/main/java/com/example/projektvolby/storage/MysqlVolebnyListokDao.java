package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.Ulica;
import com.example.projektvolby.VolebnyListok;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class MysqlVolebnyListokDao implements VolebnyListokDao{
    private JdbcTemplate jdbcTemplate;

    public MysqlVolebnyListokDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<VolebnyListok> volebnyListokRM(){
        return new RowMapper<VolebnyListok>() {

            @Override
            public VolebnyListok mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                long stranaId = rs.getLong("id");



                VolebnyListok volebnyListok = new VolebnyListok(id, stranaId);
                return volebnyListok;
            }
        };
    }
    @Override
    public List<VolebnyListok> getAll() {
        String query = "SELECT id, strany_id FROM volebny_listok ";
        return jdbcTemplate.query(query, volebnyListokRM());
    }

    @Override
    public List<VolebnyListok> getAllByStranaId(long stranaId) {
        String sql = "SELECT id, strany_id FROM volebny_listok"
                + " WHERE strany_id = " + stranaId
                ;
        return jdbcTemplate.query(sql, volebnyListokRM());
    }
    @Override
    public VolebnyListok save(VolebnyListok volebnyListok, long StranaId) throws EntityNotFoundException {
        Objects.requireNonNull(volebnyListok.getStranaId(),"Strana nemoze byt nulova");
        if (volebnyListok.getId() == 0) { //INSERT
            String query = "INSERT INTO volebny_listok (strany_id) "
                    + "VALUES (?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    statement.setLong(1, StranaId);
                    return statement;
                }
            }, keyHolder);
            long id = keyHolder.getKey().longValue();
            VolebnyListok saved = VolebnyListok.clone(volebnyListok);
            saved.setId(id);
            return saved;
        } else {	//UPDATE
            String query = "UPDATE volebny_listok SET strany_id=? "
                    + "WHERE id = ?";
            int count = jdbcTemplate.update(query,
                    StranaId,
                    volebnyListok.getId());
            if (count == 0) {
                throw new EntityNotFoundException(
                        "Volebna strana s id " + volebnyListok.getId() + " sa nenašla");
            }
            return volebnyListok;
        }
    }





}
