package at.kaindorf.onlinecasino.blackJack.player;

import at.kaindorf.onlinecasino.blackJack.gameAssets.Hand;


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

//    public boolean canWin2(boolean... boo)
//    {
//        if(boo.length!=0)
//        {
//            canWin=boo[0];
//        }
//        return canWin;
//    }

    public boolean canWin() {
        return canWin;
    }

    public void setCanWin(boolean canWin) {
        this.canWin = canWin;
    }
}
