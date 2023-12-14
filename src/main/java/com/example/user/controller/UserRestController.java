package com.example.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
  @Operation(
      summary = "Load list of user.",
      description = "Load a list with test users into the database",
      tags = { "Load Users"})
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseRest.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @GetMapping("/users")
  public ResponseEntity<UserResponseRest> getUsers(){
    ResponseEntity<UserResponseRest> response = userService.getUsers();
    return response;
  }

  @Operation(
      summary = "Get list users.",
      description = "Get list users.",
      tags = { "Get Users"})
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseRest.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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
  @Operation(
      summary = "Get User by Id.",
      description = "Get User by Id.",
      tags = { "Get User By Id" })
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseRest.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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
  @Operation(
      summary = "Get User by Name.",
      description = "Get User by Name.",
      tags = { "Get User By Name" })
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseRest.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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
  @Operation(
      summary = "Create a new User.",
      description = "Create a new User.",
      tags = { "Create a new User" })
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseRest.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @PostMapping("/users")
  private ResponseEntity<UserResponseRest> createUser( @Valid @RequestBody User user){
    User newUser = user;
    ResponseEntity<UserResponseRest> response = userService.createUser(newUser);
    return response;
  }
  
  /**
   * Edit a user by id
   * @param user
   * @param id
   * @return
    */
  @Operation(
      summary = "Edit user by id.",
      description = "Edit user by id.",
      tags = { "Edit user by id" })
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseRest.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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
  @Operation(
      summary = "Delete user by id.",
      description = "Delete user by id.",
      tags = { "Delete user by id" })
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseRest.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @DeleteMapping("/users/{id}")
  public ResponseEntity<UserResponseRest> deleteUserById( @PathVariable Long id){
    ResponseEntity<UserResponseRest> response = userService.deleteUSerById(id);
    return response;
  }
}
