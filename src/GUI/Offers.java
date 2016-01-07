package GUI;

import javax.swing.*;
import javax.swing.table.TableModel;

/**
 * Class:       Offers
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        1/2/16
 */
public interface Offers extends TableModel {

    TravelOffer fullInfo(int index);
}
