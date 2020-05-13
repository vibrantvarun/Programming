package DailyInterviewPro;

import java.util.HashMap;

public class Pairs {

    static int countpairs(int arr[],int k){

      int freq[]=new int[k];

      for(int i=0;i<arr.length;i++){
          ++freq[arr[i]%k];

      }


      return 0;
    }

    public static void main(String[] args) {
        int arr[]={1,2,2,3};
        countpairs(arr,4);
    }
}
