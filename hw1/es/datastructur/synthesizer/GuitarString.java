package es.datastructur.synthesizer;

import java.util.Random;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        int size = (int)Math.round(SR/frequency);
        buffer = new ArrayRingBuffer<>(size);
        for (int i=0;i<size;i++){
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other.

        //Dequeue everything in buffer
        int end = buffer.fillCount();
        for (int i=0;i<end;i++){
            buffer.dequeue();
        }
        //Create a array random
        double[] ranArray = new double[buffer.capacity()];
        for (int i=0;i<ranArray.length;i++)
            ranArray[i] = Math.random() - 0.5;
        // Replace with random numbers
        for (int i=0;i< buffer.capacity();i++)
            buffer.enqueue(ranArray[i]);
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double newSample = (buffer.dequeue() + buffer.peek())/2 * DECAY;
        buffer.enqueue(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.peek();
    }
}
    // TODO: Remove all comments that say TODO when you're done.
