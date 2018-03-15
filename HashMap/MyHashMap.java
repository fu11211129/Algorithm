// This is the text editor interface.
// Anything you type or change here will be seen by the other person in real time.
import java.util.*;

public class MyHashMap {

    public HashMap<String, String> map = null;
    private String val = null;
    private HashSet<String> keys = null;

    public String getVal() {
        return this.val;
    }

    public HashSet<String> getNonAffectedKey() {
        return this.keys;
    }

    public MyHashMap() {
        this.map = new HashMap<> ();
        this.keys = new HashSet<> ();
    }

    public void put(String key, String value) {
        System.out.println("PUT : " + key + " => " + value);
        map.put(key, value);
        this.keys.add(key);
    }

    public void get(String key) {
        System.out.print("GET : " + key + " => ");
        String result = "";

        if(this.keys.contains(key)) {
            result = map.getOrDefault(key, "NOT FOUND");
        } else {
            result = val;
        }
        System.out.println(result);
    }

    // O(n)
    public void putAll(String value) {
        // if(map.isEmpty()) {
        //     return;
        // }

        System.out.println();
        for(String k: map.keySet()) {
            System.out.println("PUTALL : " + k + " => " + value);
        }
        System.out.println();
        val = value;
        keys.clear();
    }

    public static void main(String[] args) {
        // MyHashMap mh = new MyHashMap();
        // mh.put("apple", "10");
        // mh.put("banana", "5");
        // mh.put("orange", "20");

        // System.out.println(val == null? mh.get("apple"): val);
        // System.out.println(val == null? mh.get("peach"): val);

        // mh.putAll("1000");
        // System.out.println(val == null? mh.get("apple"): val);

        MyHashMap mh = new MyHashMap();
        mh.put("apple", "10");
        mh.put("banana", "5");
        mh.get("banana");

        // if (!mh.get("apple").equals("10")) throw new RuntimeException("assert failed");
        // if (!mh.get("banana").equals("5")) throw new RuntimeException("assert failed");

        mh.get("apple");
        mh.get("banana");

        mh.putAll("0");
        // if (!mh.get("apple").equals("0")) throw new RuntimeException("assert failed");
        // if (!mh.get("banana").equals("0")) throw new RuntimeException("assert failed");
        mh.get("apple");
        mh.get("banana");

        mh.put("apple", "10");
        mh.get("banana");
        // if (!mh.get("apple").equals("10")) throw new RuntimeException("assert failed");
        // if (!mh.get("banana").equals("0")) throw new RuntimeException("assert failed");
        mh.get("apple");
        mh.get("banana");

    }

}
