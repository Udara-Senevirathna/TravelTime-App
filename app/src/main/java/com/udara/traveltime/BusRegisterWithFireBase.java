package com.udara.traveltime;
import java.io.Serializable;
public class BusRegisterWithFireBase implements Serializable  {
    String Uid, BusNo, DriverName, Nic_no, Seat;

    public BusRegisterWithFireBase(){

    }
    public  BusRegisterWithFireBase(String uId, String busNo, String driverName, String nic_no, String seat){
        this.Uid = uId;
        this.BusNo = busNo;
        this.DriverName = driverName;
        this.Nic_no = nic_no;
        this.Seat = seat;
    }

    // Getter and setter methods for each property

    public String getUid() {
        return Uid;
    }

    public void setUid(String uId) {
        this.Uid = uId;
    }

    public String getBusNo() {
        return BusNo;
    }

    public void setBusNo(String busNo) {
        this.BusNo = busNo;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        this.DriverName = driverName;
    }

    public String getNic_no() {
        return Nic_no;
    }

    public void setNic_no(String nic_no) {
        this.Nic_no = nic_no;
    }

    public String getSeat() {
        return Seat;
    }

    public void setSeat(String seat) {
        this.Seat = seat;
    }
}
