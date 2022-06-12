/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 18.05.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.db;

import at.kaindorf.onlinecasino.blackJack.table.Hand;
import lombok.Data;
import lombok.Setter;

import java.sql.Date;
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
