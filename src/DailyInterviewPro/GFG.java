package DailyInterviewPro;

// Java implmentation to find the
// Number of factors of very
// large number N modulo M
public class GFG {

    static long mod = 1000000007L;

    // Function for modular
// multiplication
    static long mult(long a, long b) {
        return ((a % mod) *
                (b % mod)) % mod;
    }

    // Funcion to find the number
// of factors of large Number N
    static long calculate_factors(long n) {
        long ans, cnt;
        cnt = 0;
        ans = 1;

        // Count the number of times
        // 2 divides the number N
        while (n % 2 == 0) {
            cnt++;
            n = n / 2;
        }

        // Condition to check
        // if 2 divides it
        if (cnt % 2 == 1) {
            ans = mult(ans, (cnt + 1));
        }

        // Check for all the possible
        // numbers that can divide it
        for (int i = 3; i <= Math.sqrt(n);
             i += 2) {
            cnt = 0;

            // Loop to check the number
            // of times prime number
            // i divides it
            while (n % i == 0) {
                cnt++;
                n = n / i;
            }

            // Condition to check if
            // prime number i divides it
            if (cnt % 2 == 1) {
                ans = mult(ans, (cnt + 1));
            }
        }
        // Condition to check if N
        // at the end is a prime number.
        if (n > 2) {
            ans = mult(ans, (2));
        }
        return ans % mod;
    }

    // Driver Code
    public static void main(String[] args) {
        long n = 1000L;
        mod = 17;

        System.out.print(calculate_factors(n) + "\n");
    }
}


