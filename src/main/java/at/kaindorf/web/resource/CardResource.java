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

//@Path("/cards")
public class CardResource {

    Dealer dealer;
    BlackJackPlayer blackJackPlayer;
    Deck deck;
    Table table;
    BlackJack blackJack;

    public void getStarterCards() {
        deck = new Deck();
        dealer.getHand().addCardsToHand(deck.getCardsFromDeck(2));
        blackJackPlayer.getHand().addCardsToHand(deck.getCardsFromDeck(2));
        //table = new Table()
        //blackJack = new BlackJack();
        //return Response.ok().build();
    }

    public void getDealerCard() {
//        if (loginData.getName().equals("admin") && loginData.getPwd().equals("admin")) {
//            return Response.ok().header("Authorization", creatJWT(loginData.getName())).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).build();

        //return Response.ok().build();
    }

    public void getPlayerCard() {
//        if (loginData.getName().equals("admin") && loginData.getPwd().equals("admin")) {
//            return Response.ok().header("Authorization", creatJWT(loginData.getName())).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).build();

        //return Response.ok().build();
    }
}
