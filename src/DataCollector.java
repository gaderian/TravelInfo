import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

/**
 * Class:       DataCollector
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class DataCollector {
    private NodeList offers;
    private URL source;

    public DataCollector(){

    }

    public void setSource(URL source) throws IOException, SAXException {
        //TODO Doesn't work with urls in the xml file :(
        //if (validate(new StreamSource(source.openStream())));
        this.source = source;
    }

    /**
     * Gets the latest information from the source given to the DataCollector.
     *
     * @return a list of all the travel offers.
     * @throws IOException if unable to read all the offers
     */
    public NodeList update() throws IOException {
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
     * TODO currently not in use
     * Validates the file so that it is structured in the expected way.
     *
     * @param xml TODO
     * @return
     * @throws IOException
     * @throws SAXException
     */
    private boolean validate(StreamSource xml)
            throws IOException, SAXException {

        String schemaPath = "schema.xsd";

        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
        URL url = getClass().getResource(schemaPath);
        Schema schema = factory.newSchema(new StreamSource(url.openStream()));
        Validator validator = schema.newValidator();
        validator.validate(xml);
        return true;
    }

}
