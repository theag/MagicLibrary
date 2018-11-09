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
    private String lastSearch;
    private int[] results;
    
    private final ArrayList<Deck> decks;
    
    private Library() {
        cards = new ArrayList<>();
        decks = new ArrayList<>();
        file = null;
        results = null;
    }
    
    private Library(File file) throws IOException {
        cards = new ArrayList<>();
        decks = new ArrayList<>();
        if(file.length() > Integer.MAX_VALUE) {
            throw new ArrayIndexOutOfBoundsException(file.getName() +".length() = " +file.length() +" > " +Integer.MAX_VALUE +" (maximum int)");
        }
        byte[] bytes = new byte[(int)file.length()];
        FileInputStream fin = new FileInputStream(file);
        fin.read(bytes);
        fin.close();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        int count = buffer.getInt();
        while(count > 0) {
            cards.add(new Card(buffer));
            count--;
        }
        count = buffer.getInt();
        while(count > 0) {
            decks.add(new Deck(buffer, cards));
            count--;
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
        int size = 8;
        for(Card c : cards) {
            size += c.saveSize();
        }
        for(Deck d : decks) {
            size += d.saveSize();
        }
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putInt(cards.size());
        for(Card c : cards) {
            c.save(buffer);
        }
        buffer.putInt(decks.size());
        for(Deck d : decks) {
            d.save(buffer);
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

    void deleteCard(Card card) {
        cards.remove(card);
        //todo: remove from decks
    }

    void clearSearch() {
        results = null;
        lastSearch = null;
    }

    void doSearch(String searchStr) {
        int[] temp = new int[cards.size()];
        int count = 0;
        for(int i = 0; i < cards.size(); i++) {
            if(cards.get(i).name.toLowerCase().contains(searchStr)) {
                temp[count++] = i;
            }
        }
        results = new int[count];
        System.arraycopy(temp, 0, results, 0, count);
        lastSearch = searchStr;
    }
    
    void redoSesarch() {
        if(lastSearch != null) {
            doSearch(lastSearch);
        }
    }

    int getCardIndex(Card card) {
        return cards.indexOf(card);
    }

    void addDeck(String value) {
        decks.add(new Deck(value, "default"));
        Collections.sort(decks);
    }
    
    
    
}
