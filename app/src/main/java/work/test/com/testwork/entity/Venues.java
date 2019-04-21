package work.test.com.testwork.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venues {
    @SerializedName("venue")
    private Venue venue;

    public Venue getVenue() {
        return venue;
    }
}
