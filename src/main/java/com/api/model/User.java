package com.api.model;

import lombok.Data;
@Data
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String website;
    private String phone;
    private Address address;
    private Company company;
}
