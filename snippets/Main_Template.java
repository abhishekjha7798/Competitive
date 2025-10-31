import java.util.*;
import java.io.*;

public class Main {
    static FastIO io = new FastIO();

    public static void main(String[] args) throws IOException {
        // Template for competitive coding
        // Paste snippet classes here as needed

        // Example usage:
        // int result = Sieve.sieve(1000);
        // long gcd = GCD_LCM.gcd(12, 8);
        // int[] arr = {3, 2, 1, 5, 4};
        // Arrays.sort(arr);

        io.close();
    }
}

// FastIO for faster input/output
class FastIO extends PrintWriter {
    private BufferedReader br;
    private StringTokenizer st;

    public FastIO() {
        super(new BufferedOutputStream(System.out), true);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

