/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author nbp184
 */
public class StringLists {
    
    private static StringLists instance = null;
    
    public static void load() {
        try {
            instance = new StringLists();
            BufferedReader inFile = new BufferedReader(new FileReader("Types.txt"));
            String line = inFile.readLine();
            while(line != null) {
                instance.types.add(line);
                line = inFile.readLine();
            }
            inFile.close();
            inFile = new BufferedReader(new FileReader("Sets.txt"));
            line = inFile.readLine();
            while(line != null) {
                instance.sets.add(line);
                line = inFile.readLine();
            }
            inFile.close();
        } catch(IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void save() {
        if(instance != null) {
            try {
                if(instance.changed[0]) {
                    PrintWriter outFile = new PrintWriter(new File("Types.txt"));
                    for(String type : instance.types) {
                        outFile.println(type);
                    }
                    outFile.close();
                }
                if(instance.changed[1]) {
                    PrintWriter outFile = new PrintWriter(new File("Sets.txt"));
                    for(String set : instance.sets) {
                        outFile.println(set);
                    }
                    outFile.close();
                }
            } catch(IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
    
    private ArrayList<String> types;
    private ArrayList<String> sets;
    private boolean[] changed;
    
    private StringLists() {
        types = new ArrayList<>();
        sets = new ArrayList<>();
        changed = new boolean[]{false, false};
    }
    
}
