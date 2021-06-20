package com.joutvhu.training.batch.dao.impl;

import com.joutvhu.training.batch.dao.UserDao;
import com.joutvhu.training.batch.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    @Qualifier("mainJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(List<UserDto> users) {
        jdbcTemplate.batchUpdate(
                "insert into APP_USER (USERNAME, FULLNAME, PASSWORD) values (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        UserDto user = users.get(i);
                        ps.setString(1, user.getUsername());
                        ps.setString(2, user.getFullName());
                        ps.setString(3, user.getPassword());
                    }

                    @Override
                    public int getBatchSize() {
                        return users.size();
                    }
                });
    }

    @Override
    public Integer count() {
        return jdbcTemplate.queryForObject("select count(*) from APP_USER", Integer.class);
    }
}
