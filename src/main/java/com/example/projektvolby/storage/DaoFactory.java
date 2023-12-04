package com.example.projektvolby.storage;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {
    INSTANCE;
    private KandidatiDao kandidatiDao;
    private StranyDao stranyDao;
    private UliceDao uliceDao;
    private VolebneListkyDao VolebnelistyDao;
    private VoleniKandidatiDao VolenyKandidatiDao;
    private VoliciDao voliciDao;

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
        if (kandidatiDao==null){
            kandidatiDao= new MysqlKandidatiDao(getJdbcTemplate());

        }
        return kandidatiDao;
    }

    public StranyDao getStranyDao() {
        if(stranyDao==null){
            stranyDao= new MysqlStranydao(getJdbcTemplate());
        }
        return stranyDao;
    }

    public UliceDao getUliceDao() {
        return uliceDao;
    }

    public VolebneListkyDao getVolebneListkyDao() {
        return VolebnelistyDao;
    }

    public VoleniKandidatiDao getVolenyKandidatDao() {
        return VolenyKandidatiDao;
    }

    public VoliciDao getVoliciDao() {
        return voliciDao;
    }
}
