package Tree;

public class DiameterOfBT {

    static class A{
        int d=Integer.MIN_VALUE;
    }

    int hieght(TreeNode node,A a){
        if(node==null){
            return 0;
        }

        int lefthieght=hieght(node.left,a);
        int righthieght=hieght(node.right,a);

        a.d=Math.max(lefthieght+righthieght+1,a.d);
        return Math.max(lefthieght,righthieght)+1;
    }

    int diameter(TreeNode node){
        if(node==null){
            return 0;
        }

        A a=new  A();

        hieght(node,a);

        return a.d;

    }

    public static void main(String[] args) {

        TreeNode root=new TreeNode(1);

        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);

        DiameterOfBT bt=new DiameterOfBT();
        System.out.println("Height is "+bt.diameter(root));


    }

}



class TreeNode{
    int a;
    TreeNode left,right;

    TreeNode(int a){
        this.a=a;
        this.left=this.right=null;
    }

}
