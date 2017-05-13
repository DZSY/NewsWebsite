package com.dzsy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by positif on 04/05/2017.
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name="user_id",length=16)
    private String username;

    @Column(name="password",length=16)
    private String password;

    @Column(name="email",length=30)
    private String email;

    @Column(name="activation_code",length=6)
    private String activation;



    public User () {}

    public User (String username, String password, String email, String activation) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activation = activation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }



    public void display(){
        System.out.println(username + " " + password + " " + email);
    }

}


