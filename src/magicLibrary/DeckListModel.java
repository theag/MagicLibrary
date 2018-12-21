/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author nbp184
 */
public class DeckListModel extends AbstractListModel<String> {
    
    private final Deck[] originalDecks;
    private final ArrayList<Deck> decks;
    private final ArrayList<Integer> counts;
    
    public DeckListModel() {
        originalDecks = null;
        decks = new ArrayList<>();
        counts = new ArrayList<>();
    }
    
    public DeckListModel(Card c) {
        decks = new ArrayList<>();
        counts = new ArrayList<>();
        Deck[] allDecks = Library.getInstance().getDeckArray();
        for(Deck d : allDecks) {
            if(d.hasCard(c)) {
                decks.add(d);
                counts.add(d.count(c));
            }
        }
        if(decks.isEmpty()) {
            originalDecks = new Deck[decks.size()];
            decks.toArray(originalDecks);
        } else {
            originalDecks = null;
        }
    }

    @Override
    public int getSize() {
        return decks.size();
    }

    @Override
    public String getElementAt(int index) {
        return decks.get(index) +" (" +counts.get(index) +")";
    }
    
    public boolean add(Deck d) {
        if(decks.contains(d)) {
            return false;
        }
        decks.add(d);
        counts.add(1);
        this.fireIntervalAdded(this, decks.size() - 1, decks.size());
        return true;
    }
    
    public void removeAt(int index) {
        decks.remove(index);
        counts.remove(index);
        this.fireIntervalRemoved(this, index, index);
    }
    
    public void updateCount(int index, int count) {
        counts.set(index, count);
        this.fireContentsChanged(this, index, index);
    }

    int getCount(int index) {
        return counts.get(index);
    }

    void save(Card card) {
        if(originalDecks != null) {
            for(Deck d : originalDecks) {
                if(!decks.contains(d)) {
                    d.removeCard(card);
                }
            }
        }
        for(int i = 0; i < decks.size(); i++) {
            decks.get(i).addCard(card, counts.get(i));
        }
    }
    
}
