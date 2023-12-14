package com.example.user.model;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User implements UserDetails{

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  public Long id;

  @Email( message = "Debe ser un email valido")
  @Column( unique = true )
  public String email;

  @NotBlank( message = "This field is required")
  public String name;
  
  @Length(min = 4)
  @Pattern(
    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).*$",
    message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial"
  ) 
  public String password;


  @NotBlank(message = "This field is required")
  @Pattern(regexp = "^(user|admin)$", message = "El rol debe ser 'user' o 'admin'")
  @Column(columnDefinition = "VARCHAR(255) DEFAULT 'user'")
  public String role;


  public User( @Email(message = "Debe ser un email valido") String email,
      @NotBlank(message = "This field is required") String name,
      @Length(min = 4) @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).*$", message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial") String password,
      @NotBlank(message = "This field is required") @Pattern(regexp = "^(user|admin)$", message = "El rol debe ser 'user' o 'admin'") String role) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.role = role;
  }

  public User() {
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    java.util.List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add( new SimpleGrantedAuthority(getRole()));
    return authorities;
  }

  @Override
  public String getUsername() {
    return getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  } 

  

}
