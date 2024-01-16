package com.dailycodebuffer.springdata.repository;

import com.dailycodebuffer.springdata.entity.Course;
import com.dailycodebuffer.springdata.entity.Student;
import com.dailycodebuffer.springdata.entity.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void test_getCourseWithCourseMaterial(){
        List<Course> courseList=courseRepository.findAll();
        Assertions.assertNotNull(courseList);
        System.out.println(courseList);
    }

    @Test
    public void test_saveCourseWithTeacher(){
        Teacher teacher=Teacher.builder()
                .firstName("Ranga")
                .lastName("Karnam")
                .build();

        Course course1=Course.builder()
                .title("Java")
                .credit(7)
                .teacher(teacher)
                .build();

        courseRepository.save(course1);
    }

    @DisplayName("Get the pages based on the number of records defined per page")
    @Test
    public void test_findAllPagination(){
        Pageable firstPageWithTwoRecords= PageRequest.of(0,2);
        List<Course> courseList=courseRepository.findAll(firstPageWithTwoRecords).getContent();
        System.out.println(courseList);
        long totalElements=courseRepository.findAll(firstPageWithTwoRecords).getTotalElements();
        System.out.println("totalElements: "+totalElements);
        long totalPages=courseRepository.findAll(firstPageWithTwoRecords).getTotalPages();
        System.out.println("totalPages: "+totalPages);
    }

    @DisplayName("Get the pages based on the number of records defined per page")
    @Test
    public void test_findAllBySorting(){
        Pageable firstPageWithTwoRecordsSortByTitle= PageRequest.of(0,2, Sort.by("title"));
        // Pageable firstPageWithTwoRecordsSortByCreditDesc= PageRequest.of(0,2, Sort.by("credit").descending());

        List<Course> courseList=courseRepository.findAll(firstPageWithTwoRecordsSortByTitle).getContent();
        System.out.println(courseList);
        long totalElements=courseRepository.findAll(firstPageWithTwoRecordsSortByTitle).getTotalElements();
        System.out.println("totalElements: "+totalElements);
        long totalPages=courseRepository.findAll(firstPageWithTwoRecordsSortByTitle).getTotalPages();
        System.out.println("totalPages: "+totalPages);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishek@gmail.com")
                .build();

        Course course = Course
                .builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }




}
