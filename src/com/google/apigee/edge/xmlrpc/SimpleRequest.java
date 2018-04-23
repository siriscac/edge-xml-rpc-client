package com.google.apigee.edge.xmlrpc;

import java.io.Serializable;

public class SimpleRequest implements Serializable {
    public String firstName, lastName, country;
    public Integer memberId, premium;

    public SimpleRequest(String firstName, String lastName, String country, int memberId, int x, int y) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.memberId = memberId;
        this.premium = x + y;
    }
}
