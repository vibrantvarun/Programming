package Miscellaneous.Modular;

public class ModularExponentiation {

    /*
      Problem Statement:-
                         Given three numbers x, y and p, compute (xy) % p.
    */

    static int power(int x, int y,int p){

        x=x%p;
        int res=1;
        while (y>0){

            if((y&1)==1){
                res=(res*x)%p;
            }

            y=y>>1;
            x=(x*x)%p;
        }

        return res;

    }


    static int recursionMethod2(int x, int y,int p){

        if (x==0){
            return 0;
        }

        if (y==0){
            return 1;
        }

        long c;
        if ((y&1)==1){
            c=((x%p)*recursionMethod2(x,y-1,p)%p)%p;
        }else {
            c=recursionMethod2(x,y/2,p);
            c=(c*c)%p;
        }

        return (int) ((c+p)%p);
    }


    public static void main(String[] args) {

        int x=2;
        int y=5;
        int p=13;

        System.out.println(power(x,y,p));

        System.out.print(recursionMethod2(x,y,p));
    }
}
