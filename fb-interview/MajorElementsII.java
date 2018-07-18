public class MajorElementsII {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> re = new ArrayList<> ();

        int cm = 0, cn = 0;
        int m = (1 << 31) - 1, n = (1 << 31) - 1;

        for(int v: nums) {
            if(m == v) cm += 1;
            else if(n == v) cn += 1;
            else if(cm == 0) {
                m = v;
                cm = 1;
            }
            else if(cn == 0) {
                n = v;
                cn = 1;
            }
            else {
                cm -= 1;
                cn -= 1;
            }
        }

        //System.out.println(re1 + " " + re2);

        int cnt1 = 0, cnt2 = 0;
        for(int x: nums) {
            if(x == m) cnt1 += 1;
            else if(x == n) cnt2 += 1;
        }

        if(cnt1 > nums.length / 3) re.add(m);
        if(cnt2 > nums.length / 3) re.add(n);

        return re;
    }    
}
