package ru.elenacherev.librarymanager.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "SECTIONS")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class SectionEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "SECTION_ID")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long sectionId;

    @Version
    @Column(name = "VERSION")
    private int version; //версия, инкрементится при редактировании записи

    @NotEmpty(message = "{validation.title.notempty.message}")
    @Size(min = 3, max = 60, message = "{validation.title.size.message}")
    @Column(name = "TITLE")
    private String title; //

    @ManyToOne
    @JoinColumn(name = "PARENT_SECTION_ID")
    private SectionEntity parentSection;

    @OneToMany(mappedBy = "parentSection", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<SectionEntity> sections = new HashSet<SectionEntity>();
    
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<EditionEntity> editions = new HashSet<EditionEntity>();
}
