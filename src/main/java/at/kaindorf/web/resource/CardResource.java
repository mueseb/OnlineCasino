/*
    @author: Sebastian Münzer & Armin Hartner
    @date: 29.04.2022
    @project-name: Online Casino
*/
package at.kaindorf.web.resource;

import at.kaindorf.onlinecasino.blackJack.BlackJack;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackPlayer;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackDealer;
import at.kaindorf.onlinecasino.blackJack.table.Deck;

import at.kaindorf.onlinecasino.blackJack.table.Table;
import at.kaindorf.onlinecasino.db.BlackjackDB;
import at.kaindorf.onlinecasino.db.DBdata.DBgame;
import at.kaindorf.web.beans.LoginData;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/game")
public class CardResource {

    BlackJackDealer dealer;
    BlackJackPlayer blackJackPlayer;
    Deck deck;
    Table table;
    BlackJack blackJack;
    BlackjackDB blackjackDB;
    DBgame game;


    public Response initGame(int id)
    {
        game = new DBgame();
        dealer = new BlackJackDealer();
        List<BlackJackPlayer> players = new ArrayList<>();
        BlackJackPlayer player = new BlackJackPlayer();
        players.add(new BlackJackPlayer());
        deck = new Deck();
        table = new Table(dealer,player,deck);
        blackJack = new BlackJack();
        table.setPlayerID(id);
        game.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        return Response.ok().build();
    }


    public String getPlayerStarterCards()
    {
        table.getPlayer().getHand().addCardsToHand(table.getDeck().getCardsFromDeck(2));
        return getPlayerCards(table.getPlayer());
    }


    public String getDealerStarterCards()
    {
        table.getDealer().getHand().addCardsToHand(table.getDeck().getCardsFromDeck(2));
        return getDealerCards(table.getDealer());
    }


    public String addDealerCard()
    {
        table.getDealer().getHand().addCardToHand(table.getDeck().getCardFromDeck());
        return getDealerCards(table.getDealer());
    }


    public String addPlayerCard()
    {
        table.getPlayer().getHand().addCardToHand(table.getDeck().getCardFromDeck());
        return getPlayerCards(table.getPlayer());
    }


    public String onDoubleDown()
    {
        table.getPlayer().getHand().addCardToHand(table.getDeck().getCardFromDeck());
        table.getPlayer().setStand(true);
        return getPlayerCards(table.getPlayer());
    }


    public Response onStand()
    {
        table.getPlayer().setStand(true);
        return Response.ok().build();
    }


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
            game.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
//            game.setBet();//TODO bet
            blackjackDB.saveGameStat(game);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Produces
    public int getPlayerBalance(LoginData loginData)
    {
        int balance = 0;
        try {
            if(blackjackDB == null)
            {
                blackjackDB = BlackjackDB.getInstance();
            }
            balance=blackjackDB.getUserBalance(loginData.getUsername());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public String getDealerCards(BlackJackDealer dealer){
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
