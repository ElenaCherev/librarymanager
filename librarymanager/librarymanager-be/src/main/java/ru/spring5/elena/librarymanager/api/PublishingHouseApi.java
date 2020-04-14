package ru.spring5.elena.librarymanager.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.spring5.elena.librarymanager.api.dto.Edition;
import ru.spring5.elena.librarymanager.api.dto.PublishingHouse;

public interface PublishingHouseApi {

	PublishingHouse getPublishingHouse(
			@NotNull Long publishingHouseId
	);

	Page<PublishingHouse> searchPublishingHouses(
			Pageable pageable
	);

	PublishingHouse createPublishingHouse(
			@Valid PublishingHouse publishingHouse
	);

	PublishingHouse savePublishingHouse(
			@NotNull Long publishingHouseId, 
			@Valid PublishingHouse publishingHouse
	);

	Page<Edition> searchEditions(
			@NotNull Long publishingHouseId, 
			Pageable pageable
	);

	List<Edition> updateEditionsByPublishingHouseId(
			@NotNull Long publishingHouseId, 
			@NotNull List<Long> editionIds
	);

}