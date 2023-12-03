package com.example.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @PostMapping("/users")
  private ResponseEntity<UserResponseRest> createUser( @Valid @RequestBody User user){
    ResponseEntity<UserResponseRest> response = userService.createUser(user);
    return response;
  }

}
