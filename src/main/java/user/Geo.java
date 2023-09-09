package user;

public class Geo {
    private String lat;
    private String lng;

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }

    public String toJsonString() {
        return "{"
                + "\"lat\":\"" + lat + "\","
                + "\"lng\":\"" + lng + "\""
                + "}";
    }
}
