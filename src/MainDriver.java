import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.*;

public class MainDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Blocking queue
        System.out.println("Please enter the amount of players: ");
        int totalPlayers = sc.nextInt();
        Player[] players = new Player[totalPlayers];

        //create and set players
        for(int i = 0; i < players.length; i++){
            players[i] = new Player(i+1);
            players[i].setGesture();
        }

        System.out.println("Players created: " + players.length);

        BlockingQueue<Player> playerInQueue = new ArrayBlockingQueue<>(totalPlayers);

        playerInQueue.addAll(Arrays.asList(players));
        Elimination elim = new Elimination(playerInQueue, 0, totalPlayers);
        Player playerWinner = callWinner(elim);

        System.out.println("Winner is: Player " + playerWinner.getPlayerNumber());
    }

    private static Player callWinner(Elimination elim) {

        ForkJoinPool fjp = new ForkJoinPool();
        long start = System.nanoTime();
        Player playerWinner = fjp.invoke(elim);
        fjp.shutdown();
        System.out.println((System.nanoTime() - start)/1000);
        return playerWinner;
    }
}
