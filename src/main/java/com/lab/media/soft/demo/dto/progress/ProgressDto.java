package com.lab.media.soft.demo.dto.progress;


import com.lab.media.soft.demo.model.Progress;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class ProgressDto {
    private Long id;
    private String text;

    public static ProgressDto of(Progress model) {
        ProgressDto result = new ProgressDto();
        result.setId(model.getId());
        result.setText(model.getText());

        return result;
    }
}