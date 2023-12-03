package com.example.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
      msgError(e, response, "getUsers" );
    }

    return new ResponseEntity<UserResponseRest>( response, HttpStatus.OK );
  }


  @Override
  @Transactional( readOnly = true)
  public ResponseEntity<UserResponseRest> getUSerById(Long id) {
    UserResponseRest response = new UserResponseRest();
    List<User> list = new ArrayList<>();

    try {
      Optional<User> user = userDao.findById(id);

      if(!user.isPresent()){
        response.setMetadata("Response nok", "-1", "User not found");
        return  new ResponseEntity<UserResponseRest>( response, HttpStatus.NOT_FOUND);
      }

      list.add(user.get());
      response.getUserResponse().setUsers(list);
      response.setMetadata("Response ok", "00", "Category found");

    } catch (Exception e) {
      msgError(e, response, "getUSerById" );
    }

    return  new ResponseEntity<UserResponseRest>( response, HttpStatus.OK);
  }

  @Override
  @Transactional( readOnly = true)
  public ResponseEntity<UserResponseRest> getUserByName(String name) {
    UserResponseRest response = new UserResponseRest();
    List<User> list = new ArrayList<>();

    try {
      // search by product by Name
      list = userDao.findByNameContainingIgnoreCase(name);

      if( list.size() == 0) {
        response.setMetadata("Response nok", "-1", "User not found");
        return new ResponseEntity<UserResponseRest>( response, HttpStatus.NOT_FOUND);
      }

      response.getUserResponse().setUsers(list);
      response.setMetadata("Response ok", "00", "User found successfully");

    } catch (Exception e) {
        msgError(e, response, "getUserByName" );
    }

    return new ResponseEntity<UserResponseRest>( response, HttpStatus.OK);   
  }

  @Override
  @Transactional( readOnly = false)
  public ResponseEntity<UserResponseRest> createUser(User user) {
    UserResponseRest response = new UserResponseRest();
    List<User> list = new ArrayList<>();

    try {

       User existUserEmail = userDao.findByEmailAddress( user.getEmail() );

      if( existUserEmail != null ) {
        response.setMetadata("Response nok", "-1", "Exist user with email address");
        return new ResponseEntity<UserResponseRest>( response, HttpStatus.BAD_REQUEST);
      }

      User newUser = userDao.save(user);

      if( newUser == null ) {
        response.setMetadata("Response nok", "-1", "User not found");
        return new ResponseEntity<UserResponseRest>( response, HttpStatus.BAD_REQUEST);
      }

      list.add(newUser);
      response.getUserResponse().setUsers(list);
      response.setMetadata("Response ok", "00", "User saved successfully");

    } catch (Exception e) {
      msgError(e, response, "createUser" );
    }

    return new ResponseEntity<UserResponseRest>( response, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<UserResponseRest> updateUser(User user, Long id) {
    UserResponseRest response = new UserResponseRest();
    List<User> list = new ArrayList<>();

    try {

      Optional<User> userById = userDao.findById(id);

      if( !userById.isPresent() ) {
        response.setMetadata("Response nok", "-1", "User not found");
        return new ResponseEntity<UserResponseRest>( response, HttpStatus.NOT_FOUND);
      }

      userById.get().setName( user.getName());
      userById.get().setEmail( user.getEmail());
      userById.get().setPassword( user.getPassword());
      User userToUpdate =  userDao.save( userById.get() );

      if( userToUpdate == null ){
        response.setMetadata("Response nok", "-1", "User not update");
        return new ResponseEntity<UserResponseRest>( response, HttpStatus.BAD_REQUEST);
      }

      list.add( userToUpdate);
      response.getUserResponse().setUsers(list);
      response.setMetadata("Response ok", "00", "User update successfully");

    } catch (Exception e) {
      msgError(e, response, "updateUser" );
    }

    return new ResponseEntity<UserResponseRest>( response, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<UserResponseRest> deleteUSerById(Long id) {
    UserResponseRest response = new UserResponseRest();

    try {
      
      Optional<User> searchUser= userDao.findById(id);

      if( !searchUser.isPresent() ) {
        response.setMetadata("Response nok", "-1", "User with id "+id+ " not found");
        return new ResponseEntity<UserResponseRest>( response, HttpStatus.NOT_FOUND);
      } 

      userDao.deleteById(id);
      response.setMetadata("Response ok ", "00", "User deleted successfully");

    } catch (Exception e) {
      msgError(e, response, "deleteUSerById" );
    }
    return new ResponseEntity<UserResponseRest>( response, HttpStatus.OK);
  }

  private ResponseEntity<UserResponseRest> msgError(Exception e, UserResponseRest response, String metod){
      response.setMetadata("Response nok", "-1", "Error in response: check logs");
      log.error("Error "+ metod + ": ", e);
      return new ResponseEntity<UserResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
