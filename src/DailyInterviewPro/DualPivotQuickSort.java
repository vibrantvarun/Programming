package DailyInterviewPro;

public class DualPivotQuickSort {

    static int partition(int arr[],int low, int high,int lp){


        if (arr[low]>arr[high]){
            swap(arr,low,high);
        }
        int j=low+1;
        int k=low+1;
        int lowPivot=arr[low];
        int highPivot=arr[high];
        int g=high-1;
        while (k<=g){

            if (arr[k]<lowPivot){
                swap(arr,k,j);
                j++;
            }
            else if (arr[k]>highPivot){

                swap(arr,k,g);
                while (arr[g]>highPivot && k<=g){
                    g--;
                }
                //g--;
                if (arr[k]<lowPivot){
                    swap(arr,k,j);
                    j++;
                }
            }
            k++;

        }

        swap(arr,j-1,low);
        swap(arr,g+1,high);

        lp=j-1;

        return g+1;





    }

    static  void  sort(int arr[],int low,int high,int lp){

        if (low>=high){
            return;
        }
        int rp= partition(arr,low,high,lp);

        sort(arr,low,lp-1,lp);
        sort(arr,lp+1,rp-1,lp);
        sort(arr, rp+1, high, lp);
    }


    static void dualPivotQuickSort(int arr[]){
          int lp=0;

          sort(arr,0,arr.length-1,lp);

          for (int i=0;i<arr.length;i++){
              System.out.print(arr[i]+" ");
          }
    }

    static void swap(int arr[],int i,int j){
        int temp= arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void main(String[] args) {
       int arr[]={24,8,42,75,29,77,38,57};
       dualPivotQuickSort(arr);
    }
}
