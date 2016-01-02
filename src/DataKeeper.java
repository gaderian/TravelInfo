import GUI.TravelOffer;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Class:       DataKeeper
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class DataKeeper {
    ArrayList<TravelOffer> offers;

    public DataKeeper() {
    }

    public void newData(NodeList data){
        offers = new ArrayList<TravelOffer>(data.getLength());
        for (int i = 0; i < data.getLength(); i++){

        }
    }
}
