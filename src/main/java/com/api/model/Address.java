package com.api.model;
import lombok.Data;
/**
 * This class represents an address with its corresponding street, suite, city, zipcode, and geo location.
 */
@Data
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}
