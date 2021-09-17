import DAO.scheduleDAO;
import DAO.usersDAO;
import DAO.user_tripDAO;
import classes.schedule;
import classes.users;
import classes.user_trip;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public class main {
    public static void main(String[] args) throws SQLException {
        System.out.println("its alive!");
        scheduleDAO schedule_dao = new scheduleDAO();
        List<schedule> all_trips = schedule_dao.getAllTrips();
        for (schedule trip : all_trips) {
            System.out.println("\n" + trip.getRoute_id().getRoute_name());
            System.out.println(trip.getDate_time());
            System.out.println(trip.getSeats() + "\n");
        }

        usersDAO user_dao = new usersDAO();
        user_tripDAO trip_dao = new user_tripDAO();

        Collection<users> user_list = user_dao.loadAll();
        for (users usr : user_list) {
            System.out.println("\n" + usr.getUser_name() + "\n");
            Collection<user_trip> trips = trip_dao.getOrdersByUserId(usr);
        }

        List<schedule> date_trips = schedule_dao.getTripsByDate(new Timestamp(121, 3, 21, 21, 27, 0, 0));
        for (schedule trip : date_trips) {
            System.out.println("\n" + trip.getRoute_id().getRoute_name());
            System.out.println(trip.getDate_time());
            System.out.println(trip.getSeats() + "\n");
        }
    }
}
