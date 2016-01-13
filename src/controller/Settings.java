package controller;

import java.util.prefs.Preferences;

/**
 * Class:       Settings
 *
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2016-01-12
 */

/**
 * Used for keeping track the users settings and saving them for later sessions.
 */
public class Settings {
    private Preferences prefs;

    /*Keys to the settings*/
    private static String UPDATE_INTERVAL = "interval";
    private static String SEARCH_PATTERN = "search";

    /*Default values if no available settings*/
    private static int UPDATE_INTERVAL_DEFAULT = 30;
    private static String SEARCH_PATTERN_DEFAULT = "";

    /**
     * Creates a new instance of Settings.
     */
    public Settings() {
        prefs = Preferences.userRoot().node("TravelInfo");
    }

    /**
     * Will set the update interval to the specified value.
     *
     * @param minutes the desired value for the update interval
     */
    public void setUpdateInterval(int minutes){
        prefs.putInt(UPDATE_INTERVAL, minutes);
    }

    /**
     * Will set the search pattern to the specified value.
     *
     * @param search the desired search pattern
     */
    public void setSearchPattern(String search) {
        prefs.put(SEARCH_PATTERN, search);
    }

    /**
     * Returns the saved update interval.
     *
     * @return the saved update interval
     */
    public int getUpdateInterval(){
        return prefs.getInt(UPDATE_INTERVAL, UPDATE_INTERVAL_DEFAULT);
    }

    /**
     * Returns the saved search pattern.
     *
     * @return the saved search pattern
     */
    public String getSearchPattern(){
        return prefs.get(SEARCH_PATTERN, SEARCH_PATTERN_DEFAULT);
    }

}
