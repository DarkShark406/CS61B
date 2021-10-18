public class OffByN implements CharacterComparator {
    private int N;
    public OffByN(int num){
        N = num;
    }

    @Override
    public boolean equalChars(char x, char y){
        int dis = x - y;
        return (dis == N || dis == -N);
    }
}
