package controller;

import gui.MainWindow;
import model.DataCollector;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class:       Main
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */

/**
 * Starts the application.
 */
public class Main {

    //TODO class comments for all classes
    public static void main(String[] args) {
        DataCollector collector = new DataCollector();

        try {
            collector.setSource(new URL("http://www.fritidsresor.se/Blandade-" +
                    "Sidor/feeds/tradera/"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow(new Controller(collector));
            }
        });
    }
}
