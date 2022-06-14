/*
    Klasse:  4BHIF
    @author: Sebastian Münzer
*/
package at.kaindorf.web.resource;

import at.kaindorf.onlinecasino.blackJack.BlackJack;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackDealer;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackPlayer;
import at.kaindorf.onlinecasino.blackJack.player.Player;
import at.kaindorf.onlinecasino.blackJack.table.Deck;
import at.kaindorf.onlinecasino.blackJack.table.Table;
import at.kaindorf.onlinecasino.db.BlackjackDB;
import at.kaindorf.onlinecasino.db.DBdata.DBgame;
import at.kaindorf.web.beans.BalanceData;
import at.kaindorf.web.beans.InitGameData;
import at.kaindorf.web.beans.LoginData;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Path("/game")
public class CardResource {

    BlackJackDealer dealer;
    BlackJackPlayer player;
    Deck deck;
    BlackJack blackJack;
    BlackjackDB blackjackDB = BlackjackDB.getInstance();
    DBgame game;

    Table table;

    public CardResource() throws SQLException, ClassNotFoundException {
        table=Table.getInstance();
    }


    @Path("/initGame")
    @POST
    public Response initGame(InitGameData initGameData)
    {
        game = new DBgame();
        dealer = new BlackJackDealer();
        BlackJackPlayer player = new BlackJackPlayer();
        deck = new Deck();
        System.out.println("Table C: "+LocalTime.now());
        Table table = new Table(dealer,player,deck);
        Table.setTableinstance(table);
        System.out.println("TableID:" + table.getPlayerID());
        blackJack = new BlackJack();
        System.out.println("Username CR: " + initGameData.getUsername());
        try {
            table.setPlayerID(blackjackDB.getUserIdByName(initGameData.getUsername()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        game.setPlayerID(table.getPlayerID());
        game.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        return Response.ok().build();
    }

    @Path("/getPlayerStarterCards")
    @GET
    @Produces
    public String getPlayerStarterCards()
    {
        table.getPlayer().getHand().addCardsToHand(table.getDeck().getCardsFromDeck(2));
        System.out.println(table.getPlayer().getHand().getCards().toString());
        System.out.println(getPlayerCards(table.getPlayer()));
//        return 1;
        return getPlayerCards(table.getPlayer());
//        return Response.ok(getPlayerCards(table.getPlayer())).build();
    }

    @Path("/getDealerStarterCards")
    @GET
    @Produces
    public String getDealerStarterCards()
    {
        table.getDealer().getHand().addCardsToHand(table.getDeck().getCardsFromDeck(2));
        return getDealerCards(table.getDealer());
    }

    @Path("/addDealerCard")
    public String addDealerCard()
    {
        table.getDealer().getHand().addCardToHand(table.getDeck().getCardFromDeck());
        return getDealerCards(table.getDealer());
    }


    @Path("/addPlayerCard")
    public String addPlayerCard()
    {
        table.getPlayer().getHand().addCardToHand(table.getDeck().getCardFromDeck());
        return getPlayerCards(table.getPlayer());
    }

    @Path("/doubleDown")
    public String onDoubleDown()
    {
        table.getPlayer().getHand().addCardToHand(table.getDeck().getCardFromDeck());
        table.getPlayer().setStand(true);
        return getPlayerCards(table.getPlayer());
    }


    @Path("stand")
    public Response onStand()
    {
        table.getPlayer().setStand(true);
        return Response.ok().build();
    }


    @Path("finishGame")
    public int onEndGame()
    {
        //1:Player Win; 2:Dealer Win; 3:Draw
        int num;
        int i = blackJack.winnerCheck(table);
        table.setResult(i);
        saveGame();
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
        String cards="";
        for (int i = 0; i < dealer.getHand().getCards().size(); i++) {
            cards+=dealer.getHand().getCards().get(i).getCardCode();
            if(i+1!=dealer.getHand().getCards().size())
            {
                cards+=";";
            }
        }
        return cards;
    }

    public String getPlayerCards(BlackJackPlayer player){
        String cards="";
        for (int i = 0; i < player.getHand().getCards().size(); i++) {
            cards+=player.getHand().getCards().get(i).getCardCode();
            if(i+1!=player.getHand().getCards().size())
            {
                cards+=";";
            }
        }
        return cards;
    }


//    public boolean updateBalance(int id,int balance) throws SQLException {
//        if(updateUserBalance == null)
//        {
//            updateUserBalance = connection.prepareStatement(DB_PrepStat.updateUserBalance.sqlValue);
//        }
//        updateUserBalance.setInt(1,id);
//        updateUserBalance.setInt(2,balance);
//        int rs = updateUserBalance.executeUpdate();
//        return rs == 1;
//    }

    @PATCH
    @Consumes
    public void setPlayerBalance(BalanceData balanceData){
        try {
            int userID = blackjackDB.getUserIdByName(balanceData.getName());
            blackjackDB.updateBalance(userID, balanceData.getBalance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Path("getPlayerCardCount")
    public int getPlayerCardCount(){
        BlackJackPlayer player = table.getPlayer();
        return player.getHand().getHandTotal();
    }

    @Path("getDealerCardCount")
    public int getDealerCardCount(){
        BlackJackDealer dealer = table.getDealer();
        return dealer.getHand().getHandTotal();
    }
}
