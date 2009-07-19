package random;

import java.util.Random;
import java.util.Vector;

public class RandomString {

    private static final char[] symbols = new char[36];


    static {
        for (int idx = 0; idx < 10; ++idx) {
            symbols[idx] = (char) ('0' + idx);
        }
        for (int idx = 10; idx < 36; ++idx) {
            symbols[idx] = (char) ('a' + idx - 10);
        }
    }
    private final Random random = new Random();
    private final char[] buf;

    public RandomString(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length < 1: " + length);
        }
        buf = new char[length];
    }

    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    public static void main(String[] args) {
        RandomString rs = new RandomString(8);
        int total = 1000;
        Vector<String> rand = new Vector<String>(total);
        for (int i = 0; i < total; i++) {
//            System.out.println(rs.nextString());
            String s = rs.nextString();
            System.out.println("Generated String: " + s);
            rand.add(s);

        }

        int selfCounter = 0;
        for (String string : rand) {
//            System.out.println("Checking :");
            int matched = rand.indexOf(string);
            if (matched != -1 && matched != selfCounter) {
                System.out.println("Matched occurs: " + matched);
            }
            selfCounter++;
        }
    }
}
