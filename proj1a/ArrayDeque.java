import javax.print.attribute.standard.NumberOfInterveningJobs;

public class ArrayDeque <T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.items.length];

        for (int i=0;i < other.size();i++){
            items[i] = (T)other.get(i);
        }

    }

    private void resize(int newLength){
        T[] temp = items;
        items = (T[]) new Object[newLength];
        for (int i=0;i<size;i++){
            items[i] = temp[i];
        }
        nextFirst = newLength-1;
        nextLast = size;
    }

    private void checkSize(){
        if (size == items.length)
            resize(items.length * 2);
        if (size < items.length/4 && items.length > 8)
            resize(items.length /2);
    }

    public void addFirst(T item){
        checkSize();
        items[nextFirst] = item;
        nextFirst--;
        if (nextFirst == -1)
            nextFirst = items.length-1;
        size++;
    }

    public void addLast(T item){
        checkSize();
        items[nextLast] = item;
        nextLast++;
        if (nextLast == items.length)
            nextLast = 0;
        size++;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void printDeque(){
        if (nextFirst < nextLast){
            for (int i = nextFirst+1;i < nextLast;i++){
                System.out.print(items[i] + " ");
            }
        }
        else{
            for (int i= 0;i<nextLast;i++){
                System.out.print(items[i] + " ");
            }
            for (int i = nextFirst+1;i<size;i++){
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    public T removeFirst(){
        checkSize();
        T  result;
        if (nextFirst == items.length - 1)
            nextFirst = 0;
        else
            nextFirst++;
        result = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return result;
    }

    public T removeLast(){
        checkSize();
        T result;
        if (nextLast == 0)
            nextLast = items.length-1;
        else
            nextLast--;
        result = items[nextLast];
        items[nextLast] = null;
        size--;
        return result;
    }

    public T get(int index){
        // Bắt đầu từ first (tính từ first)
        if (index < 0 || index > size)
            return null;
        return items[index];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        a.addFirst(0);
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.removeLast();
        a.addFirst(5);
        a.addFirst(6);
        a.addFirst(7);
        a.addFirst(8);
        Integer b = a.removeLast();
        System.out.println(b);
    }
}
