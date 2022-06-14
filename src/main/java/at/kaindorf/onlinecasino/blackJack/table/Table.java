
/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 06.04.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.blackJack.table;

import at.kaindorf.onlinecasino.blackJack.BlackJack;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackDealer;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackPlayer;
import at.kaindorf.onlinecasino.db.DBdata.DBgame;
import lombok.Data;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class Table {
    private int playerID;
    private int result;
    private BlackJackDealer dealer;
    private BlackJackPlayer player;
    private Deck deck;

    private static Table tableinstance;

    public BlackJackDealer getDealer() {
        return dealer;
    }

    public static Table getInstance()
    {
        return tableinstance;
    }

    public static void setTableinstance(Table table) {
        Table.tableinstance = table;
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
