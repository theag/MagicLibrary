/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 *
 * @author nbp184
 */
public class DeckList {
    
    public String name;
    private ArrayList<ListCard> cards;
    
    public DeckList(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }
    
    public DeckList(ByteBuffer buffer, ArrayList<Card> libCards) {
        name = "";
        char chr = buffer.getChar();
        while(chr != Library.ETX) {
            name += chr;
            chr = buffer.getChar();
        }
        cards = new ArrayList<>();
        int count = buffer.getInt();
        while(count > 0) {
            cards.add(new ListCard(buffer, libCards));
            count--;
        }
    }
    
    int saveSize() {
        int rv = (name.length() + 1)*2 + 4;
        for(ListCard c : cards) {
            rv += c.saveSize();
        }
        return rv;
    }
    
    void save(ByteBuffer buffer) {
        for(int i = 0; i < name.length(); i++) {
            buffer.putChar(name.charAt(i));
        }
        buffer.putChar(Library.ETX);
        buffer.putInt(cards.size());
        for(ListCard c : cards) {
            c.save(buffer);
        }
    }

    public void addCard(Card c, int count) {
        cards.add(new ListCard(c, count));
    }

    public int size() {
        return cards.size();
    }

    public Card getCard(int i) {
        return cards.get(i).card;
    }

    public int getCount(int i) {
        return cards.get(i).count;
    }

    public int getCardCount() {
        int rv = 0;
        for(ListCard c : cards) {
            rv += c.count;
        }
        return rv;
    }
    
    private class ListCard {
        private final Card card;
        private int count;
        
        public ListCard(Card card, int count) {
            this.card = card;
            this.count = count;
        }
        
        public ListCard(ByteBuffer buffer, ArrayList<Card> libCards) {
            card = libCards.get(buffer.getInt());
            count = buffer.getInt();
        }
        
        public int saveSize() {
            return 8;
        }
        
        void save(ByteBuffer buffer) {
            buffer.putInt(Library.getInstance().getCardIndex(card));
            buffer.putInt(count);
        }
        
    }
    
}
