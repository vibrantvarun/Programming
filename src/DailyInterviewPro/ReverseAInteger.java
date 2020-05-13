package DailyInterviewPro;

public class ReverseAInteger {

    public static int reverse(int num) {
        int prev_rev_sum = 0;
        int rev_sum = 0;

        while (num != 0) {
            int current_digit = num % 10;
            rev_sum = rev_sum * 10 + current_digit;

            if ((rev_sum - current_digit) / 10 != prev_rev_sum) {
                System.out.println("Number Overflowed");
            }

            prev_rev_sum = rev_sum;
            num = num / 10;
        }

        return rev_sum;
    }

    public static void main(String[] args) {
        int num = 12345;
        System.out.println(reverse(num));

        num = 1000000045;
        System.out.println(reverse(num));

    }

}
