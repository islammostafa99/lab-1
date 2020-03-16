package eg.edu.alexu.csd.filestructure.sort;
import java.util.ArrayList;
import java.util.Vector;
public class Node<T extends Comparable<T>> implements INode<T> {
    private T node=null;
    private int index;
    ArrayList<INode> k ;
    //Heap ob= Heap.getInst();
   /* public Node(Object object){
        for (int i=0;i<Controller.arr.size();i++){
            if(Controller.arr.elementAt(i).getValue().equals(object)){
                index = i;
            }
        }
    }*/
    public Node( ArrayList unordered){
       k=unordered ;
       node=null ;

    }
    public Node () {

    }
    @Override
    public INode<T> getLeftChild() {
        if((index*2)>=k.size()){
            return null;
        }
        return  k.get(index*2);
    }
    @Override
    public INode<T> getRightChild() {
        if((index*2+1)>=k.size()){
            return null;
        }
        return k.get(index*2+1);
    }
    @Override
    public INode<T> getParent() {
        if(index==1){
            return null;
        }
        return k.get((int) Math.floor((index)/2));
    }
    @Override
    public T getValue() {
        if(node==null){
            return  null ;
        }

        return (T) node;
    }
    @Override
    public void setValue(T value) {
        node=value;
    }
    public void setIndex (int i){
        index=i ;
    }
    public int getIndex () {
        return index ;
    }


}