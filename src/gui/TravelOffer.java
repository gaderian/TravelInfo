package gui;

import java.net.URL;


/**
 * Class:       TravelOffer
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */

/**
 * This class is a holder of information. The information in a TravelOffer is
 * all the information the user could use when searching for a trip.
 */
public class TravelOffer {
    private String campaign;

    private String departureFrom;
    private String departureDate;
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

    /**
     * Creates an instance of TravelOffer.
     */
    public TravelOffer() {

    }

    /**
     * Returns the campaign name.
     *
     * @return the campaign name
     */
    public String getCampaign() {
        return campaign;
    }

    /**
     * Sets the campaign name of the offer.
     *
     * @param campaign the campaign name.
     */
    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    /**
     * Returns the location of departure.
     *
     * @return the departure location.
     */
    public String getDepartureFrom() {
        return departureFrom;
    }

    /**
     * Sets the location of departure.
     *
     * @param departureFrom the location of departure
     */
    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    /**
     * Returns the departure date as a string.
     *
     * @return the departure date.
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the departure date of the offer. Preferably use the format
     * "yyyy-mm-dd"
     *
     * @param departureDate the departure date as a string.
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Returns the destination country of the trip.
     *
     * @return the destination country of the trip
     */
    public String getDestinationLand() {
        return destinationLand;
    }

    /**
     * Sets the destination country of the trip.
     *
     * @param destinationLand the destination country
     */
    public void setDestinationLand(String destinationLand) {
        this.destinationLand = destinationLand;
    }

    /**
     * Returns the destination city of the trip.
     *
     * @return the destination city.
     */
    public String getDestinationCity() {
        return destinationCity;
    }

    /**
     * Sets the destination city of the trip.
     *
     * @param destinationCity the destination city
     */
    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    /**
     * Returns the number of days the trip will stretch over.
     *
     * @return the number of days of the trip
     */
    public int getDaysAway() {
        return daysAway;
    }

    /**
     * Sets the number of days the trip will stretch over.
     *
     * @param daysAway the number of days of the trip
     */
    public void setDaysAway(int daysAway) {
        this.daysAway = daysAway;
    }

    /**
     * Returns the original price of the travel.
     *
     * @return the original price
     */
    public int getOriginalPrice() {
        return originalPrice;
    }

    /**
     * Sets the original price of the travel.
     *
     * @param originalPrice the original price
     */
    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * Returns the price provided in the offer.
     *
     * @return the current price of the trip.
     */
    public int getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Sets the price provided in the offer.
     *
     * @param currentPrice the current price of the trip
     */
    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * Returns the name of the hotel.
     *
     * @return the name of the hotel
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Sets the name of the hotel.
     *
     * @param hotelName the name of the hotel
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * Returns an URL to the location of the image for the hotel.
     *
     * @return URL to the hotel image
     */
    public URL getHotelImage() {
        return hotelImage;
    }

    /**
     * Sets the URL to the location of the image for the hotel.
     *
     * @param hotelImage URL to the hotel image
     */
    public void setHotelImage(URL hotelImage) {
        this.hotelImage = hotelImage;
    }

    /**
     * Returns the description of the room included in the description.
     *
     * @return the description of the room.
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * Sets the description of the room included in the offer.
     *
     * @param roomDescription a description of the room
     */
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    /**
     * Returns the rating of the hotel.
     *
     * @return the rating of the hotel
     */
    public float getHotelRating() {
        return hotelRating;
    }

    /**
     * Sets the rating of the hotel.
     *
     * @param hotelRating the rating of the hotel
     */
    public void setHotelRating(float hotelRating) {
        this.hotelRating = hotelRating;
    }

    /**
     * Returns the URL of where one can book the trip of the offer.
     *
     * @return the URL where one can book
     */
    public URL getBookingLink() {
        return bookingLink;
    }

    /**
     * Sets the URL to where one can book the trip.
     *
     * @param bookingLink the URL where one can book
     */
    public void setBookingLink(URL bookingLink) {
        this.bookingLink = bookingLink;
    }
}
