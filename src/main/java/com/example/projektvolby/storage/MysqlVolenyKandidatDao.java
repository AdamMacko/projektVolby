package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.VolebnyListok;
import com.example.projektvolby.VolenyKandidat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class MysqlVolenyKandidatDao implements VolenyKandidatDao {
    private JdbcTemplate jdbcTemplate;

    public MysqlVolenyKandidatDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<VolenyKandidat> volenyKandidatRM(){
        return new RowMapper<VolenyKandidat>() {

            @Override
            public VolenyKandidat mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                long stranaId = rs.getLong("voleny_kandidat");
                long listokId = rs.getLong("volebny_listok_id");



                VolenyKandidat volenyKandidat = new VolenyKandidat(id, stranaId,listokId);
                return volenyKandidat;
            }
        };
    }
    @Override
    public List<VolenyKandidat> getAllByKandidatId(long kandidatId) {
        String sql = "SELECT id, voleny_kandidat, volebny_listok_id FROM voleni_kandidati"
                + " WHERE voleny_kandidat = " + kandidatId ;
        return jdbcTemplate.query(sql, volenyKandidatRM());
    }
    @Override
    public VolenyKandidat save(VolenyKandidat volenyKandidat) throws EntityNotFoundException {
        Objects.requireNonNull(volenyKandidat, "volenykandidat nemôže byť prázdna");
        Objects.requireNonNull(volenyKandidat.getKandidatId(),"kandidatid  nemoze byt nula");
        Objects.requireNonNull(volenyKandidat.getListokId(),"volebnylistokid  nemoze byt nula");
        if (volenyKandidat.getId() == 0) { //INSERT
            String query = "INSERT INTO voleni_kandidati (voleny_kandidat,volebny_listok_id) "
                    + "VALUES (?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    statement.setLong(1, volenyKandidat.getKandidatId());
                    statement.setLong(2, volenyKandidat.getListokId());
                    return statement;
                }
            }, keyHolder);
            long id = keyHolder.getKey().longValue();
            VolenyKandidat saved =VolenyKandidat.clone(volenyKandidat);
            saved.setId(id);
            return saved;
        } else {	//UPDATE
            String query = "UPDATE voleni_kandidati SET voleny_kandidat=?,volebny_listok_id=?  "
                    + "WHERE id = ?";
            int count = jdbcTemplate.update(query,
                    volenyKandidat.getKandidatId(),
                    volenyKandidat.getListokId(),
                    volenyKandidat.getId());
            if (count == 0) {
                throw new EntityNotFoundException(
                        "Volebna strana s id " + volenyKandidat.getId() + " sa nenašla");
            }
            return volenyKandidat;
        }
    }


}
