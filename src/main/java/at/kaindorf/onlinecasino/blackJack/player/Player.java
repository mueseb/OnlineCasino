package at.kaindorf.onlinecasino.blackJack.player;

import at.kaindorf.onlinecasino.blackJack.table.Hand;

public class Player {
    private Hand hand;
    private boolean win;

    public Player(Hand hand) {
        this.hand = hand;
        this.win = true;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
