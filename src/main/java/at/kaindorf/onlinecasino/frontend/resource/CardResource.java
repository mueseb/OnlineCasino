/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.frontend.resource;

import at.kaindorf.onlinecasino.blackJack.BlackJack;
import at.kaindorf.onlinecasino.blackJack.player.BlackJackPlayer;
import at.kaindorf.onlinecasino.blackJack.player.Dealer;
import at.kaindorf.onlinecasino.blackJack.table.Deck;
import at.kaindorf.onlinecasino.blackJack.table.Table;
import at.kaindorf.onlinecasino.frontend.beans.LoginData;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/cards")
public class CardResource {

    Dealer dealer;
    BlackJackPlayer blackJackPlayer;
    Deck deck;
    Table table;
    BlackJack blackJack;

    @GET
    public Response getStarterCards() {
        deck = new Deck();
        dealer.getHand().addCardsToHand(deck.getCards(2));
        blackJackPlayer.getHand().addCardsToHand(deck.getCards(2));
        //table = new Table()
        //blackJack = new BlackJack();
        return Response.ok().build();
    }

    @GET
    public Response getDealerCard() {
//        if (loginData.getName().equals("admin") && loginData.getPwd().equals("admin")) {
//            return Response.ok().header("Authorization", creatJWT(loginData.getName())).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok().build();
    }

    @GET
    public Response getPlayerCard() {
//        if (loginData.getName().equals("admin") && loginData.getPwd().equals("admin")) {
//            return Response.ok().header("Authorization", creatJWT(loginData.getName())).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok().build();
    }
}
