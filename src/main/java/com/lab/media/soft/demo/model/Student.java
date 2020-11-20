package com.lab.media.soft.demo.model;


import lombok.*;

import javax.persistence.*;
import java.time.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    /**
     *  Успеваемость (опционально, значение из справочника)
     */
    @ManyToOne
    @JoinColumn(name = "progress_id", referencedColumnName = "id")
    private Progress progress;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}