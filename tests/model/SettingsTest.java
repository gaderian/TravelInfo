package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class:       SettingsTest
 * <p>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2016-01-14
 */
public class SettingsTest {

    @Test
    public void shouldReturnSetSearchPattern() throws Exception {
        Settings settings = new Settings();
        String pattern = "Uppland";
        settings.setSearchPattern(pattern);
        assertEquals(pattern, settings.getSearchPattern());
    }

    @Test
    public void shouldReturnSetInterval() throws Exception {
        Settings settings = new Settings();
        int interval = 30;
        settings.setUpdateInterval(interval);
        assertEquals(interval, settings.getUpdateInterval());
    }

}