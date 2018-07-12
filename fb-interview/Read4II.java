public class Read4II {
    private char[] wareHouse = new char[4];
    private int ptr = 0;
    private int cnt = 0;

    public int read(char[] buf, int n) {
        int idx = 0;
        while(idx < n) {
            if(cnt == 0) cnt = read4(wareHouse);
            if(cnt == 0) break;
            while(ptr < cnt && idx < n) buf[idx++] = wareHouse[ptr++];
            if(ptr == cnt) {
                ptr = 0;
                cnt = 0;
            }
        }
        return idx;
    }    
}
