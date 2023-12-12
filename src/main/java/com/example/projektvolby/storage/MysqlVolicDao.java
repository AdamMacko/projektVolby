package com.example.projektvolby.storage;

import com.example.projektvolby.Volic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class MysqlVolicDao implements VolicDao {
    private JdbcTemplate jdbcTemplate;



    public MysqlVolicDao(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Volic> VolicRM() {
        return new RowMapper<Volic>() {

            @Override
            public Volic mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
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
    public Volic save(Volic volic,Long ulicaId) throws EntityNotFoundException {
        Objects.requireNonNull(volic, "Volic nemôže byť prázdny");
        Objects.requireNonNull(volic.getMeno(),
                "Meno volica nemôže byť prázdne");
        Objects.requireNonNull(volic.getPriezvisko(),
                "Priezvisko volica nemôže byť prázdne");
        Objects.requireNonNull(volic.getcOP(),
                "Cislo OP volica nemôže byť prázdne");



        if (volic.getId() == null) { // INSERT
            String query = "INSERT INTO volici (meno, priezvisko, cOP, dochadzka, ulica_id) VALUES (?, ?, ?, ?, ?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    System.out.println(volic.getMeno());
                    statement.setString(1, volic.getMeno());
                    statement.setString(2, volic.getPriezvisko());
                    statement.setString(3, volic.getcOP());
                    statement.setString(4, "0");
                    statement.setLong(5, ulicaId);
                    return statement;
                }
            }, keyHolder);
            long id = keyHolder.getKey().longValue();
            Volic saved = Volic.clone(volic);
            saved.setId(id);
            return saved;


        } else { // UPDATE
            String query = "UPDATE volici SET meno=?, priezvisko=?, cOP=?, dochadzka=?, ulica_id=? WHERE id = ?";
            int count = jdbcTemplate.update(query, volic.getMeno(),
                    volic.getPriezvisko(),
                    volic.getcOP(),
                    volic.isDochadzka(), // Use isDochadzka() for boolean value
                    volic.getUlicaId(), // Assuming getUlicaId() returns ulica_id
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

    @Override
    public void deleteAll() {
        String query = "DELETE FROM volici"; // SQL príkaz na vymazanie všetkých záznamov z tabuľky volici
        jdbcTemplate.update(query);
    }

    public boolean overHesloMeno(String meno,String priezvisko,String cOP){
        boolean odpoved = false;
        String query = "SELECT COUNT(*) FROM volici WHERE meno = ? AND priezvisko = ? AND cOP = ?";
        int pocet = jdbcTemplate.queryForObject(query, Integer.class,meno,priezvisko,cOP);
        if(pocet > 0){
            odpoved = true;
        }
        return odpoved;
    }

    public void aktualizujDochadzku(String meno, String priezvisko, String cOP) {
        String query = "UPDATE volici SET dochadzka = 1 WHERE meno = ? AND priezvisko = ? AND cOP = ?";
        jdbcTemplate.update(query, meno, priezvisko, cOP);
    }

    public boolean bolVolit(String meno, String priezvisko, String cOP){
        String query = "SELECT COUNT(*) FROM volici WHERE meno = ? AND priezvisko = ? AND cOP = ? AND dochadzka = 1";
        int pocet = jdbcTemplate.queryForObject(query, Integer.class, meno, priezvisko, cOP);
        if (pocet == 1){
            return true;
        }
        return false;
    }
}