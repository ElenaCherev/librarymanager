package ru.spring5.elena.librarymanager.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GENERES")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY) // уникальный ID, генерируется сам в БД
    @Column(name = "GENERE_ID")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long genreId;

    @Version
    @Column(name = "VERSION")
    private int version; //версия, инкрементится при редактировании записи

    @Column(name = "TITLE")
    private String title; //
    
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<EditionEntity> editions = new HashSet<EditionEntity>();
}
