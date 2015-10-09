package partition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int partition(int n, int k) {
        if (n < k) {
            return 0;
        }
        else if (k == 1 || n == k) {
            return 1;
        }
        else {
            return partition(n-1, k-1) + partition(n-k, k);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        System.out.println(partition(n, k));
        }
    }


