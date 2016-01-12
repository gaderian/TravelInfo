package controller;

import gui.Offers;
import gui.TravelOffer;
import model.DataCollector;
import model.DataKeeper;
import model.Offer;
import org.w3c.dom.NodeList;

import javax.swing.event.TableModelListener;
import java.awt.*;
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
public class Controller implements Offers {
    private String[] columnNames;
    private Class<?>[] columnClasses;
    private DataKeeper keeper;
    private DataCollector collector;
    private Component component;
    private Settings settings;
    private Timer timer;


    public Controller(DataCollector collector){
        this.collector = collector;
        keeper = new DataKeeper();
        settings = new Settings();
        timer = new Timer();
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
    @Override
    public TravelOffer fullInfo(int index) {
        Offer offer = keeper.getOffer(index);
        return OfferAdapter.generateTravelOffer(offer);
    }

    @Override
    public void updateOffers(Component c) {
        component = c;
        new Updater(this, collector, settings.getSearchPattern()).start();
    }

    protected void update(NodeList nlist) {
        keeper.setNodeList(nlist);
        component.repaint();
        System.out.println("updated");
    }

    @Override
    public void searchOffers(Component c, String destination) {
        settings.setSearchPattern(destination);
        updateOffers(c);
    }

    @Override
    public void setUpdateInterval(int minutes) {
        settings.setUpdateInterval(minutes);
        timer.cancel();
        timer.purge();
        setTimer();
    }

    private void setTimer() {
        int delay = settings.getUpdateInterval();
        delay = delay*60*1000;
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

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    private class UpdateTimerTask extends TimerTask {

        private final Controller controller;

        public UpdateTimerTask(Controller c){
            controller = c;
        }

        @Override
        public void run() {
            controller.updateOffers(component);
        }
    }
}
