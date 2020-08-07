package com.mycompany.mavenproject1.dao;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


class HibernateConfiguration {

    private static SessionFactory sessionFactory;

    public static void setUp() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(new File("hibernate.cfg.xml")).build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public synchronized static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            setUp();
        }
        if (sessionFactory.isClosed()) {
            sessionFactory = null;
            setUp();
        }
        return sessionFactory;
    }

    public synchronized static Session getInstance() {
        return getSessionFactory().getCurrentSession();
    }

}
