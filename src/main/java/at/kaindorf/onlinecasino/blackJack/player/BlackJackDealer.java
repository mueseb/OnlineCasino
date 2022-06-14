/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 06.04.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.blackJack.player;


import at.kaindorf.onlinecasino.blackJack.table.Hand;


public class BlackJackDealer extends Player{

    public BlackJackDealer() {
        super(new Hand());
    }
}
