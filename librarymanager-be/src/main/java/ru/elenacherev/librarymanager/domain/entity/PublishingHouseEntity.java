package ru.elenacherev.librarymanager.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PUBLISHING_HOUSES")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class PublishingHouseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "PUBLISHING_HOUSE_ID")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long publishingHouseId;

    @Version
    @Column(name = "VERSION")
    private int version; //версия, инкрементится при редактировании записи

    @Column(name = "TITLE")
    private String title;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "LOGO")
    private byte logo;
    
    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<EditionEntity> editions = new HashSet<EditionEntity>();
}
