/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import UI.MapsUi;
import Concretes.City;
import Concretes.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author IsaacCD
 */
public class Main {

    public static final String FILE_PATH = ".\\Data\\map1.csv";
    public static final String NAME_CITIES="CITIES";
    public static final String NAME_ROADS="ROADS";

    private static JFileChooser getChooser() {
        if (chooser==null) {
            chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setMultiSelectionEnabled(false);
            chooser.setDialogTitle("Select Data File to Load");
        }
        return chooser;
    }
    private static JFileChooser chooser;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startUi();
    }
    private static void startUi() {
        MapsUi ui = new MapsUi();
        ui.setVisible(true);
    }
    private static void runConsole() {
        
        Map map = getData();
        if (map!=null) {
            System.out.println(map);
        }
    }
    public static Map getData() {
        //String name = "C:\\Users\\IsaacCD\\OneDrive\\Documents\\Western CSU\\Classes\\2017-1\\CS 191\\CS191_Project13_Maps_idenney\\Data\\map1.csv";
        Map value = null;
        String filename;
        //filename = getFile();
        filename=Main.FILE_PATH;
        ArrayList<String> data = readFile(filename);
        value = parseData(data);
        return value;
    }
    private static String getFile() {
        String value=null;
        int val = getChooser().showOpenDialog(null);
        if (val==JFileChooser.APPROVE_OPTION) {
            File file = getChooser().getSelectedFile();
            if (file!=null&&file.exists()) {
                value=file.getPath();
            }
        }
        return value;
    }
    private static ArrayList<String> readFile(String filename) {
        ArrayList<String> values = new ArrayList();
        
        try {
            BufferedReader reader=new BufferedReader(new FileReader(filename));
            String line;
            while ((line=reader.readLine())!=null) {
                values.add(line);
            }
            reader.close();
        } catch (Exception ex) {
            
        }
        
        return values;
    }
    private static Map parseData(ArrayList<String> data) {
        Map value = new Map();
        
        if (data!=null) {
            String currentKey = "";
            for (int i = 0; i<data.size();i++) {
               String line = data.get(i);
               if (line.toUpperCase().equals(Main.NAME_CITIES)) {
                   currentKey=Main.NAME_CITIES;
               }  else if (line.toUpperCase().equals(Main.NAME_ROADS)) {
                   currentKey=Main.NAME_ROADS;
               } else {
                   String[] values = line.split(",");
                   switch(currentKey) {
                       case Main.NAME_CITIES:
                           if (values.length>=3) {
                               value.addCity(values[0],values[1],values[2]);
                           }
                           break;
                       case Main.NAME_ROADS:
                           if (values.length>=5) {
                               City.addRoad(value, values[0],values[1],values[2], values[3], values[4]);
                           }
                           break;
                   }
               }
            }
        }
        
        return value;
    }
    
}
