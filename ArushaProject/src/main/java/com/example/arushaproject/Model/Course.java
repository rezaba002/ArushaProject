package com.example.arushaproject.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("courses")
public class Course {
    @Id
    @Column("id")
    private Long courseId;
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public Course() {}

    public Long getId() {return courseId;}

    public void setId(Long id) {this.courseId = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                '}';
    }
}
