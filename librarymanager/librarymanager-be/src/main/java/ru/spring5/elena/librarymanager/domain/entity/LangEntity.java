package ru.spring5.elena.librarymanager.domain.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LANGS")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class LangEntity {
    @Id
    @Column(name = "LANG_ID")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long langId;

    @Version
    @Column(name = "VERSION")
    private int version; //версия, инкрементится при редактировании записи

    @Column(name = "TITLE")
    private String title; //
    
    @OneToMany(mappedBy = "lang", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<EditionEntity> editions = new HashSet<EditionEntity>();
    
    @OneToMany(mappedBy = "publLang", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<EditionEntity> publLangEditions = new HashSet<EditionEntity>();
}
