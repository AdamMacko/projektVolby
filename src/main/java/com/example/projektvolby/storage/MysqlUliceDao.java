package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.Strana;
import com.example.projektvolby.Ulica;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MysqlUliceDao implements UliceDao{
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


}
