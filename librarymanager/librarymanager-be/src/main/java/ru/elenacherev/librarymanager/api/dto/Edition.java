package ru.elenacherev.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;
import ru.elenacherev.librarymanager.util.AgeRating;

import java.util.List;

@Builder
@Getter
public class Edition {
    private Long editionId;
    
    private String title;
    private String workTitle; //
    private int year;
    private int publishingYear;
    private String isbn;
    private boolean isIllustrated;
    private String downloadLink;
    private AgeRating age;
    private String info;
    
    private String langString;
    private Long langId;
    
    private String publLangString;
    private Long publLangId;
    
    private String publishingHouseString;
    private Long publishingHouseId;
    
    private String genreString;
    private Long genreId;
    
    private String sectionTitle;
    private Long sectionId;
    
    private List<Long> authorIds;
    private List<String> authorFIOs;
}
