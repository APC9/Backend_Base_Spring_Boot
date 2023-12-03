package com.example.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.model.User;

public interface IUserDao extends JpaRepository<User, Long>{

  // Metodo personalizado, referencia: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
  List<User> findByNameContainingIgnoreCase(String name);
}

