package com.example.arushaproject.Repository;

import com.example.arushaproject.Model.StudentCourse;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Repository
public interface StudentCourseRepository extends ReactiveCrudRepository<StudentCourse, Long> {
    Mono<StudentCourse> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
