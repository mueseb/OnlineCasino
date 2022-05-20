/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.web.resource;

import at.kaindorf.onlinecasino.blackJack.BlackJack;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackPlayer;
import at.kaindorf.onlinecasino.blackJack.player.Dealer;
import at.kaindorf.onlinecasino.blackJack.table.Deck;
import at.kaindorf.onlinecasino.blackJack.table.Table;
import at.kaindorf.onlinecasino.db.BlackjackDB;
import at.kaindorf.onlinecasino.db.DBgame;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Path("/cards")
public class CardResource {

    Dealer dealer;
    BlackJackPlayer blackJackPlayer;
    Deck deck;
    Table table;
    BlackJack blackJack;
    BlackjackDB blackjackDB;
    DBgame game;

    @GET
    public Response initGame(int id)
    {
        game = new DBgame();
        dealer = new Dealer();
        List<BlackJackPlayer> players = new ArrayList<>();
        BlackJackPlayer player = new BlackJackPlayer();
        players.add(new BlackJackPlayer());
        deck = new Deck();
        table = new Table(dealer,player,deck);
        blackJack = new BlackJack();
        table.setPlayerID(id);
        game.setStartTime(Date.valueOf(LocalDate.now()));
        return Response.ok().build();
    }

    @GET
    public String getPlayerStarterCards()
    {
        table.getPlayer().getHand().addCardsToHand(table.getDeck().getCardsFromDeck(2));
        return getPlayerCards(table.getPlayer());
    }

    @GET
    public String getDealerStarterCards()
    {
        table.getDealer().getHand().addCardsToHand(table.getDeck().getCardsFromDeck(2));
        return getDealerCards(table.getDealer());
    }

    @GET
    public String addDealerCard()
    {
        table.getDealer().getHand().addCardToHand(table.getDeck().getCardFromDeck());
        return getDealerCards(table.getDealer());
    }

    @GET
    public String addPlayerCard()
    {
        table.getPlayer().getHand().addCardToHand(table.getDeck().getCardFromDeck());
        return getPlayerCards(table.getPlayer());
    }

    @GET
    public String onDoubleDown()
    {
        table.getPlayer().getHand().addCardToHand(table.getDeck().getCardFromDeck());
        table.getPlayer().setStand(true);
        return getPlayerCards(table.getPlayer());
    }

    @GET
    public Response onStand()
    {
        table.getPlayer().setStand(true);
        return Response.ok().build();
    }

    @GET
    public int onEndGame()
    {
        //1:Player Win; 2:Dealer Win; 3:Draw
        int num;
        int i = blackJack.winnerCheck(table);
        table.setResult(i);
        return i;
    }

    public void saveGame()
    {
        try {
            blackjackDB = BlackjackDB.getInstance();
            game.setDealerHand(table.getDealer().getHand());
            game.setPlayerHand(table.getPlayer().getHand());
            game.setResult(table.getResult());
            game.setEndTime(Date.valueOf(LocalDate.now()));
//            game.setBet();//TODO bet
            blackjackDB.saveGameStat(game);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getDealerCards(Dealer dealer){
        String cards="?;";
        cards+=table.getPlayer().getHand().getCards().get(1);
        return cards;
    }

    public String getPlayerCards(BlackJackPlayer player){
        String cards="";
        cards+=table.getPlayer().getHand().getCards().get(0);
        cards+=";";
        cards+=table.getPlayer().getHand().getCards().get(1);
        return cards;
    }
}
