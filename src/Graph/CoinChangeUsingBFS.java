package Graph;

// Java implementation of the approach
import java.util.*;

public class CoinChangeUsingBFS
{

    // Function to find the minimum number
// of integers required
    static int minNumbers(int x, int []arr, int n)
    {
        // Queue for BFS
        Queue<Integer> q = new LinkedList<>();

        // Base value in queue
        q.add(x);

        // Boolean array to check if
        // a number has been visited before
        HashSet<Integer> v = new HashSet<Integer>();

        // Variable to store depth of BFS
        int d = 0;

        // BFS algorithm
        while (q.size() > 0)
        {

            // Size of queue
            int s = q.size();
            while (s-- > 0)
            {

                // Front most element of the queue
                int c = q.peek();

                // Base case
                if (c == 0)
                    return d;
                q.remove();
                if (v.contains(c) || c < 0)
                    continue;

                // Setting current state as visited
                v.add(c);

                // Pushing the required states in queue
                for (int i = 0; i < n; i++)
                    q.add(c - arr[i]);
            }
            d++;
        }

        // If no possible solution
        return -1;
    }

    // Driver code
    public static void main(String[] args)
    {
        int arr[] = { 3, 3, 4 };
        int n = arr.length;
        int x = 7;

        System.out.println(minNumbers(x, arr, n));
    }
}
