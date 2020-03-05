import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RecursiveTask;

public class Elimination extends RecursiveTask<Player> {
    BlockingQueue<Player> block_queue;
    int start;
    int end;
    int THRESHOLD = 10;

    Elimination(BlockingQueue<Player> block_queue, int start, int end){
        this.block_queue = block_queue;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Player compute() {
        if (end-start <= THRESHOLD) {
            //return winner of match
            System.out.println("Matching player!");
            return matchPlayers();
        }
            int mid = (end + start) / 2;
            //left //right
            Elimination left =  new Elimination(block_queue, start, mid);
            Elimination right = new Elimination(block_queue, mid, end);
            left.fork();
            //can use invoke all left, right,  then return
            //invokeAll(left, right);
            return returnWinner(right.compute(), left.join());
    }
    private Player matchPlayers() {
            Player player1 = block_queue.poll();
                for (int i = start; i < end-1; i++) {
                    Player player2 = block_queue.poll();
                    if (player1 != null) {
                        player1 = returnWinner(player1, player2);
                    }
                }
            //return winner of sublist
            return player1;
    }

    private Player returnWinner(Player player1, Player player2) {
        int checkWin = player1.checkWinningHand(player2);
        //loop until when one wins
        while(checkWin != 1) {
            if (checkWin == -1) {
                player1 = player2;
                checkWin = 1;
            } else if (checkWin == 0) {
                //break tie
                player1.setGesture();
                player2.setGesture();
                checkWin = player1.checkWinningHand(player2);
            }
        }
        //return winner
        return player1;
    }
}
