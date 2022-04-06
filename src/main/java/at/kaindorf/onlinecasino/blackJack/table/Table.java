/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack.table;

import at.kaindorf.onlinecasino.blackJack.player.Dealer;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackPlayer;

import java.util.List;

public class Table {
    private Dealer dealer;
    private List<BlackJackPlayer> players;
    private Deck deck;

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public List<BlackJackPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<BlackJackPlayer> players) {
        this.players = players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Table(Dealer dealer, List<BlackJackPlayer> players, Deck deck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = deck;
    }
}
