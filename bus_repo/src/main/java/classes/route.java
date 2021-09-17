package classes;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "route")
public class route {


    private int route_id;
    private String route_name;
    private double price;

    public route (){
    }

    public route (int route_id, String route_name, double price){
        this.route_id = route_id;
        this.route_name = route_name;
        this.price = price;
    }

    @Id
    @Column(name = "route_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getRoute_id(){
        return this.route_id;
    }
    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    @Column(name = "route_price")
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "route_name")
    public String getRoute_name(){
        return this.route_name;
    }
    public void setRoute_name(String route_name){
        this.route_name = route_name;
    }
}