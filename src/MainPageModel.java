/**
 * File:
 *  $Id$
 *
 * Revisions:
 *  $Log$
 */

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Dominic Cicilio on 5/7/2014.
 */
public class MainPageModel implements Observable{
    private int dim = 5; // The Dimension of the page
    private int height = 200;
    private int width = 400;
    private File directory;
    private boolean validDirectory;
    private final ArrayList<String> validFiles = new ArrayList<String>();

    /**
     * The Constructor for MainPageModel
     */
    public MainPageModel() {
        this.validDirectory = false; // Need to replace with saved data later on
        validFiles.add("assets");
        validFiles.add("starbound.config");
        validFiles.add("mods");
        validFiles.add("player");
        validFiles.add("win32");
    }

    /**
     * The height accessor
     * @return - The height of the main View
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * The width accessor
     * @return - The width of the main View
     */
    public int getWidth(){
        return this.width;
    }


    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }

    public boolean validateDir(String directoryName){
        try {
            this.directory = new File(directoryName);
            ArrayList<String> matched = new ArrayList<String>(); // Files that match valid files are put here
            File[] subDir;  // Sub Directory
            subDir = this.directory.listFiles();
            for(int i=0;i<subDir.length;i++){
                for (String s:this.validFiles){
                    if (s.equals(subDir[i].getName())){
                        System.out.println("String: " +s);
                        matched.add(s);
                    }
                }
                System.out.println(subDir[i].toString());
            }
            // Testing only
            System.out.println();
            Collections.sort(matched);
            Collections.sort(this.validFiles);
            /*
            for (String m:matched) {
                System.out.println("Matched : " + m);
            }

            for (String s:this.validFiles){
                System.out.println("String: " +s);
            }
            */
            if (this.validFiles.equals(matched)){
                System.out.println("Valid Directory!!!");
                validDirectory = true;
                return true;
            }

        }catch (NullPointerException ex){
            System.err.println("Error: Not Valid StarBound Directory");
            System.err.println("Cause: " + ex.getCause());
            System.err.println("Message: " + ex.getMessage());
        }



        return false;
    }
}
