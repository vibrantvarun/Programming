package DailyInterviewPro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {

        static final int MOD = 1000000007;

        static int add(int a, int b) {
                int r = a + b;
                return r < MOD ? r : r - MOD;
        }

        static int sub(int a, int b) {
                int r = a - b;
                return r < 0 ? r + MOD : r;
        }

        static int mul(int a, int b) {
                return (int)((long)a*(long)b % MOD);
        }

        static int sum(int... values) {
                int result = 0;
                for (int v : values) result = add(result, v);
                return result;
        }

        public static void main(String[] args) {

                //Scanner in = new Scanner(System.in);
                int n = 3;
                int p = 4;
                List<Integer> divisors = new ArrayList<Integer>();
                int div = 1;
                while (div*div <= p) {
                        divisors.add(div);
                        div++;
                }
                for (int d = div-1, i = d-1; d > 0; d--) {
                        div = p / d;
                        if (divisors.get(i) < div) {
                                divisors.add(div);
                                i++;
                        }
                }
                int k = divisors.size();
                int[] divs = new int[k];
                for (int i = 0; i < k; i++) {
                        divs[i] = divisors.get(i);
                }
                int[] diff = new int[k];
                diff[0] = divs[0];
                for (int i = 1; i < k; i++) {
                        diff[i] = divs[i] - divs[i-1];
                }
                int[] counts = Arrays.copyOf(diff, k);
                for (int i = 1; i < n; i++) {
                        int sum = sum(counts);
                        int[] updatedCounts = new int[k];
                        for (int j = 0, q = k-1; j < k; j++) {
                                while (divs[j] * divs[q] > p) {
                                        sum = sub(sum, counts[q]);
                                        q--;
                                }
                                updatedCounts[j] = mul(sum, diff[j]);
                        }
                        counts = updatedCounts;
                }
                System.out.println(sum(counts));
        }
}