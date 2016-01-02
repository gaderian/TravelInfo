package GUI;

import java.net.URL;
import java.util.Date;


/**
 * Class:       GUI.TravelOffer
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class TravelOffer {
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
    private int hotelRating;

    private URL bookingLink;

    public TravelOffer() {

    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDestinationLand() {
        return destinationLand;
    }

    public void setDestinationLand(String destinationLand) {
        this.destinationLand = destinationLand;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public int getDaysAway() {
        return daysAway;
    }

    public void setDaysAway(int daysAway) {
        this.daysAway = daysAway;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public URL getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(URL hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public int getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }

    public URL getBookingLink() {
        return bookingLink;
    }

    public void setBookingLink(URL bookingLink) {
        this.bookingLink = bookingLink;
    }
}
