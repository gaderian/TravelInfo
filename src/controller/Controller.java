package controller;

import gui.Offers;
import gui.TravelOffer;
import model.DataCollector;
import model.DataKeeper;
import model.Offer;
import model.Settings;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Class:       Controller
 * <p>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2016-01-11
 */

/**
 * This class is the connector between the model and gui of the application.
 * This will take care of all communication between the different parts of the
 * application.
 */
public class Controller extends Offers {
    private String[] columnNames;
    private Class<?>[] columnClasses;
    private DataKeeper keeper;
    private DataCollector collector;
    private Settings settings;
    private Timer timer;


    /**
     * Creates a new instance of Controller.
     *
     * @param collector the collector to be used for getting the data.
     */
    public Controller(DataCollector collector){
        this.collector = collector;
        keeper = new DataKeeper();
        settings = new Settings();
        setTimer();

        try {
            keeper.setNodeList(collector.collectData(settings.getSearchPattern()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        columnClasses = keeper.getPreviewFieldClasses();
        columnNames = keeper.getPreviewFieldNames();
    }

    /* ****** Offers ****** */

    /**
     * Will get the wanted information from the model.
     *
     * @param index the index of the wanted offer
     * @return the specified offers full information
     */
    @Override
    public TravelOffer fullInfo(int index) {
        Offer offer = keeper.getOffer(index);
        return OfferAdapter.generateTravelOffer(offer);
    }

    /**
     * Will start a new thread on which the download of the latest data will be
     * made.
     */
    @Override
    public void updateOffers() {
        new Updater(this, collector, settings.getSearchPattern()).start();
    }

    /**
     * Called by the EDT after a completed update to use the new data. Also
     * tells all listeners added that it has been updated.
     *
     * @param nlist the new data in the form of a NodeList
     */
    protected void update(NodeList nlist) {
        //TODO see if it can be replaced by listeners.
        keeper.setNodeList(nlist);
        fireTableStructureChanged();
    }

    /**
     * Will run an update from the source and only taking out the offers with
     * the desired destination.
     *
     * @param destination a string specifying the search.
     */
    @Override
    public void searchOffers(String destination) {
        settings.setSearchPattern(destination);
        updateOffers();
    }

    /**
     * Changes the settings of the application and sets the timer with the new
     * interval. The timer will be reset when the interval is changed.
     *
     * @param minutes the minutes between automated updates.
     */
    @Override
    public void setUpdateInterval(int minutes) {
        settings.setUpdateInterval(minutes);
        timer.cancel();
        timer.purge();
        setTimer();
    }

    /**
     * Returns the update interval saved in the settings. It will be given in
     * minutes.
     *
     * @return the update interval saved in the settings.
     */
    @Override
    public int getUpdateInterval() {
        return settings.getUpdateInterval();
    }

    /**
     * Returns the search pattern saved in the settings.
     *
     * @return the search pattern saved in the settings
     */
    @Override
    public String getSearch() {
        return settings.getSearchPattern();
    }

    /**
     * Will reset the timer and set the interval to the value saved in the
     * settings.
     */
    private void setTimer() {
        int delay = settings.getUpdateInterval();
        delay = delay*1000*60;
        timer = new Timer();
        timer.scheduleAtFixedRate(new UpdateTimerTask(this), delay, delay);
    }

    /* ****** TableModel ****** */

    @Override
    public int getRowCount() {
        return keeper.getNrOfOffers();
    }

    @Override
    public int getColumnCount() {
        return keeper.getNrOfPreviewFields();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return keeper.getValueAt(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    /**
     * Class for using with the timer.
     */
    private class UpdateTimerTask extends TimerTask {
        private final Controller controller;

        /**
         * Creates a new instance of UpdateTimerTask
         *
         * @param c the Controller which will run the update.
         */
        public UpdateTimerTask(Controller c){
            controller = c;
        }

        @Override
        public void run() {
            controller.updateOffers();
        }
    }
}
