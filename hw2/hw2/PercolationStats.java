package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double list[];
    private int size;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        size = N;
        this.T = T;
        list = new double[T];

        for (int i=0;i<T;i++){
            Percolation p = pf.make(size);
            while (!p.percolates()){
                int x = StdRandom.uniform(0,size);
                int y = StdRandom.uniform(0,size);
                if (!p.isOpen(x,y)){
                    p.open(x,y);
                }
            }
            list[i] = p.numberOfOpenSites()/(size*size);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(list);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(list);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return mean() + (1.96*stddev()/Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return mean() - (1.96*stddev()/Math.sqrt(T));
    }
}
