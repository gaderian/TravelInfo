package gui;

import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 * Class:       Offers
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2016-01-02
 */

/**
 * Interface used by the gui for controlling the application. The gui uses this
 * for retrieving all the information needed and for managing when updates
 * should be made.
 */
public abstract class Offers extends AbstractTableModel {

    /**
     * Returns an instance of TravelOffer with all the information provided
     * about a specific offer.
     *
     * @param index the index of the wanted offer
     * @return a fully instantiated object of TravelOffer
     */
    abstract public TravelOffer fullInfo(int index);

    /**
     * Called to force an update of the information provided.
     *
     * @param c a component which should be repainted after an update.
     */
    abstract public void updateOffers(Component c);

    /**
     * Search the offers for offers with certain countries as destinations. The
     * search can be made on several destinations at one time, each destination
     * should be separated by a comma ",". The search is not case sensitive.
     *
     * @param c a component which should be repainted after a search
     * @param destination a string specifying the search.
     */
    abstract public void searchOffers(Component c, String destination);

    /**
     * Sets the desired interval between automated updates. The interval is
     * specified in minutes.
     *
     * @param minutes the minutes between automated updates.
     */
    abstract public void setUpdateInterval(int minutes);

    /**
     * Returns the interval between updates.
     *
     * @return the interval in minutes.
     */
    abstract public int getUpdateInterval();

    /**
     * Returns the latest search.
     *
     * @return the latest search.
     */
    abstract public String getSearch();
}
