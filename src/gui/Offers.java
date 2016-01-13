package gui;

import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 * Class:       Offers
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        1/2/16
 */
public abstract class Offers extends AbstractTableModel {

    abstract public gui.TravelOffer fullInfo(int index);

    abstract public void updateOffers(Component c);

    abstract public void searchOffers(Component c, String destination);

    abstract public void setUpdateInterval(int minutes);

    abstract public int getUpdateInterval();

    abstract public String getSearch();
}
