package com.example.arushaproject;

import com.example.arushaproject.Service.StudentCourseService;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ArushaProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ArushaProjectApplication.class, args);
    }
    private final StudentCourseService studentCourseService;

    private final DatabaseClient databaseClient;

    public ArushaProjectApplication(ConnectionFactory connectionFactory, StudentCourseService studentCourseService) {
        this.databaseClient = DatabaseClient.create(connectionFactory);
        this.studentCourseService = studentCourseService;
    }

    @Override
    public void run(String... args) throws Exception {
        Mono<Void> createStudentTableMono = databaseClient
                .sql("CREATE TABLE IF NOT EXISTS students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))").then();
        createStudentTableMono.block();
        Mono<Void> createCourseTableMono = databaseClient
                .sql("CREATE TABLE IF NOT EXISTS courses (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))").then();
        createCourseTableMono.block();
        Mono<Void> createStudentCourseTableMono = databaseClient
                .sql("CREATE TABLE IF NOT EXISTS student_courses (id INT AUTO_INCREMENT, student_id INT, course_id INT, PRIMARY KEY (id))").then();
        createStudentCourseTableMono.block();
        studentCourseService.addRandomStudentCourse().subscribe();
        studentCourseService.updateStudentCourse();
    }
}
