package com.example.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.user.dao.IUserDao;
import com.example.user.model.User;

@Service
public class UserDetailsImpl implements UserDetailsService {

  @Autowired
  private IUserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userDao.findByEmailAddress(username);

    if (user == null) {
      throw new UsernameNotFoundException("User " + username + "not found");
    }

    return new User(user.getEmail(), user.getName(), user.getPassword(), user.getRole());
  }

}
