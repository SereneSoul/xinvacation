package com.xinvacation.bss.repository;

import com.xinvacation.base.repository.BaseRepository;
import com.xinvacation.bss.entity.User;

import java.util.List;

public interface IUserRepository extends BaseRepository<User,Integer> {
    User getUserByNameAndPassword(String username, String password);
    int countByName(String username);
    int countByPhone(String phone);
    int countByEmail(String email);
}
