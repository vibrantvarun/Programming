package DynammicProgramming;

public class SubsetWithGivenSum {

    static boolean getSubset(int set[],int sum){
        boolean dp[][]= new boolean[set.length+1][sum+1];


        for (int i=0;i<=set.length;i++){
            dp[i][0]=true;
        }

        for (int j=1;j<=sum;j++){
            dp[0][j]=false;
        }
        for (int i=1;i<=set.length;i++){

            for (int j=1;j<=sum;j++){
                    dp[i][j]=dp[i-1][j];

                if (j>=set[i-1]){
                    dp[i][j]=(dp[i-1][j-set[i-1]] || dp[i-1][j]);
                }
            }
        }

        return dp[set.length][sum];

    }

    public static void main(String[] args) {

        int set[]={3,34,4,12,5,2};
        int sum=9;
        if (getSubset(set, sum) == true)
            System.out.println("Found a subset"
                    + " with given sum");
        else
            System.out.println("No subset with"
                    + " given sum");
    }
}
