package DailyInterviewPro;

import java.io.IOException;
import java.util.Scanner;

public class IplBeginsCodeChefProblem {

    static long footballer[][]= new long[109][109];
    static long cricketer[][]= new long[109][109];

    static int m=1000000007;

    static long footballers(int footballers, int rooms){

        if(footballers<rooms){
            return 0;
        }
        if(rooms==1){
            return 1;
        }
        else if(footballer[footballers][rooms]!=-1){
           return footballer[footballers][rooms];
        }
        else{
            return  footballer[footballers][rooms]= (rooms*footballers(footballers-1,rooms))%m+ footballers(footballers-1,rooms-1)%m;
        }
    }

    static long cricketers(int cricketers, int rooms){
        if(cricketers<=rooms){
            return 0;
        }
        if(rooms==1){
            return 1;
        }
        else if(cricketer[cricketers][rooms]!=-1){
            return cricketer[cricketers][rooms];
        }

        else{
            return cricketer[cricketers][rooms]=((rooms*cricketers(cricketers-1,rooms))%m+ ((cricketers-1)*(cricketers(cricketers-2,rooms-1))%m)%m);
        }
    }



    public static void main(String[] args) throws IOException {

        for(int i=0;i<109;i++){
            for(int j=0;j<109;j++){
                footballer[i][j]=-1;
                cricketer[i][j]=-1;
            }
        }
        Scanner sc = new Scanner(System.in);
        int testCases=0;
        if(sc.hasNextInt()){
            testCases= sc.nextInt();
        }

        while(testCases!=0){
            int p=0,q=0,r=0;
            if(sc.hasNextInt()){
                p= sc.nextInt();
            }

            if(sc.hasNextInt()){
                q=sc.nextInt();
            }

            if(sc.hasNextInt()){
                r=sc.nextInt();
            }
            long ans=0;
            for(int i=1;i<=r-1;i++){
                ans+= (footballers(p,i)*cricketers(q,r-i));
                ans%=m;
            }
            System.out.println(ans);
            testCases=testCases-1;
        }
       /* int p=2,q=8,r=4;

       long ans=0;
        for(int i=1;i<=r-1;i++){
          ans+= (footballers(p,i)*cricketers(q,r-i));
          ans%=m;
        }
        System.out.println(ans);*/

    }

}
