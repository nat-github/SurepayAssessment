package com.api.model;

import lombok.Data;

/**
 * This class represents a User object with id, name, username, email, website, phone, address, and company attributes.
 */
@Data
public class Comments {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
