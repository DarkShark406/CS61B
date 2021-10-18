public class LinkedListDeque <T> {
    private class TNode{
        public T item;
        public TNode prev;
        public TNode next;
        public TNode(T x, TNode p, TNode n){
            item = x;
            prev = p;
            next = n;
        }

        public TNode(T x){
            item = x;
        }
    }
    private TNode sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new TNode((T)null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new TNode(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i=0;i< other.size();i++){
            addLast((T)other.get(i));
        }

    }

    public LinkedListDeque(T item) {
        sentinel = new TNode(null, null, null);
        sentinel.next = new TNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item){
        sentinel.next = new TNode(item,sentinel,sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item){
        sentinel.prev = new TNode(item,sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty(){
        if (size == 0)
            return true;
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        TNode temp = sentinel;
        while (temp.next != sentinel) {
            temp = temp.next;
            System.out.print(temp.item + " ");
        }

        for (TNode temp2 = sentinel.next; temp2 != sentinel ; temp2 = temp2.next){
            System.out.print(temp2.item + " ");
        }
        System.out.println();
    }

    public T removeFirst(){
        if (size == 0)
            return null;
        size --;
        T result = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return result;
    }

    public T removeLast() {
        if (size == 0)
            return null;
        size--;
        T result = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return result;
    }

    public T get(int index){
        if (index < 0 || index > size)
            return null;
        TNode temp = sentinel.next;

        while (temp.next != sentinel.next){
            if (index == 0)
                return temp.item;
            index--;
            temp = temp.next;
        }
        return null;
    }

    public T getRecursive_Support(int index, TNode temp){
        if (index == 0)
            return temp.item;

        return getRecursive_Support(index--, temp.next);
    }

    public T getRecursive(int index){
        if (index < 0 || index > size)
            return null;
        TNode temp = sentinel.next;
        return getRecursive_Support(index,temp);
    }

    public static void main(String[] args) {
        LinkedListDeque a = new LinkedListDeque();
        a.addLast(5);
        a.addFirst(100);
        a.addFirst(10);
        a.addLast(15);
        a.addFirst(20);
        System.out.println(a.size());
        System.out.println(a.get(0));
        System.out.println(a.getRecursive(0));
        a.printDeque();
    }
}
