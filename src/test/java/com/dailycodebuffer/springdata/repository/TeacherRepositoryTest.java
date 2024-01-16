package com.dailycodebuffer.springdata.repository;

import com.dailycodebuffer.springdata.entity.Course;
import com.dailycodebuffer.springdata.entity.CourseMaterial;
import com.dailycodebuffer.springdata.entity.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @DisplayName("We have 1 to Many mapping so we can save 1 teacher with multiple courses")
    @Test
    public void test_saveTeacherWithCourses(){

        List<Course> courseList=getCourses();
        Teacher teacher=Teacher.builder()
               // .courses(courseList)
                .firstName("Ranga")
                .lastName("Karnam")
                .build();
        Teacher newTeacher=teacherRepository.save(teacher);
        Assertions.assertNotNull(newTeacher);
        Assertions.assertEquals(newTeacher.getFirstName(),"Ranga");

    }

    private List<Course> getCourses(){
        List<Course> courseList=new ArrayList<>();
        Course course1=Course.builder().title("Java").credit(7).build();
        CourseMaterial courseMaterial1=CourseMaterial.builder().url("www.dailycodebuffer.com").build();
        course1.setCourseMaterial(courseMaterial1);
        courseList.add(course1);
        Course course2=Course.builder().title("DBA").credit(5).build();
        CourseMaterial courseMaterial2=CourseMaterial.builder().url("www.javbrains.com").build();
        course2.setCourseMaterial(courseMaterial2);
        courseList.add(course2);
        return courseList;
    }

}