/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack;

import at.kaindorf.onlinecasino.blackJack.player.Dealer;
import at.kaindorf.onlinecasino.blackJack.table.Deck;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackPlayer;
import at.kaindorf.onlinecasino.blackJack.table.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BlackJack {

    public static void main(String[] args) {

        Dealer dealer = new Dealer();
        List<BlackJackPlayer> players = new ArrayList<>();
        Deck deck = new Deck();
        System.out.println(deck.getCardDeck());
        BlackJack blackJack = new BlackJack();
        players.add(new BlackJackPlayer());
        BlackJackPlayer player = new BlackJackPlayer();
        try {
            blackJack.startGame(dealer,player,deck);
            if(player.canWin())
            {
                System.out.println("Player Win");
            }
            else{
                System.out.println("Dealer Win");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Gives the Dealer and Every Player 2 cards
    public void giveStarterCards(Table table)
    {
        System.out.println("start givingStarterCards");
        table.getDealer().getHand().addCardsToHand(table.getDeck().getCardsFromDeck(2));
//        for (BlackJackPlayer player: table.getPlayers()) {
//            player.getHand().addCardsToHand(table.getDeck().getCardsFromDeck(2));
//        }
        table.getPlayer().getHand().addCardsToHand(table.getDeck().getCardsFromDeck(2));
        System.out.println("finished givingStarterCards");
    }

    //PlayerTurn
    //Players Hit until they choose to Stand
    //Players lose on Bust (>21)
    //Ace counts as 11 or 1 depending on choice except on Bust (always 1)
    public void playerTurn(BlackJackPlayer player, Deck deck) throws IOException {
        System.out.println("start playerTurn");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if(!player.isStand())
        {
            System.out.println(player.getHand().toString());
            if(player.getTurn()==1)
            {
                System.out.println("Choose: [1] hit, [2] stand, [3] double down");
                int option = Integer.parseInt(reader.readLine());
                switch (option)
                {
                    case 1: player.getHand().addCardToHand(deck.getCardFromDeck());break;
                    case 2: player.setStand(true);break;
                    case 3: player.setBet(player.getBet()*2); break;
                }
            }
            else
            {
                System.out.println("Choose: [1] hit, [2] stand");
                int option = Integer.parseInt(reader.readLine());
                switch (option)
                {
                    case 1: player.getHand().addCardToHand(deck.getCardFromDeck());break;
                    case 2: player.setStand(true);break;
                }
            }
            if(player.getHand().getHandTotal()>21)
            {
                player.setCanWin(false);
                player.setStand(true);
            }
        }
        else
        {
            return;
        }
        player.addTurn();
    }

    //Checks if all Players are Standing
    public boolean allPlayerStandCheck(List<BlackJackPlayer> players)
    {
        boolean allStanding = true;
        for (BlackJackPlayer player:players)
        {
            if (!player.isStand()) {
                allStanding = false;
                break;
            }
        }
        System.out.println("Stand check= " + allStanding);
        return allStanding;
    }

    //DealerTurn after all Players Stand
    //Dealer hits until 17+
    //Ace counts always as 11 except on bust
    public void dealerTurn(Dealer dealer, Deck deck)
    {
        System.out.println("start dealerTurn");
        while(dealer.getHand().getHandTotal()<17)
        {
            dealer.getHand().addCardToHand(deck.getCardFromDeck());
        }
        System.out.println(dealer.getHand().toString());
    }

    public int winnerCheck(Table table)
    {
        System.out.println("start winnerCheck");
        if(table.getDealer().canWin())
        {
            if(table.getPlayer().canWin())
                {
                    if(table.getPlayer().getHand().getHandTotal()<table.getDealer().getHand().getHandTotal())
                    {
                        table.getPlayer().setCanWin(false);
                        return 2;
                    }else {
                        if(table.getPlayer().getHand().getHandTotal()==table.getDealer().getHand().getHandTotal()){
                            return 3;
                        }
                        else {
                            table.getDealer().setCanWin(false);
                            return 1;
                        }
                    }
                }else {
                return 2;
            }
        }
        else
        {
            return 1;
        }
    }

    public void startGame(Dealer dealer, BlackJackPlayer player, Deck deck) throws IOException {
        Table table = new Table(dealer,player,deck);

        System.out.println("Give starterCards");
        giveStarterCards(table);

        while(!player.isStand())
        {
//            for (BlackJackPlayer player:table.getPlayer())
//            {
//                playerTurn(player,table.getDeck());
//            }
            playerTurn(player,table.getDeck());
        }

        dealerTurn(table.getDealer(),table.getDeck());

        winnerCheck(table);
    }
}
