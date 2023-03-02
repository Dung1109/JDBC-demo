package DAO;

import modelStudent.Student;
import utility.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    public StudentDAOImpl() {
    }

    @Override
    public Boolean saveAll(List<Student> students) {
        return null;
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public List<Student> readAll() {

        List<Student> students = new ArrayList<>();

        try (Connection conn = new DBUtils().getConnection()) {
            // 1. Add lib (implementation)


            // 2. Load driver JDBC for sql server
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//            // 3. Driver manager => Connection (url, user, password)
//            String url = "jdbc:sqlserver://DESKTOP-5MKJPAU\\SQLEXPRESS;databaseName=StudentMS";
////            String user = "sa";
////            String password = "123456";
//            Connection conn = DriverManager.getConnection(url);


            // 4. Connection => Statement (SQL)

            Statement stm = conn.createStatement();


            // 5. Statement => ResultSet (executeQuery)
            String sql = "SELECT * FROM Student";
            ResultSet rs = stm.executeQuery(sql);

            // 6. Convert ResultSet => List<Student>

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Double mark = rs.getDouble("mark");

                Student student = new Student(id, name, mark);
                students.add(student);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public Student readDetail(Integer id) {
        return null;
    }

    @Override
    public Student update(Integer studentID) {
        try (Connection conn = new DBUtils().getConnection()) {
            // 1. Add lib (implementation)
            Statement stm = conn.createStatement();
            String sql = "UPDATE Student SET mark = 1000 WHERE id = " + studentID;
            int result = stm.executeUpdate(sql);
            System.out.println("Number of row updated: " + result);
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete student
    @Override
    public Student delete(Integer id) {
        try (Connection conn = new DBUtils().getConnection()) {
            // 1. Add lib (implementation)
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM Student WHERE id = " + id;
            int result = stm.executeUpdate(sql);
            System.out.println("Number of row deleted: " + result);
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> deleteAll(List<Integer> ids) {
        try (Connection conn = new DBUtils().getConnection()) {
            // 1. Add lib (implementation)
            Statement stm = conn.createStatement();
            StringBuilder sql = new StringBuilder("DELETE FROM Student WHERE id IN (");
            for (int i = 0; i < ids.size(); i++) {
                sql.append(ids.get(i));
                if (i < ids.size() - 1) {
                    sql.append(", ");
                }
            }
            sql.append(")");
            int result = stm.executeUpdate(sql.toString());
            System.out.println("Number of row deleted: " + result);
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Student student) {
        try (Connection conn = new DBUtils().getConnection()) {
            // 1. Add lib (implementation)
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO Student (name, mark) VALUES ('" + student.getName() + "', " + student.getMark() + ")";
            return stm.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(String name, double mark) {
            try (Connection conn = new DBUtils().getConnection()) {
            // 1. Add lib (implementation)
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO Student (name, mark) VALUES ('" + name + "', " + mark + ")";
            int result = stm.executeUpdate(sql);
            System.out.println("Number of row insert: " + result);
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
