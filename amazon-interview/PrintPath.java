public class PrintPath {
    static void wrong_print(int row) {
        char start = 'a';
        for(int i = 0; i < row; i += 1) {
            //char start = 'a';
            char print = start;
            for(int j = 0; j <= i; j += 1) {
                System.out.print(start++);
            }
            System.out.println();
        }
    }

    static void print(int row) {
        // char start = 'a';
        for(int i = 0; i < row; i += 1) {
            char start = 'a';
            char print = start;
            for(int j = 0; j <= i; j += 1) {
                System.out.print(start++);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        wrong_print(4);

        print(4);
    }
}
