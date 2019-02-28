/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

/**
 *
 * @author nbp184
 */
public class AdvancedSearch {
    
    public boolean allAnd;
    public boolean colourAnd;
    public boolean colourless;
    public int colourMask;//WUBRG
    public boolean legendary;
    public boolean typeAnd;
    public String[] types;
    public int[] cmc;
    public boolean textAnd;
    public String[] text;
    public Deck[] decks;
    public boolean noDeck;
    public boolean notesAnd;
    public String[] notes;
    public boolean noNotes;
    public int countOperator;//<,<=,=,>=,>
    public int countNumber;
    public boolean addsMana;
    
    public AdvancedSearch() {
        allAnd = true;
        colourAnd = true;
        colourless = false;
        colourMask = 0;
        legendary = false;
        typeAnd = true;
        types = null;
        cmc = null;
        textAnd = true;
        text = null;
        decks = null;
    }
    
    public boolean matches(Card card) {
        boolean[] results = new boolean[9];
        //colour
        if(!colourless && colourMask == 0) {
            results[0] = allAnd;
        } else if(colourAnd) {
            //anding
            if(colourless) {
                if(colourMask != 0) {
                    //can't be coloured and colourless
                    results[0] = false;
                } else {
                    //checking if colourless
                    if(card.getColours() != 0) {
                        results[0] = false;
                    } else {
                        results[0] = true;
                    }
                }
            } else {
                //checking for colour
                results[0] = colourMask == card.getColours();
            }
        } else {
            //oring
            results[0] = (colourMask & card.getColours()) != 0;
        }
        //legendary
        if(!legendary) {
            results[1] = allAnd;
        } else {
            results[1] = false;
            for(String t : card.supertype) {
                if(t.compareToIgnoreCase("legendary") == 0) {
                    results[1] = true;
                    break;
                }
            }
        }
        //type
        if(types == null) {
            results[2] = allAnd;
        } else if(typeAnd) {
            results[2] = true;
            for(String t : types) {
                boolean amType = false;
                for(String ct : card.type) {
                    if(t.compareToIgnoreCase(ct) == 0) {
                        amType = true;
                        break;
                    }
                }
                if(!amType) {
                    results[2] = false;
                    break;
                }
            }
        } else {
            results[2] = false;
            for(String t : types) {
                for(String ct : card.type) {
                    if(t.compareToIgnoreCase(ct) == 0) {
                        results[2] = true;
                        break;
                    }
                }
                if(results[2]) {
                    break;
                }
            }
        }
        //cmc
        if(cmc == null) {
            results[3] = allAnd;
        } else {
            int cCMC = card.getCMC();
            results[3] = false;
            for(int m : cmc) {
                if(m == cCMC) {
                    results[3] = true;
                    break;
                }
            }
        }
        //text
        if(text == null) {
            results[4] = allAnd;
        } else if(card.text == null) {
            results[4] = false;
        } else if(textAnd) {
            results[4] = true;
            for(String t : text) {
                if(!card.text.toLowerCase().contains(t.toLowerCase())) {
                    results[4] = false;
                    break;
                }
            }
        } else {
            results[4] = false;
            for(String t : text) {
                if(card.text.toLowerCase().contains(t.toLowerCase())) {
                    results[4] = true;
                    break;
                }
            }
        }
        //decks
        if(noDeck) {
            results[5] = true;
            for(Deck d : Library.getInstance().getDeckArray()) {
                if(d.hasCard(card)) {
                    results[5] = false;
                    break;
                }
            }
        } else if(decks == null) {
            results[5] = allAnd;
        } else {
            results[5] = false;
            for(Deck d : decks) {
                if(d.hasCard(card)) {
                    results[5] = true;
                    break;
                }
            }
        }
        //notes
        if(noNotes) {
            results[6] = card.notes == null || card.notes.trim().isEmpty();
        } else if(notes == null) {
            results[6] = allAnd;
        } else if(card.notes == null) {
            results[6] = false;
        } else if(notesAnd) {
            results[6] = true;
            for(String t : notes) {
                if(!card.notes.toLowerCase().contains(t.toLowerCase())) {
                    results[6] = false;
                    break;
                }
            }
        } else {
            results[6] = false;
            for(String t : notes) {
                if(card.notes.toLowerCase().contains(t.toLowerCase())) {
                    results[6] = true;
                    break;
                }
            }
        }
        //card count
        switch(countOperator) {
            case 0:
                results[7] = card.getCMC() < countNumber;
                break;
            case 1:
                results[7] = card.getCMC() <= countNumber;
                break;
            case 2:
                results[7] = card.getCMC() == countNumber;
                break;
            case 3:
                results[7] = card.getCMC() >= countNumber;
                break;
            case 4:
                results[7] = card.getCMC() > countNumber;
                break;
        }
        //adds mana
        if(addsMana) {
            results[8] = card.addsMana;
        } else {
            results[8] = allAnd;
        }
        
        boolean rv = allAnd;
        if(allAnd) {
            for(boolean r : results) {
                rv = rv && r;
            }
        } else {
            for(boolean r : results) {
                rv = rv || r;
            }
        }
        return rv;
    }
    
}
