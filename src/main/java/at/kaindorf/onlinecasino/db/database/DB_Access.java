package database;

import beans.Grade;
import beans.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DB_Access {

    private static DB_Access instance;
    private final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DB_Database database = null;

    private PreparedStatement insertStudentStat;
    private PreparedStatement insesrtGradeStat;
    private PreparedStatement getStudentOfClassIdStat;
    private PreparedStatement getIdGradeStat;
    private PreparedStatement getNameStat;
    private PreparedStatement updateCatNo;


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

    public void importToDB() throws SQLException, IOException {
        String sqlString = "DELETE FROM student;";
        Statement statement = database.getStatement();
        statement.executeUpdate(sqlString);

        sqlString = "DELETE FROM grade";
        statement.executeUpdate(sqlString);

        Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "Studentlist_3xHIF.csv");
        List<String> data = Files.readAllLines(path);

        List<Student> students = new ArrayList<>();
        Map<String, Integer> grades = new TreeMap<>();

        for (String line : data) {
            String[] tokens = line.split(";");

            if (!grades.containsKey(tokens[0])) {
                grades.put(tokens[0], grades.size());
            }
            students.add(new Student(0, grades.get(tokens[0]), 0, tokens[2], tokens[1], tokens[3].charAt(0), LocalDate.parse(tokens[4], DTF)));
        }

        for (String grade : grades.keySet()) {
            insertGrade(grade);
        }

        sqlString = "SELECT * FROM grade;";
        ResultSet rs = statement.executeQuery(sqlString);

        while (rs.next()) {
            int newID = rs.getInt("classid");
            int oldID = grades.get(rs.getString("classname"));

            for (Student student : students) {
                if (oldID == student.getClassid()) {
                    student.setClassid(newID);
                }
            }
        }
        database.releaseStatement(statement);

        int catno = 1;
        int classid = students.get(0).getClassid();

        for (Student student : students) {
            if (classid != student.getClassid()) {
                catno = 1;
                classid = student.getClassid();
            }
            student.setCatno(catno);
            catno++;
            insertStudent(student);
        }

    }

    public void exportToCSV() throws IOException, SQLException {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "Studentlist_3xHIF.csv");
        path.toFile().delete();

        FileWriter fw = new FileWriter(path.toFile());
        List<Student> students = getStudents();
        fw.append("Klasse;Familienname;Vorname;Geschlecht;Geburtsdatum");

        for (Student student : students)
        {
            fw.append(getGradeName(student.getClassid()));
            fw.append(student.toString());
        }
        fw.flush();
        fw.close();
    }

    public void insertStudent(Student student) throws SQLException {
        if (insertStudentStat == null) {
            insertStudentStat = database.getConnection().prepareStatement(DB_PrepStat.insertStudentStat.sqlValue);
        }
        insertStudentStat.setInt(1, student.getClassid());
        insertStudentStat.setInt(2, student.getCatno());
        insertStudentStat.setString(3, student.getFirstname());
        insertStudentStat.setString(4, student.getLastname());
        insertStudentStat.setString(5, (student.getGender() + ""));
        insertStudentStat.setDate(6, Date.valueOf(student.getDateofbirth()));
        insertStudentStat.executeUpdate();
    }


    public void insertGrade(String name) throws SQLException {
        if (insesrtGradeStat == null) {
            insesrtGradeStat = database.getConnection().prepareStatement(DB_PrepStat.insertGradeStat.sqlValue);
        }

        insesrtGradeStat.setString(1, name);
        insesrtGradeStat.executeUpdate();
    }

    public List<Student> getStudents() throws SQLException {
        String sqlString = "SELECT * FROM student ORDER BY classid, catno;";
        Statement statement = database.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);

        List<Student> students = new ArrayList<>();
        while (rs.next()) {
            students.add(new Student(rs.getInt("studentid"), rs.getInt("classid"), rs.getInt("catno"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("gender").charAt(0), rs.getDate("dateofbirth").toLocalDate()));
        }
        database.releaseStatement(statement);
        return students;
    }

    public List<Grade> getGrades() throws SQLException {
        String sqlString = "SELECT * FROM grade;";
        Statement statement = database.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        List<Grade> grades = new ArrayList<>();

        while (rs.next()) {
            grades.add(new Grade(rs.getInt("classid"), rs.getString("classname")));
        }
        database.releaseStatement(statement);
        return grades;
    }

    public List<Student> getStudentsOfClassId(int id) throws SQLException {
        if (getStudentOfClassIdStat == null) {
            getStudentOfClassIdStat = database.getConnection().prepareStatement(DB_PrepStat.getStudentOfClassIdStat.sqlValue);
        }
        getStudentOfClassIdStat.setInt(1, id);
        ResultSet rs = getStudentOfClassIdStat.executeQuery();
        List<Student> students = new ArrayList<>();

        while (rs.next()) {
            students.add(new Student(
                    rs.getInt("studentid"),
                    rs.getInt("classid"),
                    rs.getInt("catno"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("gender").charAt(0),
                    rs.getDate("dateofbirth").toLocalDate()));
        }
        return students;
    }

    public String getGradeName(int id) throws SQLException {
        if (getNameStat == null) {
            getNameStat = database.getConnection().prepareStatement(DB_PrepStat.getNameStat.sqlValue);
        }

        getNameStat.setInt(1, id);
        ResultSet rs = getNameStat.executeQuery();
        rs.next();
        return rs.getString("classname");
    }

    public int getIdGrade(String name) throws SQLException {
        if (getIdGradeStat == null) {
            getIdGradeStat = database.getConnection().prepareStatement(DB_PrepStat.getIdGradeStat.sqlValue);
        }
        getIdGradeStat.setString(1, name);
        ResultSet rs = getIdGradeStat.executeQuery();
        rs.next();
        return rs.getInt("classid");
    }

    public void updateCatNo(int id) throws SQLException {
        if (updateCatNo == null) {
            updateCatNo = database.getConnection().prepareStatement(DB_PrepStat.updateCatNo.sqlValue);
        }
        List<Student> students = getStudentsOfClassId(id);
        for (int catno = 1; catno <= students.size(); catno++) {
            updateCatNo.setInt(1, catno);
            updateCatNo.setInt(2, students.get(catno - 1).getStudentid());
            updateCatNo.executeUpdate();
        }
    }

}
