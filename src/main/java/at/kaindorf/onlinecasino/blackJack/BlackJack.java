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
        try {
            blackJack.startGame(dealer,players,deck);
            if(players.get(0).isCanWin())
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
        for (BlackJackPlayer player: table.getPlayers()) {
            player.getHand().addCardsToHand(table.getDeck().getCardsFromDeck(2));
        }
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
                //TODO: Check Aces on Bust
            }
        }
        else
        {
            return;
        }
        player.addTurn();
        System.out.println("finished playerTurn");
    }

    //Checks if all Players are Standing
    public boolean allPlayerStandCheck(List<BlackJackPlayer> players)
    {
        System.out.println("Stand check");
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
        //TODO: Check if Bust
        //TODO: Check Aces on Bust
        while(dealer.getHand().getHandTotal()<17)
        {
            dealer.getHand().addCardToHand(deck.getCardFromDeck());
        }
        System.out.println("finished dealerTurn");
    }

    public void winnerCheck(Table table)
    {
        System.out.println("start winnerCheck");
        //TODO: Check for Winner
        if(table.getDealer().isCanWin())
        {
            for (BlackJackPlayer player: table.getPlayers()) {
                if(player.isCanWin())
                {
                    if(player.getHand().getHandTotal()<table.getDealer().getHand().getHandTotal())
                    {
                        player.setCanWin(false);
                    }
                }
            }
        }
        System.out.println("finished winnerCheck");
    }

    public void startGame(Dealer dealer, List<BlackJackPlayer> players, Deck deck) throws IOException {
        Table table = new Table(dealer,players,deck);

        System.out.println("Give starterCards");
        giveStarterCards(table);
        System.out.println("Successful gave starterCards");

        while(!allPlayerStandCheck(players))
        {
            for (BlackJackPlayer player:table.getPlayers())
            {
                playerTurn(player,table.getDeck());
            }
        }

        dealerTurn(table.getDealer(),table.getDeck());

        winnerCheck(table);
    }
}
