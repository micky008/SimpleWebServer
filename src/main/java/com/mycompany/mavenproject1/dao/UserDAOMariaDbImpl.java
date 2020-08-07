package com.mycompany.mavenproject1.dao;

import com.mycompany.mavenproject1.config.SecurePassword;
import com.mycompany.mavenproject1.entity.User;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author local
 */
public class UserDAOMariaDbImpl implements UserDAO {

    @Override
    public List<User> getUsers() {
        Session session = HibernateConfiguration.getInstance();
        Transaction t = session.beginTransaction();
        try {
            TypedQuery<User> q = session.createQuery("from User");
            List<User> lu = q.getResultList();
            session.clear();
            for (User p : lu) {
                p.setPassword(null);
            }
            return lu;
        } catch (NoResultException nre) {
            //silent error
        } catch (Throwable th) {
            th.printStackTrace();
        } finally {
            t.commit();
            session.close();
        }
        return null;
    }

    @Override
    public User getUserByLoginPassword(String login, String pwd) {
        Session session = HibernateConfiguration.getInstance();
        Transaction t = session.beginTransaction();
        try {
            Query q = session.createQuery("from User where login=?1");
            q.setParameter(1, login);
            User u = (User) q.getSingleResult();

            if (u == null) {
                return null;
            }
            if (!SecurePassword.validatePassword(pwd.toCharArray(), u.getPassword())) {
                return null;
            }
            session.clear();
            u.setPassword(null);

            return u;
        } catch (Throwable th) {
            th.printStackTrace();
        } finally {
            t.commit();
            session.close();
        }
        return null;
    }

    @Override
    public Integer insertUser(User newUser) {
        try {
            Session session = HibernateConfiguration.getInstance();
            Transaction t = session.beginTransaction();
            newUser.setPassword(SecurePassword.encodePassword(newUser.getPassword().toCharArray()));
            session.save(newUser);
            t.commit();
            session.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return newUser.getId();
    }

}
