import java.util.*;

public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            String time = in.nextLine();
            String result = solutionUtil(time);
        }
    }

    static String solutionUtil(String S) {
        int curTimeinMs = Integer.parseInt(S.substring(0,2)) * 60 + Integer.parseInt(S.substring(3));
        String curTimeStr = S.substring(0,2) + S.substring(3);

        char[] a = new char[4];
        a[0] = S.charAt(0);
        a[1] = S.charAt(1);
        a[2] = S.charAt(3);
        a[3] = S.charAt(4);
        Set<String> combos = new HashSet<String> ();
        permute(a, 0, 3, combos);
        //combos.remove(curTimeStr);
        for(String re: combos) {
            System.out.print(re + "  ");
        } System.out.println("\n=============");

        int minDif = 24 * 60;
        int result = curTimeinMs;
        for(String cand: combos) {
            int h1 = cand.charAt(0) - '0';
            int h2 = cand.charAt(1) - '0';
            if(h1 * 10 + h2 >= 24) continue;

            int m1 = cand.charAt(2) - '0';
            int m2 = cand.charAt(3) - '0';
            if(m1 * 10 + m2 >= 60) continue;

            int nextTimeinMs = (h1 * 10 + h2) * 60 + (m1 * 10 + m2);
            int dif = Math.floorMod(nextTimeinMs-curTimeinMs, 24*60);
            System.out.println("next time :" + String.format("%02d:%02d",nextTimeinMs/60,nextTimeinMs%60) + "  dif is: " + dif);
            if(dif > 0 && dif < minDif) {
                minDif = dif;
                result = nextTimeinMs;
            }
        }
        System.out.println("===========\ncloset time is: " + String.format("%02d:%02d",result/60,result%60));
        return String.format("%02d:%02d",result/60,result%60);
    }

    static void permute(char[] a, int l, int r, Set<String> result) {
        if(l > r) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<a.length; ++i) sb.append(a[i]);
            result.add(sb.toString());
            return;
        }

        for(int k=l; k<=r; ++k) {
            swap(a, l, k);
            permute(a, l+1, r, result);
            swap(a, l, k);
        }
    }

    static void swap(char[] a, int l, int r) {
        char temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }
}
