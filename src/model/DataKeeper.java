package model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.lang.Integer;

/**
 * Class:       DataKeeper
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */

/**
 * An instance of DataKeeper will take a {@link NodeList} and store the 
 * information for future use.
 */
public class DataKeeper {
    private NodeList list;
    private ArrayList<OfferInfo> previewArray;

    /**
     * Creates an instance of DataKeeper.
     */
    public DataKeeper() {
    }

    /**
     * Will give a new NodeList to the DataKeeper.
     * 
     * @param list a NodeList containing all offers.
     */
    public void setNodeList(NodeList list) {
        this.list = list;
        generatePreviewArray();
    }

    /**
     * Will generate the array containing the preview information.
     */
    private void generatePreviewArray() {
        previewArray = new ArrayList<>(list.getLength());
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String dest = getTagValue("DestinationName", element);
                String date = getTagValue("OutDate", element);
                String priceStr = getTagValue("CurrentPrice", element);
                int price = Integer.parseInt(priceStr.substring(0, priceStr.length() - 3));
                previewArray.add(i, new OfferInfo(dest, date, price));
            }
        }
        previewArray.trimToSize();
    }

    /**
     * Will return the value held by a tag in the xml document. If there is no
     * value an empty string will be returned.
     *
     * @param tag     the name of the tag
     * @param element the element holding the tag
     * @return a string representing the value
     */
    private String getTagValue(String tag, Element element) {
        NodeList nlList =
                element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = nlList.item(0);
        
        if (nValue == null){
            return "";
        }
        return nValue.getNodeValue();
    }

    /**
     * Returns the number of offers currently held by the DataKeeper.
     *
     * @return the current number of offers
     */
    public int getNrOfOffers() {
        return previewArray.size();
    }

    /**
     * Returns the number of information fields of the preview.
     *
     * @return the number of preview fields
     */
    public int getNrOfPreviewFields() {
        return 3;
    }

    /**
     * Returns the names of the preview fields.
     *
     * @return the names of the preview fields
     */
    public String[] getPreviewFieldNames() {
        return new String[]{"Destination", "Date", "Price"};
    }

    /**
     * Returns the classes of the preview fields.
     *
     * @return the classes of the preview field
     */
    public Class<?>[] getPreviewFieldClasses() {
        return new Class<?>[]{String.class, String.class, int.class};
    }

    /**
     * Return the information of a specific preview field.
     *
     * @param offerIndex index telling which offer to show preview of.
     * @param previewIndex index telling which field is desired.
     * @return the information of a specific preview field.
     */
    public Object getValueAt(int offerIndex, int previewIndex) {
        OfferInfo info = previewArray.get(offerIndex);

        switch (previewIndex) {
            case 0:
                return info.getDestination();
            case 1:
                return info.getDate();
            case 2:
                return info.getPrice();
        }
        return null;
    }

    /**
     * Returns an instance of {@link Offer} containing all the information about
     * a offer.
     *
     * @param offerIndex index telling which offer to return.
     * @return an instance of Offer
     */
    public Offer getOffer(int offerIndex) {
        Offer offer = new Offer();

        Node node = list.item(offerIndex);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            offer.setDestinationLand(getTagValue("DestinationName", element));
            offer.setDestinationCity(getTagValue("CityName", element));
            offer.setDaysAway(Integer.parseInt(getTagValue("JourneyLength",
                    element)));

            String priceStr = getTagValue("CurrentPrice", element);
            int price = Integer.parseInt(priceStr.substring(0,
                    priceStr.length() - 3));
            offer.setCurrentPrice(price);

            priceStr = getTagValue("OriginalPrice", element);
            price = Integer.parseInt(priceStr.substring(0,
                    priceStr.length() - 3));
            offer.setOriginalPrice(price);

            offer.setCampaign(getTagValue("CampaignName", element));
            offer.setDepartureFrom(getTagValue("DepartureName", element));

            offer.setHotelName(getTagValue("HotelName", element));
            offer.setHotelRating(Float.parseFloat(
                    getTagValue("HotelGrade", element).replace(",", ".")));
            offer.setRoomDescription(getTagValue("RoomDescription", element));
            offer.setDepartureDate(getTagValue("OutDate", element));

            try {
                offer.setHotelImage(new URL(getTagValue("HotelImage",
                        element)));
            } catch (MalformedURLException e) {
                //No image, will simply be a bit more boring.
                offer.setHotelImage(null);
            }

            try {
                offer.setBookingLink(new URL(getTagValue("BookLink", element)));
            } catch (MalformedURLException e) {
                // No/broken booking link
            }


        }
        return offer;
    }

    /**
     * An internal class for storing the preview information of offers.
     */
    private class OfferInfo {
        private String destination;
        private String date;
        private int price;

        /**
         * Creates an instance of OfferInfo
         * @param dest the destination country of the trip
         * @param date the departure date for the trip
         * @param price the current price of the trip
         */
        public OfferInfo(String dest, String date, int price) {
            this.destination = dest;
            this.date = date;
            this.price = price;
        }

        /**
         * Returns the destination country of the trip.
         *
         * @return the destination country of the trip
         */
        public String getDestination() {
            return destination;
        }

        /**
         * Returns the departure date of the trip.
         *
         * @return the departure date of the trip
         */
        public String getDate() {
            return date;
        }

        /**
         * Returns the current price of the trip.
         *
         * @return the current price of the trip
         */
        public int getPrice() {
            return price;
        }
    }
}
