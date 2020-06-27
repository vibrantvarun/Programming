package Codechef;

import java.util.Arrays;
import java.util.Scanner;

public class CACHEHIT {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t= sc.nextInt();

        //int t=1;
        while (t-->0){
            int n= sc.nextInt();
            int b= sc.nextInt();
            int m= sc.nextInt();

//            int n=5;
//            int b=3;
//            int m=3;
            int totalBlocks= n/b;
            int remainder= n%b;

            if (remainder>0){
                totalBlocks++;
            }

            int arr[]= new int[m];

            for (int i=0;i<arr.length;i++){
                arr[i]=sc.nextInt();
            }

            //int arr[]= {0,3,4};


            int lastb=0;
            int lastSecondb=0;
            int count=0;
            for(int i=0;i<arr.length;i++) {
                lastb=arr[i]/b;
            }


            for (int j=1;j<=totalBlocks;j++){

                int k=b*j;
                if (k>arr.length){
                    k=arr.length;
                }
                for (int i=b*j-1;i<k;i++){
                    if (arr[i]==k){
                        lastSecondb=lastb;

                        lastb=b*j;
                        break;
                    }
                }
            }

            System.out.println(count);

        }
    }
}
