public class Chain {
    private int left;
    private int right;
    private int len;

    public Chain(int left, int right, int len){
        this.left = left;
        this.right = right;
        this.len = len;
    }

    public boolean legalMove(int i, int turn){
        return !((i == 1 && left != turn) || (i == len && right != turn));
    }

    public boolean isInteger(){return (len == 1 || (len == 2 && left == right));}

    public int getLeft(){return left;}

    public Chain[] move(int i, int turn){
        Chain[] result = new Chain[2];
        if(len == 1 || (len == 2 && left != right) || (len == 3 && turn != left && turn != right))
            return result;
        else if(len == 2 || (len == 3 && i == 2 && !(turn == left && turn == right))){
            result[0] = new Chain(turn,turn,1);
            return result;
        }else if(len == 3 && i == 2){
            result[0] = new Chain(turn,turn,1);
            result[1] = new Chain(turn,turn,1);
        }
        if(i == 1){
            result[0] = new Chain(turn,right,len-1);
        }else if(i == len)
            result[0] = new Chain(left,turn,len-1);
        else{
            int j = 0;
            if(i == 2){
                if(left == turn)
                    result[j++] = new Chain(turn,turn,1);
                result[j] = new Chain(turn,right,len-2);
            }else if(i == len-1){
                result[j++] = new Chain(left,turn,len-2);
                if(right == turn)
                    result[j] = new Chain(turn,turn,1);
            }else{
                result[j++] = new Chain(left,turn,i-1);
                result[j] = new Chain(turn,right,len-i);
            }
        }
        return result;
    }

    public int size(){return len;}
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(Players.toChar(left));
        s.append(len);
        s.append(Players.toChar(right));
        return s.toString();
    }
}
