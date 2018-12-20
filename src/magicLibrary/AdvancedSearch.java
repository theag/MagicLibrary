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
    public String[] decks;
    
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
        boolean[] results = new boolean[6];
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
        if(decks == null) {
            results[5] = allAnd;
        } else {
            results[5] = false;
            for(String d : decks) {
                if(card.decks.contains(d)) {
                    results[5] = true;
                    break;
                }
            }
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
