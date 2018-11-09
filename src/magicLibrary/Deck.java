/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author nbp184
 */
public class Deck implements Comparable<Deck> {
    
    public String name;
    public ArrayList<DeckList> lists;
    
    public Deck(String name, String firstList) {
        this.name = name;
        lists = new ArrayList<>();
        DeckList list = new DeckList(firstList);
        lists.add(list);
    }

    Deck(ByteBuffer buffer, ArrayList<Card> cards) {
        name = "";
        char chr = buffer.getChar();
        while(chr != Library.ETX) {
            name += chr;
            chr = buffer.getChar();
        }
        lists = new ArrayList<>();
        int count = buffer.getInt();
        while(count > 0) {
            lists.add(new DeckList(buffer, cards));
            count--;
        }
    }
    
    int saveSize() {
        int size = (name.length()  + 1)*2 + 4;
        for(DeckList dl : lists) {
            size += dl.saveSize();
        }
        return size;
    }

    void save(ByteBuffer buffer) {
        for(int i = 0; i < name.length(); i++) {
            buffer.putChar(name.charAt(i));
        }
        buffer.putChar(Library.ETX);
        buffer.putInt(lists.size());
        for(DeckList dl : lists) {
            dl.save(buffer);
        }
    }

    @Override
    public int compareTo(Deck o) {
        return name.compareTo(o.name);
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Deck) {
            Deck od = (Deck)o;
            return name.compareTo(od.name) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
}
