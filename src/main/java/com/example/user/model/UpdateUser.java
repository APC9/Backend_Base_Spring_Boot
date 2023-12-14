package com.example.user.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateUser {

  public Long id;

  @Email( message = "Debe ser un email valido")
  @Column( unique = true )
  public String email;

  @NotBlank( message = "This field is required")
  public String name;

  @NotBlank(message = "This field is required")
  @Pattern(regexp = "^(user|admin)$", message = "El rol debe ser 'user' o 'admin'")
  @Column(columnDefinition = "VARCHAR(255) DEFAULT 'user'")
  public String role;
}
