import java.util.*;

public class BreakBad_trie {

	public static TrieNode root = new TrieNode();

	public static void insertStr(String s) {
		TrieNode p = root;

		for(int i=0;i<s.length(); ++i) {
			char c = Character.toLowerCase(s.charAt(i));
			int branch = (c - 'a');

			if(p.next[branch] == null) p.next[branch] = new TrieNode();
			p = p.next[branch];
		}
		p.isWord = true;
	}

	public static void buildTrie(String[] strs) {
		int le = strs.length;

		for(int i=0; i<le; ++i) {
			insertStr(strs[i]);
		}
	}

	public static int dfs(char[] ca, int idx) {
		int le = ca.length;
		TrieNode p = root;
		int i = idx, result = -1;

		while(i < le && p.next[ca[i] - 'a'] != null) {
			p = p.next[ca[i] - 'a'];
			if(p.isWord) result = i;
			i += 1;
		}

		return result;
	}

	public static void breakBad(String[] strs, String name) {
		buildTrie(strs);

		char[] ca = name.toCharArray();
		int le = ca.length;
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<le; ) {
			char c = ca[i];
			TrieNode p = root;
			if(p.next[c - 'a'] == null) {
				i += 1;
				sb.append(c);
			} else {
				int j = dfs(ca, i);
				if(j == -1) {
					sb.append(ca[i]);
					i += 1;
				} else {
					sb.append("[");
					sb.append(Character.toUpperCase(ca[i]));
					for(int k=i+1; k<=j; ++k) sb.append(ca[k]);
					sb.append("]");
					i = j+1;
				}
			}
		}

		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		String[] strs = new String[] {"ad", "c", "e", "bcd"};
		String name = "abcdef";

		BreakBad_trie.breakBad(strs, name);
	}
}