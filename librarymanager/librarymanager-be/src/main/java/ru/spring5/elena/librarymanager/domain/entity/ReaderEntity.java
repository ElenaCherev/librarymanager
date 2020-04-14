package ru.spring5.elena.librarymanager.domain.entity;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
/*
 * Класс для  хранения инфы о читателях библиотеки
 */

@Entity
@Table(name = "READERS")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class ReaderEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 993948553969252992L;

    @Id
    @GeneratedValue(strategy = IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "READER_ID")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long readerId;

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
    private Date birthdate; //дата рождения, чтобы камасутру мелким не выдать

    @Temporal(TemporalType.DATE)
    @Column(name = "REG_DATE")
    private Date regDate;

    @Size(min = 0, max = 12, message = "{validation.telephone.Size.message}")
    @Column(name = "TELEPHONE")
    private String telephone; //телефон, чтобы присылать смс о готовых заказах и задолжностях

    @NotEmpty(message = "{validation.email.NotEmpty.message}")
    @Size(min = 4, max = 200, message = "{validation.email.Size.message}")
    @Column(name = "EMAIL")
    private String email; //электронная почта. Уникальное поле, используется для авторизации

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<OrderEntity> orders = new HashSet<OrderEntity>();

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<BookUsingEntity> bookUsings = new HashSet<BookUsingEntity>();

    @Formula("(SURNAME || ' ' || FIRST_NAME || ' ' || PATRONYMIC)")
    @Transient
    private String fio;

}
