import java.util.*;

public class PrefixSuffixSearch {

    static Map<String,Integer> map;
    static TrieNode root;
    static int ans = -1;

    public static void main(String[] args) {
        String[] words = new String[] {"apple", "apppple", "appppppple"};
        PrefixSuffixSearch ps = new PrefixSuffixSearch(words);
        int greatestWeight = ps.f("a", "e");
        System.out.println(greatestWeight);

        greatestWeight = ps.f("ap", "e");
        System.out.println(greatestWeight);
    }

    public PrefixSuffixSearch(String[] words) {
        map= new HashMap<>();
        root= new TrieNode();
        for(int i=0;i<words.length;i++) {
            map.put(words[i],i);
            add(words[i],root);
        }

    }

    public void add(String word,TrieNode node){
        char[] wordc = word.toCharArray();
        for(int i=0;i<word.length();i++){
            int c=wordc[i]-'a';
            if(node.children[c]==null) node.children[c]=new TrieNode();
            node=node.children[c];
        }
        node.word=word;
    }

    public int f(String prefix, String suffix) {
        TrieNode node=find(root,prefix);
        if(node==null) return -1;
        ans=-1;
        findf(node,suffix,suffix.length());
        return ans;
    }

    public TrieNode find(TrieNode node, String prefix){
        for(int i=0;i<prefix.length();i++){
            int c=prefix.charAt(i)-'a';
            if(node.children[c]==null) return null;
            node=node.children[c];
        }
        return node;
    }

    public void findf(TrieNode node,String suffix,int len){
        if(node.word!=null){
            int start= node.word.length()-len;
            if(start>=0 && node.word.substring(start).equals(suffix)){
                if(map.get(node.word)>ans) ans=map.get(node.word);
            }
        }
        for(int i=0;i<26;i++){
            if(node.children[i]!=null) findf(node.children[i],suffix,len);
        }
    }

    static class TrieNode{
        String word;
        TrieNode[] children;
        TrieNode(){
            word=null;
            children= new TrieNode[26];
        }
    }
}
