package com.cabbooking.dao;

import com.cabbooking.models.Booking;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class BookingDAO {
    public int save(Booking b) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        int id = (int) s.save(b);
        t.commit(); s.close();
        return id;
    }

    public Booking get(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Booking b = s.get(Booking.class, id);
        s.close();
        return b;
    }

    public List<Booking> listAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Booking> list = s.createQuery("from Booking", Booking.class).list();
        s.close();
        return list;
    }

    public void delete(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Booking b = s.get(Booking.class, id);
        if (b != null) s.delete(b);
        t.commit(); s.close();
    }
}
