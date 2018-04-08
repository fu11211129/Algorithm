import java.util.*;

public class LFU {

    private Map<Integer, Integer> key2val;
    private Map<Integer, Node> key2node;
    private DoubleLinkedList dll;
    private int cap;

    public LFUCache(int capacity) {
        key2val = new HashMap<> ();
        key2node = new HashMap<> ();
        dll = new DoubleLinkedList();
        cap = capacity;
    }

    public int get(int key) {
        if(key2val.containsKey(key)) {
            int val = key2val.get(key);
            increaseFreq(key);
            return val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(cap <= 0) return;

        if(key2val.containsKey(key)) {
            key2val.put(key, value);
            increaseFreq(key);
        } else {
            if(key2val.size() < cap) {
                key2val.put(key, value);
                increaseFreq(key);

            } else {
                evictOld();
                key2val.put(key, value);
                increaseFreq(key);
            }

        }
    }

    public void increaseFreq(int key) {
        if(key2node.containsKey(key)) {

            Node node = key2node.get(key);
            int nextFreq = node.freq + 1;
            node.keys.remove(key);

            if(node.next == dll.tail) {
                Node newNode = new Node(key, nextFreq);
                newNode.keys.add(key);
                dll.addAfter(node, newNode);
                key2node.put(key, newNode);

            } else if(node.next != dll.tail && node.next.freq != node.freq + 1) {
                Node newNode = new Node(key, nextFreq);
                newNode.keys.add(key);
                dll.addAfter(node, newNode);
                key2node.put(key, newNode);

            } else if(node.next != dll.tail && node.next.freq == node.freq + 1) {
                node.next.keys.add(key);
                key2node.put(key, node.next);
            }

            if(node.keys.size() == 0) dll.unlink(node);

        } else {

            if(dll.head.next == dll.tail || dll.head.next.freq != 1) {
                Node newNode = new Node(key, 1);
                newNode.keys.add(key);
                dll.addAfter(dll.head, newNode);
                key2node.put(key, newNode);

            } else {
                dll.head.next.keys.add(key);
                key2node.put(key, dll.head.next);
            }

        }
    }

    public void evictOld() {
        if(dll.head.next == dll.tail) return;

        int leastFreqKey = 0;
        for(int k: dll.head.next.keys) {
            leastFreqKey = k;
            break;
        }

        dll.head.next.keys.remove(leastFreqKey);
        key2val.remove(leastFreqKey);
        key2node.remove(leastFreqKey);
        if(dll.head.next.keys.size() == 0) dll.unlink(dll.head.next);
    }

    class DoubleLinkedList {
        Node head;
        Node tail;

        public DoubleLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.pre = head;
        }

        public void addAfter(Node node, Node newNode) {
            Node right = node.next;
            node.next = newNode;
            newNode.next = right;
            right.pre = newNode;
            newNode.pre = node;
        }

        public void unlink(Node node) {
            Node left = node.pre;
            Node right = node.next;
            left.next = right;
            right.pre = left;
        }
    }

    class Node {
        int key;
        int freq;
        LinkedHashSet<Integer> keys;
        Node pre;
        Node next;

        public Node(int k, int f) {
            key = k;
            freq = f;
            keys = new LinkedHashSet<> ();
            pre = next = null;
        }
    }
}
