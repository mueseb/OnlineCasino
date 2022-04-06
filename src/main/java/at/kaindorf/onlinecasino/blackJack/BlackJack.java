/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack;

import at.kaindorf.onlinecasino.beans.Dealer;
import at.kaindorf.onlinecasino.beans.Deck;
import at.kaindorf.onlinecasino.beans.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BlackJack {

    public static void main(String[] args) {

        Dealer dealer = new Dealer();
        List<Player> players = new ArrayList<>();
        Deck deck = new Deck();

        BlackJack blackJack = new BlackJack();
        try {
            blackJack.startGame(dealer,players,deck);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Gives the Dealer and Every Player 2 cards
    public void giveStarterCards(Table table)
    {
        table.getDealer().getHand().addCardToHand(table.getDeck().getCardDeck().pop());
        table.getDealer().getHand().addCardToHand(table.getDeck().getCardDeck().pop());

        for (Player player: table.getPlayers()) {
            player.getHand().addCardToHand(table.getDeck().getCardDeck().pop());
            player.getHand().addCardToHand(table.getDeck().getCardDeck().pop());
        }
    }

    //PlayerTurn
    //Players Hit until they choose to Stand
    //Players lose on Bust (>21)
    //Ace counts as 11 or 1 depending on choice except on Bust (always 1)
    public void playerTurn(Player player, Deck deck) throws IOException {
        //TODO: Check if Bust
        //TODO: Check Aces on Bust
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if(!player.isStand())
        {
            if(player.getTurn()==1)
            {
                System.out.println("Choose: [1] hit, [2] stand, [3] double down");
                int option = Integer.parseInt(reader.readLine());
                switch (option)
                {
                    case 1: player.getHand().addCardToHand(deck.getCardDeck().pop());break;
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
                    case 1: player.getHand().addCardToHand(deck.getCardDeck().pop());break;
                    case 2: player.setStand(true);break;
                }
            }
        }
        else
        {
            return;
        }
        player.addTurn();
    }

    //Checks if all Players are Standing
    public boolean allPlayerStand(List<Player> players)
    {
        boolean allStanding = true;
        for (Player player:players)
        {
            if (!player.isStand()) {
                allStanding = false;
                break;
            }
        }
        return allStanding;
    }

    //DealerTurn after all Players Stand
    //Dealer hits until 17+
    //Ace counts always as 11 except on bust
    public void dealerTurn(Dealer dealer, Deck deck)
    {
        //TODO: Check if Bust
        //TODO: Check Aces on Bust
        while(dealer.getHand().getHandTotal()<17)
        {
            dealer.getHand().addCardToHand(deck.getCardDeck().pop());
        }
    }

    public void winnerCheck(Table table)
    {
        //TODO: Check for Winner
    }

    public void startGame(Dealer dealer, List<Player> players, Deck deck) throws IOException {
        Table table = new Table(dealer,players,deck);

        System.out.println("Give starterCards");
        giveStarterCards(table);
        System.out.println("Successful gave starterCards");

        while(!allPlayerStand(players))
        {
            for (Player player:table.getPlayers())
            {
                playerTurn(player,table.getDeck());
            }
        }

        dealerTurn(table.getDealer(),table.getDeck());

        winnerCheck(table);
    }
}
