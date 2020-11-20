package com.lab.media.soft.demo.service;

import com.lab.media.soft.demo.dto.student.*;

import java.util.List;

public interface StudentService {

    List<StudentDto> findAllStudents(Integer offset, Integer limit, String query);

    StudentDto getStudent(Long studentId);

    StudentDto createStudent(StudentUpdateDto dto);

    StudentDto updateStudent(Long studentId, StudentUpdateDto dto);

    void deleteStudent(Long studentId);
}