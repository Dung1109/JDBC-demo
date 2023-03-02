package main;

import DAO.StudentDAO;
import DAO.StudentDAOImpl;
import modelStudent.Student;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
}
