package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Class:       MainWindow
 * <p>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */

/**
 * Used to set up the user interface of a application showing travel offers.
 */
public class MainWindow {
    private JFrame frame;
    private JPanel window;
    private CardLayout cl;
    private Offers offers;
    private JTextPane fullInfo;
    private ImageIcon hotelImage;
    private URI bookingLink;

    /**
     * Creates a window containing the application.
     *
     * @param offersIn object extending the abstract class {@link Offers}
     */
    public MainWindow(Offers offersIn) {
        offers = offersIn;
        frame = new JFrame("TravelInfo");
        cl = new CardLayout();
        frame.setLayout(cl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel offerListCard = buildOfferListPanel(offers);
        JPanel fullInfoCard = buildFullInfoPanel();
        JMenuBar menuBar = buildMenuBar();


        window = new JPanel();
        window.setLayout(cl);
        window.add("FullInfo", fullInfoCard);
        window.add("Offers", offerListCard);
        cl.show(window, "Offers");

        frame.add(BorderLayout.CENTER, window);
        frame.setJMenuBar(menuBar);
        frame.setSize(600, 550);
        frame.setVisible(true);

    }

    /**
     * Creates a menu bar for the application.
     *
     * @return the completed menu bar
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenu intervalMenu = buildIntervalMenu();
        menu.add(intervalMenu);

        JMenuItem help = buildHelpItem();
        menu.add(help);

        return menuBar;
    }

    /**
     * Creates a menu item which when clicked will show useful information to
     * the user.
     *
     * @return the finished JMenuItem
     */
    private JMenuItem buildHelpItem() {
        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //TODO fix better help message
                JOptionPane.showMessageDialog(frame, "Made by: Erik Moström");
            }
        });
        return help;
    }

    /**
     * Creates a menu in which the user can choose the interval between updates
     *
     * @return the finished JMenu
     */
    private JMenu buildIntervalMenu() {
        JMenu intervalMenu = new JMenu("Update Interval");
        final int interval30 = 30;
        final int interval60 = 60;
        final int interval90 = 90;
        final int interval120 = 120;

        ButtonGroup group = new ButtonGroup();

        /*30 min button*/
        JRadioButtonMenuItem button30 = new JRadioButtonMenuItem(interval30 + " min");
        button30.setActionCommand(String.valueOf(interval30));
        group.add(button30);
        intervalMenu.add(button30);

        /*60 min button*/
        JRadioButtonMenuItem button60 = new JRadioButtonMenuItem(interval60 + " min");
        button60.setActionCommand(String.valueOf(interval60));
        group.add(button60);
        intervalMenu.add(button60);

        /*90 min button*/
        JRadioButtonMenuItem button90 = new JRadioButtonMenuItem(interval90 + " min");
        button90.setActionCommand(String.valueOf(interval90));
        group.add(button90);
        intervalMenu.add(button90);

        /*120 min button*/
        JRadioButtonMenuItem button120 = new JRadioButtonMenuItem(interval120 + " min");
        button120.setActionCommand(String.valueOf(interval120));
        group.add(button120);
        intervalMenu.add(button120);

        switch (offers.getUpdateInterval()) {
            case interval30:
                button30.setSelected(true);
                break;
            case interval60:
                button60.setSelected(true);
                break;
            case interval90:
                button90.setSelected(true);
                break;
            case interval120:
                button120.setSelected(true);
                break;
        }

        /*Create the listener which will set the update interval*/
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offers.setUpdateInterval(Integer.parseInt(e.getActionCommand()));
            }
        };

        /*Add the listener to all radio buttons*/
        button30.addActionListener(buttonListener);
        button60.addActionListener(buttonListener);
        button90.addActionListener(buttonListener);
        button120.addActionListener(buttonListener);

        return intervalMenu;
    }

    /**
     * Creates a panel which will show the full information of a travel offer.
     *
     * @return the finished JPanel
     */
    private JPanel buildFullInfoPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        /*The place of the full information as text*/
        fullInfo = new JTextPane();

        //TODO remove or fix proper centering
        /*StyledDocument doc = fullInfo.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);*/

        /*The holder of the hotel image*/
        hotelImage = new ImageIcon();
        JLabel label = new JLabel(hotelImage);

        /*The back button*/
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(window, "Offers");
            }
        });

        /*The booking button*/
        JButton bookButton = new JButton("Book in browser");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(bookingLink);
                    } catch (Exception exception) {
                        //TODO fix exception handling
                        exception.printStackTrace();
                    }
                }
            }
        });

        /*Panel containing the two buttons*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(bookButton);

        /*Add everything*/
        panel.add(BorderLayout.CENTER, fullInfo);
        panel.add(BorderLayout.NORTH, label);
        panel.add(BorderLayout.SOUTH, buttonPanel);
        return panel;
    }

    /**
     * Creates a panel which will show the list of all offer with the
     * abbreviated information.
     *
     * @param tm the AbstractTableModel which contains all the offers
     * @return the finished JPanel
     */
    private JPanel buildOfferListPanel(AbstractTableModel tm) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        /*Building the table*/
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

        /*Building the update button*/
        JButton updateListButton = new JButton("Update");
        updateListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offers.updateOffers(window);
            }
        });

        /*Building the search bar*/

        JTextField searchField = new JTextField();
        Action searchAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offers.searchOffers(table, searchField.getText());
            }
        };
        searchField.setBorder(BorderFactory.createTitledBorder("search"));
        searchField.addActionListener(searchAction);
        searchField.setText(offers.getSearch());

        /*Adding everything to the panel*/
        panel.add(BorderLayout.CENTER, scrollPane);
        panel.add(BorderLayout.SOUTH, new JPanel().add(updateListButton));
        panel.add(BorderLayout.NORTH, searchField);

        return panel;
    }

    /**
     * Updates the information contained in the full info panel. Called when the
     * user click at an offer.
     *
     * @param offer the offer whose information should be showed.
     */
    private void updateFullInfoCard(TravelOffer offer) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(offer.getHotelImage());
        } catch (IOException e) {
            /*There is no image available*/
        }
        hotelImage.setImage(image.getScaledInstance(250, 250,
                Image.SCALE_DEFAULT));
        try {
            bookingLink = offer.getBookingLink().toURI();
        } catch (URISyntaxException e) {
            //TODO some message or such for user "Cannot book, broken link"
            e.printStackTrace();
        }

        String fullInfoText = "Campaign:\t\t " + offer.getCampaign();

        fullInfoText += "\n\nDeparts from:\t " + offer.getDepartureFrom();
        fullInfoText += "\nDeparture date:\t " + offer.getDepartureDate();
        fullInfoText += "\nDestination:\t " + offer.getDestinationLand() +
                ", " + offer.getDestinationCity();
        fullInfoText += "\nJourney Length:\t " + offer.getDaysAway() + " days";

        fullInfoText += "\n\nOriginal Price:\t " +
                offer.getOriginalPrice() + " kr";
        fullInfoText += "\nYour price:\t\t " + offer.getCurrentPrice() + " kr";

        fullInfoText += "\n\nHotel:\t\t " + offer.getHotelName();
        fullInfoText += "\nRating:\t\t " + offer.getHotelRating() + "/5";
        fullInfoText += "\nRoom:\t\t " + offer.getRoomDescription();

        fullInfo.setText(fullInfoText);
    }

}


