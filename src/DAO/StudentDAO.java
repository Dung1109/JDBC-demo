package DAO;

import modelStudent.Student;

import java.util.List;

public interface StudentDAO{

    Boolean saveAll(List<Student> students);
    Boolean saveAllByTransaction(List<Student> students);
    Boolean saveAllByBatch(List<Student> students);

    Student save(Student student);

    List<Student> readAll();

    Student readDetail(Integer id);

    Student update(Integer student);

    Student delete(Integer id);

    List<Student> deleteAll(List<Integer> ids);

    int insert(Student student);

    int insert(String name, double mark);

    double sumMark(Integer id);

    Student saveUseStore(Student student);
}
