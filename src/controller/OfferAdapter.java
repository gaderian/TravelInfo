package controller;

import gui.TravelOffer;
import model.Offer;

/**
 * Class:       OfferAdapter
 *
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2016-01-10
 */

/**
 *
 * Class for converting between TravelOffer in the gui package and Offer in the
 * model package.
 */
public class OfferAdapter {
    /**
     * Creates a new instance of {@link TravelOffer} containing the information
     * in the given {@link Offer}
     *
     * @param offer the Offer containing the original information.
     * @return a instance of TravelOffer containing the information of the Offer
     */
    public static TravelOffer generateTravelOffer(Offer offer){
        TravelOffer travelOffer = new TravelOffer();

        travelOffer.setBookingLink(offer.getBookingLink());
        travelOffer.setCampaign(offer.getCampaign());
        travelOffer.setCurrentPrice(offer.getCurrentPrice());
        travelOffer.setOriginalPrice(offer.getOriginalPrice());
        travelOffer.setDaysAway(offer.getDaysAway());
        travelOffer.setDepartureFrom(offer.getDepartureFrom());
        travelOffer.setDestinationCity(offer.getDestinationCity());
        travelOffer.setDestinationLand(offer.getDestinationLand());
        travelOffer.setHotelName(offer.getHotelName());
        travelOffer.setHotelImage(offer.getHotelImage());
        travelOffer.setHotelRating(offer.getHotelRating());
        travelOffer.setRoomDescription(offer.getRoomDescription());

        travelOffer.setDepartureDate(offer.getDepartureDate());

        return travelOffer;
    }
}
