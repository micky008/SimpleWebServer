/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.dao;

/**
 *
 * @author local
 */
public class FactoryDAO {

    private static UserDAO udao = new UserDAOMariaDbImpl();

    public static UserDAO getUserDAO() {
        return udao;
    }

    public static void init() {
        HibernateConfiguration.setUp();
    }
}
