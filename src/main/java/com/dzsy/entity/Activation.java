package com.dzsy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by positif on 13/05/2017.
 */
@Entity
@Table(name = "activating")
public class Activation {

    @Id
    @Column(name="user_id",length=16)
    private String username;

    @Column(name="activation_code",length=6)
    private String activation;


    public Activation () {}

    public Activation (String username, String activation) {
        this.username = username;
        this.activation = activation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }



}
