package ru.spring5.elena.librarymanager.mapper;

import java.util.stream.Collectors;

import ru.spring5.elena.librarymanager.api.dto.Reader;
import ru.spring5.elena.librarymanager.domain.entity.ReaderEntity;

public class ReaderMapper {
    public static Reader map(ReaderEntity entity) {
        return Reader.builder()
                .readrId(entity.getReaderId())
                .firstName(entity.getFirstName())
                .surname(entity.getSurname())
                .patronymic(entity.getPatronymic())
                .birthdate(entity.getBirthdate())
                .regDate(entity.getRegDate())
                .telephone(entity.getTelephone())
                .email(entity.getEmail())
                .bookUsingIds(entity.getBookUsings()
                		.stream()
                		.map(bookUsingEntity->
                				bookUsingEntity
                				.getBookUsingId())
                		.collect(Collectors.toSet())
                 )
                .orderIds(entity.getOrders()
                		.stream()
                		.map(orderEntity-> orderEntity.getOrderId())
                		.collect(Collectors.toSet()))
                .build();
    }

    public static ReaderEntity map(Reader dto) {
        return map(dto, new ReaderEntity());
    }

    public static ReaderEntity map(Reader dto, ReaderEntity entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setSurname(dto.getSurname());
        entity.setPatronymic(dto.getPatronymic());
        entity.setBirthdate(dto.getBirthdate());
        entity.setRegDate(dto.getRegDate());
        entity.setTelephone(dto.getTelephone());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
