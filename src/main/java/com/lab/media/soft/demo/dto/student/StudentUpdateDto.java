package com.lab.media.soft.demo.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

import static com.lab.media.soft.demo.util.Constants.LETTERS_ONLY_PATTERN;

@Getter
@Setter
@NoArgsConstructor
public class StudentUpdateDto {

    @Pattern(regexp = LETTERS_ONLY_PATTERN)
    @JsonProperty("first_name")
    private String firstName;

    @Pattern(regexp = LETTERS_ONLY_PATTERN)
    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("patronymic")
    private String patronymic;

    @NotNull
    @JsonProperty("birthday")
    private LocalDate birthday;

    @NotNull
    @JsonProperty("progress_id")
    private Long progressId;
}