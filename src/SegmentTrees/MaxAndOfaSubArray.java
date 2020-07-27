package SegmentTrees;

import java.util.Arrays;

public class MaxAndOfaSubArray {

    Node segmentArray[];

    MaxAndOfaSubArray(int arr[]){
        int x= (int) Math.ceil(Math.log(arr.length)/Math.log(2));

        int maxSize= 2* (int) Math.pow(2,x)-1;

        segmentArray= new Node[maxSize];

        Arrays.fill(segmentArray,new Node(0,0,0,-1,-1));
        constructSegmentTree(arr,0,arr.length-1,0);
    }

    Node constructSegmentTree(int arr[],int startingIndex,int endingIndex,int currentSegmentIndex){
       if (startingIndex==endingIndex){
           segmentArray[currentSegmentIndex]=new Node(0,arr[startingIndex],0,-1,startingIndex);
           return segmentArray[currentSegmentIndex];
       }

       int mid=mid(startingIndex,endingIndex);

       Node left=constructSegmentTree(arr,startingIndex,mid,2*currentSegmentIndex+1);
       Node right= constructSegmentTree(arr,mid+1,endingIndex,2*currentSegmentIndex+2);

       int firstMax=0;
       int secondMax=0;
       int maxIndex=-1;
       int secondMaxIndex=-1;
       if (left.max>right.max && left.secondMax>right.max){
           firstMax=left.max;
           maxIndex=left.maxIndex;
           secondMax=left.secondMax;
           secondMaxIndex=left.secondMaxIndex;
       }else if(right.max>left.max && right.secondMax>left.max){
           firstMax=right.max;
           maxIndex=right.maxIndex;
           secondMax=right.secondMax;
           secondMaxIndex=right.secondMaxIndex;
       }else if (left.max>right.max){
           firstMax=left.max;
           secondMax=right.max;
           maxIndex=left.maxIndex;
           secondMaxIndex=right.maxIndex;
       }else if (right.max>left.max){
           firstMax=right.max;
           maxIndex=right.maxIndex;

           secondMax=left.max;
           secondMaxIndex=left.maxIndex;
       }else if(left.max==right.max) {
           if (left.secondMax>right.secondMax){
               firstMax=right.max;
               secondMax=left.secondMax;
               maxIndex=right.maxIndex;
               secondMaxIndex=left.secondMaxIndex;
           }else if (right.secondMax>left.secondMax){
               firstMax=right.max;
               secondMax=right.secondMax;
               maxIndex=right.maxIndex;
               secondMaxIndex=right.secondMaxIndex;

           }else {
               firstMax=right.max;
               secondMax=right.max;
               maxIndex=right.maxIndex;
               secondMaxIndex=right.secondMax;
           }

       }
       segmentArray[currentSegmentIndex]=new Node(secondMax,firstMax,firstMax & secondMax,secondMaxIndex,maxIndex);

       return segmentArray[currentSegmentIndex];
    }


    int mid(int startingIndex, int endingIndex){
        return (startingIndex+(endingIndex-startingIndex)/2);
    }






    public static void main(String[] args) {
        //int arr[]={4,2,3,12,4,5,15,1,3,7,8,2};
        int arr[]={100,99,98,97};

        MaxAndOfaSubArray max= new MaxAndOfaSubArray(arr);

        for (int i=0;i<max.segmentArray.length;i++){

            System.out.print(max.segmentArray[i].andOp+" --- "+max.segmentArray[i].secondMax+" --- "+max.segmentArray[i].max+ " --- "+ max.segmentArray[i].maxIndex + " --- "+max.segmentArray[i].secondMaxIndex);
            System.out.println();

        }

        System.out.println(Math.abs(max.segmentArray[0].maxIndex-max.segmentArray[0].secondMaxIndex)+1);
    }

}

class Node{
    int andOp;
    int secondMax;
    int secondMaxIndex;
    int max;
    int maxIndex;
    Node(int secondMax,int max,int andOp,int secondMaxIndex,int maxIndex){
     this.andOp=andOp;
     this.secondMaxIndex=secondMaxIndex;
     this.maxIndex=maxIndex;
     this.secondMax=secondMax;
     this.max=max;

    }
}
