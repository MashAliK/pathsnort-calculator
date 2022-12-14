import java.util.ArrayList;
import java.util.Collections;
public class Game {
    ArrayList<Chain> board;
    int turn;
    private static final int targetDep = 0;

    public Game(int inpTurn){
        board = new ArrayList<>();
        turn = inpTurn;
    }

    public Game(ArrayList<Chain> inpBoard, int inpTurn){
        board = inpBoard;
        turn = inpTurn;
    }

    public void add(Chain c){
        board.add(c);
    }

    public ArrayList<Chain> move(int chainNum, int index){
        ArrayList<Chain> newBoard = new ArrayList<>();
        for(int i = 0; i < chainNum; i++)
            newBoard.add(board.get(i));
        Chain[] newChains = board.get(chainNum).move(index,turn);
        if(newChains[0] != null)
            newBoard.add(newChains[0]);
        if(newChains[1] != null)
            newBoard.add(newChains[1]);
        for(int i = chainNum+1; i < board.size(); i++)
            newBoard.add(board.get(i));
        return newBoard;
    }
    public boolean atStop(){
        for(Chain c: board)
            if(!c.isInteger())
                return false;
        return true;
    }

    public int stop(){
        return stop(0);
    }
    private int stop(int recD){
        if(atStop()){
            int stopValue = 0;
            for(Chain c: board)
                stopValue = stopValue + ((c.getLeft() == Players.L) ? 1 : -1)*(c.size());
            return stopValue;
        }
        int moveCount = 0;
        ArrayList<Integer> stops = new ArrayList<Integer>();
        for(int i = 0; i < board.size(); i++){
            Chain c = board.get(i);
            for(int j = 1; j <= c.size(); j++){
                if (c.legalMove(j,turn)) {
                    Game newGame = new Game(move(i,j),Players.switchTurn(turn));
                    if(recD < targetDep) {
                        for (int k = 0; k < recD; k++)
                            System.out.print("\t");
                        System.out.print(Players.playerName(turn) + " moving to node " + j + " in chain number " +
                                (i + 1) + " (" + c + ")");
                        if(recD != targetDep-1)
                            System.out.println();
                    }
                    int moveStop = newGame.stop(recD+1);
                    stops.add(moveStop);
                    if(recD == targetDep-1)
                        System.out.println(" (Resulting stop: "+moveStop+")");
                    moveCount++;
                }
            }
        }
        if(turn == Players.L)
            return(Collections.max(stops));
        else
            return(Collections.min(stops));
    }


}
