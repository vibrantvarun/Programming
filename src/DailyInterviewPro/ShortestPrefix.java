package DailyInterviewPro;

import java.util.*;

public class ShortestPrefix {


    public static void main(String[] args) {
        List<String> worlist= new ArrayList<>(Arrays.asList("dog",
                "cat",
                "apple",
                "apricot",
                "fish"));


        Map<String,Boolean> resultMap= new HashMap<>();

        for(int i=0;i<worlist.size();i++){
           for(int j=0;j<worlist.get(i).length();) {
               if (resultMap.get(worlist.get(i).substring(j,j+1)) == null) {
                   resultMap.put(String.valueOf(worlist.get(i).charAt(j)), true);
                   break;
               } else {
                   resultMap.put(worlist.get(i).substring(j++, j), true);
                   break;
               }
           }

        }

        for(Map.Entry<String,Boolean> entry: resultMap.entrySet()){

            System.out.println(entry.getKey());

        }







    }
}
