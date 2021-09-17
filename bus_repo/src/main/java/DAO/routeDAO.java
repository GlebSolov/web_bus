package DAO;

import classes.route;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.List;

public class routeDAO {


    public List<route> loadAll() throws SQLException{
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<route> criteria = builder.createQuery(route.class);
        criteria.from(route.class);
        List<route> response = session.createQuery(criteria).getResultList();
        session.close();
        return response;
    }


}
