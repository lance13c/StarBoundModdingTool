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

/**
 * Created by Dominic on 5/7/2014.
 */
public class MainPageView extends JFrame{
    private MainPageView view;
    private JPanel mainPanel,upperPanel,leftPanel, upperRightPanel, rightPanel,
        topOfRightPanel;
    //private JButton recipesB; // Recipes Button
    private JButton verifyB,unpackB; // Verify Button, Checks if the directory is valid
    private JButton[][] gridB;  // Grid of Buttons
    private JTextField dirLocTF; // Directory Location Text Field
    private JLabel verifyL, unpackL; // Verifying Directory Location Label
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
        leftPanel = new JPanel(new GridLayout(this.GRID_DIMENSION,this.GRID_DIMENSION));

        rightPanel = new JPanel(new BorderLayout());
        topOfRightPanel = new JPanel(new BorderLayout());

        verifyB = new JButton("Verify"); // Verifies if the directory is valid
        unpackB = new JButton("Unpack Assets"); // Unpacks assets folder

        dirLocTF = new JTextField(this.directoryBanner);

        unpackL = new JLabel("No Unpacked Assets");
        unpackL.setBorder((BorderFactory.createEmptyBorder(5,10,5,10)));
        verifyL = new JLabel("This is the Label");
        verifyL.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

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
                leftPanel.add(gridB[j][i]);
            }
        }
        topOfRightPanel.add(unpackB,BorderLayout.WEST);
        topOfRightPanel.add(unpackL,BorderLayout.EAST);
        rightPanel.add(topOfRightPanel,BorderLayout.NORTH);
        //rightPanel.setPreferredSize(new Dimension(model.getWidth()/2,model.getHeight()/2));
        //rightPanel.setSize(model.getWidth()/2,model.getHeight()/2);
        mainPanel.add(upperPanel,BorderLayout.NORTH);
        mainPanel.add(leftPanel,BorderLayout.WEST);
        mainPanel.add(rightPanel,BorderLayout.EAST);

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
        unpackB.addActionListener(listener);
    }

    /**
     * Creates a popup message
     * @param message - The message to be displayed
     * @param title - title of the popup
     */
    public void popUpMessage(String message, String title){
        JFrame tempF = new JFrame();
        tempF.setAlwaysOnTop(true);
        tempF.setLocationRelativeTo(null);
        tempF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(tempF,message,title,JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * The Accessor for dirLocTF
     * @return - The directory location TextView
     */
    public JTextField getDirLocTF(){
        return this.dirLocTF;
    }
    public JLabel getVerifyLabel() {return this.verifyL;}
    public JLabel getUnpackedLabel() {return this.unpackL;}
}
