package at.kaindorf.onlinecasino.blackJack.player;

import at.kaindorf.onlinecasino.blackJack.table.Hand;


//Generic Player
public class Player {
    private Hand hand;
    private boolean canWin;

    public Player(Hand hand) {
        this.hand = hand;
        this.canWin = true;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean canWin() {
        return canWin;
    }

    public void setCanWin(boolean canWin) {
        this.canWin = canWin;
    }
}
