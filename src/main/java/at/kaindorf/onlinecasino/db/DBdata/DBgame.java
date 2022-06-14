/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 24.05.2022
 * @project-name: Online Casino
 */

/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 18.05.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.db.DBdata;

import at.kaindorf.onlinecasino.blackJack.table.Hand;
import at.kaindorf.onlinecasino.blackJack.table.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DBgame {
    private int gameID;
    private int playerID;
    private int bet;
    private String dealerHand;
    private String playerHand;
    private Timestamp startTime;
    private Timestamp endTime;
    private String result;

    private static DBgame gameInstance;

    public static DBgame getInstance()
    {
        return gameInstance;
    }

    public static void setTableInstance(DBgame dBgame) {
        DBgame.gameInstance = dBgame;
    }
}
