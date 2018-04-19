import java.util.*;

public class SortTransformedArray {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {

        if(a == 0) {
            int[] result = new int[nums.length];
            for(int i=0; i<nums.length; ++i) result[i] = b*nums[i] + c;
            if(b < 0) reverseArray(result);
            return result;
        }

        int n = nums.length;
        int x = (int)Math.ceil(-1.0 * b / (2.0 * a));
        int idx = Arrays.binarySearch(nums, x);
        idx = idx < 0? (-idx-1): idx;
        int q = idx;
        int p = q - 1;
        int e = 0;

        LinkedList<Integer> list = new LinkedList<> ();

        while(p >= 0 && q < n) {
            int vp = quad(a, b, c, nums[p]);
            int vq = quad(a, b, c, nums[q]);

            if(a > 0) {
                if(vp <= vq) {
                    list.add(vp);
                    p -= 1;
                } else {
                    list.add(vq);
                    q += 1;
                }

            } else {
                if(vp >= vq) {
                    list.add(vp);
                    p -= 1;
                } else {
                    list.add(vq);
                    q += 1;
                }
            }
        }

        if(a > 0) {
            while(p >= 0) list.add(quad(a, b, c, nums[p--]));
            while(q < n) list.add(quad(a, b, c, nums[q++]));
        } else {
            while(p >= 0) list.add(quad(a, b, c, nums[p--]));
            while(q < n) list.add(quad(a, b, c, nums[q++]));
        }

        if(a < 0) Collections.reverse(list);

        int[] result = new int[n];
        for(int nn: list) result[e++] = nn;
        return result;
    }

    private int quad(int a, int b, int c, int v) {
        return a * v * v + b * v + c;
    }

    private void reverseArray(int[] arr) {
        int l = 0, r = arr.length-1;
        while(l <= r) {
            swap(arr, l++, r--);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        SortTransformedArray sf = new SortTransformedArray();
        int[] arr = new int[] {-97,-95,-82,-81,-76,-75,-74,-73,-72,-69,-68,-66,-64,-61,-56,-53,-51,-50,-47,-46,-43,-41,-39,-33,-30,-30,-29,-28,-27,-26,-25,-25,-23,-22,-18,-16,-16,-15,-9,-4,-2,-1,5,16,20,20,21,21,24,24,33,39,40,44,44,49,51,53,54,55,57,58,58,59,62,63,65,67,68,69,71,72,73,73,74,76,77,78,79,81,88,90,91,92,92,96,97};
        int a = 31, b = 71, c = 96;
        int[] re = sf.sortTransformedArray(arr, a, b, c);

        for(int x: re) System.out.print(x + " ");
        System.out.println();
    }
}
