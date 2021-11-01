public class UnionFind {

    int[] parents;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parents = new int[n];
        for (int i = 0;i<n;i++)
                parents[i] = -1;

    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= parents.length)
            throw new IllegalArgumentException("v1 is not valid");
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return Math.abs(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        try{
            validate(v1);
            validate(v2);
        }
        catch (Exception e){

        }
        if (connected(v1,v2))
            return;
        int weight1 = sizeOf(v1);
        int weight2 = sizeOf(v2);
        // Case: <, >, =
        if (weight1 <= weight2){
            int root1 = find(v1);
            int root2 = find(v2);
            parents[root2] -= weight1;
            parents[root1] = root2;
        }
        else {
            int root1 = find(v1);
            int root2 = find(v2);
            parents[root1] -= weight2;
            parents[root2] = root1;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int x = parent(vertex);
        if (x < 0) return vertex;
        else
            return find(parent(vertex));
    }

    public static void main(String[] args) {

    }
}
