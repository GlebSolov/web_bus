package util;

import classes.route;
import classes.stations;
import classes.users;
import classes.user_trip;
import classes.schedule;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration;

                configuration = new Configuration().configure();
                configuration.addAnnotatedClass(route.class);
                configuration.addAnnotatedClass(users.class);
                configuration.addAnnotatedClass(stations.class);
                configuration.addAnnotatedClass(schedule.class);
                configuration.addAnnotatedClass(user_trip.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("EXCEPTION: " + e);
            }
        }
        if (sessionFactory == null){
            System.err.println("WARNING sessionFactory == null");
        }
        return sessionFactory;
    }
}