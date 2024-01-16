package com.dailycodebuffer.springdata.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "COURSE")
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_sequence")
    private Long courseId;
    private String title;
    private Integer credit;

    @OneToOne(
            mappedBy = "course",
            cascade = CascadeType.ALL
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;


    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId" 
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id"                    
            )
    )
    List<Student> students;

    public void addStudents(Student student){
        if(students == null){
            students = new ArrayList<>();
        }

        students.add(student);
    }
}
