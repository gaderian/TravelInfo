package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class:       DataCollector
 * <p>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */

/**
 * Will get information stored as a xml from a specified source.
 */
public class DataCollector {
    private NodeList offers;
    private URL source;

    public DataCollector() {

    }

    /**
     * Sets the source to get the information from.
     * @param source an URL pointing to the location of a xml
     */
    public void setSource(URL source) {
        this.source = source;
    }


    /**
     * Collects the latest data from the specified source. If
     * {@code destination} is an empty string all the available offers will be
     * returned, otherwise just the offers with a destination matching one of
     * the destinations in the search string.
     *
     * @param destinations a search string with destinations separated by ","
     * @return a NodeList containing the wanted offers.
     * @throws IOException if something goes wrong with the parsing of the data
     */
    public  NodeList collectData(String destinations) throws IOException {
        NodeList allOffers = collect();

        if (destinations.isEmpty()) {
            return allOffers;
        }

        /*Split into separate destinations*/
        String[] names = destinations.split(" ?, ?| $");

        ArrayList<Node> wantedOffers = new ArrayList<>(1000);

        for (int i = 0; i < allOffers.getLength(); i++) {
            Node node = allOffers.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                for (String name : names) {
                    if (name.compareToIgnoreCase(getTagValue("DestinationName",
                            element)) == 0) {
                        wantedOffers.add(node);
                    }
                }
            }
        }
        wantedOffers.trimToSize();
        return new ImplementedNL(wantedOffers);
    }

    /**
     * Will return the value held by a tag in the xml document.
     *
     * @param tag     the name of the tag
     * @param element the element holding the tag
     * @return a string representing the value, if null an empty string will be
     * returned
     */
    private String getTagValue(String tag, Element element) {
        NodeList nlList =
                element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = nlList.item(0);

        if (nValue == null) {
            return "";
        }
        return nValue.getNodeValue();
    }

    /**
     * Gets the latest data from the set source. Gets the data and makes a
     * NodeList containing all the offer elements in the source xml.
     *
     * @return a NodeList containing all offers.
     * @throws IOException if anything goes wrong when parsing the xml
     */
    private NodeList collect() throws IOException {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            InputSource is = new InputSource(source.openStream());
            is.setEncoding("UTF-8");

            Document doc = dBuilder.parse(is);
            Element data = doc.getDocumentElement();
            data.normalize();
            offers = doc.getElementsByTagName("Offer");
        } catch (ParserConfigurationException e) {
            throw new IOException("Unable to configure parser");
        } catch (SAXException e) {
            throw new IOException("Not correct format");
        }
        return offers;
    }

    /**
     * A simple implementation of {@link NodeList}.
     */
    private class ImplementedNL implements NodeList {
        final private ArrayList<Node> list;

        /**
         * Creates an instance of ImplementedNL.
         *
         * @param list an ArrayList containing all the nodes of the new NodeList
         */
        private ImplementedNL(ArrayList<Node> list) {
            this.list = list;
        }

        @Override
        public Node item(int index) {
            return list.get(index);
        }

        @Override
        public int getLength() {
            return list.size();
        }
    }

}
