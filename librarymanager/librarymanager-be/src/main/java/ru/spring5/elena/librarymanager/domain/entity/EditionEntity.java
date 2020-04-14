package ru.spring5.elena.librarymanager.domain.entity;

import lombok.*;
import ru.spring5.elena.librarymanager.util.AgeRating;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "EDITIONS")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class EditionEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "EDITION_ID")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long editionId;

    @Version
    @Column(name = "VERSION")
    private int version; //версия, инкрементится при редактировании записи

    @Column(name = "TITLE")
    private String title;

    @Column(name = "WORK_TITLE")
    private String workTitle; //

    @Column(name = "YEAR")
    private int year;

    @ManyToOne
    @JoinColumn(name = "GENERE_ID")
    private GenreEntity genre;

    @ManyToOne
    @JoinColumn(name = "LANG_ID")
    private LangEntity lang;

    @ManyToOne
    @JoinColumn(name = "SECTION_ID")
    private SectionEntity section;

    @Column(name = "PUBLISHING_YEAR")
    private int publishingYear;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "IS_ILLUSTRATED")
    private boolean isIllustrated;

    @Column(name = "DOWNLOAD_LINK")
    private String downloadLink;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "AGE")
    private AgeRating age;

    @Column(name = "INFO")
    private String info;

    @ManyToOne
    @JoinColumn(name = "PUBLISHING_HOUSE_ID")
    private PublishingHouseEntity publishingHouse;

    @ManyToOne
    @JoinColumn(name = "PUPL_LANG_ID")
    private LangEntity publLang;

    @ManyToMany
    @JoinTable(name = "AUTHOR_EDITION", joinColumns = @JoinColumn(name = "EDITION_ID"), inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    @Setter(AccessLevel.NONE)
    private Set<AuthorEntity> authors = new HashSet<AuthorEntity>();

    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<OrderEntity> orders = new HashSet<OrderEntity>();
    
    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<BookEntity> books = new HashSet<BookEntity>();
}
