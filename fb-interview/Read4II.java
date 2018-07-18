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

    // @Follow Up
    // Given a function read4k() (return a string 4000 length)
    // make a function printLine() that can print line (break by '\n')
    public int printLine(int n) {
        int idx = 0;
        while(idx < n) {
            if(cnt == 0) cnt = read4k(warehouse); // wareHouse = new char[4000]
            if(cnt == 0) break; // no more chars in stream.

            for(; ptr < cnt && idx < n && wareHouse[ptr] != '/n') {
                print(wareHouse[ptr++]);
            }

            if(ptr == cnt) {
                ptr = 0;
                cnt = 0;
            }

            if(wareHouse[ptr] == '/n') {
                print('\n');
            }
        }
    }
}
