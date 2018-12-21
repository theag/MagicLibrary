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
    private final ArrayList<String> decks;
    private String lastSearch;
    private AdvancedSearch lastSearch2;
    private int[] results;
    
    private Library() {
        cards = new ArrayList<>();
        file = null;
        results = null;
        decks = new ArrayList<>();
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
            addDecks(cards.get(cards.size() - 1));
            count--;
        }
        count = buffer.getInt();
        while(count > 0) {
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
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putInt(cards.size());
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
            addDecks(c);
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
    }

    void clearSearch() {
        results = null;
        lastSearch = null;
        lastSearch2 = null;
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
        lastSearch2 = null;
    }
    
    void doSearch(AdvancedSearch search) {
        int[] temp = new int[cards.size()];
        int count = 0;
        for(int i = 0; i < cards.size(); i++) {
            if(search.matches(cards.get(i))) {
                temp[count++] = i;
            }
        }
        results = new int[count];
        System.arraycopy(temp, 0, results, 0, count);
        lastSearch = null;
        lastSearch2 = search;
    }
    
    void redoSesarch() {
        if(lastSearch != null) {
            doSearch(lastSearch);
        } else if(lastSearch2 != null) {
            doSearch(lastSearch2);
        }
    }

    int getCardIndex(Card card) {
        return cards.indexOf(card);
    }
    
    public void updateDecks() {
        decks.clear();
        for(Card c : cards) {
            for(String d : c.decks) {
                if(!decks.contains(d)) {
                    decks.add(d);
                }
            }
        }
        Collections.sort(decks);
    }

    private void addDecks(Card c) {
        for(String d : c.decks) {
            if(!decks.contains(d)) {
                decks.add(d);
            }
        }
        Collections.sort(decks);
    }

    public String getDeckListString() {
        String rv = "";
        for(String d : decks) {
            if(!rv.isEmpty()) {
                rv += "\n";
            }
            rv += d;
        }
        return rv;
    }

    String[] getDeckVector() {
        String[] rv = new String[decks.size()];
        rv = decks.toArray(rv);
        return rv;
    }

    Card getCardByName(String string) {
        //todo: update with better search
        for(Card c : cards) {
            if(c.name.compareToIgnoreCase(string) == 0) {
                return c;
            }
        }
        return null;
    }
    
}
