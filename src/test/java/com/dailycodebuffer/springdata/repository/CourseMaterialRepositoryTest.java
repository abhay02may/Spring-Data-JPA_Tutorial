package com.dailycodebuffer.springdata.repository;

import com.dailycodebuffer.springdata.entity.Course;
import com.dailycodebuffer.springdata.entity.CourseMaterial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void test_saveCourseMaterialWithCourse(){
        Course course= Course.builder()
                       .credit(5)
                       .title("DSA")
                       .build();
        CourseMaterial courseMaterial=CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();
        CourseMaterial courseMaterial2=courseMaterialRepository.save(courseMaterial);
        Assertions.assertNotNull(courseMaterial2);
        Assertions.assertEquals(course.getTitle(),"DSA");
    }

    @Test
    public void test_getAllCourseMaterials(){
        List<CourseMaterial> courseMaterialList=courseMaterialRepository.findAll();
        courseMaterialList.stream().forEach(courseMaterial -> System.out.println(courseMaterial));
        Assertions.assertNotNull(courseMaterialList);
    }



}