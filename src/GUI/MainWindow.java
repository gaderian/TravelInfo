package GUI;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Class:       MainWindow
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class MainWindow {
    private JFrame frame;

    public MainWindow(Offers offers) {
        frame = new JFrame("TravelInfo");
        //TODO make card layout, one card with table and one with more info
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel centerPanel = buildCenterPanel(offers);

        frame.add(centerPanel);
        frame.setSize(400, 750);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel buildCenterPanel(TableModel tm) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTable table = new JTable(tm);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    }

}
