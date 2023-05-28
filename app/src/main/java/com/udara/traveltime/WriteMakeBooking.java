package com.udara.traveltime;

import java.io.Serializable;

public class WriteMakeBooking implements Serializable {
    private String selectedSeat, route_id, userId, date, time;
    private  int bookingStatus;
    public WriteMakeBooking(String selectedSeat, String FirebaseUserID, String Route_id, int BookingStatus, String Date, String Time) {
        this.selectedSeat = selectedSeat;
        this.route_id = Route_id;
        this.userId = FirebaseUserID;
        this.bookingStatus = BookingStatus;
        this.date = Date;
        this.time = Time;
    }

    public String getSelectedSeat() {
        return this.selectedSeat;
    }

    public void setSelectedSeat(String selectedSeat) {
        this.selectedSeat = selectedSeat;
    }


    public String getRoute_id() {
        return this.route_id;
    }

    public void setRoute_id(String Route_id) {
        this.route_id = Route_id;
    }

    public String getFirebaseUserID() {
        return this.userId;
    }

    public void setFirebaseUserID(String FirebaseUserID) {
        this.userId = FirebaseUserID;
    }


    public String getDate() {
        return this.date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String Time) {
        this.time = Time;
    }


    public int getBookingStatus() {
        return this.bookingStatus;
    }

    public void setBookingStatus(int BookingStatus) {
        this.bookingStatus = BookingStatus;
    }
}
