package DAO;

import classes.route;
import classes.schedule;
import classes.stations;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class stationsDAO {

    public stations getTripById(int station_id) throws SQLException {
        Session session = null;
        List<stations> stations = new ArrayList<stations>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<stations> query = session.createQuery("from stations WHERE station_id = :station_id", stations.class);
        query.setParameter("station_id", station_id);
        stations = (List<stations>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        for(stations station : stations){
            return station;
        }
        return null;
    }
}