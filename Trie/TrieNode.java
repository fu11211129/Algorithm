public class TrieNode {

	public boolean isWord;

	public TrieNode[] next = new TrieNode[26];

	public TrieNode() {
		for(int i=0; i<26; ++i) {
			this.next[i] = null;
		}
		this.isWord = false;
	}
}