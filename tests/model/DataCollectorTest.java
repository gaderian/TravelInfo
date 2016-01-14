package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class:       DataCollectorTest
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class DataCollectorTest {
    //TODO write more tests

    @Test
    public void shouldGetAThousandOffers() throws Exception {
        DataCollector collector = new DataCollector();
        collector.setSource(getClass().getResource("file.xml").toURI().toURL());

        assertEquals(1000, collector.collectData("").getLength());
    }

}