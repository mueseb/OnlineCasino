/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack.player;

import at.kaindorf.onlinecasino.beans.Hand;

public class BlackJackPlayer extends Player{
    private int turn;
    private int bet;
    private boolean stand;

    public int getTurn() {
        return turn;
    }

    public void addTurn()
    {
        turn++;
    }

    public BlackJackPlayer() {
        super(new Hand());
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

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
