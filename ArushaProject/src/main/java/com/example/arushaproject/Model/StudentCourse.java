package com.example.arushaproject.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("student_courses")
public class StudentCourse {
    @Id
    private Long id;

    @Column("student_id")
    private Long studentId;
    @Column("course_id")
    private Long courseId;

    public StudentCourse(Long id, Long studentId, Long courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public StudentCourse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
