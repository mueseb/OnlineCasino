/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack;

import at.kaindorf.onlinecasino.beans.Dealer;
import at.kaindorf.onlinecasino.beans.Deck;
import at.kaindorf.onlinecasino.beans.Player;

import java.util.List;

public class Table {
    private Dealer dealer;
    private List<Player> players;
    private Deck deck;

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Table(Dealer dealer, List<Player> players, Deck deck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = deck;
    }
}
