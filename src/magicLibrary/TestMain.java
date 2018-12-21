/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author nbp184
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //newLibrary();
        //testDialog();
        //moveToNewDecks();
    }
    
    public static void testDialog() {
        File fc = new File(MainFrame.cardFile);
        File fd = new File(MainFrame.deckFile);
        if(fc.exists() && fd.exists()) {
            try {
                Library.makeLibrary(fc, fd);
            } catch (IOException ex) {
                System.out.println("Library creation failed");
                System.out.println(ex.getMessage());
                ex.printStackTrace(System.out);
            }
        }
        CardDialog.showEditDialog(null, true, Library.getInstance().resultAt(0));
    }
    
    /*public static void moveToNewDecks() {
        File fc = new File(MainFrame.cardFile);
        File fd = new File(MainFrame.deckFile);
        if(fc.exists() && fd.exists()) {
            try {
                Library.makeLibrary(fc, fd);
                Library l = Library.getInstance();
                Deck deck;
                for(Card c : l) {
                    for(String d : c.decks) {
                        deck = new Deck(d);
                        if(!l.addDeck(deck)) {
                            deck = l.getDeckByName(d);
                        }
                        deck.addCard(c);
                    }
                }
                l.save(fc, fd);
            } catch (IOException ex) {
                System.out.println("Library creation failed");
                System.out.println(ex.getMessage());
                ex.printStackTrace(System.out);
            }
        }
    }*/
    
    public static void newLibrary() throws IOException {
        Library l = Library.getInstance();
        Card c = new Card();
        c.name = "Aether Vial";
        c.supertype = new String[0];
        c.type = new String[]{"Artifact"};
        c.subtype = new String[0];
        c.manaCost = new String[]{"1"};
        c.text = "At the beginning of your upkeep, you may put a charge counter of Aether Vial.\ntap: You may put a creature card with converted mana cost equal to the number of charge counters on Aether Vial from your hand onto the battlefield.";
        c.fancyText = "At the beginning of your upkeep, you may put a charge counter of Aether Vial.\n{T}: You may put a creature card with converted mana cost equal to the number of charge counters on Aether Vial from your hand onto the battlefield.";
        c.count = 1;
        //c.decks.add("Sygg");
        c.lastUpdated = Calendar.getInstance().getTimeInMillis();
        l.addCard(c);
        c = new Card();
        c.name = "Stonybrook Banneret";
        c.supertype = new String[0];
        c.type = new String[]{"Creature"};
        c.subtype = new String[]{"Merfolk","Wizard"};
        c.manaCost = new String[]{"1","U"};
        c.text = "Islandwalk\nMerfolk spells and Wizard spells you cast cost 1 less to cast.";
        c.fancyText = "Islandwalk\nMerfolk spells and Wizard spells you cast cost {1} less to cast.";
        c.power = "1";
        c.toughness = "1";
        c.notes = "Alt to Aether Vial";
        c.count = 2;
        //c.decks.add("Sygg");
        //c.decks.add("Merfolk");
        c.lastUpdated = Calendar.getInstance().getTimeInMillis();
        l.addCard(c);
        c = new Card();
        c.name = "Mutagenic Growth";
        c.supertype = new String[0];
        c.type = new String[]{"Instant"};
        c.subtype = new String[0];
        c.manaCost = new String[]{"GP"};
        c.text = "Target creature gets +2/+2 until end of turn.";
        c.count = 4;
        //c.decks.add("Infect");
        c.lastUpdated = Calendar.getInstance().getTimeInMillis();
        l.addCard(c);
        //l.save(new File("library.txt"));
    }
    
}
