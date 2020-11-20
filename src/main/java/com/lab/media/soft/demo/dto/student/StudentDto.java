package com.lab.media.soft.demo.dto.student;

import com.fasterxml.jackson.annotation.*;
import com.lab.media.soft.demo.dto.progress.ProgressDto;
import com.lab.media.soft.demo.model.Student;
import lombok.*;

import java.time.LocalDate;

import static com.lab.media.soft.demo.util.Constants.DATE_PATTERN;
import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDate birthday;

    @JsonProperty("progress")
    private ProgressDto progress;

    public static StudentDto of(Student model) {
        StudentDto result = new StudentDto();
        result.setId(model.getId());
        result.setFirstName(model.getFirstName());
        result.setLastName(model.getLastName());
        result.setPatronymic(model.getPatronymic());
        result.setBirthday(model.getBirthday());
        result.setProgress(nonNull(model.getProgress()) ? ProgressDto.of(model.getProgress()) : null);

        return result;
    }
}
