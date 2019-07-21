/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.dao;

import com.mycompany.mavenproject1.entity.User;
import java.util.List;

/**
 *
 * @author local
 */
public interface UserDAO {
    
    List<User> getUsers();
    User getUserByLoginPassword(String login, String password);
    
}
