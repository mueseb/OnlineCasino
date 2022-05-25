/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.db;

import at.kaindorf.onlinecasino.blackJack.gameAssets.Hand;
import lombok.Data;
import lombok.Setter;

import java.sql.Timestamp;

//TODO rework
@Data
@Setter
public class DBgame {
    private int gameID;
    private int playerID;
    private int bet;
    private Hand dealerHand;
    private Hand playerHand;
    private Timestamp startTime;
    private Timestamp endTime;
    private int result;
}
