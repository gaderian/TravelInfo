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
public class Updater extends Thread {
    private Controller controller;
    private DataCollector collector;
    private String destination;

    public Updater(Controller controller, DataCollector collector, String destination) {
        this.controller = controller;
        this.collector = collector;
        this.destination = destination;
    }

    @Override
    public void run() {

        try {
            final NodeList nlist = collector.collectData(destination);

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
