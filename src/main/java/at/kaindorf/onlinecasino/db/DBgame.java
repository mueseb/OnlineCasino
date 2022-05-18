/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.db;

import at.kaindorf.onlinecasino.blackJack.table.Hand;
import lombok.Data;

import java.util.Date;

//TODO rework
@Data
public class DBgame {
    private int id;
    private int bet;
    private Hand dealerHand;
    private Hand playerHand;
    private Date gameTime;
    private boolean result;
}
