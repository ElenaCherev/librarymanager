package ru.elenacherev.librarymanager.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "BOOKS")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class BookEntity {
    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = IDENTITY) //уникальный ID, генерируется сам в БД
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long bookId;

    @Version
    @Column(name = "VERSION")
    private int version; //версия, инкрементится при редактировании записи

    @Column(name = "IS_RESERVED")
    private boolean isReserved;

    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable;

    @Column(name = "SHELF_NUMBER")
    private int shelfNumber;

    @ManyToOne
    @JoinColumn(name = "EDITION_ID")
    private EditionEntity edition;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<BookUsingEntity> bookUsings = new HashSet<BookUsingEntity>();
}
