/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.beans;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Hand hand;
    private int turn;
    private int bet;
    private boolean stand;

    public Hand getHand() {
        return hand;
    }

    public int getTurn() {
        return turn;
    }

    public void addTurn()
    {
        turn++;
    }

    public Player() {
        this.turn = 1;
        this.stand = false;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean isStand() {
        return stand;
    }

    public void setStand(boolean stand) {
        this.stand = stand;
    }
}
