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
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    
    public static void makeLibrary(File cardFile, File deckFile) throws IOException {
        instance = new Library(cardFile, deckFile);
    }
    
    public static Library getInstance() {
        if(instance == null) {
            instance = new Library();
        }
        return instance;
    }
    
    public static Differences getDifferences(File cardFile, File deckFile) throws IOException {
        Library other = new Library(cardFile, deckFile);
        Differences diff = new Differences();
        Modification m;
        for(Card local : instance.cards) {
            if(other.cards.contains(local)) {
                m = new Modification(local.name);
                local.listDifferences(other.getCardByName(local.name), m.differences);
                if(!m.differences.isEmpty()) {
                    diff.modifiedC.add(m);
                }
            } else {
                diff.localC.add(local.name);
            }
        }
        for(Card drive : other.cards) {
            if(!instance.cards.contains(drive)) {
                diff.driveC.add(drive.name);
            }
        }
        for(Deck local : instance.decks) {
            if(other.decks.contains(local)) {
                m = new Modification(local.name);
                local.listDifferences(other.getDeckByName(local.name), m.differences);
                if(!m.differences.isEmpty()) {
                    diff.modifiedD.add(m);
                }
            } else {
                diff.localD.add(local.name);
            }
        }
        for(Deck drive : other.decks) {
            if(!instance.decks.contains(drive)) {
                diff.driveD.add(drive.name);
            }
        }
        return diff;
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
        for(Deck d : decks) {
            if(d.hasCard(card)) {
                d.removeCard(card);
            }
        }
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
    
    public static class Differences {
        
        private static final SimpleDateFormat dateF = new SimpleDateFormat("MMM d, yyyy h:mm:ss aa");
        
        long timestamp;
        ArrayList<String> driveC;
        ArrayList<String> localC;
        ArrayList<Modification> modifiedC;
        ArrayList<String> driveD;
        ArrayList<String> localD;
        ArrayList<Modification> modifiedD;
        
        public Differences() {
            timestamp = Calendar.getInstance().getTimeInMillis();
            driveC = new ArrayList<>();
            localC = new ArrayList<>();
            modifiedC = new ArrayList<>();
            driveD = new ArrayList<>();
            localD = new ArrayList<>();
            modifiedD = new ArrayList<>();
        }
        
        public void save(File file) throws IOException {
            PrintWriter fOut = new PrintWriter(file);
            fOut.println("Magic Library");
            fOut.println("Difference Report");
            fOut.println(dateF.format(new java.util.Date(timestamp)));
            fOut.println();
            fOut.println("Cards");
            fOut.println("-----");
            fOut.println("Drive Only:");
            for(String s : driveC) {
                fOut.println("\t" +s);
            }
            fOut.println();
            fOut.println("Local Only:");
            for(String s : localC) {
                fOut.println("\t" +s);
            }
            fOut.println();
            fOut.println("Modified:");
            for(Modification m : modifiedC) {
                fOut.println("\t" +m.name);
                for(String s : m.differences) {
                    fOut.println("\t\t" +s);
                }
            }
            fOut.println();
            fOut.println("Decks");
            fOut.println("-----");
            fOut.println("Drive Only:");
            for(String s : driveD) {
                fOut.println("\t" +s);
            }
            fOut.println();
            fOut.println("Local Only:");
            for(String s : localD) {
                fOut.println("\t" +s);
            }
            fOut.println();
            fOut.println("Modified:");
            for(Modification m : modifiedD) {
                fOut.println("\t" +m.name);
                for(String s : m.differences) {
                    fOut.println("\t\t" +s);
                }
            }
            fOut.close();
        }
        
        @Override
        public String toString() {
            String rv = "";
            rv += "Magic Library\n";
            rv += "Difference Report\n";
            rv += dateF.format(new java.util.Date(timestamp))+"\n";
            rv += "\n";
            rv += "Cards\n";
            rv += "-----\n";
            rv += "Drive Only:\n";
            for(String s : driveC) {
                rv += "\t" +s +"\n";
            }
            rv += "\n";
            rv += "Local Only:\n";
            for(String s : localC) {
                rv += "\t" +s +"\n";
            }
            rv += "\n";
            rv += "Modified:\n";
            for(Modification m : modifiedC) {
                rv += "\t" +m.name +"\n";
                for(String s : m.differences) {
                    rv += "\t\t" +s +"\n";
                }
            }
            rv += "\n";
            rv += "Decks\n";
            rv += "-----\n";
            rv += "Drive Only:\n";
            for(String s : driveD) {
                rv += "\t" +s +"\n";
            }
            rv += "\n";
            rv += "Local Only:\n";
            for(String s : localD) {
                rv += "\t" +s +"\n";
            }
            rv += "\n";
            rv += "Modified:\n";
            for(Modification m : modifiedD) {
                rv += "\t" +m.name +"\n";
                for(String s : m.differences) {
                    rv += "\t\t" +s +"\n";
                }
            }
            return rv;
        }
        
    }
    
    private static class Modification {
        String name;
        ArrayList<String> differences;
        
        public Modification(String name) {
            this.name = name;
            differences = new ArrayList<>();
        }
    }
    
}
