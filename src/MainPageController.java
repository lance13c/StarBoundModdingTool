/**
 * File:
 *  $Id$
 *
 * Revisions:
 *  $Log$
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dominic Cicilio on 5/7/2014.
 */
public class MainPageController{
    private MainPageView view;
    private MainPageModel model;

    /**
     * The Constructor for the MainPageController
     * @param view - The MainPageView
     * @param model - The MainPageModel
     */
    public MainPageController(MainPageView view, MainPageModel model) {
        this.model = model;
        this.view = view;
        this.view.addButtonListener(new ButtonListener());
    }

    /**
     * Initializes the data when opening the program
     */
    public void init(){
        this.model.createSaveFile();
        if (this.model.readSaveData()){
            view.getDirLocTF().setText(this.model.getDir());
            view.getVerifyLabel().setText("Valid Directory");
            this.view.validate();
        }

    }

    /**
     * A nested class that is the ActionListener for the view
     */
    class ButtonListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Action Command: " + e.getActionCommand());
            if (e.getActionCommand().equals("Verify")){  // Verify Button
                System.out.println("Verify Command: " + view.getDirLocTF().getText());
                if (model.validateDir(view.getDirLocTF().getText()) == true){
                    view.getVerifyLabel().setText("Valid Directory");
                    model.writeToSaveFile("Dir",view.getDirLocTF().getText());
                    view.validate();
                }
            }
        }
    }
}
