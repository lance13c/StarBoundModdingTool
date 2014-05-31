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
        String data = this.model.readSaveData("Dir");
        if (data != null){
            model.validateDir(data);
            view.getDirLocTF().setText(this.model.getDir());
            view.getVerifyLabel().setText("Valid Directory");
            this.view.validate();
        }
        this.model.createUnpackingFile();
        data = this.model.readSaveData("Unpacked");
        if (data != null){
            model.setUnpacked(Boolean.getBoolean(data));
            if(data.equals("true")){
                view.getUnpackedLabel().setText("Found Unpacked Assets");
            }
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
            if (e.getActionCommand().equals("Unpack Assets")){
                if (model.hasUnpacked()) {
                    view.getUnpackedLabel().setText("Found Unpacked Assets");
                }else{
                    int tempI = model.runUnpackingFile();
                    if (tempI == 1){
                        view.popUpMessage("Please wait until console window is closed(the black box)." +
                                "The will take several minutes, please be patient.","Unpacking Assets");
                        view.getUnpackedLabel().setText("Found Unpacked Assets");
                    }else if(tempI == 2){
                        view.popUpMessage("The File to Unpack the assets is not create. Please wait 5 seconds" +
                                 "and try again","Unpacking File Not Created");
                    }
                }
            }
        }
    }
}
