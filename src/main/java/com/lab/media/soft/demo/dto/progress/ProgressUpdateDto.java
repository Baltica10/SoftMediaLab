package com.lab.media.soft.demo.dto.progress;


import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
public class ProgressUpdateDto {

    @NotBlank
    private String text;
}