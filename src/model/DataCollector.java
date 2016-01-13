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
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
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
     * Gets the latest information from the source given to the model.DataCollector.
     *
     * @return a list of all the travel offers.
     * @throws IOException if unable to read all the offers
     */
    public NodeList collectData() throws IOException {
        return collect();
    }

    public synchronized NodeList collectData(String destinations) throws IOException {
        if (destinations.isEmpty()) {
            return collect();
        }

        /*Split to separate destinations*/
        String[] names = destinations.split(" ?, ?| $");

        NodeList allOffers = collect();
        ArrayList<Node> wantedOffers = new ArrayList<>();

        for (int i = 0; i < allOffers.getLength(); i++) {
            Node node = allOffers.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                for (String name : names) {
                    if (name.compareToIgnoreCase(getTagValue("DestinationName", element)) == 0) {
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

    private class ImplementedNL implements NodeList {
        ArrayList<Node> list;

        protected ImplementedNL(ArrayList<Node> list) {
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
