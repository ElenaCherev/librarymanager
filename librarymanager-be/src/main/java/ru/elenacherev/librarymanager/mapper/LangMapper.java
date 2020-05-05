package ru.elenacherev.librarymanager.mapper;

import ru.elenacherev.librarymanager.api.dto.Lang;
import ru.elenacherev.librarymanager.domain.entity.LangEntity;

public class LangMapper {
    public static Lang map(LangEntity entity) {
        return Lang.builder()
                .langId(entity.getLangId())
                .title(entity.getTitle())
                .build();
    }

    public static LangEntity map(Lang dto) {
        return map(dto, new LangEntity());
    }

    public static LangEntity map(Lang dto, LangEntity entity) {
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
