package at.kaindorf.onlinecasino.db.database;


public enum DB_PrepStat {
    getUserByID("SELECT name,balance FROM public.\"player\" WHERE playerid = ?;"),
    getUserByName("SELECT playerid FROM public.\"player\" WHERE name = ?;"),
    insertUser("INSERT INTO public.\"player\" (name, password) VALUES(?, ?)"),
    updateUserBalance("UPDATE public.\"player\" SET balance = ? WHERE id = ?;"),
    checkUserDuplicate("SELECT count(*) FROM public.\"player\" WHERE name = ?;"),
    insertGameByID("INSERT INTO public.\"game\" (id,  bet, dealerhand, playerhand, date, result) VALUES(?, ?, ?, ?, ?, ?)"),
    getGamesByID("SELECT gamestuff FROM public.\"game\" WHERE name = ?;"), //TODO replace gamestuff
    checkUserPassword("SELECT password FROM public.\"player\" WHERE name = ?;");

    DB_PrepStat(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public final String sqlValue;


}

