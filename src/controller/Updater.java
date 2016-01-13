package controller;

import model.DataCollector;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.io.IOException;

/**
 * Class:       Updater
 * <p>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2016-01-11
 */

/**
 * Will make an update of the information contained in the application on a
 * separate thread.
 */
public class Updater extends Thread {
    private Controller controller;
    private DataCollector collector;
    private String destination;

    /**
     * Creates a new instance of Updater. A new instance will be needed for
     * every update.
     *
     * @param controller the {@link Controller} of the application
     * @param collector the DataCollector which should collect the data
     * @param destination a string containing the desired destinations
     */
    public Updater(Controller controller, DataCollector collector, String destination) {
        this.controller = controller;
        this.collector = collector;
        this.destination = destination;
    }

    /**
     * Will make an update of the data.
     */
    @Override
    public void run() {

        try {
            final NodeList nlist = collector.collectData(destination);

            /* the EDT will make the final step of updating to avoid thread
            problems*/
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    controller.update(nlist);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
