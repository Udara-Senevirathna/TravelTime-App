package com.udara.traveltime;



import java.io.Serializable;

public class RWDataToFirebas implements Serializable {

    private String firstName, lastName, NIC;

    public RWDataToFirebas() {
    }

    public RWDataToFirebas(String firstName, String lastName, String NIC) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.NIC = NIC;
    }

    // Getter and setter methods for each property

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }
}
