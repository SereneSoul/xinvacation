package com.xinvacation.bss.repository;

import com.xinvacation.bss.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User getUserByNameAndPassword(String username, String password);
}
