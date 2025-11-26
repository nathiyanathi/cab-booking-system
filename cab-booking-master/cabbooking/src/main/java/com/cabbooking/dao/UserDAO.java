package com.cabbooking.dao;

import com.cabbooking.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDAO {
    public int save(User u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        int id = (int) s.save(u);
        t.commit(); s.close();
        return id;
    }

    public User get(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        User u = s.get(User.class, id);
        s.close();
        return u;
    }

    public List<User> listAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<User> list = s.createQuery("from User", User.class).list();
        s.close();
        return list;
    }

    public void delete(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        User u = s.get(User.class, id);
        if (u != null) s.delete(u);
        t.commit(); s.close();
    }
}
