package com.mycompany.mavenproject1.dao;

import com.mycompany.mavenproject1.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author local
 */
public class UserDAOMOOCImpl implements UserDAO {

    @Override
    public List<User> getUsers() {
        User u1 = new User();
        u1.setEmail("toto@toto.gr");
        u1.setLogin("toto");
        u1.setPassword("toto");
        User u2 = new User();
        u2.setEmail("riri@toto.gr");
        u2.setLogin("riri");
        u2.setPassword("riri");
        User u3 = new User();
        u3.setEmail("loulou@loulou.gr");
        u3.setLogin("loulou");
        u3.setPassword("loulou");

        List<User> us = new ArrayList<>(3);
        us.add(u3);
        us.add(u2);
        us.add(u1);
        
        return us;
        
    }

    @Override
    public User getUserByLoginPassword(String login, String password) {
        List<User> us = this.getUsers();
        for (User u : us){
            if (u.getLogin().equals(login) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;    
    }
    
    public void toto(){}

    @Override
    public Integer insertUser(User newUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
