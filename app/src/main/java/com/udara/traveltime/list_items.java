package com.udara.traveltime;

public class list_items {
    String depature_location;
    String arrival_location;
    String time;

    public list_items(String Depature_location, String Arrival_location, String Time) {
        this.depature_location = Depature_location;
        this.arrival_location = Arrival_location;
        this.time = Time;
    }

    public String getDepature_location() {
        return depature_location;
    }

    public void setName(String Depature_location) {
        this.depature_location = Depature_location;
    }

    public String getArrival_location() {
        return arrival_location;
    }

    public void setArrival_location(String Arrival_location) {
        this.arrival_location = Arrival_location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String Time) {
        this.time = Time;
    }

}
