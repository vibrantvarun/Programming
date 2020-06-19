package Tree;


import java.util.Vector;

public class SubtreeQueries {

    Vector<ClubValues> clubValues;

    int dfsUtil(NodeIdTree tree){
        if (tree==null){
            return 0;
        }

        int res= tree.childs.size();
        clubValues.add(new ClubValues(tree.id,tree.value));
        for (NodeIdTree node: tree.childs){
            res+=dfsUtil(node);
        }

        updateValue(tree,res+1);

        return  res;
    }

    void updateValue(NodeIdTree tree,int res){
        for (int i=0;i<clubValues.size();i++){
            if (clubValues.get(i).dfsId==tree.id){
                clubValues.get(i).subtreesSize=res;
                break;
            }
        }
    }


    void dfs(NodeIdTree tree,int n){
        if (tree==null){
            return;
        }

        clubValues=new Vector<>();
        dfsUtil(tree);

    }


    public static void main(String[] args) {
        NodeIdTree narryTree= new NodeIdTree(1,2);
       narryTree.childs.add(new NodeIdTree(2,3));
       narryTree.childs.add(new NodeIdTree(3,5));
       narryTree.childs.add(new NodeIdTree(4,3));
       narryTree.childs.add(new NodeIdTree(5,1));

       narryTree.childs.get(0).childs.add(new NodeIdTree(6,4));

       narryTree.childs.get(2).childs.add(new NodeIdTree(7,4));
       narryTree.childs.get(2).childs.add(new NodeIdTree(8,3));
       narryTree.childs.get(2).childs.add(new NodeIdTree(9,1));


       SubtreeQueries sq= new SubtreeQueries();
       sq.dfs(narryTree,9);

       for (int i=0;i<sq.clubValues.size();i++){

           System.out.print(sq.clubValues.get(i).dfsId+" ");
       }

       System.out.println();

        for (int i=0;i<sq.clubValues.size();i++){
            System.out.print(sq.clubValues.get(i).subtreesSize+ " ");
        }

        System.out.println();

        for (int i=0;i<sq.clubValues.size();i++){
            System.out.print(sq.clubValues.get(i).dfsValue+" ");
        }

    }
}


class NodeIdTree{

    int id;
    int value;

    Vector<NodeIdTree> childs;

    NodeIdTree(int id, int value){
        this.id=id;
        this.value=value;
        this.childs=new Vector<>();
    }
}

class ClubValues{
    int dfsId;
    int dfsValue;
    int subtreesSize;

    ClubValues(int dfsId,int dfsValue){
        this.dfsId=dfsId;
        this.dfsValue=dfsValue;
        this.subtreesSize=0;
    }

}