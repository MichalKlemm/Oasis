package repository;

import dataUser.DataUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(DataUser datauser) {
        return jdbcTemplate.update("INSERT INTO users (name, surname, personid, uuid) VALUES(?,?,?,?)",
                new Object[] { datauser.getName(), datauser.getSurname(), datauser.getPersonId(), datauser.getUuid() });
    }

    @Override
    public int update(DataUser datauser) {
        return jdbcTemplate.update("UPDATE users SET name=?, surname=? WHERE id=?",
                new Object[] { datauser.getName(), datauser.getSurname(), datauser.getId() });
    }

    @Override
    public DataUser findById(int id) {
        try {
            DataUser dataUser = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?",
                    BeanPropertyRowMapper.newInstance(DataUser.class), id);
            return dataUser;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    @Override
    public List<DataUser> findAll() {
        return jdbcTemplate.query("SELECT * from users", BeanPropertyRowMapper.newInstance(DataUser.class));
    }

}
