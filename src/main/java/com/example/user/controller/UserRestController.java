package com.example.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.UpdateUser;
import com.example.user.model.User;
import com.example.user.response.UserResponseRest;
import com.example.user.services.IUserService;

import jakarta.validation.Valid;

@CrossOrigin( origins = "*" ) // se puede colocar una Url para una ruta especifica ej: "http://localhost:4200"
@RestController
@RequestMapping("/api/v1")
public class UserRestController {

  @Autowired
  private IUserService userService;

  /**
   * Get all Users
   * @return ResponseEntity<UserResponseRest>
    */
  @GetMapping("/users")
  public ResponseEntity<UserResponseRest> getUsers(){
    ResponseEntity<UserResponseRest> response = userService.getUsers();
    return response;
  }

  @GetMapping("/users/load")
  public ResponseEntity<UserResponseRest> loadUsers(){
    ResponseEntity<UserResponseRest> response = userService.loadUsers();
    return response;
  }
  
  /**
   * Get user by ID
   * @param id
   * @return ResponseEntity<UserResponseRest>
    */
  @GetMapping("/users/{id}")
  public ResponseEntity<UserResponseRest> getUserById( @PathVariable Long id){
    ResponseEntity<UserResponseRest> response = userService.getUSerById(id);
    return response;
  }

  /**
   * Get user by name
   * @param name
   * @return ResponseEntity<UserResponseRest>
    */
  @GetMapping("/users/filter/{name}")
  public ResponseEntity<UserResponseRest> getUserByName( @PathVariable String name ){
    ResponseEntity<UserResponseRest> response = userService.getUserByName(name);
    return response;
  }

  /**
   * Create a new User
   * @param user
   * @return ResponseEntity<UserResponseRest>
    */
  @PostMapping("/users")
  private ResponseEntity<UserResponseRest> createUser( @Valid @RequestBody User user){
    User newUser = user;
    ResponseEntity<UserResponseRest> response = userService.createUser(newUser);
    return response;
  }
  
  @PutMapping("/users/{id}")
  private ResponseEntity<UserResponseRest> updateUser( @Valid @RequestBody UpdateUser user, @PathVariable Long id ){
    ResponseEntity<UserResponseRest> response = userService.updateUser(user, id);
    return response;
  }

  /**
   * Delete a User by iD
   * @param id
   * @return ResponseEntity<UserResponseRest>
    */
  @DeleteMapping("/users/{id}")
  public ResponseEntity<UserResponseRest> deleteUserById( @PathVariable Long id){
    ResponseEntity<UserResponseRest> response = userService.deleteUSerById(id);
    return response;
  }
}
