package com.example.projektvolby.storage;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {
    INSTANCE;
    private KandidatDao kandidatDao;
    private StranaDao stranaDao;
    private UliceDao uliceDao;
    private VolebneListkyDao VolebnelistyDao;
    private VoleniKandidatiDao VolenyKandidatiDao;
    private VolicDao volicDao;

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




    public KandidatDao getKandidatiDao() {
        if (kandidatDao==null){
            kandidatDao= new MysqlKandidatDao(getJdbcTemplate());

        }
        return kandidatDao;
    }

    public StranaDao getStranyDao() {
        if(stranaDao==null){
            stranaDao= new MysqlStranaDao(getJdbcTemplate());
        }
        return stranaDao;
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

    public VolicDao getVoliciDao() {
        return volicDao;
    }
}
