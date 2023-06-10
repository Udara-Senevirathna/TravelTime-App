package com.udara.traveltime;



import java.io.Serializable;

public class RWDataToFirebas implements Serializable {


    private String firstName, lastName, nic;
    public RWDataToFirebas(){}

    public RWDataToFirebas(String firstName, String lastName, String nic){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String mobileNumber) {
        this.nic = nic;
    }
}
