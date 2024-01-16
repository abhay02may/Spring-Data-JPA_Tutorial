package com.dailycodebuffer.springdata.repository;

import com.dailycodebuffer.springdata.entity.Guardian;
import com.dailycodebuffer.springdata.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void test_saveStudentToDB(){

        Guardian guardian= Guardian.builder()
                .guardianName("Manoj Rai")
                .guardianEmail("manoj@gmail.com")
                .guardianMobile("9999988888")
                .build();


        Student student=Student.builder()
                .firstName("Vinod")
                .lastName("Rai")
                .emailId("vinod@gmail.com")
                .guardian(guardian)
                .build();
        Student student1=studentRepository.save(student);
        Assertions.assertNotNull(student1);
        Assertions.assertNotNull(student1.getStudentId());
        Assertions.assertEquals(student1.getFirstName(),"Vinod");
    }

    @DisplayName("Get All the Students")
    @Test
    public void test_getAllStudents(){
        List<Student> studentList=studentRepository.findAll();
        studentList.stream().forEach(student -> System.out.println(student));
        Assertions.assertNotNull(studentList);
    }

    @DisplayName("Get Student by Student Id")
    @Test
    public void test_getStudentById(){
        Student student=studentRepository.findById(52L).orElseThrow(null);
        Assertions.assertNotNull(student);
        Assertions.assertEquals(student.getStudentId(),52L);
    }

    @DisplayName("Get Student by Student firstName")
    @Test
    public void test_getStudentByFirstName(){
        Student student=studentRepository.findByFirstName("Vinod");
        System.out.println(student);
        Assertions.assertNotNull(student);
        Assertions.assertEquals(student.getFirstName(),"Vinod");
    }

    @DisplayName("Get All the Students having last name is matching for few characters")
    @Test
    public void test_getAllStudentsByMatchingLastName(){
        List<Student> studentList=studentRepository.findByLastNameContaining("ra");
        studentList.stream().forEach(student -> System.out.println(student));
        Assertions.assertNotNull(studentList);
    }


    @DisplayName("Get All the Students with Matching Guardian Name ")
    @Test
    public void test_getAllStudentsByMatchingGuardianName(){
        List<Student> studentList=studentRepository.findByGuardianGuardianName("Shashikant Verma");
        studentList.stream().forEach(student -> System.out.println(student));
        Assertions.assertNotNull(studentList);
    }

    @DisplayName("Get Student by Student firstName and LastName")
    @Test
    public void test_getStudentByFirstNameAndLastName(){
        Student student=studentRepository.findByFirstNameAndLastName("Vinod","Rai");
        System.out.println(student);
        Assertions.assertNotNull(student);
        Assertions.assertEquals(student.getFirstName(),"Vinod");
    }

    @DisplayName("Get Student by Email address using JPQL")
    @Test
    public void test_getStudentDetailsByEmailAddress(){
        Student student=studentRepository.getStudentDetailsByEmailAddress("vinod@gmail.com");
        System.out.println(student);
        Assertions.assertNotNull(student);
        Assertions.assertEquals(student.getFirstName(),"Vinod");
    }

    @DisplayName("Get Student by Email address using Native QUERY")
    @Test
    public void test_getStudentDetailsByEmailAddressNative(){
        Student student=studentRepository.getStudentDetailsByEmailAddressNative("vinod@gmail.com");
        System.out.println(student);
        Assertions.assertNotNull(student);
        Assertions.assertEquals(student.getFirstName(),"Vinod");
    }

    // Native Named Parameter
    @DisplayName("Get Student by Email address using Native Named Parameter QUERY")
    @Test
    public void test_getStudentDetailsByEmailAddressNativeNamedParam(){
        Student student=studentRepository.getStudentDetailsByEmailAddressNativeNamedParam("vinod@gmail.com");
        System.out.println(student);
        Assertions.assertNotNull(student);
        Assertions.assertEquals(student.getFirstName(),"Vinod");
    }

    @DisplayName("Update using Native Query with @Modifying and @Transactional annotation")
    @Test
    public void test_updateFirstNameWithEmailId(){
        int updateCount=studentRepository.updateFirstNameWithEmailId("VINOD","vinod@gmail.com");
        System.out.println("updateCount : "+updateCount);
        Assertions.assertEquals(updateCount,1);
    }

}