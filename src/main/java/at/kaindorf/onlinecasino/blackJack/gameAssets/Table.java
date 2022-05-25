/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack.gameAssets;

import at.kaindorf.onlinecasino.blackJack.player.Dealer;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackPlayer;
import lombok.Data;

@Data
public class Table {
    private int playerID;
    private int result;
    private Dealer dealer;
    private BlackJackPlayer player;
    private Deck deck;

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public BlackJackPlayer getPlayer() {
        return player;
    }

    public void setPlayers(BlackJackPlayer player) {
        this.player = player;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Table(Dealer dealer, BlackJackPlayer player, Deck deck) {
        this.dealer = dealer;
        this.player = player;
        this.deck = deck;
    }
}
