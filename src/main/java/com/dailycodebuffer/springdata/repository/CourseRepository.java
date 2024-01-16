package com.dailycodebuffer.springdata.repository;

import com.dailycodebuffer.springdata.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}
