/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 29.04.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.db.connection;


public enum DB_PrepStat {
    getUserByID("SELECT name,balance FROM public.\"player\" WHERE playerid = ?;"),
    getUserIDByName("SELECT playerid FROM public.\"player\" WHERE name = ?;"),
    getGamesByID("SELECT id, playerid,  bet, dealerhand, playerhand, starttime, endtime, result FROM public.\"game\" WHERE playerid = ?;"), //TODO replace gamestuff
    getUserBalance("SELECT balance FROM public.\"player\" WHERE name = ?;"),

    insertUser("INSERT INTO public.\"player\" (name, password) VALUES(?, ?)"),
    saveGame("INSERT INTO public.\"game\" (playerid,  bet, dealerhand, playerhand, starttime, endtime, result) VALUES(?, ?, ?, ?, ?, ?, ?)"),

    updateUserBalance("UPDATE public.\"player\" SET balance = ? WHERE playerid = ?;"),

    checkUserDuplicate("SELECT count(*) FROM public.\"player\" WHERE name = ?;"),
    checkUserPassword("SELECT password FROM public.\"player\" WHERE name = ?;");

    DB_PrepStat(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public final String sqlValue;


}

