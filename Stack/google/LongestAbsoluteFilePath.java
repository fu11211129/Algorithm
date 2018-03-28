import java.util.*;

public class LongestAbsoluteFilePath {
    public static void main(String[] args) {
        LongestAbsoluteFilePath la = new LongestAbsoluteFilePath();
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        int result = la.lengthLongestPath("dir\n        file.txt".replaceAll(" ", "\t"));
        System.out.println(result);
    }

    public int lengthLongestPath(String input) {
        String[] dirs = input.split("\n");
        // System.out.println(dirs[1].length());
        Stack<String> stack = new Stack<> ();
        int dirDepth = -1;
        int maxDirDepth = -1;
        int len = 0;
        int result = 0;
        boolean hasFile = false;

        for(String d: dirs) {
            int i=0;
            int tabs = 0;
            while(i < d.length() && (d.charAt(i) == ' ')) i += 1;
            if(i == 4) tabs += 1;


            // System.out.println(i);
            if(d.indexOf('.') >= 0) hasFile = true;

            if(dirDepth < i) {
                stack.push(d.substring(i));
                len += d.length() - i;
                result = Math.max(result, len);
                dirDepth += 1;
                maxDirDepth = Math.max(maxDirDepth, dirDepth);
            } else if(dirDepth == i) {
                String dpop = stack.pop();
                len -= dpop.length();
                stack.push(d.substring(i));
                len += d.length() - i;
            } else {
                String dpop = stack.pop();
                len -= dpop.length();
                dirDepth -= 1;
            }
        }

        if(!hasFile) return 0;
        if(maxDirDepth > 0) result += maxDirDepth;
        return result;
    }
}
