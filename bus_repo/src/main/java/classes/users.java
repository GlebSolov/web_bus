package classes;


import javax.persistence.Column;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class users {


    private int user_id;
    private boolean is_admin;
    private String user_name;
    private String user_contact_info;
    private String passw;

    public users (){
    }

    public users (int user_id, boolean is_admin, String user_name, String user_contact_info, String passw){
        this.user_id = user_id;
        this.is_admin = is_admin;
        this.user_name = user_name;
        this.user_contact_info = user_contact_info;
        this.passw = passw;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUser_id(){
        return this.user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    @Column(name="is_admin")
    public boolean getIs_admin(){
        return this.is_admin;
    }
    public void setIs_admin(boolean is_admin){
        this.is_admin = is_admin;
    }

    @Column(name = "user_name")
    public String getUser_name(){
        return this.user_name;
    }
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    @Column(name = "user_contact_info")
    public String getUser_contact_info(){
        return this.user_contact_info;
    }
    public void setUser_contact_info(String user_contact_info){
        this.user_contact_info = user_contact_info;
    }

    @Column(name = "passw")
    public String getUser_passw(){
        return this.passw;
    }
    public void setUser_passw(String passw){
        this.passw = passw;
    }
}
