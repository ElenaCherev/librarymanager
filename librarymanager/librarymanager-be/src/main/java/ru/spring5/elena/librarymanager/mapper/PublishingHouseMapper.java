package ru.spring5.elena.librarymanager.mapper;

import ru.spring5.elena.librarymanager.api.dto.PublishingHouse;
import ru.spring5.elena.librarymanager.domain.entity.PublishingHouseEntity;

public class PublishingHouseMapper {
    public static PublishingHouse map(PublishingHouseEntity entity) {
        return PublishingHouse.builder()
                .publishingHouseId(entity.getPublishingHouseId())
                .title(entity.getTitle())
                .logo(entity.getLogo())
                .build();
    }

    public static PublishingHouseEntity map(PublishingHouse dto) {
        return map(dto, new PublishingHouseEntity());
    }

    public static PublishingHouseEntity map(PublishingHouse dto, PublishingHouseEntity entity) {
        entity.setTitle(dto.getTitle());
        entity.setLogo(dto.getLogo());
        return entity;
    }
}
