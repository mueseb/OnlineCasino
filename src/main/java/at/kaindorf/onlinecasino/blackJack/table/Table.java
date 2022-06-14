
/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 06.04.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.blackJack.table;

import at.kaindorf.onlinecasino.blackJack.player.BlackJackDealer;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackPlayer;
import lombok.Data;

@Data
public class Table {
    private int playerID;
    private int result;
    private BlackJackDealer dealer;
    private BlackJackPlayer player;
    private Deck deck;

    public BlackJackDealer getDealer() {
        return dealer;
    }

    public void setDealer(BlackJackDealer dealer) {
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

    public Table(BlackJackDealer dealer, BlackJackPlayer player, Deck deck) {
        this.dealer = dealer;
        this.player = player;
        this.deck = deck;
    }
}
