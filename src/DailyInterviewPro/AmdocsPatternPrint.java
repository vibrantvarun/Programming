package DailyInterviewPro;

public class AmdocsPatternPrint {

    public static void main(String []args){

        int k=0;
        for(int i=0;i<4;i++)
        {
            if(i%2==0)
            {
                for(int y=0;y<i;y++)
                {
                    System.out.print(++k+"*");
                }
                System.out.print(++k);
            }
            else
            {
                int t=k+i+1;
                for(int x=0;x<i;x++)
                {
                    System.out.print((t--)+"*");
                }
                System.out.print(t--);
                k=k+i+1;
            }
            System.out.println();
        }

    }
}