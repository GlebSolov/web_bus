package classes;


import javax.persistence.*;

@Entity
@Table(name = "user_trip")
public class user_trip {
    private stations from_station_id;
    private stations to_station_id;
    private int user_in_trip_id;

    private schedule trip_id;
    private users user_id;

    public user_trip(){
    }

    public user_trip(schedule trip_id, stations from_station_id, stations to_station_id, int user_in_trip_id, users user_id) {
        this.trip_id = trip_id;
        this.from_station_id = from_station_id;
        this.to_station_id = to_station_id;
        this.user_in_trip_id = user_in_trip_id;
        this.user_id = user_id;
    }

    @ManyToOne(targetEntity = stations.class)
    @JoinColumn(name = "from_station_id", referencedColumnName = "station_id")
    public stations getFrom_station_id() {
        return from_station_id;
    }

    public void setFrom_station_id(stations from_station_id) {
        this.from_station_id = from_station_id;
    }

    @ManyToOne(targetEntity = stations.class)
    @JoinColumn(name = "to_station_id", referencedColumnName = "station_id")
    public stations getTo_station_id() {
        return to_station_id;
    }

    public void setTo_station_id(stations to_station_id) {
        this.to_station_id = to_station_id;
    }

    @Id
    @Column(name = "user_in_trip_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUser_in_trip_id() {
        return user_in_trip_id;
    }

    public void setUser_in_trip_id(int user_in_trip_id) {
        this.user_in_trip_id = user_in_trip_id;
    }

    @ManyToOne(targetEntity = users.class)
    @JoinColumn(name = "user_id")
    public users getUser() {
        return user_id;
    }

    public void setUser(users user_id) {
        this.user_id = user_id;
    }


    @ManyToOne(targetEntity = schedule.class)
    @JoinColumn(name = "trip_id")
    public schedule getTrip() {
        return trip_id;
    }

    public void setTrip(schedule trip_id) {
        this.trip_id = trip_id;
    }
}
