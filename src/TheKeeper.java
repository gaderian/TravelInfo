import GUI.Offers;
import GUI.TravelOffer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.lang.Integer;
import java.util.Date;

/**
 * Class:       TheKeeper
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class TheKeeper implements Offers {
    private NodeList list;
    private ArrayList<OfferInfo> table;

    public TheKeeper() {
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
        return nValue.getNodeValue();
    }

    @Override
    public int getRowCount() {
        return list.getLength();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Destination";
            case 1:
                return "Date";
            case 2:
                return "Price";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return int.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    @Override
    public TravelOffer fullInfo(int index) {
        TravelOffer offer = new TravelOffer();

        Node node = list.item(index);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            offer.setDestinationLand(getTagValue("DestinationName", element));
            offer.setDestinationCity(getTagValue("CityName", element));

            String priceStr = getTagValue("CurrentPrice", element);
            int price = Integer.parseInt(priceStr.substring(0, priceStr.length() - 3));
            offer.setCurrentPrice(price);

            priceStr = getTagValue("OriginalPrice", element);
            price = Integer.parseInt(priceStr.substring(0, priceStr.length() - 3));
            offer.setOriginalPrice(price);

            offer.setCampaign(getTagValue("CampaignName", element));

            offer.setDepartureFrom(getTagValue("DepartureName", element));

            offer.setRoomDescription(getTagValue("RoomDescription", element));

            offer.setDaysAway(Integer.parseInt(getTagValue("JourneyLength", element)));

            offer.setHotelName(getTagValue("HotelName", element));
            offer.setHotelRating(Integer.parseInt(getTagValue("HotelGrade", element)));

            try {
                offer.setHotelImage(new URL(getTagValue("HotelImage", element)));
            } catch (MalformedURLException e) {
                //No image, will simply be a bit more boring.
                offer.setHotelImage(null);
            }

            try {
                offer.setBookingLink(new URL(getTagValue("BookLink", element)));
            } catch (MalformedURLException e) {
                //TODO should not crash but should probably do something about the missing link...
                e.printStackTrace();
            }

            String[] date = getTagValue("OutDate", element).split("-");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            offer.setDepartureDate(new Date(year, month, day));

        }
        return null;
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
