public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y){
        int dis = x -y;
        return (dis == 1 || dis == -1);
    }

}

