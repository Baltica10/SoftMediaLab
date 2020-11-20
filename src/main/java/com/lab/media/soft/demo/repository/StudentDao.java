package com.lab.media.soft.demo.repository;

import com.lab.media.soft.demo.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface StudentDao extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndIsDeletedFalse(Long aLong);

    @Query(value = "SELECT * " +
            "FROM students s " +
            "WHERE s.is_deleted = false " +
            "AND ((cast(s.id as varchar) LIKE CONCAT('%', :query, '%')) " +
            "OR (LOWER(s.first_name) LIKE CONCAT('%', :query, '%')) " +
            "OR (LOWER(s.last_name) LIKE CONCAT('%', :query, '%')) " +
            "OR (LOWER(s.patronymic) LIKE CONCAT('%', :query, '%')))",
            nativeQuery = true)
    List<Student> findAllByParams(Pageable pageable, @Param("query") String query);
}