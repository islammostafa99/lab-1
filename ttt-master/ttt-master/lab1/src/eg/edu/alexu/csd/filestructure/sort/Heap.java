package eg.edu.alexu.csd.filestructure.sort;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Heap<T extends Comparable<T>> implements IHeap<T> {
    private Node left;
    private Node right;
    private Node largest;
    private INode swap;
    private INode temp;
    private INode temp2 ;
    private INode temp3 ;
    ArrayList<INode> unordered = new ArrayList();
    private int index;
    private int lastPosititon = 1;
    private int position =1;
    private int a, b,c;

    //public Heap(ArrayList unordered) {
       // for (int i=0;i<unordered.size();i++){
           // INode s=new Node();
            //s.setValue((Comparable) unordered.get(i));
           // this.unordered.add(i,s);
        //}
    //}
    public Heap() {
        unordered.add(null) ;
    }

    /*public static  Heap ob=new Heap();
    public static Heap getInst() {
        if(ob==null) {
            return new Heap();
        }
        return ob;
    }*/

    @Override
    public INode<T> getRoot() {
        if(this.unordered.size()==1){
            return  null ;
        }
        return this.unordered.get(1);
    }

    @Override
    public int size() {
        return this.unordered.size()-1;
    }

    @Override
    public void heapify(INode<T> node) {
        if(node==null){
            return;
        }
        Node t = (Node) node;
        if (node.getLeftChild() == null) {
            return;
        }
//        if(node.getRightChild()==null){
//            return;
//        }
        left = (Node) node.getLeftChild();
        right = (Node) node.getRightChild();

        /*for (int i = 1; i < this.unordered.size(); i++) {
            if (this.unordered.get(i).getValue().equals(node)) {
                index = i;
            }
        }*/
        index=t.getIndex() ;
        if (left.getValue().compareTo(node.getValue()) > 0) {
            largest = left;
        } else {
            largest = (Node) node;
        }
        if (right.getValue().compareTo(largest.getValue()) > 0) {
            largest = right;
        }
        /*for (int i = 1; i < this.unordered.size(); i++) {
            if (this.unordered.get(i).getValue().equals(largest)) {
                b = i;
            }
        }*/
        b=largest.getIndex() ;
        a = index;
        if (a != b && b<this.unordered.size()&&a<this.unordered.size()) {
            swap = largest;
            this.unordered.remove(b);
            this.unordered.add(b, node);
            this.unordered.remove(a);
            this.unordered.add(a, swap);
            heapify(largest);
        }
        return;
    }


    @Override
    public T extract() {
        if(this.unordered.size()==1){
            return  null ;
        }
        Collection<T> hh=new ArrayList<T>((Collection<? extends T>) this.unordered);
        build(hh);
        temp=this.unordered.get(1);
        temp3=this.unordered.get(this.unordered.size()-1);
        this.unordered.remove(1);
        this.unordered.add(1,temp3);
        this.unordered.remove(this.unordered.size()-1);
        lastPosititon-- ;
        if(this.unordered.size()!=1) {
            temp2 = this.unordered.get(1);
            heapify(temp2);
        }
        return (T) temp.getValue();
    }

    /*private void trickleDown(int parent) {
        if (parent * 2 + 1 == lastPosititon && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 1)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 1));
            this.unordered.remove(parent*2+1);
            this.unordered.add(parent*2+1, swap);
            return;
        }
        if (parent * 2 + 2 == lastPosititon && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 2)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 2));
            this.unordered.remove(parent*2+2);
            this.unordered.add(this.unordered.indexOf(parent * 2 + 2), swap);
            return;
        }
        if (parent * 2 + 1 >= lastPosititon || parent * 2 + 2 >= lastPosititon) {
            return;
        }
        if ((int) this.unordered.get(parent * 2 + 1) > (int) this.unordered.get(parent * 2 + 2) && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 1)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 1));
            this.unordered.remove(parent*2+1);
            this.unordered.add(parent * 2 + 1, swap);
            trickleDown(parent * 2 + 1);
        } else if ((int) this.unordered.get(parent * 2 + 2) > (int) this.unordered.get(parent * 2 + 1) && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 2)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 2));
            this.unordered.remove(parent*2+2);
            this.unordered.add(parent * 2 + 2, swap);
            trickleDown(parent * 2 + 2);
        }
    }*/

    @Override
    public void insert(T element) {
        if(element==null){
            return;
        }
        //build(this.unordered);
        //lastPosititon = this.unordered.size() - 1;
        Node n = new Node(unordered);
        n.setIndex(lastPosititon);
        n.setValue(element);
        this.unordered.add(lastPosititon,n);
        lastPosititon++ ;
        //e.arr.add(n) ;
        trickleUp(lastPosititon-1);
        //int i=lastPosititon-1;
       /* while(i!=1&&i!=0 &&(((Integer) unordered.get(i).getValue()).intValue())>(((Integer) unordered.get(i).getValue()).intValue())) {
            Integer temp= (Integer) unordered.get(i).getValue();
            unordered.get(i).setValue(unordered.get(i/2).getValue());
            unordered.get(i/2).setValue(temp);
            //System.out.println("element"+" "+unordered.get(i)[i].getValue()+"swaped"+unordered.get(i)[i/2].getValue());
            unordered.get(i/2).setValue((Integer) temp);
            //System.out.println("element"+" "+arr[i].getValue()+"swaped"+arr[i/2].getValue());
            i=i/2;
        }*/

    }

    private void trickleUp(int lastPosititon) {
        if (lastPosititon == 1) {
            return;
        }

       // INode K = new Node();
        //K.setValue(this.unordered.get(lastPosititon));
        if(lastPosititon==0||lastPosititon>=this.unordered.size()){
            return;
        }
        Node child = (Node) this.unordered.get(lastPosititon);
        if(child.getValue()==null){
            return;
        }
        //int num = child.getIndex() ;
        //INode child = K;
        Node parent = (Node)child.getParent();

        if (child.getValue().compareTo(parent.getValue()) > 0) {
            swap = child;
            this.unordered.remove(lastPosititon);
            this.unordered.add(lastPosititon, parent);

//           for (int i = 1; i < this.unordered.size(); i++) {
//                if (this.unordered.get(i).equals(parent.getValue())) {
//                    c = i;
//                }
//            }
            c=parent.getIndex() ;
            this.unordered.remove(c);
            this.unordered.add(c, swap);
            int l = lastPosititon;
            l=l/2;
            trickleUp(l);

        }
        return;
    }




    @Override
    public void build(java.util.Collection<T> unordered) {
        if(unordered==null){
            return;
        }
        /*for (int j = 0; j < unordered.size() ; j++) {
            this.unordered.add(j, (INode) unordered.iterator().next());
        }*/
        /*for (Object e : unordered){
           insert((Comparable) e);
        }*/
        if (unordered.size()==0){
            return;
        }
        this.unordered.clear();
        this.unordered.add(0,null);

        Iterator<T> it=unordered.iterator();
        lastPosititon=1;
        while (it.hasNext()){
            Node n =new Node(this.unordered);
            n.setIndex(lastPosititon);
            n.setValue(it.next());
            this.unordered.add(lastPosititon,n);
            lastPosititon++ ;
        }
        for (int i =((this.unordered.size() / 2)); i > 0; i--) {
            heapify(this.unordered.get(i));
        }
        for(int i = this.unordered.size()-1;i>0;i--){
            //Collections.swap(this.unordered,1,i);
            heapify(this.unordered.get(i));
            // MOMKN swap
        }
    }

    public void make(T val){
        Node l = new Node(this.unordered) ;
        l.setValue(val);
        l.setIndex(lastPosititon);
        this.unordered.add(lastPosititon,l);
        lastPosititon++ ;
    }
}