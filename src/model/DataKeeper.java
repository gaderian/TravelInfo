package model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.lang.Integer;
import java.util.Date;

/**
 * Class:       DataKeeper
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class DataKeeper {
    private NodeList list;
    private ArrayList<OfferInfo> table;

    public DataKeeper() {
    }

    public void setNodeList(NodeList list) {
        this.list = list;
        generateTable();
    }

    private void generateTable() {
        table = new ArrayList<OfferInfo>(list.getLength());
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String dest = getTagValue("DestinationName", element);
                String date = getTagValue("OutDate", element);
                String priceStr = getTagValue("CurrentPrice", element);
                int price = Integer.parseInt(priceStr.substring(0, priceStr.length() - 3));
                table.add(i, new OfferInfo(dest, date, price));
            }
        }
        table.trimToSize();
    }

    /**
     * Will return the value held by a tag in the xml document.
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

    public int getNrOfOffers() {
        return table.size();
    }

    public int getNrOfPreviewFields() {
        return 3;
    }

    public String[] getPreviewFieldNames() {
        String[] names = {"Destination", "Date", "Price"};
        return names;
    }

    public Class<?>[] getPreviewFieldClasses() {
        Class<?>[] classes = {String.class, String.class, int.class};
        return classes;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        OfferInfo info = table.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return info.getDestination();
            case 1:
                return info.getDate();
            case 2:
                return info.getPrice();
        }
        return null;
    }

    public Offer getOffer(int index) {
        Offer offer = new Offer();

        Node node = list.item(index);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            offer.setDestinationLand(getTagValue("DestinationName", element));
            offer.setDestinationCity(getTagValue("CityName", element));
            offer.setDaysAway(Integer.parseInt(getTagValue("JourneyLength", element)));

            String priceStr = getTagValue("CurrentPrice", element);
            int price = Integer.parseInt(priceStr.substring(0, priceStr.length() - 3));
            offer.setCurrentPrice(price);

            priceStr = getTagValue("OriginalPrice", element);
            price = Integer.parseInt(priceStr.substring(0, priceStr.length() - 3));
            offer.setOriginalPrice(price);

            offer.setCampaign(getTagValue("CampaignName", element));
            offer.setDepartureFrom(getTagValue("DepartureName", element));

            offer.setHotelName(getTagValue("HotelName", element));
            offer.setHotelRating(Float.parseFloat(getTagValue("HotelGrade", element).replace(",", ".")));
            offer.setRoomDescription(getTagValue("RoomDescription", element));
            offer.setDepartureDate(getTagValue("OutDate", element));

            try {
                offer.setHotelImage(new URL(getTagValue("HotelImage", element)));
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

    private class OfferInfo {
        private String destination;
        private String date;
        private int price;

        public OfferInfo(String dest, String date, int price) {
            this.destination = dest;
            this.date = date;
            this.price = price;
        }

        public String getDestination() {
            return destination;
        }

        public String getDate() {
            return date;
        }

        public int getPrice() {
            return price;
        }
    }
}
