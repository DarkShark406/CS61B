package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean grid[][];
    private int size;
    private int openSites;

    // calculate index at site (i,j)
    public int indexSite(int row,int col){
        return size*row + col;
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if (N <= 0){
            throw new IllegalArgumentException("N must be greater than 0");
        }
        uf = new WeightedQuickUnionUF(N*N);
        size = N;
        openSites = 0;
        grid = new boolean[N][N];
        // All sites of the grid at the beginning are close (false)
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                grid[i][j] = false;
            }
        }
    }

    private void checkConnect(int row,int col){
        if (row == 0){
            if (col == 0){
                checkConnectRight(row,col);
                checkConnectDown(row,col);
            }
            else if (col == size-1){
                checkConnectLeft(row,col);
                checkConnectDown(row,col);
            }
            else{
                checkConnectLeft(row,col);
                checkConnectRight(row,col);
                checkConnectDown(row,col);
            }
        }
        else if (row == size - 1){
            if (col == 0){
                checkConnectRight(row,col);
                checkConnectUp(row,col);
            }
            else if (col == size-1){
                checkConnectLeft(row,col);
                checkConnectUp(row,col);
            }
            else{
                checkConnectLeft(row,col);
                checkConnectRight(row,col);
                checkConnectUp(row,col);
            }
        }
        else{
            if (col == 0){
                checkConnectRight(row,col);
                checkConnectUp(row,col);
                checkConnectDown(row,col);
            }
            else if (col == size - 1){
                checkConnectLeft(row,col);
                checkConnectUp(row,col);
                checkConnectDown(row,col);
            }
            else{
                checkConnectLeft(row,col);
                checkConnectRight(row,col);
                checkConnectUp(row,col);
                checkConnectDown(row,col);
            }
        }
    }

    private void checkConnectLeft(int row, int col){
        if (grid[row][col-1] == true)
            uf.union(indexSite(row,col),indexSite(row,col-1));
    }

    private void checkConnectRight(int row, int col){
        if (grid[row][col+1] == true)
            uf.union(indexSite(row,col),indexSite(row,col+1));
    }

    private void checkConnectUp(int row, int col){
        if(grid[row-1][col] == true)
            uf.union(indexSite(row,col),indexSite(row-1,col));
    }

    private void checkConnectDown(int row, int col){
        if(grid[row+1][col] == true)
            uf.union(indexSite(row,col),indexSite(row+1,col));
    }


    private boolean validate(int row,int col){
        if (row < 0 || col < 0 || row >= size || col >= size)
            return true;
        return false;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        if (validate(row,col)){
            throw new IndexOutOfBoundsException();
        }
        if(!grid[row][col]) {
            grid[row][col] = true;
            openSites++;
            checkConnect(row,col);
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (validate(row,col)){
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (validate(row,col)){
            throw new IndexOutOfBoundsException();
        }
        for (int i=0;i<size-1;i++){
            if (uf.connected(indexSite(row,col),indexSite(0,i)))
                return true;
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites(){
        return openSites;
    }

    // does the system percolate?
    public boolean percolates(){
        for (int i = 0;i<size;i++){
            if (isFull(size-1,i))
                return true;
        }
        return false;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){

    }
}
