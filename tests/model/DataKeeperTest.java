package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class:       DataKeeperTest
 * <p>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2016-01-14
 */
public class DataKeeperTest {

    @Test
    public void shouldReturnThousandOffers() throws Exception {
        DataCollector collector = new DataCollector();
        DataKeeper keeper = new DataKeeper();
        collector.setSource(getClass().getResource("file.xml").toURI().toURL());

        keeper.setNodeList(collector.collectData(""));
        assertEquals(1000, keeper.getNrOfOffers());
    }

    @Test
    public void shouldGiveCorrectPreviewInformation() throws Exception {
        DataCollector collector = new DataCollector();
        DataKeeper keeper = new DataKeeper();
        collector.setSource(getClass().getResource("test.xml").toURI().toURL());

        keeper.setNodeList(collector.collectData(""));
        assertEquals("Gran Canaria", keeper.getValueAt(0, 0));
        assertEquals("2014-01-12", keeper.getValueAt(0, 1));
        assertEquals(2498, keeper.getValueAt(0, 2));

    }

    @Test
    public void shouldGiveInitializedOffer() throws Exception {
        DataCollector collector = new DataCollector();
        DataKeeper keeper = new DataKeeper();
        collector.setSource(getClass().getResource("test.xml").toURI().toURL());

        keeper.setNodeList(collector.collectData(""));
        Offer offer = keeper.getOffer(0);
        assertNotNull(offer);
    }
}