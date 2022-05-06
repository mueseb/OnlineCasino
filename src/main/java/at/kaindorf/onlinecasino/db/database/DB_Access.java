package at.kaindorf.onlinecasino.db.database;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class DB_Access {

    private static DB_Access instance;
    private final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DB_Database database = null;
    private Connection connection;

    public DB_Access() throws ClassNotFoundException {
        this.database = new DB_Database();
    }

    public static DB_Access getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new DB_Access();
        }
        return instance;
    }

    public void connect() throws SQLException {
        database.connect();
    }

    public void disconnect() throws SQLException {
        database.disconnect();
    }

    public DB_Database getDatabase() {
        return database;
    }


    //        try {
//            DB_Access db_access = new DB_Access();
//            Class.forName("org.postgresql.Driver");
////            Connection db = DriverManager.getConnection(url, username, password);
//            db_access.connect();
//            String sqlString = "SELECT * FROM public.\"User\";";
////
////            statement = db_access.createStatement();
////            ResultSet rs = statement.executeQuery(sqlString);
//            while(rs.next()) {
//                //System.out.println(rs.getInt(1));
//                System.out.println(rs.getString("id"));
//                System.out.println(rs.getString("name"));
//            }
//            statement.close();
//            db_access.close();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//    public void insertUser(Student student) throws SQLException {
//        if (insertStudentStat == null) {
//            insertStudentStat = database.getConnection().prepareStatement(database.DB_PrepStat.insertStudentStat.sqlValue);
//        }
//        insertStudentStat.setInt(1, student.getClassid());
//        insertStudentStat.setInt(2, student.getCatno());
//        insertStudentStat.setString(3, student.getFirstname());
//        insertStudentStat.setString(4, student.getLastname());
//        insertStudentStat.setString(5, (student.getGender() + ""));
//        insertStudentStat.setDate(6, Date.valueOf(student.getDateofbirth()));
//        insertStudentStat.executeUpdate();
//    }


//    public void insertGrade(String name) throws SQLException {
//        if (insesrtGradeStat == null) {
//            insesrtGradeStat = database.getConnection().prepareStatement(database.DB_PrepStat.insertGradeStat.sqlValue);
//        }
//
//        insesrtGradeStat.setString(1, name);
//        insesrtGradeStat.executeUpdate();
//    }

//    public List<Student> getStudents() throws SQLException {
//        String sqlString = "SELECT * FROM student ORDER BY classid, catno;";
//        Statement statement = database.getStatement();
//        ResultSet rs = statement.executeQuery(sqlString);
//
//        List<Student> students = new ArrayList<>();
//        while (rs.next()) {
//            students.add(new Student(rs.getInt("studentid"), rs.getInt("classid"), rs.getInt("catno"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("gender").charAt(0), rs.getDate("dateofbirth").toLocalDate()));
//        }
//        database.releaseStatement(statement);
//        return students;
//    }

//    public List<Grade> getGrades() throws SQLException {
//        String sqlString = "SELECT * FROM grade;";
//        Statement statement = database.getStatement();
//        ResultSet rs = statement.executeQuery(sqlString);
//        List<Grade> grades = new ArrayList<>();
//
//        while (rs.next()) {
//            grades.add(new Grade(rs.getInt("classid"), rs.getString("classname")));
//        }
//        database.releaseStatement(statement);
//        return grades;
//    }

//    public List<Student> getStudentsOfClassId(int id) throws SQLException {
//        if (getStudentOfClassIdStat == null) {
//            getStudentOfClassIdStat = database.getConnection().prepareStatement(database.DB_PrepStat.getStudentOfClassIdStat.sqlValue);
//        }
//        getStudentOfClassIdStat.setInt(1, id);
//        ResultSet rs = getStudentOfClassIdStat.executeQuery();
//        List<Student> students = new ArrayList<>();
//
//        while (rs.next()) {
//            students.add(new Student(
//                    rs.getInt("studentid"),
//                    rs.getInt("classid"),
//                    rs.getInt("catno"),
//                    rs.getString("firstname"),
//                    rs.getString("lastname"),
//                    rs.getString("gender").charAt(0),
//                    rs.getDate("dateofbirth").toLocalDate()));
//        }
//        return students;
//    }

//    public String getGradeName(int id) throws SQLException {
//        if (getNameStat == null) {
//            getNameStat = database.getConnection().prepareStatement(database.DB_PrepStat.getNameStat.sqlValue);
//        }
//
//        getNameStat.setInt(1, id);
//        ResultSet rs = getNameStat.executeQuery();
//        rs.next();
//        return rs.getString("classname");
//    }
//
//    public int getIdGrade(String name) throws SQLException {
//        if (getIdGradeStat == null) {
//            getIdGradeStat = database.getConnection().prepareStatement(database.DB_PrepStat.getIdGradeStat.sqlValue);
//        }
//        getIdGradeStat.setString(1, name);
//        ResultSet rs = getIdGradeStat.executeQuery();
//        rs.next();
//        return rs.getInt("classid");
//    }
//
//    public void updateCatNo(int id) throws SQLException {
//        if (updateCatNo == null) {
//            updateCatNo = database.getConnection().prepareStatement(database.DB_PrepStat.updateCatNo.sqlValue);
//        }
//        List<Student> students = getStudentsOfClassId(id);
//        for (int catno = 1; catno <= students.size(); catno++) {
//            updateCatNo.setInt(1, catno);
//            updateCatNo.setInt(2, students.get(catno - 1).getStudentid());
//            updateCatNo.executeUpdate();
//        }
//    }

}
