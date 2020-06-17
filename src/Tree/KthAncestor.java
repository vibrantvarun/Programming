package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class KthAncestor {

    //Time Complexity O(n) & Space Complexity O(n)
    public void method1(TreeNode root,int n,int k, int obj){

        int ancestors[]= new int[n+1];

        Queue<TreeNode> queue= new LinkedList<>();
        queue.add(root);
        ancestors[root.a]=-1;
        while (!queue.isEmpty()){

            TreeNode node= queue.peek();
            queue.remove();

            if (node.left!=null){
                ancestors[node.left.a]=node.a;
                queue.add(node.left);
            }

            if (node.right!=null){
                ancestors[node.right.a]=node.a;
                queue.add(node.right);
            }
        }

        int count=0;
        while (obj!=-1){

            obj=ancestors[obj];
            count++;

            if(count==k){
                break;
            }


        }

        System.out.println(obj);


    }

    int k=2;
    public TreeNode method2(TreeNode root,int value){

        //Time Complexity O(n)
        if (root==null){
            return null;
        }

        if (root.a==value || method2(root.left,value)!=null || method2(root.right,value)!=null){
            if (k>0){
                k--;
            }else if (k==0){
                System.out.println(root.a);
                return null;
            }
            return root;
        }

        return null;
    }

    public boolean method3(TreeNode root, int value, Vector<Integer> vector){
       //Time Complexity O(n)
        if (root==null){
            return false;
        }
        vector.add(root.a);

        if (root.a==value){
            return true;
        }

        if (method3(root.left,value,vector) || method3(root.right,value,vector)){
            return true;
        }

        vector.removeElement(vector.size()-1);
        return  false;

    }

    public static void main(String[] args) {
        TreeNode root= new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);



        KthAncestor anc= new KthAncestor();
        anc.method1(root,5,2,5);

        TreeNode parent=anc.method2(root,5);
        if (parent!=null){
            System.out.println(-1);
        }

        Vector<Integer> vector= new Vector<>();
        boolean isFound=anc.method3(root,5,vector);

        int s=2;
        if (s<=0 || s>vector.size()-1){
            System.out.println(-1);
        }else {
            System.out.println(vector.get(vector.size()-1-s));
        }

    }
}
