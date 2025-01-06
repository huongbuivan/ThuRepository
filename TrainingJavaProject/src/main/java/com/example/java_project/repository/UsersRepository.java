package com.example.java_project.repository;

import com.example.java_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    List<User> findByUserNameContainingIgnoreCase(String userName);
}
