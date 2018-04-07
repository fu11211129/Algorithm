import java.util.ArrayList;

public class KMP {

	public static int[] next = null;

	public static void prt(Object o) {
		System.out.print(o);
	}

	public static void pln(Object o) {
		System.out.println(o);
	}

	public static void buildNext(String t) {
		int n = t.length();
		next = new int[n];
		if(n < 2)  return;

		next[0] = next[1] = 0;
		for(int i=2; i<=n-1; ++i) {
			int j = next[i-1];
			while(j != 0 && t.charAt(j) != t.charAt(i-1))  j = next[j];
			if(t.charAt(j) == t.charAt(i-1))  next[i] = j+1;
			else next[i] = 0;
		}
	}

	public static ArrayList<Integer> find(String s, String t) {
		int ns = s.length(), nt = t.length();

		ArrayList<Integer> res = new ArrayList<Integer> ();
		if(ns < nt)  return res;

		int ps = 0, pt = 0;
		for(; ps < ns; ++ps) {

			while(pt != 0 && s.charAt(ps) != t.charAt(pt)) {
				pt = next[pt];
			}

			if(s.charAt(ps) == t.charAt(pt)) {
				++pt;
			}

			if(pt == nt) {
				res.add(ps-nt+1);
				pt = 0;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		String s = "i love you i love you ";
		String t = "love";

		buildNext(t);
		ArrayList<Integer> res = new ArrayList<Integer> ();
		res = find(s, t);
		pln(res);

	}

}
