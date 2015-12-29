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

    public MainWindow(TableModel tm) {
        frame = new JFrame("TravelInfo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel centerPanel = buildCenterPanel(tm);

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
