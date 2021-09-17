package App.Controller;

import DAO.*;
import classes.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class loginControl {

    @RequestMapping(value = "/userhome", method = RequestMethod.POST)
    public ModelAndView userhome(@RequestParam(name = "user_id", required = true) int user_id) throws SQLException {
        usersDAO usersdao = new usersDAO();
        users user = usersdao.getUserById(user_id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userhome");
        modelAndView.addObject("user_id", user_id);
        modelAndView.addObject("is_admin", user.getIs_admin());
        return modelAndView;
    }


    @RequestMapping(value = "/adminhome", method = RequestMethod.POST)
    public ModelAndView adminhome(@RequestParam(name = "user_id", required = true) int user_id) throws SQLException {
        usersDAO usersdao = new usersDAO();
        users user = usersdao.getUserById(user_id);
        List<users> allusers = usersdao.loadAll();
        scheduleDAO tripsdao = new scheduleDAO();
        List<schedule> trips = tripsdao.getAllTrips();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminhome");
        modelAndView.addObject("usersList", allusers);
        modelAndView.addObject("tripsList", trips);
        modelAndView.addObject("user_id", user_id);
        modelAndView.addObject("is_admin", user.getIs_admin());
        return modelAndView;
    }





    @RequestMapping(value = "/usersorders/{id}", method = RequestMethod.POST)
    public ModelAndView usersorders(@PathVariable("id") int info_id,
                                    @RequestParam(name = "user_id", required = true) int user_id) throws SQLException {
        users user = new users();
        user.setUser_id(info_id);
        user_tripDAO usersintripsdao = new user_tripDAO();
        List<user_trip> trips = usersintripsdao.getOrdersByUserId(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usersorders");
        modelAndView.addObject("usersOrders", trips);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/tripsorders/{id}", method = RequestMethod.POST)
    public ModelAndView tripsorders(@PathVariable("id") int info_id,
                                    @RequestParam(name = "user_id", required = true) int user_id) throws SQLException {
        user_tripDAO users_in_tripsdao = new user_tripDAO();
        schedule trip = new schedule();
        trip.setTrip_id(info_id);

        List<user_trip> trips = users_in_tripsdao.getOrdersByTripId(trip);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tripsorders");
        modelAndView.addObject("trip", trip);
        modelAndView.addObject("tripsOrders", trips);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/addtrip", method = RequestMethod.POST)
    public ModelAndView addtripppp(@RequestParam(name = "user_id", required = true) int user_id) throws SQLException {
        routeDAO routesdao = new routeDAO();
        List<route> allroutes = routesdao.loadAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addtrip");
        modelAndView.addObject("allRoutes", allroutes);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/addnewtrip", method = RequestMethod.POST)
    public ModelAndView addtrippp(@RequestParam(name = "routeselect", required = true) int route_id,
                                  @RequestParam(name = "datetime", required = true) String datetime,
                                  @RequestParam(name = "seats",required = true) int seats,
                                  @RequestParam(name = "user_id", required = true) int user_id) throws SQLException {
        schedule trip = new schedule();
        route route = new route();
        route.setRoute_id(route_id);
        trip.setRoute_id(route);
        trip.setSeats(seats);

        datetime += ":00";
        System.out.println(datetime.replace('T', ' '));
        trip.setDate_time(Timestamp.valueOf(datetime.replace('T', ' ')));
        scheduleDAO scheduledao = new scheduleDAO();
        scheduledao.addTrip(trip);

        routeDAO routesdao = new routeDAO();
        List<route> allroutes = routesdao.loadAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addtrip");
        modelAndView.addObject("allRoutes", allroutes);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/deletetrip", method = RequestMethod.POST)
    public ModelAndView deletetripp(@RequestParam(name = "trip_id", required = true) int trip_id,
                                    @RequestParam(name = "user_id", required = true) int user_id) throws SQLException {
        schedule trip = new schedule();
        trip.setTrip_id(trip_id);

        scheduleDAO scheduledao = new scheduleDAO();
        scheduledao.deleteTrip(trip);

        usersDAO usersdao = new usersDAO();
        List<users> allusers = usersdao.loadAll();
        List<schedule> trips = scheduledao.getAllTrips();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminhome");
        modelAndView.addObject("usersList", allusers);
        modelAndView.addObject("tripsList", trips);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/updatetrip/{id}", method = RequestMethod.POST)
    public ModelAndView updatetrip(@PathVariable("id") int id,
                                   @RequestParam(name = "user_id", required = true) int user_id) throws SQLException {
        scheduleDAO scheduledao = new scheduleDAO();
        schedule trip = scheduledao.getTripById(id);

        routeDAO routesdao = new routeDAO();
        List<route> allroutes = routesdao.loadAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updatetrip");
        modelAndView.addObject("oldtrip", trip);
        modelAndView.addObject("allRoutes", allroutes);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/updatetrip", method = RequestMethod.POST)
    public ModelAndView addtripp(@RequestParam(name = "trip_id", required = true) int trip_id,
                                 @RequestParam(name = "routeselect", required = true) int route_id,
                                 @RequestParam(name = "datetime", required = true) String datetime,
                                 @RequestParam(name = "seats",required = true) int seats,
                                 @RequestParam(name = "user_id",required = true) int user_id) throws SQLException {
        schedule trip = new schedule();

        route Route = new route();
        Route.setRoute_id(route_id);
        trip.setRoute_id(Route);

        trip.setSeats(seats);

        datetime += ":00";
        trip.setDate_time(Timestamp.valueOf(datetime.replace('T', ' ')));

        trip.setTrip_id(trip_id);

        scheduleDAO scheduledao = new scheduleDAO();
        scheduledao.updateTrip(trip);

        usersDAO usersdao = new usersDAO();
        List<users> allusers = usersdao.loadAll();
        scheduleDAO tripsdao = new scheduleDAO();
        List<schedule> trips = tripsdao.getAllTrips();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminhome");
        modelAndView.addObject("usersList", allusers);
        modelAndView.addObject("tripsList", trips);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ModelAndView adduser(@RequestParam(name = "user_id", required = true) int user_id) throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adduser");
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/addnewuser", method = RequestMethod.POST)
    public ModelAndView adduserr(@RequestParam(name = "name", required = true) String name,
                                 @RequestParam(name = "passw", required = true) String passw,
                                 @RequestParam(name = "phone", required = true) String phone,
                                 @RequestParam(name = "admin", required = false) boolean admin,
                                 @RequestParam(name = "user_id",required = true) int user_id) throws SQLException {
        users user = new users();
        System.out.println(name);
        user.setUser_name(name);
        System.out.println(admin);
        System.out.println(passw);
        user.setUser_passw(passw);
        if(admin){
            user.setIs_admin(admin);
        }else {
            user.setIs_admin(false);
        }
        //user.setIs_admin(admin);
        user.setUser_contact_info("{\"phone\": \"" + phone + "\"}");

        usersDAO usersdao = new usersDAO();
        usersdao.addUser(user);

        routeDAO routesdao = new routeDAO();
        List<users> allusers = usersdao.loadAll();
        scheduleDAO tripsdao = new scheduleDAO();
        List<schedule> trips = tripsdao.getAllTrips();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminhome");
        modelAndView.addObject("usersList", allusers);
        modelAndView.addObject("tripsList", trips);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    public ModelAndView deleteuser(@RequestParam(name = "user_id", required = true) int user_id,
                                   @RequestParam(name = "delete_user_id", required = true) int delete_user_id) throws SQLException {
        users user = new users();
        user.setUser_id(delete_user_id);

        usersDAO usersdao = new usersDAO();
        usersdao.deleteUser(user);

        scheduleDAO scheduledao = new scheduleDAO();
        List<users> allusers = usersdao.loadAll();
        List<schedule> trips = scheduledao.getAllTrips();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminhome");
        modelAndView.addObject("usersList", allusers);
        modelAndView.addObject("tripsList", trips);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/updateuser/{id}", method = RequestMethod.POST)
    public ModelAndView updateuser(@PathVariable("id") int id,
                                   @RequestParam(name = "user_id", required = true) int user_id) throws SQLException {
        usersDAO usersdao = new usersDAO();
        users user = usersdao.getUserById(id);
        System.out.println("\nname:" + user.getUser_name() + "\ncontact info: " + user.getUser_contact_info() + "\nadmin: " + user.getIs_admin() + "\n");


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updateuser");
        modelAndView.addObject("olduser", user);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }


    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public ModelAndView updateuserr(@RequestParam(name = "change_user_id", required = true) int change_user_id,
                                    @RequestParam(name = "name", required = true) String name,
                                    @RequestParam(name = "phone", required = true) String phone,
                                    @RequestParam(name = "admin",required = true) boolean admin,
                                    @RequestParam(name = "user_id", required = true) int user_id,
                                    @RequestParam(name = "passw", required = true) String passw
                                    ) throws SQLException {
        users user = new users();

        user.setUser_id(change_user_id);
        user.setUser_name(name);
        user.setUser_name(passw);
        user.setUser_contact_info("{\"phone\": \"" + phone + "\"}");
        user.setIs_admin(admin);
        usersDAO usersdao = new usersDAO();
        usersdao.updateUser(user);

        List<users> allusers = usersdao.loadAll();
        scheduleDAO tripsdao = new scheduleDAO();
        List<schedule> trips = tripsdao.getAllTrips();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminhome");
        modelAndView.addObject("usersList", allusers);
        modelAndView.addObject("tripsList", trips);
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/selecttrip", method = RequestMethod.POST)
    public ModelAndView selecttrip(@RequestParam(name = "from", required = true) int from_id,
                                   @RequestParam(name = "to", required = true) int to_id,
                                   @RequestParam(name = "user_id", required = true) int user_id) throws SQLException{
        stationsDAO stationsdao = new stationsDAO();
        stations from = stationsdao.getTripById(from_id);
        stations to = stationsdao.getTripById(to_id);
        scheduleDAO scheduledao = new scheduleDAO();
        List<schedule> trips = scheduledao.getTripsByStations(from, to);
        int price;
        if(trips.get(0).getRoute_id().getRoute_id() == 1){
            price = 123;
        } else if (trips.get(0).getRoute_id().getRoute_id() == 2){
            price = 6;
        } else {
            price = 100000000;
        }

        usersDAO usersdao = new usersDAO();
        users user = usersdao.getUserById(user_id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("selecttrip");
        modelAndView.addObject("trips", trips);
        modelAndView.addObject("from", from);
        modelAndView.addObject("to", to);
        modelAndView.addObject("user_id", user_id);
        modelAndView.addObject("is_admin", user.getIs_admin());
        modelAndView.addObject("price", price);

        return modelAndView;

    }

    @RequestMapping(value = "/ordertrip", method = RequestMethod.POST)
    public ModelAndView selecttripp(@RequestParam(name = "trip_id", required = true) int trip_id,
                                    @RequestParam(name = "from_id", required = true) int from_id,
                                    @RequestParam(name = "to_id", required = true) int to_id,
                                    @RequestParam(name = "user_id", required = true) int user_id) throws SQLException{
        user_trip trip = new user_trip();
        user_tripDAO user_tripdao = new user_tripDAO();
        scheduleDAO scheduledao = new scheduleDAO();
        stationsDAO stationsdao = new stationsDAO();
        usersDAO usersdao = new usersDAO();
        trip.setTrip(scheduledao.getTripById(trip_id));
        trip.setTo_station_id(stationsdao.getTripById(to_id));
        trip.setFrom_station_id(stationsdao.getTripById(from_id));
        trip.setUser(usersdao.getUserById(user_id));
        user_tripdao.addTicketsOrder(trip);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userhome");
        modelAndView.addObject("user_id", user_id);
        modelAndView.addObject("is_admin", usersdao.getUserById(user_id).getIs_admin());
        return modelAndView;

    }
}
