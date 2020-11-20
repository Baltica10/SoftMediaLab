package com.lab.media.soft.demo.controller;

import com.lab.media.soft.demo.dto.student.*;
import com.lab.media.soft.demo.exception.ValidationException;
import com.lab.media.soft.demo.service.StudentService;
import io.swagger.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

import static com.lab.media.soft.demo.util.Constants.*;

@RestController
@RequestMapping(API_V1 + STUDENTS)
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @ApiOperation("Поиск всех студентов")
    @GetMapping()
    public ResponseEntity<List<StudentDto>> findAllStudents(@ApiParam(value = "Пагинация, номер страницы. Начинается с 0", example = "0")
                                                            @RequestParam(required = false, defaultValue = "0", value = "offset") Integer offset,

                                                            @ApiParam(value = "Пагинация, кол-во объектов на странице. 10, 15, 30, 50", example = "15")
                                                            @RequestParam(required = false, defaultValue = "15", value = "limit") Integer limit,

                                                            @ApiParam(value = "Текстовый поиск по ФИО и ID", example = "иванов")
                                                            @RequestParam(required = false, defaultValue = "", value = "query") String query) {
        return ResponseEntity.ok(service.findAllStudents(offset, limit, query));
    }

    @ApiOperation("Поиск студента по ID")
    @GetMapping(ID)
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStudent(id));
    }

    @ApiOperation("Создать студента")
    @PostMapping()
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentUpdateDto dto,
                                                    @NotNull @ApiIgnore Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException("Validation error!", errors.getFieldErrors());
        }

        return ResponseEntity.ok(service.createStudent(dto));
    }

    @ApiOperation("Изменить студента")
    @PutMapping(ID)
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,
                                                    @Valid @RequestBody StudentUpdateDto dto,
                                                    @NotNull @ApiIgnore Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException("Validation error!", errors.getFieldErrors());
        }

        return ResponseEntity.ok(service.updateStudent(id, dto));
    }

    @ApiOperation("Удалить студента")
    @DeleteMapping(ID)
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
