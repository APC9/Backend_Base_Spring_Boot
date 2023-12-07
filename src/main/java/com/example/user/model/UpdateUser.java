package com.example.user.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUser {

  public Long id;

  @Email( message = "Debe ser un email valido")
  @Column( unique = true )
  public String email;

  @NotBlank( message = "This field is required")
  public String name;
}
