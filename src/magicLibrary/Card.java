/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
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
    public float price;
    public long lastUpdated;

    public Card() {}

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
        price = buffer.getFloat();
        lastUpdated = buffer.getLong();
    }
    
    @Override
    public String toString() {
        String rv = "[" +name +"    ";
        for(String s : manaCost) {
            rv += s;
        }
        rv += "]\n[";
        for(int i = 0; i < supertype.length; i++) {
            if(i > 0) {
                rv += " ";
            }
            rv += supertype[i];
        }
        for(int i = 0; i < type.length; i++) {
            if(supertype.length > 0 || i > 0) {
                rv += " ";
            }
            rv += type[i];
        }
        if(subtype.length > 0) {
            rv += " -- " +subtype[0];
        }
        for(int i = 1; i < subtype.length; i++) {
            rv += " " +subtype[i];
        }
        rv += "]\n[" +text +"]";
        if(power != null) {
            rv += "\n[" +power +"/" +toughness +"]";
        } else if(loyalty != null) {
            rv += "\n[" +loyalty+"]";
        }
        rv += "\nHave: " +count +"\nCosts: " +formatPrice(true) +"\nNotes: " +notes +"\nLast Updated: " +formatUpdate();
        return rv;
    }

    public String formatPrice(boolean dollarSign) {
        String rv = ""+price;
        int dot = rv.indexOf(".");
        if(rv.length() - dot < 3) {
            rv += "0";
        } else if(rv.length() - dot > 3) {
            rv = rv.substring(0, dot +3);
        }
        while(dot >= 4) {
            rv = rv.substring(0, dot - 4) +"," +rv.substring(dot - 3);
            dot -= 3;
        }
        if(dollarSign) {
            return "$" + rv;
        } else {
            return rv;
        }
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
        rv += 4 + 4 + 8;
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
        buffer.putFloat(price);
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
    
}
