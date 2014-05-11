/**
 * File:
 *  $Id$
 *
 * Revisions:
 *  $Log$
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Dominic on 5/7/2014.
 */
public class MainPageView extends JFrame implements Observer{
    private MainPageView view;
    private JPanel mainPanel,upperPanel,centerPanel, upperRightPanel;
    //private JButton recipesB; // Recipes Button
    private JButton verifyB; // Verify Button, Checks if the directory is valid
    private JButton[][] gridB;  // Grid of Buttons
    private JTextField dirLocTF; // Directory Location Text Field
    private JLabel verifyL; // Verifying Directory Location Label
    private final String TITLE = "StarBoundModdingTool";
    private final int GRID_DIMENSION = 4;
    private final String directoryBanner = "Place StarBound Directory Here";
    private String[][] gridButtonNames;

    /**
     * The Constructor for the MainPageView
     * @param model - the MainPageModel
     */
    public MainPageView(MainPageModel model) {
        this.setSize(model.getWidth(),model.getHeight());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(this.TITLE);
        mainPanel = new JPanel(new BorderLayout());
        upperPanel = new JPanel(new BorderLayout());
        upperRightPanel = new JPanel((new BorderLayout()));
        centerPanel = new JPanel(new GridLayout(this.GRID_DIMENSION,this.GRID_DIMENSION));
        //recipesB = new JButton("Recipes");

        verifyB = new JButton("Verify");
        //enterB.setName("Enter");
        dirLocTF = new JTextField(this.directoryBanner);
        verifyL = new JLabel("This is the Label");
        gridB = new JButton[this.GRID_DIMENSION][this.GRID_DIMENSION];

        // Initialized Array of Buttons
        for(int i=0;i<this.GRID_DIMENSION;i++){
            for(int j=0;j<this.GRID_DIMENSION;j++){
                gridB[j][i] = new JButton();
            }
        }
        gridB[0][0].setText("Recipes");

        upperRightPanel.add(verifyL,BorderLayout.EAST);
        upperRightPanel.add(verifyB);
        upperPanel.add(upperRightPanel,BorderLayout.EAST);
        upperPanel.add(dirLocTF);
        for(int i=0;i<this.GRID_DIMENSION;i++){
            for(int j=0;j<this.GRID_DIMENSION;j++){
                centerPanel.add(gridB[j][i]);
            }
        }
        //centerPanel.setPreferredSize(new Dimension(model.getWidth()/2,model.getHeight()/2));
        //centerPanel.setSize(model.getWidth()/2,model.getHeight()/2);
        mainPanel.add(upperPanel,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);

        this.add(mainPanel);
        this.validate();

    }

    /**
     * Adds an action listener to all this view's the buttons
     * @param listener - the object that will be listening
     */
    public void addButtonListener(ActionListener listener){
        for(int i=0;i<this.GRID_DIMENSION;i++){
            for(int j=0;j<this.GRID_DIMENSION;j++){
                gridB[j][i].addActionListener(listener);
            }
        }
        verifyB.addActionListener(listener);
    }

    /**
     * The Accessor for dirLocTF
     * @return - The directory location TextView
     */
    public JTextField getDirLocTF(){
        return dirLocTF;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
