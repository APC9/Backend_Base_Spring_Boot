package com.example.user.data;

import java.util.ArrayList;
import java.util.List;

import com.example.user.model.User;

import lombok.Data;

@Data
public class DataUser {

  List<User> users = new ArrayList<User>();

   public DataUser() {
        users = new ArrayList<>();

        users.add(new User("usuario1@example.com", "Usuario1", "P@ssword1"));
        users.add(new User("usuario2@example.com", "Usuario2", "Secure123#"));
        users.add(new User("usuario3@example.com", "Usuario3", "StrongPwd!789"));
        users.add(new User("usuario4@example.com", "Usuario4", "Pass123$word"));
        users.add(new User("usuario5@example.com", "Usuario5", "Secret!5678"));
        users.add(new User("usuario6@example.com", "Usuario6", "P@55phrase"));
        users.add(new User("usuario7@example.com", "Usuario7", "SecurePwd#987"));
        users.add(new User("usuario8@example.com", "Usuario8", "ComplexPwd!123"));
        users.add(new User("usuario9@example.com", "Usuario9", "Passw0rd@456"));
        users.add(new User("usuario10@example.com","Usuario10",  "Ex@mple789Pwd"));
    }

    public List<User> getUsers() {
        return users;
    }

}
