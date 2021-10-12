/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabasejpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author nkotchs
 */
public class StudentDatabaseJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //test persist input data in database
       Student std = new Student(6, "Jones", 4.00);
       persist(std);
       List<Student> studentList = StudentTable.findAllStudent();
       printAllStudent(studentList);
       //insert
       Student std1 = new Student(3, "Jack", 4.00);
       Student std2 = new Student(4, "Rose", 3.90);
       StudentTable.insertStudent(std1);
       StudentTable.insertStudent(std2);
       studentList = StudentTable.findAllStudent();
       printAllStudent(studentList);
       //update
       std = StudentTable.findStudentById(1);
       if (std != null) {
           std.setName("Charlie");
           StudentTable.updateStudent(std);
       }
       studentList = StudentTable.findAllStudent();
       printAllStudent(studentList);
       //delete
       std = StudentTable.findStudentById(6);
       StudentTable.removeStudent(std);    
       studentList = StudentTable.findAllStudent();
       printAllStudent(studentList);
       //select student name = Marry
       studentList = StudentTable.findStudentByName("Marry");
       printAllStudent(studentList);
    }
    public static void printAllStudent(List<Student> studentList) {
        for(Student std : studentList) {
           System.out.print(std.getId() + " ");
           System.out.print(std.getName() + " ");
           System.out.println(std.getGpa() + " ");
       }
    } 

    //move to StudentTable
    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    
}
