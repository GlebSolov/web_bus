package DAO;

import classes.schedule;
import classes.users;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class usersDAO {


    public void addUser(users user) throws SQLException{
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateUser(users user) throws SQLException{
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void deleteUser(users user) throws SQLException{
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public users getUserById(int user_id) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<users> user = new ArrayList<users>();

        session.beginTransaction();
        Query<users> query = session.createQuery("from users WHERE user_id = :user_id", users.class);
        query.setParameter("user_id", user_id);
        user = (List<users>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        for(users us : user){
            return us;
        }
        return null;

    }

    public users checkbyIDandPssw (int user_id, String passw) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<users> user = new ArrayList<users>();

        session.beginTransaction();
        Query<users> query = session.createQuery("from users WHERE user_id = :user_id and passw := passw", users.class);
        query.setParameter("user_id", user_id);
        query.setParameter("passw", passw);
        user = (List<users>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        for(users us : user){
            return us;
        }
        return null;

    }



    public List<users> loadAll() throws SQLException{
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<users> criteria = builder.createQuery(users.class);
        criteria.from(users.class);
        List<users> response = session.createQuery(criteria).getResultList();
        session.close();
        return response;
    }


}
