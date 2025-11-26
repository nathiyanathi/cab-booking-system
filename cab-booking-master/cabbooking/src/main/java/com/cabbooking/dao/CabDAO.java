package com.cabbooking.dao;

import com.cabbooking.models.Cab;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CabDAO {
    public int save(Cab c) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        int id = (int) s.save(c);
        t.commit(); s.close();
        return id;
    }

    public Cab get(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Cab c = s.get(Cab.class, id);
        s.close();
        return c;
    }

    public List<Cab> listAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Cab> list = s.createQuery("from Cab", Cab.class).list();
        s.close();
        return list;
    }

    public void update(Cab c) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.update(c);
        t.commit(); s.close();
    }

    public void delete(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Cab c = s.get(Cab.class, id);
        if (c != null) s.delete(c);
        t.commit(); s.close();
    }
}
