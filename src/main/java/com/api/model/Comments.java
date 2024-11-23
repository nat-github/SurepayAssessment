package com.api.model;

import lombok.Data;

/**
 * This class represents a Comments object with postid,id,name, email, body attributes.
 */
@Data
public class Comments {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
