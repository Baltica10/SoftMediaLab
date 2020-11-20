package com.lab.media.soft.demo.service;

import com.lab.media.soft.demo.dto.progress.*;
import com.lab.media.soft.demo.dto.student.*;
import com.lab.media.soft.demo.model.Progress;

import java.util.List;

public interface ReferencesService {

    List<ProgressDto> findAllProgress();

    ProgressDto createProgress(ProgressUpdateDto dto);

    ProgressDto updateProgress(Long progressId, ProgressUpdateDto dto);

    void deleteProgress(Long progressId);

    Progress findById(Long progressId);
}