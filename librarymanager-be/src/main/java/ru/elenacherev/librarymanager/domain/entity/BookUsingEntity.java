package ru.elenacherev.librarymanager.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "BOOK_USINGS")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class BookUsingEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "BOOK_USING_ID")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long bookUsingId;

    @Version
    @Column(name = "VERSION")
    private int version; //версия, инкрементится при редактировании записи

    @NotNull(message = "{validation.startDate.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    private Date startDate; //Книгу взяли

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate; //Книгу вернули

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private ReaderEntity reader; //кто взял книгу

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private BookEntity book; //какую книгу взяли

}
