package es.datastructur.synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }


    public int capacity() {
        return rb.length;
    }


    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {

        if (isFull())
        {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last++;
        if (last == capacity())
            last = 0;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (isEmpty()){
            throw new RuntimeException("Ring Buffer underflow");
        }

        T res = rb[first];
        first++;
        if (first == capacity())
            first = 0;
        fillCount--;
        return res;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {

        if (isEmpty()){
            throw new RuntimeException("Ring Buffer underflow");
        }

        return rb[first];
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator<T> implements Iterator<T>{

        private int index;
        private int numOfItems;

        @Override
        public boolean hasNext() {
            return numOfItems<fillCount;
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new RuntimeException("there's no more elements to return");
            }

            T nextItem = (T) rb[index];
            index++;
            if (index == capacity())
                index = 0;
            numOfItems++;
            return nextItem;
        }
    }
    @Override
    public boolean equals(Object o){
        if (o == null) return false; // check null
        if (this.getClass() != o.getClass()) return false; // check equal class

        ArrayRingBuffer<T> arbO = (ArrayRingBuffer <T>) o;  // (type) object o
        if (fillCount != arbO.fillCount)     return  false;
        T[] itemArray = (T[]) new Object[arbO.fillCount];

        int index = 0;
        for (T item : arbO) {
            itemArray[index++] = item;
        }

        T[] curArray = (T[]) new Object[fillCount];
        index = 0;
        for (T item : this) {
            curArray[index++] = item;
        }

        for (int i = 0; i < curArray.length; i++) {
            if (curArray[i] != itemArray[i])
                return false;
        }
        return true;

    }
}

