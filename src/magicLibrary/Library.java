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
    
    public static boolean makeLibrary(File cardFile, File deckFile) throws IOException {
        if(instance == null) {
            instance = new Library(cardFile, deckFile);
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
    
    private final ArrayList<Card> cards;
    private final ArrayList<Deck> decks;
    private String lastSearch;
    private AdvancedSearch lastSearch2;
    private int[] results;
    
    private Library() {
        cards = new ArrayList<>();
        results = null;
        decks = new ArrayList<>();
    }
    
    private Library(File cardFile, File deckFile) throws IOException {
        cards = new ArrayList<>();
        decks = new ArrayList<>();
        if(cardFile.length() > Integer.MAX_VALUE) {
            throw new ArrayIndexOutOfBoundsException(cardFile.getName() +".length() = " +cardFile.length() +" > " +Integer.MAX_VALUE +" (maximum int)");
        }
        if(deckFile.length() > Integer.MAX_VALUE) {
            throw new ArrayIndexOutOfBoundsException(deckFile.getName() +".length() = " +deckFile.length() +" > " +Integer.MAX_VALUE +" (maximum int)");
        }
        byte[] bytes = new byte[(int)cardFile.length()];
        FileInputStream fin = new FileInputStream(cardFile);
        fin.read(bytes);
        fin.close();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        int count = buffer.getInt();
        while(count > 0) {
            cards.add(new Card(buffer));
            count--;
        }
        bytes = new byte[(int)deckFile.length()];
        fin = new FileInputStream(deckFile);
        fin.read(bytes);
        fin.close();
        buffer = ByteBuffer.wrap(bytes);
        count = buffer.getInt();
        while(count > 0) {
            decks.add(new Deck(buffer, cards));
            count--;
        }
        results = null;
    }
    
    public void save(File cardFile, File deckFile) throws IOException {
        int size = 4;
        for(Card c : cards) {
            size += c.saveSize();
        }
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putInt(cards.size());
        for(Card c : cards) {
            c.save(buffer);
        }
        FileOutputStream fOut = new FileOutputStream(cardFile);
        fOut.write(buffer.array());
        fOut.close();
        size = 4;
        for(Deck d : decks) {
            size += d.saveSize();
        }
        buffer = ByteBuffer.allocate(size);
        buffer.putInt(decks.size());
        for(Deck d : decks) {
            d.save(buffer);
        }
        fOut = new FileOutputStream(deckFile);
        fOut.write(buffer.array());
        fOut.close();
    }
    
    public boolean addCard(Card c) {
        if(!cards.contains(c)) {
            cards.add(c);
            Collections.sort(cards);
            return true;
        } else {
            return false;
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
    
    Card getCardByName(String name) {
        //todo: update with better search
        for(Card c : cards) {
            if(c.name.compareToIgnoreCase(name) == 0) {
                return c;
            }
        }
        return null;
    }

    Deck[] getDeckArray() {
        Deck[] arr = new Deck[decks.size()];
        arr = decks.toArray(arr);
        return arr;
    }

    boolean addDeck(Deck deck) {
        if(!decks.contains(deck)) {
            decks.add(deck);
            Collections.sort(decks);
            return true;
        } else {
            return false;
        }
    }

    Deck getDeckByName(String name) {
        //todo: update with better search
        for(Deck d : decks) {
            if(d.name.compareToIgnoreCase(name) == 0) {
                return d;
            }
        }
        return null;
    }

    boolean hasDecks() {
        return !decks.isEmpty();
    }

    int deckCount() {
        return decks.size();
    }

    Deck getDeck(int index) {
        return decks.get(index);
    }

    int getDeckIndex(Deck deck) {
        return decks.indexOf(deck);
    }

    String getDeckListString() {
        String rv = "";
        for(Deck d : decks) {
            if(!rv.isEmpty()) {
                rv += "\n";
            }
            rv += d.name;
        }
        return rv;
    }
    
    String getDeckListString(Card card) {
        String rv = "";
        for(Deck d : decks) {
            if(d.hasCard(card)) {
                if(!rv.isEmpty()) {
                    rv += "\n";
                }
                rv += d.name;
            }
        }
        return rv;
    }
    
}
