package controller;

import gui.Offers;
import gui.TravelOffer;
import model.DataCollector;
import model.DataKeeper;
import model.Offer;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.event.TableModelListener;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

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

    public Controller(DataCollector collector){
        this.collector = collector;
        keeper = new DataKeeper();
        try {
            keeper.setNodeList(collector.collectData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        columnClasses = keeper.getPreviewFieldClasses();
        columnNames = keeper.getPreviewFieldNames();
    }

    @Override
    public TravelOffer fullInfo(int index) {
        Offer offer = keeper.getOffer(index);
        return OfferAdapter.generateTravelOffer(offer);
    }

    @Override
    public void updateOffers(Component c) {
        try {
            collector.setSource(new URL("http://www8.cs.umu.se/kurser/5DV135/HT14/labbar/lab2/exempeldata/131209_14:55:09.xml"));
            component = c;
            new Updater(this, collector).start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    protected void update(NodeList nlist) {
        keeper.setNodeList(nlist);
        component.repaint();
    }

    @Override
    public void searchOffers(String destination) {

    }

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
}
