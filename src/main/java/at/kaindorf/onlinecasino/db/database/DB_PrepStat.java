package at.kaindorf.onlinecasino.db.database;

public enum DB_PrepStat {
    insertStudentStat("INSERT INTO student (classid, catno, firstname, lastname, gender, dateofbirthdate) VALUES(?, ?, ?, ?, ?, ?)"),
    insertGradeStat("INSERT INTO grade (classname) VALUES(?)"),
    getStudentOfClassIdStat("SELECT * FROM student WHERE calssid = ? ORDER BY lastname, firstname;"),
    getNameStat("SELECT classname FROM grade WHERE calssid = ?;"),
    getIdGradeStat("SELECT classid FROM grade WHERE classname = ?;"),
    updateCatNo("UPDATE student SET catno = ? WHERE studentid = ?;");
    DB_PrepStat(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    final String sqlValue;
}

