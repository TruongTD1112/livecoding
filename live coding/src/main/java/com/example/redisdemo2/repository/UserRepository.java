package com.example.redisdemo2.repository;

import com.example.redisdemo2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer integer);

    Optional<User> findByUsername(String username);
}
