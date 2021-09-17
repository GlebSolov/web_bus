package DAO;

import classes.schedule;
import classes.stations;
import classes.users;
import classes.user_trip;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class scheduleDAO {

    public void addTrip(schedule trip) throws SQLException{
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(trip);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateTrip(schedule trip) throws SQLException{
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(trip);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void deleteTrip(schedule trip) throws SQLException{
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(trip);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }


    public List<schedule> getAllTrips () throws SQLException{
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<schedule> criteria = builder.createQuery(schedule.class);
        criteria.from(schedule.class);
        List<schedule> response = session.createQuery(criteria).getResultList();
        session.close();
        return response;
    }

    public List<schedule> getTripsByStations (stations from_station, stations to_station) throws SQLException{
        System.out.println(from_station.getRoute_id().getRoute_id() + "  " + to_station.getRoute_id().getRoute_id());
        if (from_station.getRoute_id().getRoute_id() != to_station.getRoute_id().getRoute_id()){
            System.out.println("\nim here!!!\n");
            return new ArrayList<schedule>();
        }
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<schedule> query = session.createQuery("from schedule WHERE route_id = :route_id", schedule.class);
        query.setParameter("route_id", to_station.getRoute_id());
        List<schedule> trips = query.getResultList();

        List<schedule> result = new ArrayList<schedule>();
        user_tripDAO users_in_tripsdao = new user_tripDAO();
        for(schedule trip: trips){
            List<user_trip> taken = users_in_tripsdao.getOrdersByTripId(trip);
            if (taken.size() < trip.getSeats()){
                schedule t = new schedule();
                t.setTrip_id(trip.getTrip_id());
                t.setDate_time(trip.getDate_time());
                t.setRoute_id(trip.getRoute_id());
                t.setSeats(trip.getSeats() - taken.size());
                result.add(t);
            }
        }
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return result;
    }

    public schedule getTripById(int trip_id) throws SQLException {
        Session session = null;
        List<schedule> trips = new ArrayList<schedule>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<schedule> query = session.createQuery("from schedule WHERE trip_id = :trip_id", schedule.class);
        query.setParameter("trip_id", trip_id);
        trips = (List<schedule>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        for(schedule trip : trips){
            return trip;
        }
        return null;
    }

    public List<schedule> getTripsByDate (Timestamp date_time)  throws SQLException{
        Session session = null;
        List<schedule> trips = new ArrayList<schedule>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<schedule> query = session.createQuery("from schedule WHERE date_time = :date_time", schedule.class);
        query.setParameter("date_time", date_time);
        trips = (List<schedule>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return trips;
    }


}
