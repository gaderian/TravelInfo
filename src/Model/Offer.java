package model;

import java.net.URL;
import java.util.Date;

/**
 * Created by erik on 2016-01-10.
 */
public class Offer {
    private String campaign;

    private String departureFrom;
    private Date departureDate;
    private String destinationLand;
    private String destinationCity;
    private int daysAway;

    private int originalPrice;
    private int currentPrice;

    private String hotelName;
    private URL hotelImage;
    private String roomDescription;
    private float hotelRating;

    private URL bookingLink;

    protected Offer() {
    }

    public String getCampaign() {
        return campaign;
    }

    protected void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getDepartureFrom() {
        return departureFrom;
    }

    protected void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    protected void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDestinationLand() {
        return destinationLand;
    }

    protected void setDestinationLand(String destinationLand) {
        this.destinationLand = destinationLand;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    protected void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public int getDaysAway() {
        return daysAway;
    }

    protected void setDaysAway(int daysAway) {
        this.daysAway = daysAway;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    protected void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    protected void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getHotelName() {
        return hotelName;
    }

    protected void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public URL getHotelImage() {
        return hotelImage;
    }

    protected void setHotelImage(URL hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    protected void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public float getHotelRating() {
        return hotelRating;
    }

    protected void setHotelRating(float hotelRating) {
        this.hotelRating = hotelRating;
    }

    public URL getBookingLink() {
        return bookingLink;
    }

    protected void setBookingLink(URL bookingLink) {
        this.bookingLink = bookingLink;
    }
}
