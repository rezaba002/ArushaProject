package com.example.arushaproject.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("students")
public class Student {
    @Id
    @Column("id")
    private Long studentId;
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public Student() {}

    public Long getId() {
        return studentId;
    }

    public void setId(Long id) {
        this.studentId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                '}';
    }
}
