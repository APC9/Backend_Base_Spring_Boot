package com.example.user.response;

import java.util.List;

import com.example.user.model.User;

import lombok.Data;

@Data
public class UserResponse {

  private List<User> users;

}
