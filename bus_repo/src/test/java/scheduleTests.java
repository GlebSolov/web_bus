import classes.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import DAO.scheduleDAO;

import org.testng.annotations.Test;
import org.testng.Assert;

public class scheduleTests {
    @Test
    public void getTripsByDateFoundTest()  throws SQLException{
        scheduleDAO schedule_dao = new scheduleDAO();
        Timestamp date_time = new Timestamp(121, 3, 21, 21, 27, 0, 0);
        List<schedule> date_trips = schedule_dao.getTripsByDate(date_time);
        for (schedule trip : date_trips) {
            Assert.assertEquals(trip.getTrip_id(), 1);
        }
    }

    @Test
    public void getTripsByDateNotFoundTest()  throws SQLException{
        scheduleDAO schedule_dao = new scheduleDAO();
        Timestamp date_time = new Timestamp(120, 3, 21, 21, 27, 0, 0);
        List<schedule> date_trips = schedule_dao.getTripsByDate(date_time);
        Assert.assertEquals(date_trips.size(), 0);
    }

    @Test
    public void getTripsByStationsFoundTest() throws SQLException{
        scheduleDAO schedule_dao = new scheduleDAO();

        stations to_station = new stations();
        stations from_station = new stations();
        route Route = new route();
        Route.setRoute_id(1);
        to_station.setRoute_id(Route);
        from_station.setRoute_id(Route);
        List<schedule> trips = schedule_dao.getTripsByStations(to_station, from_station);
        Assert.assertNotEquals(trips.size(), 0);

    }

    @Test
    public void getTripsByStationsNotFoundTest() throws SQLException{
        scheduleDAO schedule_dao = new scheduleDAO();

        stations to_station = new stations();
        stations from_station = new stations();

        route f_route = new route();
        f_route.setRoute_id(1);
        route s_route = new route();
        s_route.setRoute_id(2);
        to_station.setRoute_id(f_route);
        from_station.setRoute_id(s_route);
        List<schedule> trips = schedule_dao.getTripsByStations(to_station, from_station);
        Assert.assertEquals(trips.size(), 0);

    }

    @Test
    public void getAllTripsTest () throws SQLException{
        scheduleDAO schedule_dao = new scheduleDAO();
        List<schedule> trips = schedule_dao.getAllTrips();
        Assert.assertEquals(trips.size(), 7);
    }

    @Test
    public void addUpdateDeleteTripTest () throws SQLException{
        scheduleDAO schedule_dao = new scheduleDAO();
        schedule trip = new schedule();
        trip.setDate_time(new Timestamp(122, 3, 21, 21, 27, 0, 0));

        route Route = new route();
        Route.setRoute_id(1);
        trip.setRoute_id(Route);
        trip.setSeats(10);
        schedule_dao.addTrip(trip);
        int id = trip.getTrip_id();
        System.out.println(schedule_dao.getTripById(id));
        System.out.println(schedule_dao.getTripById(id).getSeats());
        Assert.assertEquals(schedule_dao.getTripById(id).getSeats(), 10);
        trip.setSeats(11);
        schedule_dao.updateTrip(trip);
        int s = schedule_dao.getTripById(id).getSeats();
        Assert.assertEquals(s, 11);
        schedule_dao.deleteTrip(trip);
        Assert.assertEquals(schedule_dao.getAllTrips().size(), 7);
    }
}
