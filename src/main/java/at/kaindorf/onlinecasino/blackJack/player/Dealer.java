/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack.player;


import at.kaindorf.onlinecasino.blackJack.table.Hand;


public class Dealer extends Player{

    public Dealer() {
        super(new Hand());
    }
}
