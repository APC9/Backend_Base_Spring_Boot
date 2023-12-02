package com.example.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.model.User;

public interface IUserDao extends JpaRepository<User, Long>{

}
