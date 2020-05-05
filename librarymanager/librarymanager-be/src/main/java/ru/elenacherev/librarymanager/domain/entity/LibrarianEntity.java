package ru.elenacherev.librarymanager.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "LIBRARIANS")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class LibrarianEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "LIBRARIAN_ID")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long librarianId;

    @Version
    @Column(name = "VERSION")
    private int version; //версия, инкрементится при редактировании записи

}
