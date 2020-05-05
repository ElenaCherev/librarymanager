package ru.elenacherev.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Builder
@Getter
public class Reader {
    private Long readrId;
    
    private String firstName; //имя
    private String surname; //фамилия
    private String patronymic; //отчество
    private Date birthdate; //дата рождения, чтобы камасутру мелким не выдать
    private Date regDate;
    private String telephone; //телефон, чтобы присылать смс о готовых заказах и задолжностях
    private String email; //электронная почта. Уникальное поле, используется для авторизации
    
    private Set<Long> bookUsingIds;
    
    private Set<Long> orderIds;
}
