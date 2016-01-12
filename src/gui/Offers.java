package gui;

import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Class:       Offers
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        1/2/16
 */
public interface Offers extends TableModel {

    gui.TravelOffer fullInfo(int index);

    void updateOffers(Component c);

    void searchOffers(Component c, String destination);

    void setUpdateInterval(int minutes);
}
