package com.api.model;
import lombok.Data;
/**
 * This class represents a Post object with userId, id, title, and body attributes.
 */
@Data
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
}
