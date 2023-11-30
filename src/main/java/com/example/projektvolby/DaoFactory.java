package com.example.projektvolby;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public enum DaoFactory {
    INSTANCE;
    private KandidatiDao kandidatiDao;
    private  StranyDao stranyDao;
    private UliceDao uliceDao;
    private Volebne_listkyDao volebne_listyDao;
    private Voleny_kandidatiDao voleny_kandidatiDao;
    private VoliciDao voliciDao;

    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJdbcTemplate() {
        if(jdbcTemplate==null){
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("paz1c");
            dataSource.setPassword("paz1cjesuper");
            dataSource.setUrl("jdbc:mysql://localhost:3306/paz1c");
            jdbcTemplate = new JdbcTemplate(dataSource);


        }
        return jdbcTemplate;
    }




    public KandidatiDao getKandidatiDao() {

        return kandidatiDao;
    }

    public StranyDao getStranyDao() {
        return stranyDao;
    }

    public UliceDao getUliceDao() {
        return uliceDao;
    }

    public Volebne_listkyDao getVolebne_listyDao() {
        return volebne_listyDao;
    }

    public Voleny_kandidatiDao getVoleny_kandidatiDao() {
        return voleny_kandidatiDao;
    }

    public VoliciDao getVoliciDao() {
        return voliciDao;
    }
}
