public final class Players {
    private Players(){}

    public static final int L = 0;
    public static final int R = 1;
    public static final int U = 2;

    public static char toChar(int a){
        if(a == L)
            return 'L';
        else if(a == R)
            return 'R';
        else
            return 'U';
    }

    public static String playerName(int p){return (p == L) ? "Left" : "Right";}

    public static int switchTurn(int turn){return (turn == L) ? R : L;}
}
