package com.lab.media.soft.demo.repository;

import com.lab.media.soft.demo.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgressDao extends JpaRepository<Progress, Long> {
    Optional<Progress> findByText(String text);
}