package Tree;

import java.util.Iterator;
import java.util.Vector;

public class DiameterOfNAryTree {


    int height(NarrayNode node){

        if (node==null){
            return 0;
        }

        Iterator<NarrayNode> itr= node.childs.iterator();
        int maxheight=0;
        while (itr.hasNext()){

            NarrayNode child= itr.next();
            int childhieght= height(child);

            if(childhieght>maxheight){
                maxheight=childhieght;
            }

        }
        return maxheight+1;
    }

    int diameter(NarrayNode root){

        int max1=0; int max2=0;
        Iterator<NarrayNode> itr= root.childs.iterator();


        while (itr.hasNext()){
            int height= height(itr.next());
            if (height>max1){
                max2=max1;
                max1=height;
            }else if (height>max2){
                max2=height;
            }
        }



        int maxChildDiameter=0;

        for (NarrayNode child:root.childs){
            maxChildDiameter=Math.max(diameter(child),maxChildDiameter);
        }

        return Math.max(maxChildDiameter,max1+max2+1);
    }



    public static void main(String[] args) {

        NarrayNode root= new NarrayNode(1);

        (root.childs).add(new NarrayNode(2));
        (root.childs).add(new NarrayNode(2));
        (root.childs).add(new NarrayNode(2));
        (root.childs).add(new NarrayNode(2));
        (root.childs.get(0).childs).add(new NarrayNode(2));
        (root.childs.get(0).childs).add(new NarrayNode(2));
        (root.childs.get(2).childs).add(new NarrayNode(2));
        (root.childs.get(3).childs).add(new NarrayNode(2));
        (root.childs.get(3).childs).add(new NarrayNode(2));
        (root.childs.get(3).childs).add(new NarrayNode(2));
        (root.childs.get(0).childs.get(0).childs).add(new NarrayNode(2));
        (root.childs.get(0).childs.get(0).childs).add(new NarrayNode(2));
        (root.childs.get(3).childs.get(2).childs).add(new NarrayNode(2));

        DiameterOfNAryTree dNary= new DiameterOfNAryTree();
        System.out.println(dNary.diameter(root));
    }
}


class NarrayNode{
    int a;
    Vector<NarrayNode> childs;

    NarrayNode(int a){
        this.a=a;
        this.childs=new Vector<>();
    }

}
