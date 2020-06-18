package BinarySearchTree;

import java.util.HashMap;

public class LowestCommonAncestor {

    Node root;
    ParentNode parentroot;
    LowestCommonAncestor(){
        root=null;
        parentroot=null;
    }
    public Node addNode(Node root,int value){
        if (root==null){
            return new Node(value);
        }

        if (value<root.a){
            root.left=addNode(root.left,value);
        }else if (value>root.a){
            root.right=addNode(root.right,value);
        }

        return root;
    }


    public void insert(int key){
        root=addNode(root,key);
    }

    Node lcaMethod1UsingRecursion(Node node, int n1, int n2){

        //Time Complexity O(h) and space Complexity O(1) but we use stack in Recursion
        if (node==null){
            return null;
        }

        if (node.a>n1 && node.a>n2){
            return lcaMethod1UsingRecursion(node.left,n1,n2);
        }else if (node.a<n1 && node.a <n2){
            return lcaMethod1UsingRecursion(node.right,n1,n2);
        }

        return node;
    }


    Node lcaMethod1UsingIteration(Node node,int n1, int n2){

        //Time Complexity O(h)
        while (node!=null){

            if (node.a>n1 && node.a>n2){
                node=node.left;
            }else if (node.a<n1 && node.a<n2){
                node=node.right;
            }
            else break;

        }
        return node;
    }

    public ParentNode addParentNode(ParentNode root,int value){
        if (root==null){
            return new ParentNode(value);
        }

        if (value<root.a){
            root.left=addParentNode(root.left,value);
            root.left.parent=root;

        }else if (value>root.a){
            root.right=addParentNode(root.right,value);
            root.right.parent=root;
        }

        return root;
    }


    public void insertParentNode(int key){
        parentroot=addParentNode(parentroot,key);
    }

    public ParentNode method2UsingParentPointer(ParentNode n1,ParentNode n2){
        //Time Complexity O(h) and Space Complexity O(h)
        HashMap<ParentNode,Boolean> ancestors= new HashMap<>();

        while (n1!=null){
            ancestors.put(n1,true);
            n1=n1.parent;
        }

        while (n2!=null){
            if (ancestors.get(n2)!=ancestors.isEmpty()){
                return n2;
            }
        }


        return null;

    }


    int depth(ParentNode node){
        int d=-1;
        while (node!=null){
            ++d;
            node=node.parent;
        }
        return d;
    }

    public ParentNode method2UsingParentPointerDifference(ParentNode n1, ParentNode n2){

        //Time Complexity O(h) and Space Complexity O(1)
        int depthOfN1= depth(n1);
        int depthOfN2= depth(n2);

        int diff= depthOfN1-depthOfN2;

        if (diff<0){
            ParentNode temp= n1;
            n1=n2;
            n2=temp;
            diff=-diff;
        }

        while (diff--!=0){
            n1=n1.parent;
        }

        while (n1.parent!=null && n2.parent!=null){
            if (n1==n2){
                return n1;
            }
            n1=n1.parent;
            n2=n2.parent;
        }

        return null;
    }


    public static void main(String[] args) {
        LowestCommonAncestor lowestCommonAncestor= new LowestCommonAncestor();
        lowestCommonAncestor.insert(20);
        lowestCommonAncestor.insert(8);
        lowestCommonAncestor.insert(22);
        lowestCommonAncestor.insert(4);
        lowestCommonAncestor.insert(12);
        lowestCommonAncestor.insert(10);
        lowestCommonAncestor.insert(14);

        Node lca1=lowestCommonAncestor.lcaMethod1UsingRecursion(lowestCommonAncestor.root,10,14);
        Node lca2=lowestCommonAncestor.lcaMethod1UsingRecursion(lowestCommonAncestor.root,14,8);
        Node lca3=lowestCommonAncestor.lcaMethod1UsingRecursion(lowestCommonAncestor.root,10,22);

        System.out.println(lca1.a+"  "+lca2.a+"  "+lca3.a);

        Node lca4=lowestCommonAncestor.lcaMethod1UsingIteration(lowestCommonAncestor.root,10,14);
        Node lca5=lowestCommonAncestor.lcaMethod1UsingIteration(lowestCommonAncestor.root,14,8);
        Node lca6=lowestCommonAncestor.lcaMethod1UsingIteration(lowestCommonAncestor.root,10,22);
        System.out.println(lca4.a+"  "+lca5.a+"  "+lca6.a);

        lowestCommonAncestor.insertParentNode(20);
        lowestCommonAncestor.insertParentNode(8);
        lowestCommonAncestor.insertParentNode(22);
        lowestCommonAncestor.insertParentNode(4);
        lowestCommonAncestor.insertParentNode(12);
        lowestCommonAncestor.insertParentNode(10);
        lowestCommonAncestor.insertParentNode(14);

        ParentNode n1= lowestCommonAncestor.parentroot.left.right.left;
        ParentNode n2= lowestCommonAncestor.parentroot.left;
        ParentNode lca7=lowestCommonAncestor.method2UsingParentPointer(n1,n2);
        System.out.println(lca7.a);

        ParentNode lca8=lowestCommonAncestor.method2UsingParentPointerDifference(n1,n2);
        System.out.println(lca8.a);
    }
}

class Node{
    int a;
    Node left, right;

    Node(int a){
      this.a=a;
      this.left=this.right=null;
    }
}

class ParentNode{
    int a;
    ParentNode left, right,parent;

    ParentNode(int a){
        this.a=a;
        this.left=this.right=this.parent=null;
    }

}
