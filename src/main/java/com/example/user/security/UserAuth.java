package com.example.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.user.dao.IUserDao;
import com.example.user.model.User;

@Component
public class UserAuth implements AuthenticationProvider{

  @Autowired
  private IUserDao userDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String userName = authentication.getName();
    String password = authentication.getCredentials().toString();
    User user = this.userDao.findByEmailAddress(userName);

    if( user == null ) {
      throw new BadCredentialsException("No User registered with this data"); 
    }
      
    if( passwordEncoder.matches(password, user.getPassword() )){
      return new UsernamePasswordAuthenticationToken( user.getUsername(), user.getPassword(), user.getAuthorities() );
    }else{
      throw new BadCredentialsException("password is incorrect");
    }

  }

  @Override
  public boolean supports(Class<?> authentication) {
    return ( UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }

}
