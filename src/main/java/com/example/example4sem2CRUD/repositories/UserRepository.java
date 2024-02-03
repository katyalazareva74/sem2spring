package com.example.example4sem2CRUD.repositories;

import com.example.example4sem2CRUD.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private  final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return (List<User>) jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable (firstName,lastName) VALUES ( ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return  user;
    }

    /**
     * Метод deletebyId(int id) удаляет запись из базы данных по заданному id.
     * @param id - номер записи в базе данных
     */
    public void deletebyId(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    /**
     * Метод findById(int id) ищет запись в базе данных по заданному id.
     * @param id - номер записи в базе данных
     * @return - возвращает искомый объет
     */
    public User findById(int id) {
        String sql = "SELECT * FROM userTable WHERE id=?";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jdbc.queryForObject(sql, userRowMapper, id);
    }

    /**
     * Метод upDate(User user) ищет запись в базе данных по заданному id.
     * @param user объект класса User
     * @return - возвращает отредактированный объект
     */
    public User upDate(User user) {
        String sql = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }
}
