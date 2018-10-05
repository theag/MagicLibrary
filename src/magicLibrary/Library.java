/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author nbp184
 */
public class Library implements Iterable<Card> {
    
    public static final char NULL = (char)0;
    public static final char ETX = (char)3;
    
    private static Library instance = null;
    
    public static boolean makeLibrary(File file) throws IOException {
        if(instance == null) {
            instance = new Library(file);
            return true;
        } else {
            return false;
        }
    }
    
    public static Library getInstance() {
        if(instance == null) {
            instance = new Library();
        }
        return instance;
    }
    
    private File file;
    private final ArrayList<Card> cards;
    private int[] results;
    
    private Library() {
        cards = new ArrayList<>();
        file = null;
        results = null;
    }
    
    private Library(File file) throws IOException {
        cards = new ArrayList<>();
        if(file.length() > Integer.MAX_VALUE) {
            throw new ArrayIndexOutOfBoundsException(file.getName() +".length() = " +file.length() +" > " +Integer.MAX_VALUE +" (maximum int)");
        }
        byte[] bytes = new byte[(int)file.length()];
        FileInputStream fin = new FileInputStream(file);
        fin.read(bytes);
        fin.close();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        while(buffer.hasRemaining()) {
            cards.add(new Card(buffer));
        }
        this.file = file;
        results = null;
    }
    
    public void save() throws IOException {
        if(file != null) {
            save(file);
        } else {
            throw new NullPointerException("No File");
        }
    }
    
    public void save(File file) throws IOException {
        int size = 0;
        for(Card c : cards) {
            size += c.saveSize();
        }
        ByteBuffer buffer = ByteBuffer.allocate(size);
        for(Card c : cards) {
            c.save(buffer);
        }
        FileOutputStream fOut = new FileOutputStream(file);
        fOut.write(buffer.array());
        fOut.close();
    }
    
    public void addCard(Card c) {
        if(!cards.contains(c)) {
            cards.add(c);
            Collections.sort(cards);
        }
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    int resultSize() {
        if(results == null) {
            return cards.size();
        } else {
            return results.length;
        }
    }

    Card resultAt(int index) {
        if(results == null) {
            return cards.get(index);
        } else {
            return cards.get(results[index]);
        }
    }
    
    
    
}
