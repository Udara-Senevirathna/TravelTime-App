package com.udara.traveltime;

import java.io.Serializable;

public class WriteMakeBooking implements Serializable {
    private String selectedSeat;

    public WriteMakeBooking(String selectedSeat) {
        this.selectedSeat = selectedSeat;
    }

    public String getSelectedSeat() {
        return this.selectedSeat;
    }

    public void setSelectedSeat(String selectedSeat) {
        this.selectedSeat = selectedSeat;
    }
}
