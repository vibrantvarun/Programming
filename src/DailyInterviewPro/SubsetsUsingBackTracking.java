package DailyInterviewPro;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SubsetsUsingBackTracking {

    static void subset(Vector<Vector<Integer>>  listOfsubsets,Vector<Integer> subset,int index,int [] array){

        listOfsubsets.add(subset);
        System.out.println(listOfsubsets.get(listOfsubsets.size()-1));
        for(int i=index;i<array.length;i++){

            subset.add(array[i]);
            subset(listOfsubsets,subset,i+1,array);

            System.out.println(" Element Removed is " + subset.get(subset.size()-1));
            subset.remove(subset.size()-1);
        }
        return;
    }


    static Vector<Vector<Integer>>  subsetUtil(int[] array){
        Vector<Vector<Integer>> listOfsubsets= new Vector<>();
        Vector<Integer> subset=new Vector<>();

        int index=0;
        subset(listOfsubsets,subset,index,array);
        return listOfsubsets;
    }

    public static void main(String[] args) {
        int array[]={1,2,3};

        Vector<Vector<Integer>> subset=subsetUtil(array);

        for(int i=0;i<subset.size();i++){
          for(int j=0;j< subset.get(i).size();j++){
              System.out.print(subset.get(i).get(j)+ " ");
          }
        }

    }
}
