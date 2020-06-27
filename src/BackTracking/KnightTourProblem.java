package BackTracking;

import java.security.cert.X509Certificate;

public class KnightTourProblem {

    int N=8;
    boolean  isSafe(int x ,int y,int sol[][]){
        return (x>=0 && x<N && y>=0 && y<N && sol[x][y]==-1);
    }

    void printSolution(int sol[][]) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++)
                System.out.print(sol[x][y] + " ");
            System.out.println();
        }
    }


    boolean KnightTourProblem(int x, int y, int xCoordinate[],int yCoordinate[],int move,int sol[][]){

        if (move==N*N){
            return true;
        }

        int nextx,nexty;
        for (int i=0;i<N;i++){
           nextx=x+xCoordinate[i];
           nexty=y+yCoordinate[i];

           if (isSafe(nextx,nexty,sol)){
               sol[nextx][nexty]=move;

               if (KnightTourProblem(nextx,nexty,xCoordinate,yCoordinate,move+1,sol)){
                   return true;
               }else {
                   sol[nextx][nexty]=-1;
               }

           }
        }

        return false;
    }

    void getTour(){
        int sol[][]=new int[N][N];
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                sol[i][j]=-1;
            }
        }
        int xCoordinate[]={2,1,-1,-2,-2, -1, 1, 2};
        int yCoordinate[]={1,2,2,1,-1, -2, -2, -1};


        sol[0][0]=0;

        if (!KnightTourProblem(0,0,xCoordinate,yCoordinate,1,sol)){
            System.out.println("Solution does not exist");
            return;
        }

        printSolution(sol);
    }


    public static void main(String[] args) {
        KnightTourProblem kt= new KnightTourProblem();
        kt.getTour();
    }
}
