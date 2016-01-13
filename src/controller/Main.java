package controller;

import gui.MainWindow;
import model.DataCollector;
import model.DataKeeper;
import org.xml.sax.SAXException;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class:       Main
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class Main {

    //TODO class comments for all classes
    public static void main(String[] args) {
        DataCollector collector = new DataCollector();

        //final DataKeeper keeper = new DataKeeper();
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
