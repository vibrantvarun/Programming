package BackTracking;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class WarnsdoffAlgo {

    public static final int N=8;
    public static int cx[]={1,1,2,2,-1,-1,-2,-2};
    public static int cy[]={2, -2, 1, -1, 2, -2, 1, -1};
    Cell warnsdoffAlgoNextMove(int arr[],Cell cell){

        int mindegIndex=-1;
        int mindegree=N+1;
        int nx,ny,c;


        int start= ThreadLocalRandom.current().nextInt(1000)%N;

        for (int count=0;count<N;++count){

            int i= (start+count)%N;

            nx=cell.x+cx[i];
            ny=cell.y+cy[i];

            if (isEmpty(arr,nx,ny) && (c=getDegree(arr,nx,ny))<mindegree){
                mindegIndex=i;
                mindegree=c;
            }
        }

        if (mindegIndex==-1){
            return null;
        }

        nx= cell.x+cx[mindegIndex];
        ny= cell.y+cy[mindegIndex];

        arr[ny*N+nx]=arr[cell.y*N+cell.x]+1;

        cell.x=nx;
        cell.y=ny;

        return cell;
    }

    int getDegree(int a[],int x, int y){

        int count=0;

        for (int i=0;i<N;++i){
            if (isEmpty(a,x+cx[i],y+cy[i])){
                count++;
            }
        }

        return count;
    }


    boolean findClosedTour(){

        int arr[]= new int[N*N];
        Arrays.fill(arr,-1);

        int x=3;
        int y=2;
        Cell cell = new Cell(x,y);
        arr[cell.y*N+cell.x]=1;

        Cell ret=null;
        for (int i=0;i<(N*N-1);++i){
            ret=warnsdoffAlgoNextMove(arr,cell);
            if (ret==null){
                return false;
            }
        }

        if (!neighbour(ret.x,ret.y,x,y)){
            return false;
        }

        print(arr);

        return true;
    }

    void print(int a[]) {
        for (int i = 0; i < N; ++i)
        {
            for (int j = 0; j < N; ++j)
                System.out.printf("%d\t", a[j * N + i]);
            System.out.printf("\n");
        }
    }

    boolean neighbour(int x,int y,int xx,int yy){

        for (int i=0;i<N;++i){
            if (x+cx[i]==xx && y+cy[i]==yy){
                return true;
            }
        }
        return false;
    }

    boolean limits(int x, int y) {
        return ((x >= 0 && y >= 0) &&
                (x < N && y < N));
    }



    boolean isEmpty(int arr[],int x,int y){
        return (limits(x,y) && arr[y*N+x]==-1);
    }

    public static void main(String[] args) {

        while (!new WarnsdoffAlgo().findClosedTour()){
            ;
        }
    }
}

class Cell{
    int x;
    int y;
    Cell(int x,int y){
        this.x=x;
        this.y=y;
    }


}