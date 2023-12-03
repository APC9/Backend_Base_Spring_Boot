package com.example.user.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "users") // Nombre de la table, por defecto toma el nombre de la clase
public class User {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  public Long id;

  @Email( message = "Debe ser un email valido")
  @Column( unique = true)
  public String email;

  @NotBlank( message = "This field is required")
  public String name;
  
  @Length(min = 4, max = 10)
  @Pattern(
    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
    message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial"
  )
  public String password;


}
