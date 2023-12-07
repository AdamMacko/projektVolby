package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.Strana;
import com.example.projektvolby.Ulica;
import com.example.projektvolby.Volic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class MysqlVoliciDao implements VolicDao {
    private JdbcTemplate jdbcTemplate;
    private VolicDao volicDao = DaoFactory.INSTANCE.getVoliciDao();


    public MysqlVoliciDao(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Volic> VolicRM() {
        return new RowMapper<Volic>() {

            @Override
            public Volic mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String meno = rs.getString("meno");
                String priezvisko = rs.getString("priezvisko");
                String cOP = rs.getString("cOP");
                boolean dochadzka  = rs.getBoolean("dochadzka");
                Long ulicaId = rs.getLong("ulica_id");

                return ( new Volic(id, meno, priezvisko, cOP,dochadzka,ulicaId));

            }
        };
    }

    @Override
     public List<Volic> getAll(){
        String query = "SELECT id, meno, priezvisko,cOP,dochadzka,ulica_id FROM volici ";
     return(jdbcTemplate.query(query,VolicRM()));

    }

    @Override
    public Volic save(Volic volic) throws EntityNotFoundException {
        Objects.requireNonNull(volic, "Volic nemôže byť prázdny");
        Objects.requireNonNull(volic.getMeno(),
                "Meno volica nemôže byť prázdne");
        Objects.requireNonNull(volic.getPriezvisko(),
                "Priezvisko volica nemôže byť prázdne");
        Objects.requireNonNull(volic.getcOP(),
                "Cislo OP volica nemôže byť prázdne");
        Objects.requireNonNull(volic.getUlica(),
                "Ulica volica nemôže byť prázdna");


        if (volic.getId() == 0) { //INSERT
            String query = "INSERT INTO kandidati (meno, priezvisko, cOP,dochadzka, ulica_id) "
                    + "VALUES (?,?,?,?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, volic.getMeno());
                    statement.setString(2, volic.getPriezvisko());
                    statement.setString(3,volic.getcOP());
                    statement.setBoolean(4,false);
                    statement.setLong(5,volic.getUlica());
                    return statement;
                }
            }, keyHolder);
            long id = keyHolder.getKey().longValue();
            Volic saved = Volic.clone(volic);
            saved.setId(id);
            return saved;
        } else {	//UPDATE
            String query = "UPDATE volici SET meno=?, priezvisko=?, cOP=?,dochadzka=?, ulica_id=? "
                    + "WHERE id = ?";
            int count = jdbcTemplate.update(query, volic.getMeno(),
                    volic.getPriezvisko(),
                    volic.getcOP(),
                    volic.isDochadzka(),
                    volic.getUlica(),
                    volic.getId());
            if (count == 0) {
                throw new EntityNotFoundException(
                        "Volic s id " + volic.getId() + " sa nenašiel");
            }
            return volic;
        }
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        String query = "DELETE FROM volici WHERE id = ?";
        int count = jdbcTemplate.update(query, id);
        if (count == 0) {
            throw new EntityNotFoundException(
                    "Volic s id " + id + " sa nenašiel");
        }
    }




}
