package com.udara.traveltime;



import java.io.Serializable;

public class RWDataToFirebas implements Serializable {


    private String firstName, lastName, nic, userId, email;
    boolean isAdmin;
    public RWDataToFirebas(){}

    public RWDataToFirebas(String firstName, String lastName, String nic, String userId, String email, boolean isAdmin){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.userId = userId;
        this.email = email;
        this.isAdmin = isAdmin;
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

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }


    public String getemail() {
        return email;
    }

    public void setemail(String userId) {
        this.email = email;
    }

    public boolean getisAdmin() {
        return isAdmin;
    }

    public void setisAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
