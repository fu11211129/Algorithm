import java.util.*;

public class MaxChunksToMakeSortedII {
    static int maxChunksToSorted(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        int n = arr.length;
        int chunks = 0;
        int dif = 0;


        int prev = 0;
        System.out.print("[");
        for(int i=0; i<n; ++i) {
            dif += arr[i] - copy[i];
            if(dif == 0) {
                chunks += 1;
                System.out.print("[");
                for(int p=prev; p<i; ++p) {
                    System.out.print(arr[p] + ", ");
                } System.out.print(arr[i] + "],");
                prev = i+1;
            }
        }
        System.out.print("]");

        return chunks;
    }

    public static void main(String[] args) {
        int[] a = new int[] {2, 1, 4, 3, 5};
        int chunks = maxChunksToSorted(a);
        System.out.println(chunks + "chunks");
    }
}
