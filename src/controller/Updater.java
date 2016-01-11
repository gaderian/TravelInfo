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
    Controller controller;
    DataCollector collector;

    public Updater(Controller controller, DataCollector collector) {
        this.controller = controller;
        this.collector = collector;
    }

    @Override
    public void run() {

        try {
            final NodeList nlist = collector.collectData();

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
