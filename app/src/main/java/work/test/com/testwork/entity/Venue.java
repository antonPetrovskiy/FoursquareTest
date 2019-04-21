package work.test.com.testwork.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("location")
    @Expose
    private Location location;

    public class Location {
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lng")
        @Expose
        private String lng;
        @SerializedName("address")
        @Expose
        private String address;

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }

        public String getAddress() {
            return address;
        }
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
