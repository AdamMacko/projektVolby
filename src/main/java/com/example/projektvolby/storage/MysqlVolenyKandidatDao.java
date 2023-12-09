package com.example.projektvolby.storage;

import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlVolenyKandidatDao implements VolenyKandidatDao {
    private JdbcTemplate jdbcTemplate;

    public MysqlVolenyKandidatDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
