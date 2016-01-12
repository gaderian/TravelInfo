package controller;

import java.util.prefs.Preferences;

/**
 * Class:       Settings
 * <p>
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2016-01-12
 */
public class Settings {
    private Preferences prefs;

    /*Keys to the settings*/
    private static String UPDATE_INTERVAL = "interval";
    private static String SEARCH_PATTERN = "search";

    /*Default values if no available settings*/
    private static int UPDATE_INTERVAL_DEFAULT = 30;
    private static String SEARCH_PATTERN_DEFAULT = "";

    public Settings() {
        prefs = Preferences.userRoot().node("TravelInfo");
    }

    public void setUpdateInterval(int minutes){
        prefs.putInt(UPDATE_INTERVAL, minutes);
    }

    public void setSearchPattern(String search) {
        prefs.put(SEARCH_PATTERN, search);
    }

    public int getUpdateInterval(){
        return prefs.getInt(UPDATE_INTERVAL, UPDATE_INTERVAL_DEFAULT);
    }

    public String getSearchPattern(){
        return prefs.get(SEARCH_PATTERN, SEARCH_PATTERN_DEFAULT);
    }

}
