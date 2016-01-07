package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class:       MainWindow
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class MainWindow {
    private JFrame frame;
    private JPanel fullInfoCard;
    private JPanel window;
    private CardLayout cl;
    private Offers offers;
    private JTextArea fullInfo;
    private ImageIcon hotelImage;

    public MainWindow(Offers offersIn) {
        offers = offersIn;
        frame = new JFrame("TravelInfo");
        //TODO make card layout, one card with table and one with more info
        cl = new CardLayout();
        frame.setLayout(cl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel offerListCard = buildOfferListPanel(offers);
        fullInfoCard = buildFullInfoPanel();

        window = new JPanel();
        window.setLayout(cl);
        window.add("FullInfo", fullInfoCard);
        window.add("Offers", offerListCard);
        cl.show(window, "Offers");

        frame.add(BorderLayout.CENTER, window);
        frame.setSize(600, 550);
        //frame.pack();
        frame.setVisible(true);

    }

    private JPanel buildFullInfoPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        fullInfo = new JTextArea();

        hotelImage = new ImageIcon();
        JLabel label = new JLabel(hotelImage);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(window, "Offers");
            }
        });

        panel.add(BorderLayout.CENTER, fullInfo);
        panel.add(BorderLayout.NORTH, label);
        panel.add(BorderLayout.SOUTH, backButton);
        return panel;
    }

    private JPanel buildOfferListPanel(TableModel tm) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JTable table = new JTable(tm);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    TravelOffer offer = offers.fullInfo(row);
                    updateFullInfoCard(offer);
                    cl.show(window, "FullInfo");
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    }

    private void updateFullInfoCard(TravelOffer offer) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(offer.getHotelImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        hotelImage.setImage(image.getScaledInstance(250, 250, Image.SCALE_DEFAULT));

        String fullInfoText = "Campaign:\t\t " + offer.getCampaign();

        fullInfoText += "\n\nDeparts from:\t\t " + offer.getDepartureFrom();
        fullInfoText += "\nDeparture date:\t " + offer.getDepartureDate();
        fullInfoText += "\nDestination:\t\t " + offer.getDestinationLand() + ", " + offer.getDestinationCity();
        fullInfoText += "\nJourney Length:\t " + offer.getDaysAway() + " days";

        fullInfoText += "\n\nOriginal Price:\t\t " + offer.getOriginalPrice() + " kr";
        fullInfoText += "\nYour price:\t\t " + offer.getCurrentPrice() + " kr";

        fullInfoText += "\n\nHotel:\t\t " + offer.getHotelName();
        fullInfoText += "\nRating:\t\t " + offer.getHotelRating() + "/5";
        fullInfoText += "\nRoom:\t\t " + offer.getRoomDescription();

        fullInfo.setText(fullInfoText);
    }

}


