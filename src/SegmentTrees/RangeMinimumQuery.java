package SegmentTrees;

public class RangeMinimumQuery {

    int segmentArray[];

    RangeMinimumQuery(int arr[]){
        int x=(int) Math.ceil(Math.log(arr.length)/Math.log(2));

        int maxSize= 2*(int) Math.pow(2,x)-1;

        segmentArray=new int[maxSize];

        constructSegementTree(arr,0,arr.length-1,0);

    }

    int constructSegementTree(int arr[], int startingIndex,int endingIndex,int currentSegementIndex){

        if (startingIndex==endingIndex){
            segmentArray[currentSegementIndex]=arr[startingIndex];
            return arr[startingIndex];
        }

        int mid =mid(startingIndex,endingIndex);

        segmentArray[currentSegementIndex]=Math.min(constructSegementTree(arr,startingIndex,mid,2*currentSegementIndex+1),constructSegementTree(arr,mid+1,endingIndex,2*currentSegementIndex+2));

        return segmentArray[currentSegementIndex];

    }

    int mid(int startingIndex, int endingIndex){
        return (startingIndex+(endingIndex-startingIndex)/2);
    }


    int getMinUtil(int arr[], int startingIndex, int endingIndex, int l, int r, int currentSegementIndex) {
        if (l <= startingIndex && endingIndex <= r) {
            return segmentArray[currentSegementIndex];
        }
        if (l > endingIndex || startingIndex > r) {
            return Integer.MAX_VALUE;
        }
        int mid = mid(startingIndex, endingIndex);

        return Math.min(getMinUtil(arr, startingIndex, mid, l, r, 2 * currentSegementIndex + 1), getMinUtil(arr, mid + 1, endingIndex, l, r, 2 * currentSegementIndex + 2));

    }

    int getMinimum(int arr[],int l, int r){

        if(l<0 || r>arr.length-1|| r<l){
            return -1;
        }

        return getMinUtil(arr,0,arr.length-1,l,r,0);
    }

    public static void main(String[] args) {

        int arr[] = {1, 3, 2, 7, 9, 11};
        RangeMinimumQuery rg= new RangeMinimumQuery(arr);
        System.out.println(rg.getMinimum(arr,1,5));
    }
}
