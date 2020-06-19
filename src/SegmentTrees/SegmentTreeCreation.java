package SegmentTrees;

public class SegmentTreeCreation {

    int segmentArray[];

    SegmentTreeCreation(int arr[]) {

        int x = (int) Math.ceil(Math.log(arr.length) / Math.log(2));


        int maxsize = 2 * (int) Math.pow(2, x) - 1;

        segmentArray = new int[maxsize];

        constructSegmentTree(arr,0,arr.length-1,0);

    }

    int mid(int startingIndex, int endingIndex) {
        return (startingIndex + (endingIndex - startingIndex) / 2);
    }

    int constructSegmentTree(int arr[], int startingIndex, int endingIndex, int currentSegmentIndex) {
        if (startingIndex == endingIndex) {
            segmentArray[currentSegmentIndex] = arr[startingIndex];
            return arr[startingIndex];
        }


        int mid = mid(startingIndex, endingIndex);

        segmentArray[currentSegmentIndex] = constructSegmentTree(arr, startingIndex, mid, 2 * currentSegmentIndex + 1) + constructSegmentTree(arr, mid + 1, endingIndex, 2 * currentSegmentIndex + 2);

        return segmentArray[currentSegmentIndex];

    }

    int getSumUtil(int arr[],int startingIndex, int endingIndex, int l, int r, int currentSegmentIndex){

        if (l<=startingIndex && endingIndex<=r){
            return segmentArray[currentSegmentIndex];
        }

        if (r<startingIndex || l>endingIndex){
            return 0;
        }

        int mid=mid(startingIndex,endingIndex);

        return getSumUtil(arr,startingIndex,mid,l,r,2*currentSegmentIndex+1)+getSumUtil(arr,mid+1,endingIndex,l,r,2*currentSegmentIndex+2);
    }


    int getSum(int arr[],int l,int r){

        if (l < 0 || r > arr.length - 1|| l>r) {
            return -1;
        }


        return getSumUtil(arr,0,arr.length-1,l,r,0);
    }


    void updateUtil(int arr[],int startingIndex,int endingIndex, int i,int diff, int currentSegmentIndex){

        if (i>endingIndex || i<startingIndex){
            return;
        }

        segmentArray[currentSegmentIndex]=segmentArray[currentSegmentIndex]+diff;
        if (startingIndex!=endingIndex){
            int mid=mid(startingIndex,endingIndex);
            updateUtil(arr,startingIndex,mid,i,diff,2*currentSegmentIndex+1);
            updateUtil(arr,mid+1,endingIndex,i,diff,2*currentSegmentIndex+2);
        }

    }


    void updateValue(int arr[],int i, int newValue){
        if (i<0 || i>arr.length-1){
            return;
        }

        int diff= newValue-arr[i];
        arr[i]=newValue;

        updateUtil(arr,0,arr.length-1,1,diff,0);
    }


    public static void main(String[] args) {

        int arr[]={1,3,5,7,9,11};

        //Creation is O(n)
        SegmentTreeCreation st= new SegmentTreeCreation(arr);

        for(int i=0;i<st.segmentArray.length;i++){
            System.out.print(st.segmentArray[i]+" ");
        }
        System.out.println();

        //Sum Time Complexity is O(log(n))
        System.out.println("Sum is ::"+st.getSum(arr,1,3));

        //Update Time Complexity is O(log(n))
        st.updateValue(arr,1,10);

        for(int i=0;i<st.segmentArray.length;i++){
            System.out.print(st.segmentArray[i]+" ");
        }
        System.out.println();
        System.out.println("Sum is ::"+st.getSum(arr,1,3));


    }
}
