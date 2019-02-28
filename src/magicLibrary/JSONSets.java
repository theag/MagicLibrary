/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author nbp184
 */
public class JSONSets {
    
    private final JSONObject cards;
    private final JSONArray sets;
    
    public JSONSets() throws FileNotFoundException {
        cards = new JSONObject(new JSONTokener(new FileInputStream("AllCards.json")));
        sets = new JSONArray(new JSONTokener(new FileInputStream("SetList.json")));
    }
    
    public String[] getSets(String name) {
        JSONObject obj1, obj2;
        JSONArray arr;
        obj1 = this.cards.getJSONObject(name);
        if(obj1 == null) {
            return null;
        }
        ArrayList<String> lst = null;
        if(obj1.has("printings")) {
            arr = obj1.getJSONArray("printings");
            lst =  new ArrayList<>();
            for(int i = 0; i < arr.length(); i++) {
                int j;
                for(j = 0; j < sets.length(); j++) {
                    obj2 = sets.getJSONObject(j);
                    if(obj2.getString("code").compareToIgnoreCase(arr.getString(i)) == 0) {
                        if(obj2.getString("type").compareTo("promo") != 0 &&
                                obj2.getString("type").compareTo("funny") != 0 &&
                                obj2.getString("type").compareTo("archenemy") != 0 &&
                                obj2.getString("type").compareTo("starter") != 0 &&
                                obj2.getString("type").compareTo("memorabilia") != 0) {
                            lst.add(obj2.getString("name"));
                        } else {
                            
                        }
                        break;
                    }
                }
                if(j == sets.length()) {
                    lst.add("Unknown Code (" +arr.getString(i) +")");
                }
            }
        }
        if(lst == null) {
            return null;
        }
        String[] rv = new String[lst.size()];
        lst.toArray(rv);
        return rv;
    }
    
}
