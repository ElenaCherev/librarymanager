package ru.spring5.elena.librarymanager.domain.entity;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "AUTHORS")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "AUTHOR_ID")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long authorId;

    @Version
    @Column(name = "VERSION")
    private int version; //версия, инкрементится при редактировании записи

    @NotEmpty(message = "{validation.firstname.notempty.message}")
    @Size(min = 3, max = 60, message = "{validation.firstname.size.message}")
    @Column(name = "FIRST_NAME")
    private String firstName; //имя

    @NotEmpty(message = "{validation.surname.notempty.message}")
    @Size(min = 1, max = 60, message = "{validation.surname.size.message}")
    @Column(name = "SURNAME")
    private String surname; //фамилия

    @Size(min = 0, max = 60, message = "{validation.patronymic.Size.message}")
    @Column(name = "PATRONYMIC")
    private String patronymic; //отчество

    @NotNull(message = "{validation.birthday.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDATE")
    private Date birthdate; //

    @Temporal(TemporalType.DATE)
    @Column(name = "DEATHDATE")
    private Date deathdate; //

    @Size(min = 0, max = 1000, message = "{validation.info.Size.message}")
    @Column(name = "INFO")
    private String info; //доп. информация

    @ManyToMany
    @JoinTable(name = "AUTHOR_EDITION", joinColumns = @JoinColumn(name = "AUTHOR_ID"), inverseJoinColumns = @JoinColumn(name = "EDITION_ID"))
    @Setter(AccessLevel.NONE)
    private Set<EditionEntity> editions = new HashSet<EditionEntity>(); //Все работы данного автора

    @Formula("(SURNAME || ' ' || FIRST_NAME || ' ' || PATRONYMIC)")
    @Transient
    private String fio;
}
