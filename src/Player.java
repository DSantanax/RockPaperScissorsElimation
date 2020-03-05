import java.util.Random;

public class Player {
    private static Random rand = new Random();
    private char handGesture;
    private int playerNumber;

    public Player(int num){
        playerNumber = num;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    public void setGesture() {
        int gesture = rand.nextInt(3);
        if(gesture == 0){
            handGesture = 'r';
        }
        else if(gesture == 1){
            handGesture ='p';
        }
        else{
            handGesture = 's';
        }
    }
    //can change to addScore();

    public char getHandGesture(){return handGesture;}

    public int checkWinningHand(Player p2){

        char player2Gesture = p2.getHandGesture();
        //s>p
        if(handGesture == 's' && player2Gesture == 'p'){
            System.out.println("Player: " + playerNumber + " Scissors beats Player: "+ p2.getPlayerNumber() + " Paper!");
            return 1;
        }
        //r>s
        else if(handGesture == 'r' && player2Gesture == 's'){
            System.out.println("Player: " + playerNumber + " Rock beats Player: "+ p2.getPlayerNumber() + " Scissors!");
            return 1;

        }
        //p>r
        else if(handGesture == 'p' && player2Gesture == 'r'){
            System.out.println("Player: " + playerNumber + " Paper beats Player: "+ p2.getPlayerNumber() + " Rock!");
            return 1;

        }
        //tie
        else if(handGesture == player2Gesture){
            System.out.println("Tie! " + "Player: " + playerNumber  + " vs Player: "+ p2.getPlayerNumber() + " Going again!");
            return 0;

        }
        //lost
        else {
            System.out.println("Player: " + playerNumber +" lost! against Player: " + p2.getPlayerNumber());
            return -1;
        }
    }
}
