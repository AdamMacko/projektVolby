package com.example.projektvolby.storage;

import com.example.projektvolby.Ulica;
import com.example.projektvolby.Volic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlVoliciDao implements VolicDao{
    private  JdbcTemplate jdbcTemplate;

    public MysqlVoliciDao(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
