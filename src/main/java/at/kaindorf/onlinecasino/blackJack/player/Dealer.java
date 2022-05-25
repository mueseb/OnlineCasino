/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack.player;


import at.kaindorf.onlinecasino.blackJack.gameAssets.Hand;


public class Dealer extends Player{

    public Dealer() {
        super(new Hand());
    }
}
