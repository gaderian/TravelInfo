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
        CardLayout cl = new CardLayout();
        frame.setLayout(cl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel offerList = buildOfferListPanel(offers);

        frame.add(offerList);
        frame.setSize(400, 750);
        frame.pack();
        frame.setVisible(true);

    }

    private JPanel buildOfferListPanel(TableModel tm) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTable table = new JTable(tm);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println("row: " + row + "\ncol: " + col + "\n");
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    }

}
