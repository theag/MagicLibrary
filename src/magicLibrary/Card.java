/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author nbp184
 */
public class Card implements Comparable<Card> {
    
    private static final SimpleDateFormat dateF = new SimpleDateFormat("MMM d, yyyy");

    public String name;
    public String[] supertype;
    public String[] type;
    public String[] subtype;
    public String[] manaCost;
    public String text;
    public String fancyText;
    public String power;
    public String toughness;
    public String loyalty;
    public String notes;
    public int count;
    public long lastUpdated;

    public Card() {
    }

    protected Card(ByteBuffer buffer) {
        name = "";
        char chr = buffer.getChar();
        while(chr != Library.ETX) {
            name += chr;
            chr = buffer.getChar();
        }
        supertype = new String[buffer.getInt()];
        for(int i = 0; i < supertype.length; i++) {
            supertype[i] = "";
            chr = buffer.getChar();
            while (chr != Library.ETX) {
                supertype[i] += chr;
                chr = buffer.getChar();
            }
        }
        type = new String[buffer.getInt()];
        for(int i = 0; i < type.length; i++) {
            type[i] = "";
            chr = buffer.getChar();
            while (chr != Library.ETX) {
                type[i] += chr;
                chr = buffer.getChar();
            }
        }
        subtype = new String[buffer.getInt()];
        for(int i = 0; i < subtype.length; i++) {
            subtype[i] = "";
            chr = buffer.getChar();
            while (chr != Library.ETX) {
                subtype[i] += chr;
                chr = buffer.getChar();
            }
        }
        manaCost = new String[buffer.getInt()];
        for(int i = 0; i < manaCost.length; i++) {
            manaCost[i] = "";
            chr = buffer.getChar();
            while(chr != Library.ETX) {
                manaCost[i] += chr;
                chr = buffer.getChar();
            }
        }
        chr = buffer.getChar();
        if(chr == Library.NULL) {
            text = null;
        } else {
            text = "";
            while(chr != Library.ETX) {
                text += chr;
                chr = buffer.getChar();
            }
        }
        chr = buffer.getChar();
        if(chr == Library.NULL) {
            fancyText = null;
        } else {
            fancyText = "";
            while(chr != Library.ETX) {
                fancyText += chr;
                chr = buffer.getChar();
            }
        }
        chr = buffer.getChar();
        if(chr == Library.NULL) {
            power = null;
        } else {
            power = "";
            while(chr != Library.ETX) {
                power += chr;
                chr = buffer.getChar();
            }
        }
        chr = buffer.getChar();
        if(chr == Library.NULL) {
            toughness = null;
        } else {
            toughness = "";
            while(chr != Library.ETX) {
                toughness += chr;
                chr = buffer.getChar();
            }
        }
        chr = buffer.getChar();
        if(chr == Library.NULL) {
            loyalty = null;
        } else {
            loyalty = "";
            while(chr != Library.ETX) {
                loyalty += chr;
                chr = buffer.getChar();
            }
        }
        chr = buffer.getChar();
        if(chr == Library.NULL) {
            notes = null;
        } else {
            notes = "";
            while(chr != Library.ETX) {
                notes += chr;
                chr = buffer.getChar();
            }
        }
        count = buffer.getInt();
        lastUpdated = buffer.getLong();
    }
    
    @Override
    public String toString() {
        return name;
    }

    public String formatUpdate() {
        return dateF.format(new Date(lastUpdated));
    }

    @Override
    public int compareTo(Card card) {
        if(card == null) {
            return name.compareTo(null);
        }
        return name.compareToIgnoreCase(card.name);
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Card) {
            return compareTo((Card)o) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name.toLowerCase());
        return hash;
    }

    protected int saveSize() {
        int rv = 2*(name.length() + 1);
        rv += 4;
        for(String s : supertype) {
            rv += 2*(s.length() + 1);
        }
        rv += 4;
        for(String s : type) {
            rv += 2*(s.length() + 1);
        }
        rv += 4;
        for(String s : subtype) {
            rv += 2*(s.length() + 1);
        }
        rv += 4;
        for(String s : manaCost) {
            rv += 2*(s.length() + 1);
        }
        if(text == null) {
            rv += 2;
        } else {
            rv += 2*(text.length() + 1);
        }
        if(fancyText == null) {
            rv += 2;
        } else {
            rv += 2*(fancyText.length() + 1);
        }
        if(power == null) {
            rv += 2;
        } else {
            rv += 2*(power.length() + 1);
        }
        if(toughness == null) {
            rv += 2;
        } else {
            rv += 2*(toughness.length() + 1);
        }
        if(loyalty == null) {
            rv += 2;
        } else {
            rv += 2*(loyalty.length() + 1);
        }
        if(notes == null) {
            rv += 2;
        } else {
            rv += 2*(notes.length() + 1);
        }
        rv += 4 + 8;
        return rv;
    }

    protected void save(ByteBuffer buffer) {
        for(int i = 0; i < name.length(); i++) {
            buffer.putChar(name.charAt(i));
        }
        buffer.putChar(Library.ETX);
        buffer.putInt(supertype.length);
        for(int j = 0; j < supertype.length; j++) {
            for (int i = 0; i < supertype[j].length(); i++) {
                buffer.putChar(supertype[j].charAt(i));
            }
            buffer.putChar(Library.ETX);
        }
        buffer.putInt(type.length);
        for(int j = 0; j < type.length; j++) {
            for (int i = 0; i < type[j].length(); i++) {
                buffer.putChar(type[j].charAt(i));
            }
            buffer.putChar(Library.ETX);
        }
        buffer.putInt(subtype.length);
        for(int j = 0; j < subtype.length; j++) {
            for (int i = 0; i < subtype[j].length(); i++) {
                buffer.putChar(subtype[j].charAt(i));
            }
            buffer.putChar(Library.ETX);
        }
        buffer.putInt(manaCost.length);
        for(int j = 0; j < manaCost.length; j++) {
            for(int i = 0; i < manaCost[j].length(); i++) {
                buffer.putChar(manaCost[j].charAt(i));
            }
            buffer.putChar(Library.ETX);
        }
        if(text == null) {
            buffer.putChar(Library.NULL);
        } else {
            for (int i = 0; i < text.length(); i++) {
                buffer.putChar(text.charAt(i));
            }
            buffer.putChar(Library.ETX);
        }
        if(fancyText == null) {
            buffer.putChar(Library.NULL);
        } else {
            for (int i = 0; i < fancyText.length(); i++) {
                buffer.putChar(fancyText.charAt(i));
            }
            buffer.putChar(Library.ETX);
        }
        if(power == null) {
            buffer.putChar(Library.NULL);
        } else {
            for (int i = 0; i < power.length(); i++) {
                buffer.putChar(power.charAt(i));
            }
            buffer.putChar(Library.ETX);
        }
        if(toughness == null) {
            buffer.putChar(Library.NULL);
        } else {
            for (int i = 0; i < toughness.length(); i++) {
                buffer.putChar(toughness.charAt(i));
            }
            buffer.putChar(Library.ETX);
        }
        if(loyalty == null) {
            buffer.putChar(Library.NULL);
        } else {
            for (int i = 0; i < loyalty.length(); i++) {
                buffer.putChar(loyalty.charAt(i));
            }
            buffer.putChar(Library.ETX);
        }
        if(notes == null) {
            buffer.putChar(Library.NULL);
        } else {
            for (int i = 0; i < notes.length(); i++) {
                buffer.putChar(notes.charAt(i));
            }
            buffer.putChar(Library.ETX);
        }
        buffer.putInt(count);
        buffer.putLong(lastUpdated);
    }

    public String getTypeString() {
        if(type == null) {
            return "None";
        } else {
            String rv = "";
            for(int i = 0; i < type.length; i++) {
                if(i > 0) {
                    rv += " ";
                }
                rv += type[i];
            }
            return rv;
        }
    }

    String getSuperTypeString() {
        String rv = "";
        for(int i = 0; i < supertype.length; i++) {
            if(i > 0) {
                rv += " ";
            }
            rv += supertype[i];
        }
        return rv;
    }

    String getSubTypeString() {
        String rv = "";
        for(int i = 0; i < subtype.length; i++) {
            if(i > 0) {
                rv += " ";
            }
            rv += subtype[i];
        }
        return rv;
    }

    int getColours() {
        int rv = 0;
        for(String m : manaCost) {
            if(m.contains("W")) {
                rv = rv | 1;
            }
            if(m.contains("U")) {
                rv = rv | 2;
            }
            if(m.contains("B")) {
                rv = rv | 4;
            }
            if(m.contains("R")) {
                rv = rv | 8;
            }
            if(m.contains("G")) {
                rv = rv | 16;
            }
        }
        return rv;
    }

    int getCMC() {
        int rv = 0;
        for(String s : manaCost) {
            try {
                int m = Integer.parseInt(s);
                rv += m;
            } catch(NumberFormatException ex) {
                if(s.compareTo("X") != 0) {
                    rv++;
                }
            }
        }
        return rv;
    }

    String getAllTypeString() {
        String rv = getSuperTypeString();
        if(!rv.isEmpty()) {
            rv += " ";
        }
        rv += getTypeString();
        if(subtype.length > 0) {
            rv += " -- "+getSubTypeString();
        }
        return rv;
    }

    void listDifferences(Card otherCard, ArrayList<String> differences) {
        //type
        String mine = getAllTypeString();
        String other = otherCard.getAllTypeString();
        if(mine.compareToIgnoreCase(other) != 0) {
            differences.add("Type (Local: " +mine +" Drive: " +other +")");
        }
        //mana
        int untypedMine = 0;
        int xCountMine = 0;
        int[] manaCountMine = new int[20];
        int index, prime;
        for(String s : manaCost) {
            index = -1;
            switch(s.charAt(0)) {
                case 'W':
                    index = 0;
                    prime = 2;
                    break;
                case 'U':
                    index = 1;
                    prime = 3;
                    break;
                case 'B':
                    index = 2;
                    prime = 5;
                    break;
                case 'R':
                    index = 3;
                    prime = 7;
                    break;
                case 'G':
                    index = 4;
                    prime = 11;
                    break;
                case 'X':
                    prime = 0;
                    xCountMine++;
                    break;
                default:
                    prime = 0;
                    untypedMine += Integer.parseInt(s);
                    break;
            }
            if(index >= 0 && s.length() == 2) {
                switch(s.charAt(1)) {
                    case 'W':
                        prime *= 2;
                        break;
                    case 'U':
                        prime *= 3;
                        break;
                    case 'B':
                        prime *= 5;
                        break;
                    case 'R':
                        prime *= 7;
                        break;
                    case 'G':
                        prime *= 11;
                        break;
                    case 'P':
                        prime = 0;
                        index += 5;
                        break;
                }
                if(prime != 0) {
                    switch(prime) {
                        case 4:
                            index = 0;
                            break;
                        case 6:
                            index = 10;
                            break;
                        case 10:
                            index = 11;
                            break;
                        case 14:
                            index = 12;
                            break;
                        case 22:
                            index = 13;
                            break;
                        case 9:
                            index = 1;
                            break;
                        case 15:
                            index = 14;
                            break;
                        case 21:
                            index = 15;
                            break;
                        case 33:
                            index = 16;
                            break;
                        case 25:
                            index = 2;
                            break;
                        case 35:
                            index = 17;
                            break;
                        case 55:
                            index = 18;
                            break;
                        case 49:
                            index = 3;
                            break;
                        case 77:
                            index = 19;
                            break;
                        case 121:
                            index = 4;
                            break;
                    }
                }
                manaCountMine[index]++;
            } else if(index >= 0) {
                manaCountMine[index]++;
            }
        }
        int untypedOther = 0;
        int xCountOther = 0;
        int[] manaCountOther = new int[20];
        for(String s : otherCard.manaCost) {
            index = -1;
            switch(s.charAt(0)) {
                case 'W':
                    index = 0;
                    prime = 2;
                    break;
                case 'U':
                    index = 1;
                    prime = 3;
                    break;
                case 'B':
                    index = 2;
                    prime = 5;
                    break;
                case 'R':
                    index = 3;
                    prime = 7;
                    break;
                case 'G':
                    index = 4;
                    prime = 11;
                    break;
                case 'X':
                    prime = 0;
                    xCountOther++;
                    break;
                default:
                    prime = 0;
                    untypedOther += Integer.parseInt(s);
                    break;
            }
            if(index >= 0 && s.length() == 2) {
                switch(s.charAt(1)) {
                    case 'W':
                        prime *= 2;
                        break;
                    case 'U':
                        prime *= 3;
                        break;
                    case 'B':
                        prime *= 5;
                        break;
                    case 'R':
                        prime *= 7;
                        break;
                    case 'G':
                        prime *= 11;
                        break;
                    case 'P':
                        prime = 0;
                        index += 5;
                        break;
                }
                if(prime != 0) {
                    switch(prime) {
                        case 4:
                            index = 0;
                            break;
                        case 6:
                            index = 10;
                            break;
                        case 10:
                            index = 11;
                            break;
                        case 14:
                            index = 12;
                            break;
                        case 22:
                            index = 13;
                            break;
                        case 9:
                            index = 1;
                            break;
                        case 15:
                            index = 14;
                            break;
                        case 21:
                            index = 15;
                            break;
                        case 33:
                            index = 16;
                            break;
                        case 25:
                            index = 2;
                            break;
                        case 35:
                            index = 17;
                            break;
                        case 55:
                            index = 18;
                            break;
                        case 49:
                            index = 3;
                            break;
                        case 77:
                            index = 19;
                            break;
                        case 121:
                            index = 4;
                            break;
                    }
                }
                manaCountOther[index]++;
            } else if(index >= 0) {
                manaCountOther[index]++;
            }
        }
        boolean foundDiff = false;
        for(int i = 0; i < 20; i++) {
            if(manaCountMine[i] != manaCountOther[i]) {
                foundDiff = true;
                break;
            }
        }
        if(foundDiff || untypedMine != untypedOther || xCountMine != xCountOther) {
            differences.add("Mana Cost (Local: " +getManaString() +" Drive: " +otherCard.getManaString() +")");
        }
        //text
        if(text.compareToIgnoreCase(otherCard.text) != 0) {
            differences.add("Text (Local: " +text +" Drive: " +otherCard.text +")");
        }
        //power toughnes loyalty
        if(stringCompare(power, otherCard.power) != 0 || stringCompare(toughness,otherCard.toughness) != 0) {
            differences.add("Power/Toughness (Local: " +power +"/" +toughness +" Drive: " +otherCard.power +"/" +otherCard.toughness +")");
        } else if(stringCompare(loyalty,otherCard.loyalty) != 0) {
            differences.add("Loyalty (Local: " +loyalty +" Drive: " +otherCard.loyalty +")");
        }
        //notes
        if(stringCompare(notes,otherCard.notes) != 0) {
            differences.add("Notes (Local: " +notes +" Drive: " +otherCard.notes +")");
        }
        //count
        if(count != otherCard.count) {
            differences.add("Count (Local: " +count +" Drive: " +otherCard.count +")");
        }
    }

    private String getManaString() {
        String rv = "";
        for(String s : manaCost) {
            if(!rv.isEmpty()) {
                rv += ",";
            }
            rv += s;
        }
        return rv;
    }

    private int stringCompare(String s1, String s2) {
        if(s1 == null && s2 == null) {
            return 0;
        } else if(s1 == null) {
            return -1;
        } else if(s2 == null) {
            return 1;
        } else {
            return s1.compareToIgnoreCase(s2);
        }
    }
    
}
