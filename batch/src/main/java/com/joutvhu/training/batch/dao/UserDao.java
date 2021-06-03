package com.joutvhu.training.batch.dao;

import com.joutvhu.training.batch.model.dto.UserDto;

import java.util.List;

public interface UserDao {
    void insert(List<UserDto> users);

    Integer count();
}
