package at.kaindorf.onlinecasino.db.connection;


public enum DB_PrepStat {
    getUserByID("SELECT name,balance FROM public.\"player\" WHERE playerid = ?;"),
    getUserByName("SELECT playerid FROM public.\"player\" WHERE name = ?;"),
    getGamesByID("SELECT gamestuff FROM public.\"game\" WHERE name = ?;"), //TODO replace gamestuff

    insertUser("INSERT INTO public.\"player\" (name, password) VALUES(?, ?)"),
    saveGame("INSERT INTO public.\"game\" (playerid,  bet, dealerhand, playerhand, starttime, endtime, result) VALUES(?, ?, ?, ?, ?, ?, ?)"),

    updateUserBalance("UPDATE public.\"player\" SET balance = ? WHERE id = ?;"),

    checkUserDuplicate("SELECT count(*) FROM public.\"player\" WHERE name = ?;"),
    checkUserPassword("SELECT password FROM public.\"player\" WHERE name = ?;");

    DB_PrepStat(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public final String sqlValue;


}

