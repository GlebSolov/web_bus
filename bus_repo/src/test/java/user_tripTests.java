import DAO.usersDAO;
import DAO.user_tripDAO;
import classes.*;

import java.sql.SQLException;
import java.util.List;
import DAO.scheduleDAO;

import org.testng.annotations.Test;
import org.testng.Assert;

public class user_tripTests {
    @Test
    public void getOrdersCountByTripIdFound() throws SQLException{
        user_tripDAO usrt = new user_tripDAO();
        scheduleDAO sch = new scheduleDAO();
        schedule trip = sch.getTripById(1);
        int result = usrt.getOrdersCountByTripId(trip);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void getOrdersCountByTripIdNotFound() throws SQLException{
        user_tripDAO usrt = new user_tripDAO();
        scheduleDAO sch = new scheduleDAO();
        schedule trip = sch.getTripById(2);
        int result = usrt.getOrdersCountByTripId(trip);
        Assert.assertEquals(result, 0);
    }

    @Test
    public void getOrdersByTripIdFound() throws SQLException{
        user_tripDAO usrt = new user_tripDAO();
        scheduleDAO sch = new scheduleDAO();
        schedule trip = sch.getTripById(1);
        List<user_trip> result = usrt.getOrdersByTripId(trip);
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void getOrdersByTripIdNotFound() throws SQLException{
        user_tripDAO usrt = new user_tripDAO();
        scheduleDAO sch = new scheduleDAO();
        schedule trip = sch.getTripById(2);
        List<user_trip> result = usrt.getOrdersByTripId(trip);
        Assert.assertEquals(result.size(), 0);
    }


    @Test
    public void getOrdersByUserIdFound() throws SQLException{
        user_tripDAO usrt = new user_tripDAO();
        usersDAO user_dao = new usersDAO();
        users user = user_dao.getUserById(1);
        List<user_trip> result = usrt.getOrdersByUserId(user);
        Assert.assertEquals(result.size(), 1);
    }

    @Test
    public void getOrdersByUserIdNotFound() throws SQLException{
        user_tripDAO usrt = new user_tripDAO();
        usersDAO user_dao = new usersDAO();
        users user = user_dao.getUserById(3);
        List<user_trip> result = usrt.getOrdersByUserId(user);
        Assert.assertEquals(result.size(), 0);
    }

}
