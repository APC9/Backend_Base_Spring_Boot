package com.example.user.services;

import org.springframework.http.ResponseEntity;

import com.example.user.model.User;
import com.example.user.response.UserResponseRest;

public interface IUserService {

  public ResponseEntity<UserResponseRest> getUsers();
  public ResponseEntity<UserResponseRest> getUSerById( Long id );
  public ResponseEntity<UserResponseRest> getUserByName(String name);
  public ResponseEntity<UserResponseRest> createUser( User user );
  public ResponseEntity<UserResponseRest> deleteUSerById( Long id );
  public ResponseEntity<UserResponseRest> updateUser(  User user, Long id );

}
