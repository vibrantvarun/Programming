package DailyInterviewPro;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {

    static void calculateMaximumInK(int arr[],int k){

        Deque<Integer> deque= new LinkedList<>();
        int i;
        for(i=0;i<k;++i){
            while(!deque.isEmpty() && arr[i]>=arr[deque.peekLast()]){
                deque.removeLast();
            }
            deque.addLast(i);
        }
        for(;i<arr.length;++i){
            System.out.println(arr[deque.peek()]);

            System.out.println("deque.peek() & i-k is" +deque.peek()+"   "+ (i-k));
            while(!deque.isEmpty()&& deque.peek()<=i-k){
               deque.removeFirst();
            }

            while(!deque.isEmpty()&& arr[i]>=arr[deque.peekLast()]){
                System.out.println("arr[i] && arr[deque.peekLast()]  "+arr[i]+"   " +arr[deque.peekLast()]);
                deque.removeLast();
            }
            System.out.println("size is "+deque.size());
            deque.iterator().forEachRemaining(it -> {
                int s = it.intValue();
                System.out.println("item is  " + s);
            });
            deque.addLast(i);
        }
    }



    public static void main(String[] args) {
        int arr[]={1,2,4,3,7,6};
        int k=3;
        calculateMaximumInK(arr,k);
    }
}
