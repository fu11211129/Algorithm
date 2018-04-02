import java.util.*;

public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        Arrays.sort(people, new Comparator<int[]> () {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) return a[1] - b[1];
                return b[0] - a[0];
            }
        });

        ArrayList<int[]> tmp = new ArrayList<> ();
        for(int i=0; i<n; ++i) {
            //int index = people[i][1];
            tmp.add(people[i][1], new int[] {people[i][0], people[i][1]});
            // for(int k=0; k<tmp.size(); ++k) System.out.print(tmp.get(i)[0] + " " + tmp.get(i)[1]+ "\t");
            // System.out.println();
            for(int[] e: tmp) {
                System.out.print(e[0] + " " + e[1] + "\t");
            } System.out.println();
        }

        int co = 0;
        int[][] result = new int[n][2];
        for(int[] e: tmp) {
            result[co][0] = e[0];
            result[co++][1] = e[1];
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] people = new int[][] {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        QueueReconstructionByHeight qr = new QueueReconstructionByHeight();
        int[][] result = qr.reconstructQueue(people);
        for(int[] line: result) System.out.println(line[0] + " " + line[1]);
    }
}
