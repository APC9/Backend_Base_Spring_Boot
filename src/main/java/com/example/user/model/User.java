package com.example.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "users") // Nombre de la table, por defecto toma el nombre de la clase
public class User {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  public Long id;

  @Column( unique = true)
  @Email
  public String email;

  @Size( min = 2, max = 4)
  public String name;
  
  @Size( min = 2, max = 4)
  public String password;


}
