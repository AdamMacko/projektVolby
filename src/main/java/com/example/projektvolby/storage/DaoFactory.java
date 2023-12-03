package com.example.projektvolby.storage;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {
    INSTANCE;
    private KandidatiDao kandidatiDao;
    private StranaDao stranyDao;
    private UliceDao uliceDao;
    private VolebneListkyDao volebne_listyDao;
    private VoleniKandidatiDao voleny_kandidatiDao;
    private VolicDao volicDao;

    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJdbcTemplate() {
        if(jdbcTemplate==null){
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("heslo");
            dataSource.setUrl("jdbc:mysql://localhost:3306/evolby");
            jdbcTemplate = new JdbcTemplate(dataSource);


        }
        return jdbcTemplate;
    }




    public KandidatiDao getKandidatiDao() {

        return kandidatiDao;
    }

    public StranaDao getStranyDao() {
        if(stranyDao == null){
          //  stranyDao = new MysqlStranyDao(getJdbcTemplate());
        }


        return stranyDao;
    }

    public UliceDao getUliceDao() {
        return uliceDao;
    }

    public VolebneListkyDao getVolebneListkyDao() {
        return volebne_listyDao;
    }

    public VoleniKandidatiDao getVolenyKandidatDao() {
        return voleny_kandidatiDao;
    }

    public VolicDao getVoliciDao() {
        if (volicDao == null){

        }

        return volicDao;
    }
}
