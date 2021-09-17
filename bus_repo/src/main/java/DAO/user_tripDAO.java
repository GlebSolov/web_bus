package DAO;

import classes.schedule;
import classes.user_trip;
import classes.users;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class user_tripDAO {
    public List<user_trip> getOrdersByUserId (users user) throws SQLException{
        Session session = null;
        List<user_trip> trips = new ArrayList<user_trip>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<user_trip> query = session.createQuery("from users_in_trips WHERE user_id = :user_id", user_trip.class);
        query.setParameter("user_id", user);
        trips = (List<user_trip>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return trips;
    }

    public void addTicketsOrder (user_trip order) throws SQLException{
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public List<user_trip> loadAll() throws SQLException{
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<user_trip> criteria = builder.createQuery(user_trip.class);
        criteria.from(user_trip.class);
        List<user_trip> response = session.createQuery(criteria).getResultList();
        session.close();
        return response;
    }

    public List<user_trip> getOrdersByTripId (schedule trip) throws SQLException{
        Session session = null;
        List<user_trip> trips = new ArrayList<user_trip>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<user_trip> query = session.createQuery("from users_in_trips WHERE trip_id = :trip_id", user_trip.class);
        query.setParameter("trip_id", trip);
        trips = (List<user_trip>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return trips;
    }

    public int getOrdersCountByTripId (schedule trip) throws SQLException {
        Session session = null;
        int trips_cnt = 0;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<user_trip> query = session.createQuery("from users_in_trips WHERE trip_id = :trip_id", user_trip.class);
        query.setParameter("trip_id", trip);
        trips_cnt = query.list().size();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return trips_cnt;
    }
}
