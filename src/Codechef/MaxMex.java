package Codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MaxMex {


    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int t=Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int arr[]=new int[n];
            StringTokenizer st1= new StringTokenizer(br.readLine());
            for (int i=0;i<arr.length;i++){
                arr[i]=Integer.parseInt(st1.nextToken());
            }
            Arrays.sort(arr);
            int d=0;
            if (arr.length>=3){
                int d1= arr[1]-arr[0];
                int d2= arr[2]-arr[1];

                if (d1!=d2){
                    if (d1>d2){
                        d=d2;
                    }else{
                        d=d1;
                    }
                }else {
                    d=d1;
                }

                for (int i=0;i<arr.length-1;i++){
                    if (arr[i+1]-arr[i]!=d && arr[i]+d==m){
                        System.out.println(arr[i]+d);
                    }
                }
            }else if (arr.length==2){
                if (arr[1]-arr[0]>0 && arr[0]+1==m){
                    System.out.println(1);
                }else {
                    System.out.println(-1);
                }
            }else {
                System.out.println(-1);
            }
        }

    }
}
