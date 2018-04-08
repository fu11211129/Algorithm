import java.util.*;

public class LRUCache {
    
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> mapping;
    private int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
        this.mapping = new HashMap<Integer, Node> ();
    }
    
    public void remove(Node node) {
        if(node.pre != null) {
            node.pre.next = node.next;
        } else {
            head = node.next;
        }
        
        if(node.next != null) {
            node.next.pre = node.pre;
        } else {
            tail = node.pre;
        }
    }
    
    public void addFirst(Node node) {
        node.next = head;
        node.pre = null;
        
        if(head != null) {
            head.pre = node;
        } head = node;
        
        if(tail == null) {
            tail = head;
        }
    }
    
    public int get(int key) {
        if(mapping.containsKey(key)) {
            Node node = mapping.get(key);
            int val = node.val;
            remove(node);
            addFirst(node);
            return val;
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if(mapping.containsKey(key)) {
            Node node = mapping.get(key);
            node.val = value;
            remove(node);
            addFirst(node);
        } else {
            if(capacity > mapping.size()) {
                Node newNode = new Node(key, value);
                addFirst(newNode);
                mapping.put(key, newNode);
            } else {
                Node node = tail;
                mapping.remove(node.key);
                remove(node);
                Node newNode = new Node(key, value);
                addFirst(newNode);
                mapping.put(key, newNode);
            }
        }
    }

    static class Node {
        public int key;
        public int val;
        public Node pre;
        public Node next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }
}