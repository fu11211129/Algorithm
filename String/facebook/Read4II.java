public class Read4II {

    public char[] wareHouse = new char[4];
    public int counts = 0;
    public int pt = 0;

    public int read(char[] buf, int n) {
        int index = 0;
        while(index < n) {
            // if warehouse is empty, fill new chars into warehouse with read4
            if(counts == 0) counts = read4(wareHouse);

            // if no more chars can be read from read4, stop reading.
            if(counts == 0) break;

            while(pt < counts && index < n)  buf[index++] = wareHouse[pt++];

            if(pt >= counts) {
                pt = 0;
                counts = 0;
            }
        }
        return index;
    }
}
