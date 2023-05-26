package com.udara.traveltime;

public class list_items {
    String depature_location, arrival_location, time, price, bus_no, route_no, route_button_id;


    public list_items(String Depature_location, String Arrival_location, String Time, String Price, String Bus_no, String Route_no, String Route_button_id) {
        this.depature_location = Depature_location;
        this.arrival_location = Arrival_location;
        this.time = Time;
        this.price = Price;
        this.bus_no = Bus_no;
        this.route_no = Route_no;
        this.route_button_id = Route_button_id;
    }

    public String getDepature_location() {
        return depature_location;
    }

    public void setDepature_location(String Depature_location) {
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


    public String getPrice() {
        return price;
    }

    public void setPrice(String Price) {
        this.price = Price;
    }

    public String getBus_no() {
        return bus_no;
    }

    public void setBus_no(String Bus_no) {
        this.bus_no = Bus_no;
    }

    public String getRoute_no() {
        return route_no;
    }

    public void setRoute_no(String Route_no) {
        this.route_no = Route_no;
    }

    public String getRoute_button_id() {
        return route_button_id;
    }

    public void setRoute_button_id(String Route_button_id) {
        this.route_button_id = Route_button_id;
    }

}
