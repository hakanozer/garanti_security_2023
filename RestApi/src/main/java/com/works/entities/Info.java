package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    public Info() {
    }

    public Info(String username, String roles, String url, String sessionID, String userAgent, String time, String ip) {
        this.username = username;
        this.roles = roles;
        this.url = url;
        this.sessionID = sessionID;
        this.userAgent = userAgent;
        this.time = time;
        this.ip = ip;
    }

    private String username;
    private String roles;
    private String url;
    private String sessionID;
    private String userAgent;
    private String time;
    private String ip;

}
