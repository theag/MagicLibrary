/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.util.ArrayList;

/**
 *
 * @author nbp184
 */
public class DeckList {
    
    public String name;
    private ArrayList<ListCard> cards;
    private int cardCount;
    
    public DeckList(String name) {
        this.name = name;
        cards = new ArrayList<>();
        cardCount = 0;
    }

    public void addCard(Card c, int count) {
        cards.add(new ListCard(c, count));
        cardCount += count;
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
        return cardCount;
    }
    
    private class ListCard {
        private final Card card;
        private int count;
        
        public ListCard(Card card, int count) {
            this.card = card;
            this.count = count;
        }
        
    }
    
}
