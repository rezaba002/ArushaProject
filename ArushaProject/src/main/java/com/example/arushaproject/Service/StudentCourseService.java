package com.example.arushaproject.Service;

import com.example.arushaproject.Model.Course;
import com.example.arushaproject.Model.Student;
import com.example.arushaproject.Model.StudentCourse;
import com.example.arushaproject.Repository.CourseRepository;
import com.example.arushaproject.Repository.StudentCourseRepository;
import com.example.arushaproject.Repository.StudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StudentCourseService {
    List<Student> studentList = new ArrayList<>();
    List<Course> courseList = new ArrayList<>();
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;

    public StudentCourseService(StudentRepository studentRepository, CourseRepository courseRepository,
                                StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    public Mono<Void> addRandomStudentCourse() {

        Student student1 = new Student("ahmad");
        studentList.add(student1);

        Student student2 = new Student("reza");
        studentList.add(student2);

        Student student3 = new Student("sahar");
        studentList.add(student3);

        System.out.println("studentList: " + studentList);

        Course course1 = new Course("riazi");
        courseList.add(course1);

        Course course2 = new Course("physic");
        courseList.add(course2);

        Course course3 = new Course("shimi");
        courseList.add(course3);

        System.out.println("courseList: " + courseList);

        Flux<Student> studentFlux = Flux.fromIterable(studentList)
                .flatMap(studentRepository::save);
        System.out.println("studentFlux: " + studentFlux);

        Flux<Course> courseFlux = Flux.fromIterable(courseList)
                .flatMap(courseRepository::save);

        System.out.println("courseFlux: " + courseFlux);
        Random random = new Random(1000L);
        return Flux.zip(studentFlux.collectList(), courseFlux.collectList())
                .flatMap(tuple -> {
                    List<Student> students = tuple.getT1();
                    List<Course> courses = tuple.getT2();


                    List<Mono<StudentCourse>> studentCourseMonos = new ArrayList<>();
                    for (Student student : students) {
                        for (Course course : courses) {
                            studentCourseMonos.add(studentCourseRepository.save(new StudentCourse(random.nextLong(), student.getId(), course.getId())));
                        }
                    }
                    return Flux.merge(studentCourseMonos);
               })
                .then();
    }

    public void updateStudentCourse() {
        Mono<Student> filteredStudent = studentRepository.findById(1L)
                .flatMap(student -> {
                    student.setName("mohammad");
                    return studentRepository.save(student);
                });
        filteredStudent.subscribe();

        Mono<Course> filteredCourse = courseRepository.findById(2L)
                .flatMap(course -> {
                    course.setName("varzesh");
                    return courseRepository.save(course);
                });
        filteredCourse.subscribe();
    }
}


