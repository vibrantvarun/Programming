package BackTracking;

public class NQueenProblem {

    int M=4;

    void printSolution(int arr[][])
    {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++)
                System.out.print(" " + arr[i][j]
                        + " ");
            System.out.println();
        }
    }

    boolean isSafe(int arr[][],int row,int col){

        for (int i=0;i<M;i++){
            if (arr[row][i]==1){
                return false;
            }
        }

        for (int i=row,j=col;i>=0 && j>=0;i--,j--){

            if (arr[i][j]==1){
                return false;
            }
        }

        for (int i=row,j=col;i<M && j>=0;i++,j--){
            if (arr[i][j]==1){
                return false;
            }
        }
       return true;
    }

    boolean NQueenProblem(int arr[][],int col){

        if (col>=M){
            return true;
        }

        for (int i=0;i<M;i++){
            if (isSafe(arr,i,col)){

                arr[i][col]=1;

                if (NQueenProblem(arr,col+1)){
                    return true;
                }

                arr[i][col]=0;
            }


        }

        return false;
    }



    void solveNQ(){

        int arr[][]={ { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 } };
        if (!NQueenProblem(arr,0)){
            System.out.println("Solution does not exits");
            return;
        }

        printSolution(arr);
    }



    public static void main(String[] args) {
        NQueenProblem nq= new NQueenProblem();
        nq.solveNQ();
    }
}
