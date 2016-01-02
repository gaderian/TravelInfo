import GUI.MainWindow;
import org.xml.sax.SAXException;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

/**
 * Class:       Main
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class Main {

    public static void main(String[] args) {
        DataCollector collector = new DataCollector();

        final TheKeeper keeper = new TheKeeper();
        try {
            collector.setSource(new URL("http://www8.cs.umu.se/kurser/5DV135/" +
                    "HT14/labbar/lab2/exempeldata/131209_09:25:00.xml"));
            keeper.setNodeList(collector.update());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow(keeper);
            }
        });
    }
}
