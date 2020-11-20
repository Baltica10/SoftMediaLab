package com.lab.media.soft.demo.service;

import com.lab.media.soft.demo.dto.student.*;
import com.lab.media.soft.demo.exception.*;
import com.lab.media.soft.demo.model.Student;
import com.lab.media.soft.demo.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.lab.media.soft.demo.util.Constants.*;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final ReferencesService referencesService;

    public StudentServiceImpl(StudentDao studentDao,
                              ReferencesService referencesService) {
        this.studentDao = studentDao;
        this.referencesService = referencesService;
    }

    @Override
    public List<StudentDto> findAllStudents(Integer offset, Integer limit, String query) {
        return studentDao.findAllByParams(PageRequest.of(offset, limit), query).stream()
                .sorted(Comparator.comparing(Student::getId))
                .map(StudentDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudent(Long studentId) {
        return StudentDto.of(studentDao.findByIdAndIsDeletedFalse(studentId)
                .orElseThrow(() -> new EntityNotFoundException(STUDENT_NOT_FOUND)));
    }

    @Override
    public StudentDto createStudent(StudentUpdateDto dto) {
        log.info("Create new student ");

        return updateValues(new Student(), dto);
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentUpdateDto dto) {
        Student student = studentDao.findByIdAndIsDeletedFalse(studentId)
                .orElseThrow(() -> new EntityNotFoundException(STUDENT_NOT_FOUND));
        log.info("Update student by ID:{}", studentId);
        return updateValues(student, dto);
    }

    private StudentDto updateValues(Student model, StudentUpdateDto dto) {

        //Student age min 14 years
        LocalDate currentDate = LocalDate.now();
        if (dto.getBirthday().isAfter(currentDate.minusYears(14))) {
            throw new ValidationException("Date of birth cannot be greater " + currentDate.minusYears(14));
        }

        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setPatronymic(dto.getPatronymic());
        model.setBirthday(dto.getBirthday());
        model.setProgress(referencesService.findById(dto.getProgressId()));

        if (nonNull(model.getId())) {
            model.setUpdatedAt(LocalDateTime.now());
        }

        return StudentDto.of(studentDao.saveAndFlush(model));
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentDao.findByIdAndIsDeletedFalse(studentId)
                .orElseThrow(() -> new EntityNotFoundException(STUDENT_NOT_FOUND));
        student.setIsDeleted(TRUE);

        log.info("Delete student by ID:{}", studentId);
        studentDao.saveAndFlush(student);
    }
}