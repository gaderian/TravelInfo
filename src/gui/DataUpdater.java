package gui;

import javax.swing.*;

/**
 * Created by erik on 2016-01-10.
 */
public class DataUpdater extends SwingWorker<String, Object> {
    private Offers offers;

    public DataUpdater(Offers offers){
        this.offers = offers;
    }
    @Override
    protected String doInBackground() throws Exception {
        offers.update();
        return "done";
    }

    @Override
    protected void done() {
        offers.completeUpdate();
        //TODO need to repaint the table after an update is made
    }
}
