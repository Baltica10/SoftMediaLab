package com.lab.media.soft.demo.service;

import com.lab.media.soft.demo.dto.progress.*;
import com.lab.media.soft.demo.exception.EntityNotFoundException;
import com.lab.media.soft.demo.model.Progress;
import com.lab.media.soft.demo.repository.ProgressDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.lab.media.soft.demo.util.Constants.PROGRESS_NOT_FOUND;

@Slf4j
@Service
public class ReferencesServiceImpl implements ReferencesService {

    private final ProgressDao progressDao;

    public ReferencesServiceImpl(ProgressDao progressDao) {
        this.progressDao = progressDao;
    }

    @Override
    public List<ProgressDto> findAllProgress() {
        return progressDao.findAll().stream()
                .sorted(Comparator.comparing(Progress::getId))
                .map(ProgressDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public ProgressDto createProgress(ProgressUpdateDto dto) {
        log.info("Create new Progress, text:{}", dto.getText());

        Optional<Progress> optional = progressDao.findByText(dto.getText());

        return optional.map(ProgressDto::of).orElseGet(() -> ProgressDto.of(progressDao.saveAndFlush(new Progress(dto.getText()))));
    }

    @Override
    public ProgressDto updateProgress(Long progressId, ProgressUpdateDto dto) {
        Progress progress = findById(progressId);
        progress.setText(dto.getText());

        log.info("Update Progress, ID:{} text:{}", progressId, dto.getText());
        return ProgressDto.of(progressDao.saveAndFlush(progress));
    }

    @Override
    public void deleteProgress(Long progressId) {
        progressDao.deleteById(progressId);
        log.info("Delete Progress, ID:{}", progressId);
    }

    @Override
    public Progress findById(Long progressId) {
        return progressDao.findById(progressId)
                .orElseThrow(() -> new EntityNotFoundException(PROGRESS_NOT_FOUND));
    }
}