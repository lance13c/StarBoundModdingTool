/**
 * File:
 *  $Id$
 *
 * Revisions:
 *  $Log$
 */

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Dominic Cicilio on 5/7/2014.
 */
public class MainPageModel{
    private int dim = 5; // The Dimension of the page
    private int height = 300;
    private int width = 800;
    private File directory;
    //private String dir = "";
    private boolean validDirectory;
    private final ArrayList<String> validFiles = new ArrayList<String>();
    private final String SAVE_FILE_NAME = "SaveInfo.save";
    private String initSaveData = "";

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
        initSaveData = String.format("%s=\n%s=\n%s=","Dir","Mod1","Mod2");
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

    /**
     * Checks whether the directory is valid
     * @param directoryName - the directory as a string
     * @return - true if it is valid, otherwise false
     */
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

    /**
     * Finds the saved directory if one exists.
     * Later on, make it so this reads all save references and prints out which
     * ones were read in
     * @return - true if the data was received
     */
    public boolean readSaveData(){
        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new FileReader(this.SAVE_FILE_NAME));

            String tempLine = buff.readLine();
            //if (tempLine.equals(null)){break;}
            if (!tempLine.equals(null)) {
                String[] tempArray = tempLine.split("=");
                //for (String s : tempArray) {
                if (tempArray[0].equals("Dir")){
                    if(tempArray.length > 1){
                        if (this.validateDir(tempArray[1])){
                            return true;
                        }

                    }
                }
                    //System.out.println(s);
                //}
            }

            System.out.println("File Found");
        }catch (FileNotFoundException e) {
            System.err.println("SaveFile Not Found");
        }catch (IOException exp){
            System.err.println("Line was not available");
        }finally {
            try{
                if (buff != null){
                    buff.close();
                }
            }catch (IOException ex){
                System.err.println("File Buffer never created");
            }
        }

        //File saveInfo
        return false;
    }

    /**
     * Grabs data from the file and puts it into a string. Divides that string up by each new line.
     * Going through each line it checks if the dataRef equals the first element on the new line and
     * checks if the length is greater then 1. If so, it will write to the SaveInfo File.
     * @param dataRef - String before = in SaveInfo specifying where to save the data
     * @param data - The data to save
     */
    public void writeToSaveFile(String dataRef,String data){
        //BufferedWriter buffW = null;
        //BufferedReader buffR = null;
        //RandomAccessFile raf = null;
        Scanner scan = null;
        FileWriter writer = null;
        String fileData;
        String[] dataArray;
        String updatedFileData = "";
        try{
            String[] subDataArray;
            scan = new Scanner(new File(this.SAVE_FILE_NAME));
            fileData = scan.useDelimiter("\\A").next();
            dataArray = fileData.split("\n");
            for (String s: dataArray){
                subDataArray = s.split("=");
                if (dataRef.equals(subDataArray[0])) {
                        updatedFileData += dataRef + "=" + data;
                }else{
                    for (int i=0;i<subDataArray.length;i++){
                        updatedFileData += subDataArray[i] + "=";
                    }
                }
                updatedFileData += "\n";  // Might be added an additional new line every time
            }
        }catch (FileNotFoundException ex) {
            System.err.println("Writing File Not Found");
        }finally {
            if (scan != null) {
                scan.close();
            }
            System.out.println(updatedFileData);
        }

        try{
            writer = new FileWriter(new File(this.SAVE_FILE_NAME),false);
            writer.write(updatedFileData);
            writer.flush();
        }catch (IOException e){
            System.err.println("Error Writing to File");
        }finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }catch (IOException ex){
                System.err.println("Error Closing SaveFile");
            }
        }

    }

    /**
     * Creates the File that contains all the saved information
     */
    public void createSaveFile(){
        FileWriter writer = null;
        if (!new File("SaveInfo.save").exists()) {
            try {
                writer = new FileWriter(new File("SaveInfo.save"), false);
                writer.write(initSaveData);
                writer.flush();
            } catch (IOException e) {
                e.getStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    System.err.println("Error Closing SaveFile");
                }
            }
        }
    }

    public String getDir(){
        return this.directory.getPath();
    }
}
