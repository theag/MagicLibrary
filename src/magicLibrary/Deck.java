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
public class Deck {
    
    public String name;
    public ArrayList<DeckList> lists;
    
    public Deck(String name, String firstList) {
        this.name = name;
        lists = new ArrayList<>();
        DeckList list = new DeckList(firstList);
        lists.add(list);
    }
    
}
