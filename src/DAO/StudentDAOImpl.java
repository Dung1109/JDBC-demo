package DAO;

import modelStudent.Student;
import utility.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    public StudentDAOImpl() {
    }

    @Override
    public Boolean saveAll(List<Student> students) {
        try (Connection conn = new DBUtils().getConnection()) {
            String sql = "INSERT INTO STUDENT VALUES (?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql); // 5 Statement => execute => ResultSet
            int row = 0;

            for (Student student : students) {
                stm.setString(1, student.getName());
                stm.setDouble(2, student.getMark());
                stm.executeUpdate();
                row++;
            }
            System.out.println("Record inserted : " + row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Boolean saveAllByTransaction(List<Student> students) {
        try (Connection conn = new DBUtils().getConnection()) {
            String sql = "INSERT INTO STUDENT VALUES (?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql); // 5 Statement => execute => ResultSet
            int row = 0;
            conn.setAutoCommit(false);

            for (Student student : students) {
                stm.setString(1, student.getName());
                stm.setDouble(2, student.getMark());
                stm.executeUpdate();
                row++;
            }
            System.out.println("Record inserted : " + row);

            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Boolean saveAllByBatch(List<Student> students) {
        try (Connection conn = new DBUtils().getConnection()) {
            String sql = "INSERT INTO STUDENT VALUES (?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql); // 5 Statement => execute => ResultSet
            int row = 0;
            conn.setAutoCommit(false);

            for (Student student : students) {
                stm.setString(1, student.getName());
                stm.setDouble(2, student.getMark());

                stm.addBatch();
                row++;
            }
            System.out.println("Record inserted : " + row);
            stm.executeBatch();

            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public List<Student> readAll() {

        List<Student> students = new ArrayList<>();

        try (Connection conn = new DBUtils().getConnection()) {

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

    @Override
    public double sumMark(Integer id) {
        try (Connection conn = new DBUtils().getConnection()) {
            // 1. Add lib (implementation)
            Statement stm = conn.createStatement();
            String sql = "SELECT SUM(mark) FROM Student WHERE id = " + id;
//            ResultSet rs = stm.executeQuery(sql);

            stm.executeQuery(sql);
            ResultSet rs = stm.getResultSet();
            // find column name of result set
            String columnCount = rs.getMetaData().getColumnName(1);
            System.out.println("columnCount = " + columnCount);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student saveUseStore(Student student) {
        try (Connection conn = new DBUtils().getConnection()) {
            String sql = "{ CALL sp_Student_insert(?, ?, ?, ?) }";
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, student.getName());
            stm.setDouble(2, student.getMark());
            stm.registerOutParameter(3, Types.VARCHAR);
            stm.registerOutParameter(4, Types.VARCHAR);
            stm.execute();
            System.out.println("Output 1: " + stm.getString(3));
            System.out.println("Output 2: " + stm.getString(4));
//            System.out.println("Done");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
}
