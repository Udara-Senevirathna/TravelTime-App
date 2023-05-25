package com.udara.traveltime;

import java.io.Serializable;

public class RWDataToFirebas implements Serializable {

    private String getfirstName, getlastName, getNIC;

    public RWDataToFirebas(){}

    public RWDataToFirebas(String firstName, String lastName, String getNIC){
        this.getfirstName = firstName;
        this.getlastName = lastName;
        this.getNIC = getNIC;
    }
    // setters
    public void setFname(String firstName){
        this.getfirstName = firstName;

    }
    public void setLname(String lastName){
        this.getlastName = lastName;
    }
    public void setNIC(String nic){
        this.getNIC = nic;
    }

    // getters

    public String getFName(){
        return this.getfirstName;
    }
    public String getLname(){
        return this.getlastName;
    }
    public String getNIC(String nic){
        return  this.getNIC;
    }
}
