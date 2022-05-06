package at.kaindorf.onlinecasino.db.database;

public enum DB_PrepStat {
    getUserByID("SELECT id FROM public.\"player\" WHERE name = ?;"),
    insertUser("INSERT INTO public.\"player\" (playerid, name, password, balance) VALUES(?, ?, ?, ?)"),
    updateUserById("UPDATE public.\"player\" SET balance = ? WHERE id = ?;");

    DB_PrepStat(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    final String sqlValue;
}

