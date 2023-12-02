package com.example.user.services;

import org.springframework.http.ResponseEntity;

import com.example.user.model.User;
import com.example.user.response.UserResponseRest;

public interface IUserService {

  public ResponseEntity<UserResponseRest> getUsers();
  public ResponseEntity<UserResponseRest> createCategory( User user );

}
