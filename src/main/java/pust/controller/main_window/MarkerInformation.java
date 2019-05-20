package pust.controller.main_window;

public class MarkerInformation {


    private String scrolltextDescription;
    private String markerDescription;
    private String latLong;
    private String title;


    public MarkerInformation(String scrolltextDescription, String markerDescription, String latLong,String title) {
        this.scrolltextDescription = scrolltextDescription;
        this.markerDescription = markerDescription;
        this.latLong = latLong;
        this.title= title;
    }

    public String getScrolltextDescription() {
        return scrolltextDescription;
    }

    public void setScrolltextDescription(String scrolltextDescription) {
        this.scrolltextDescription = scrolltextDescription;
    }

    public String getMarkerDescription() {
        return markerDescription;
    }

    public void setMarkerDescription(String markerDescription) {
        this.markerDescription = markerDescription;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
