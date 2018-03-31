public class MaxChunksToMakeSortedII {
    public int maxChunksToSorted(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        int n = arr.length;
        int chunks = 0;
        int dif = 0;

        for(int i=0; i<n; ++i) {
            dif += arr[i] - copy[i];
            if(dif == 0) chunks += 1;
        }

        return chunks;
    }    
}
