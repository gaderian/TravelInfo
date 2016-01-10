import model.DataCollector;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Class:       DataCollectorTest
 * <p/>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-29
 */
public class DataCollectorTest {

    @Test
    public void shouldAccept() throws Exception {
        DataCollector collector = new DataCollector();
        collector.setSource(new URL("http://www8.cs.umu.se/kurser/5DV135/HT14/labbar/lab2/exempeldata/131209_09:25:00.xml"));
        assertEquals(1000, collector.collectData().getLength());
    }

    @Test
    public void shouldThrowForUnValidXML() throws Exception {

    }
}