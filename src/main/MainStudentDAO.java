package main;

import DAO.StudentDAO;
import DAO.StudentDAOImpl;
import modelStudent.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MainStudentDAO {

    StudentDAO studentDAO = new StudentDAOImpl();

    public static void main(String[] args) {
        MainStudentDAO main = new MainStudentDAO();

//        main.studentDAO.insert("John", 10.3);
//        main.studentDAO.update(1);
//        main.studentDAO.delete(5);
        main.studentDAO.deleteAll(List.of(2, 3, 4));

        List<Student> students = main.studentDAO.readAll();

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void test_delete_student() {
        MainStudentDAO main = new MainStudentDAO();
        main.studentDAO.delete(10);
    }

    @Test
    public void test_sum_mark() {
        MainStudentDAO main = new MainStudentDAO();
        double sum = main.studentDAO.sumMark(12);
        System.out.println(sum);
    }

    @Test
    public void test_saveUseStore() {
        MainStudentDAO main = new MainStudentDAO();
        Student student = new Student(0, "John", 9.5);
        Student student1 = main.studentDAO.saveUseStore(student);
//        System.out.println(student1);

    }


    @Test
    public void test_saveAllByTransaction() {
        MainStudentDAO main = new MainStudentDAO();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            students.add(new Student(0, "John", 9.5));
        }
        long start = System.currentTimeMillis();
        main.studentDAO.saveAllByTransaction(students);
        long end = System.currentTimeMillis();
        System.out.println("Time by transaction: " + (end - start));
    }

    @Test
    public void test_saveAll() {
        MainStudentDAO main = new MainStudentDAO();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            students.add(new Student(0, "John", 9.5));
        }
        long start = System.currentTimeMillis();
        main.studentDAO.saveAllByTransaction(students);
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
    }

    @Test
    public void test_saveAllByBatch() {
        MainStudentDAO main = new MainStudentDAO();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            students.add(new Student(0, "John", 9.5));
        }
        long start = System.currentTimeMillis();
        main.studentDAO.saveAllByBatch(students);
        long end = System.currentTimeMillis();
        System.out.println("Time by batch: " + (end - start));
    }
}


