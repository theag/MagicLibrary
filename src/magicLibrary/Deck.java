/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author nbp184
 */
public class Deck implements Comparable<Deck>, Iterable<Deck.DeckCard> {
    
    public String name;
    private final ArrayList<DeckCard> cards;
    
    public Deck(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    Deck(ByteBuffer buffer, ArrayList<Card> cards) {
        char chr = buffer.getChar();
        name = "";
        while(chr != Library.ETX) {
            name += chr;
            chr = buffer.getChar();
        }
        int count = buffer.getInt();
        this.cards = new ArrayList<>();
        while(count > 0) {
            this.cards.add(new DeckCard(cards.get(buffer.getInt()), buffer.getInt()));
            count--;
        }
    }
    
    @Override
    public String toString() {
        return name;
    }

    private void addCard(Card c) {
        DeckCard dc = new DeckCard(c);
        int index = cards.indexOf(dc);
        if(index >= 0) {
            cards.get(index).count++;
        } else {
            cards.add(dc);
        }
    }

    @Override
    public int compareTo(Deck o) {
        return name.compareToIgnoreCase(o.name);
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Deck) {
            Deck d = (Deck)o;
            return compareTo(d) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name.toLowerCase());
        return hash;
    }
    
    public int saveSize() {
        return 2*(name.length() + 1) + 4 + 8*cards.size();
    }
    
    public void save(ByteBuffer buffer) {
        for(int i = 0; i < name.length(); i++) {
            buffer.putChar(name.charAt(i));
        }
        buffer.putChar(Library.ETX);
        Library l = Library.getInstance();
        buffer.putInt(cards.size());
        for(DeckCard dc : cards) {
            buffer.putInt(l.getCardIndex(dc.card));
            buffer.putInt(dc.count);
        }
    }

    @Override
    public Iterator<DeckCard> iterator() {
        return cards.iterator();
    }
    
    public int size() {
        return cards.size();
    }

    DeckCard get(int i) {
        return cards.get(i);
    }

    boolean hasCard(Card c) {
        DeckCard dc = new DeckCard(c);
        return cards.contains(dc);
    }

    int count(Card c) {
        DeckCard dc = new DeckCard(c);
        if(cards.contains(dc)) {
            return cards.get(cards.indexOf(dc)).count;
        } else {
            return 0;
        }
    }

    void removeCard(Card card) {
        DeckCard dc = new DeckCard(card);
        cards.remove(dc);
    }

    void addCard(Card card, int count) {
        DeckCard dc = new DeckCard(card, count);
        if(cards.contains(dc)) {
            cards.get(cards.indexOf(dc)).count = count;
        } else {
            cards.add(dc);
            Collections.sort(cards);
        }
    }

    void listDifferences(Deck otherDeck, ArrayList<String> differences) {
        //drive only cards
        int count = differences.size();
        for(DeckCard dc : otherDeck.cards) {
            if(!cards.contains(dc)) {
                differences.add("\t" +dc.card.name);
            }
        }
        if(differences.size() > count) {
            differences.add(count, "Drive Only:");
        }
        //local only cards
        count = differences.size();
        for(DeckCard dc : cards) {
            if(!otherDeck.cards.contains(dc)) {
                differences.add("\t" +dc.card.name);
            }
        }
        if(differences.size() > count) {
            differences.add(count, "Local Only:");
        }
        
        //diff number cards
        count = differences.size();
        for(DeckCard dc : otherDeck.cards) {
            int index = cards.indexOf(dc);
            if(index >= 0 && cards.get(index).count != dc.count) {
                differences.add("\t" +dc.card.name +" Drive: " +dc.count +", Local: " +cards.get(index).count);
            }
        }
        if(differences.size() > count) {
            differences.add(count, "Modified:");
        }
    }
    
    public static class DeckCard implements Comparable<DeckCard> {
        
        public final Card card;
        public int count;
        
        public DeckCard(Card card) {
            this.card = card;
            count = 1;
        }
        
        public DeckCard(Card card, int count) {
            this.card = card;
            this.count = count;
        }
        
        @Override
        public String toString() {
            return card.name +" (" +count +")";
        }

        @Override
        public int compareTo(DeckCard o) {
            return card.compareTo(o.card);
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 79 * hash + Objects.hashCode(this.card.name.toLowerCase());
            return hash;
        }
        
        @Override
        public boolean equals(Object o) {
            if(o instanceof DeckCard) {
                DeckCard dc = (DeckCard)o;
                return compareTo(dc) == 0;
            } else {
                return false;
            }
        }
        
    }
    
}
