package classes;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "schedule")
public class schedule {
    private int trip_id;
    private route route_id;
    private Timestamp date_time;
    private int seats;

    public schedule(){
    }

    public schedule(int trip_id, route route_id, Timestamp date_time, int seats) {
        this.trip_id = trip_id;
        this.route_id = route_id;
        this.date_time = date_time;
        this.seats = seats;
    }

    @Id
    @Column(name = "trip_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    @ManyToOne(targetEntity = route.class)
    @JoinColumn(name = "route_id")
    public route getRoute_id() {
        return route_id;
    }

    public void setRoute_id(route route_id) {
        this.route_id = route_id;
    }

    @Column(name = "date_time")
    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    @Column(name = "seats")
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
