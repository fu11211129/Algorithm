import java.util.*;

public class RearrangeChar {
	static class CharFreq implements Comparable<CharFreq>{
		char c;
		int freq;
		public CharFreq(char ch, int count){
			c = ch;
			freq = count;
		}
		@Override
		public int compareTo(CharFreq o) {
			int comp = Double.compare(freq, o.freq);
			if(comp == 0){
				comp = Character.compare(o.c, c);
			}
			
			return comp;
		}
	}

	static Random rand = new Random();

	public static String rearrangeAdjacentDuplicates(String str){
		int n = str.length();
		StringBuffer rearranged = new StringBuffer();
		PriorityQueue<CharFreq> maxHeap = new PriorityQueue<CharFreq>(256, Collections.reverseOrder());
		int freqHistoGram[] = new int[256];
		//build the character frequency histogram
		for(char c : str.toCharArray()){
			freqHistoGram[c]++;
			
			//if a character repeats more than n/2 then we can't rearrange
			if(freqHistoGram[c] > (n+1)/2){
				return str;
			}
		}
		//build the max heap of histogram
		for(char i  = 0; i < 256; i++){
			if(freqHistoGram[i] > 0)
				maxHeap.add(new CharFreq(i, freqHistoGram[i]));
		}
		
		//rearrange - pop top 2 most frequent items and arrange them in adjacent positions
		//decrease the histogram frequency of the selected chars 
		while(!maxHeap.isEmpty()){
			//extract top one and decrease the hstogram by one
			CharFreq first = maxHeap.poll();
			rearranged.append(first.c);
			first.freq--;
			
			CharFreq second = null;
			//extract second top and decrease the histogram by one
			if(!maxHeap.isEmpty()){
				second = maxHeap.poll();
				rearranged.append(second.c);
				second.freq--;
			}
			
			//add back the updated histograms 
			if(first.freq > 0){
				maxHeap.add(first);
			}
			if(second != null && second.freq > 0){
				maxHeap.add(second);
			}
		}
		
		return rearranged.toString();
	}

	public static void main(String[] args) {
		String str = randomInput(10000000);

		long start = System.currentTimeMillis();
		String result = rearrangeAdjacentDuplicates(str);
		long end = System.currentTimeMillis();
		System.out.println((end-start) + " ms");


		start = System.currentTimeMillis();
		result = rearrange2(str);
		end = System.currentTimeMillis();
		System.out.println((end-start) + " ms");
	}

	public static String rearrange2(String str) {
		char[] cstr = str.toCharArray();
		int[] count = new int[256];
		for(char c: cstr) count[c] += 1;

		List<CharFreq> ls = new ArrayList<> ();
		int maxFreq = 0;
		for(int i=0; i<256; ++i) {
			if(count[i] != 0) {
				char c = (char) i;
				ls.add(new CharFreq(c, count[i]));
				maxFreq = Math.max(maxFreq, count[i]);
			}
		}

		Collections.sort(ls, Collections.reverseOrder());
		int bucketNum = ls.size();
		char[][] cell = new char[bucketNum][maxFreq];

		for(int i=0; i<cell.length; ++i) Arrays.fill(cell[i], '#');
		int r = 0;
		//Map<Integer, Integer> col2RowNum = new HashMap<>();
		for(CharFreq cf: ls) {
			char ch = cf.c;
			int f = cf.freq;
			for(int c=0; c<f; ++c) {
				cell[r][c] = ch;
				//col2RowNum.put(c, col2RowNum.getOrDefault(c, 0) + 1);
			}
			r += 1;
		}

		StringBuilder result = new StringBuilder();
		for(int j=0; j<maxFreq; ++j) {
			for(int i=0; i<bucketNum; ++i) {
				if(cell[i][j] == '#') {
					if(i < bucketNum-1 && cell[i][j] == '#') break;
					else continue;
				}
				result.append(cell[i][j]);
			}
		}

		return result.toString();
	}

	public static String randomInput(int len) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<len; ++i) {
			int id = rand.nextInt(26);
			char c = (char)(65+id);
			sb.append(c);
		}
		return sb.toString();
	}

}