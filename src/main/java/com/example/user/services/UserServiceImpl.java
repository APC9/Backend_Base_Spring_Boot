package com.example.user.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.user.dao.IUserDao;
import com.example.user.model.User;
import com.example.user.response.UserResponseRest;

@Service
public class UserServiceImpl implements IUserService{

  @Autowired
  private IUserDao userDao;

  // Errores en Consola
  private final Logger log = LoggerFactory.getLogger( UserServiceImpl.class );

  @Override
  @Transactional( readOnly = true)
  public ResponseEntity<UserResponseRest> getUsers() {
    UserResponseRest response = new UserResponseRest();

    try {
      List<User> users = (List<User>) userDao.findAll();
      response.getUserResponse().setUsers(users);
      response.setMetadata("Response ok", "00", "successful response");
    } catch (Exception e) {
      log.error("Error getUsers: ", e);
      response.setMetadata("Response nok", "-1", "Error in response: check logs");
      return new ResponseEntity<UserResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR );
    }

    return new ResponseEntity<UserResponseRest>( response, HttpStatus.OK );
  }

  @Override
  @Transactional
  public ResponseEntity<UserResponseRest> createCategory(User user) {
    UserResponseRest response = new UserResponseRest();
    List<User> list = new ArrayList<>();

    try {

      User newUser = userDao.save(user);

      if( newUser == null ) {
        response.setMetadata("Response nok", "-1", "User not found");
        return new ResponseEntity<UserResponseRest>( response, HttpStatus.BAD_REQUEST);
      }

      list.add(newUser);
      response.getUserResponse().setUsers(list);
      response.setMetadata("Respuesta ok", "00", "User saved successfully");

    } catch (Exception e) {
      response.setMetadata("Respuesta nok", "-1", "Error al crear categoria");
      log.error("Error createCategory: ", e);
      return new ResponseEntity<UserResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<UserResponseRest>( response, HttpStatus.OK);
  }

}
