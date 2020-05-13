package DailyInterviewPro;

public class DeleteMiddleOfLinkedList {

    public static Node deleteMiddle(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }

        Node fastPointer = head;
        Node slowPointer = head;

        Node prev = null;

        while (fastPointer != null && fastPointer.next != null && fastPointer.next.next!=null) {
            fastPointer = fastPointer.next.next;
            prev = slowPointer;
            slowPointer = slowPointer.next;
        }

        prev.next=slowPointer.next;


        return head;

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
       // head.next.next.next.next.next=new Node(6);

        Node deleteNodeListHead = deleteMiddle(head);

        while (deleteNodeListHead != null) {
            System.out.println(deleteNodeListHead.a);
            deleteNodeListHead = deleteNodeListHead.next;
        }
    }


}

class Node {
    int a;
    Node next;

    Node(int a) {
        this.a = a;
        this.next = null;
    }
}