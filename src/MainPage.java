/**
 * File:
 *  $Id$
 *
 * Revisions:
 *  $Log$
 */
/**
 * Created by Dominic Cicilio on 5/1/2014.
*/


public class MainPage {
    private MainPageView view;
    private MainPageModel model;
    private MainPageController controller;

        //Controls Everything
    public static void main(String[] arg){
        MainPage main = new MainPage();
        main.model = new MainPageModel();
        main.view = new MainPageView(main.model);
        main.controller = new MainPageController(main.view,main.model);

        main.view.setVisible(true);
        main.controller.init();
    }

    /**
     * The Constructor for MainPage
     */
    public MainPage() {
    }
}
