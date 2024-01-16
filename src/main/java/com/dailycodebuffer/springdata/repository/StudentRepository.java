package com.dailycodebuffer.springdata.repository;

import com.dailycodebuffer.springdata.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    public Student findByFirstName(String firstName);
    public List<Student> findByLastNameContaining(String lastName);
    public List<Student> findByLastNameNotNull();
    public List<Student> findByGuardianGuardianName(String guardianName);
    public Student findByFirstNameAndLastName(String firstName,String lastName);

    // Using JPQL Query
    @Query("select s from Student s where s.emailId=?1 ")
    public Student getStudentDetailsByEmailAddress(String emailAddress);

    // Using Native SQL QUERY
    @Query(
            value = "Select * from Student student where student.email_id=?1 ",
            nativeQuery = true
    )
    public Student getStudentDetailsByEmailAddressNative(String emailAddress);

    // Using Native Named Parameter QUERY
    @Query(
            value = "Select * from Student student where student.email_id=:emailId ",
            nativeQuery = true
    )
    public Student getStudentDetailsByEmailAddressNativeNamedParam(@Param("emailId") String emailAddress);

    // Update using Native Query with @Modifying and @Transactional annotation

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE STUDENT SET FIRST_NAME=?1 WHERE EMAIL_ID=?2 ",
            nativeQuery = true
    )
    int updateFirstNameWithEmailId(String firstName, String emailId);
}
