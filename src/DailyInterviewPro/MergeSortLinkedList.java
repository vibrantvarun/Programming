package DailyInterviewPro;

public class MergeSortLinkedList {

    static LinkedNode head=null;
    public void createNewNode(int k){
        LinkedNode temp=new LinkedNode(k);
        if(head==null){
            head=temp;
        }else{
            LinkedNode temp1=head;
            while(temp1.next!=null){
                temp1=temp1.next;
            }
            temp1.next=temp;
        }
    }

    public void printList(LinkedNode temp){
        while(temp!=null){
            System.out.println(temp.a);
            temp=temp.next;
        }
    }


    public LinkedNode sortList(){

        LinkedNode temp=head;

        int count=0;
        while(temp!=null){
            count++;
            temp=temp.next;
        }

        int listArray[]=new int[count];

        LinkedNode dummy=new LinkedNode(-1);

        dummy.next=head;

        LinkedNode left,right,tail;

        for(int step=1;step<listArray.length;step<<=1){

              temp=dummy.next;
              tail=dummy;
              while(temp!=null){
                  left=temp;
                  right=split(left,step);
                  temp=split(right,step);
                  tail=merge(left,right,tail);
              }
        }

        return dummy.next;

    }

    public LinkedNode split(LinkedNode temp,int n){
        for(int i=1;temp!=null && i<n;i++){
            temp=temp.next;
        }

        if(temp==null){
            return null;
        }

        LinkedNode second= temp.next;
        temp.next=null;
        return second;
    }

    public LinkedNode merge(LinkedNode left, LinkedNode right, LinkedNode head1){
        LinkedNode temp=head1;

        while(left!=null && right!=null){

            if(left.a>right.a){
                temp.next=right;
                temp=right;
                right=right.next;
            }else{
                temp.next=left;
                temp=left;
                left=left.next;
            }
        }
        if(left!=null){
            temp.next=left;
        }else {
            temp.next=right;
        }

        while(temp.next!=null){
            temp=temp.next;
        }
        return temp;
    }


    public static void main(String[] args) {
        MergeSortLinkedList ms= new MergeSortLinkedList();

        ms.createNewNode(5);
        ms.createNewNode(8);
        ms.createNewNode(7);
        ms.createNewNode(4);
        ms.createNewNode(9);
        ms.createNewNode(10);
        ms.createNewNode(1);

        LinkedNode temp=ms.sortList();
        System.out.println("final List");

        ms.printList(temp);
    }
}


class LinkedNode{
    int a;
    LinkedNode next;
    LinkedNode(int k){
        this.a=k;
        this.next=null;
    }
}
