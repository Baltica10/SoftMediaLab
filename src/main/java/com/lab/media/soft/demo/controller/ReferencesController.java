package com.lab.media.soft.demo.controller;

import com.lab.media.soft.demo.dto.progress.*;
import com.lab.media.soft.demo.exception.ValidationException;
import com.lab.media.soft.demo.service.ReferencesService;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

import static com.lab.media.soft.demo.util.Constants.*;

@RestController
@RequestMapping(API_V1 + REFERENCES)
public class ReferencesController {

    private final ReferencesService service;

    public ReferencesController(ReferencesService service) {
        this.service = service;
    }

    @ApiOperation("Справочник 'успеваемость'")
    @GetMapping(PROGRESS)
    public ResponseEntity<List<ProgressDto>> findAllProgress() {
        return ResponseEntity.ok(service.findAllProgress());
    }

    @ApiOperation("Справочник 'успеваемость' - создать новое значение")
    @PostMapping(PROGRESS)
    public ResponseEntity<ProgressDto> createProgress(@Valid @RequestBody ProgressUpdateDto dto,
                                                      @NotNull @ApiIgnore Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException("Validation error!", errors.getFieldErrors());
        }

        return ResponseEntity.ok(service.createProgress(dto));
    }

    @ApiOperation("Справочник 'успеваемость' - изменить")
    @PutMapping(PROGRESS + ID)
    public ResponseEntity<ProgressDto> updateProgress(@PathVariable Long id,
                                                      @Valid @RequestBody ProgressUpdateDto dto,
                                                      @NotNull @ApiIgnore Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException("Validation error!", errors.getFieldErrors());
        }

        return ResponseEntity.ok(service.updateProgress(id, dto));
    }

    @ApiOperation("Справочник 'успеваемость' - удалить")
    @DeleteMapping(PROGRESS + ID)
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        service.deleteProgress(id);
        return ResponseEntity.ok().build();
    }
}