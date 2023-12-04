package com.example.projektvolby.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.projektvolby.Kandidati;
import com.example.projektvolby.Strany;
import javafx.scene.text.Text;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;


public class MysqlStranydao implements StranyDao{
    private JdbcTemplate jdbcTemplate;
    private KandidatiDao kandidatiDao = DaoFactory.INSTANCE.getKandidatiDao();
    public MysqlStranydao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Strany> getAll() {
        String query = "SELECT id, name, study_year FROM subject "
                + "ORDER BY study_year DESC, name ASC";
        List<Strany> result = jdbcTemplate.query(query, new RowMapper<Strany>() {

            @Override
            public Strany mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String nazov = rs.getString("nazov");
                String sqlvolebnyPlan = rs.getString("volebny_plan");
                Text volebnyPlan = new Text(sqlvolebnyPlan);
//				List<Student> students = studentDao.getAllBySubjectId(id);
                return new Strany(id, nazov, volebnyPlan, null);
            }
        });
        for (Strany str: result) {
            str.setKandidati(kandidatiDao.getAllBySubjectId(str.getId()));
        }
        return result;
    }
    @Override
    public Strany save(Strany strana) throws EntityNotFoundException {
        Objects.requireNonNull(strana, "Strana nemôže byť prázdna");
        Objects.requireNonNull(strana.getKandidati(),
                "List kandidatov nemôže byť prázdny");
        Objects.requireNonNull(strana.getNazov(),
                "Meno strany nemôže byť prázdne");

        if (strana.getId() == null) { //INSERT
            String query = "INSERT INTO strany (nazov,volebny_plan) "
                    + "VALUES (?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, strana.getNazov());
                Text volebnyPlanText = strana.getVolebny_plan();
                String volebnyPlanValue =volebnyPlanText.getText();
                statement.setString(2, volebnyPlanValue);
                return statement;
            }, keyHolder);
            long id = keyHolder.getKey().longValue();
            for (Kandidati kandidat: strana.getKandidati()) {
                kandidatiDao.save(kandidat,id);
            }
            return new Strany(id,
                    strana.getNazov(),
                    strana.getVolebny_plan(),
                    strana.getKandidati()
                            .stream()
                            .map(kandidat-> Kandidati.clone(kandidat))
                            .toList());

        } else {	//UPDATE
            String query = "UPDATE subject SET nazov=?, volebny_plan=? "
                    + "WHERE id = ?";
            int count = jdbcTemplate.update(query, strana.getNazov(),
                    strana.getVolebny_plan(),
                    strana.getId());
            if (count == 0) {
                throw new EntityNotFoundException(
                        "Subject with id " + strana.getId() + " not found");
            }
//			String deleteStudentsQuery = "DELETE FROM student where subject_id=?";
//			jdbcTemplate.update(deleteStudentsQuery, subject.getId());
//			for (Student student: subject.getStudents()) {
//				studentDao.save(student, subject.getId());
//			}
            for (Kandidati novyKan: strana.getKandidati()) {
                if (novyKan.getId() == null) {
                    kandidatiDao.save(novyKan, strana.getId());
                }
            }
            List<Kandidati> minuly = kandidatiDao.getAllBySubjectId(strana.getId());
            for (Kandidati minKan: minuly) {
                if (! strana.getKandidati().contains(minKan)) {
                    kandidatiDao.delete(minKan.getId());
                }
            }
            return strana;
        }
    }
    @Override
    public void delete(long stranaId) throws EntityNotFoundException {
        String query = "DELETE FROM kandidati WHERE strany_id=?";
        jdbcTemplate.update(query,stranaId);
        query = "DELETE FROM strany WHERE id=?";
        int count = jdbcTemplate.update(query,stranaId);
        if (count == 0) {
            throw new EntityNotFoundException(
                    "Strana s id " + stranaId + " neexistuje");
        }
    }








}
