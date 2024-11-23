package com.api.model;

import lombok.Data;
/**
 * This class represents a Company with its name, catchPhrase, and bs.
 */
@Data
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;
}
