
/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 06.04.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.blackJack.table;

import at.kaindorf.onlinecasino.blackJack.player.Dealer;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackPlayer;
import at.kaindorf.onlinecasino.db.DBplayer;
import lombok.Data;

import java.util.List;

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
