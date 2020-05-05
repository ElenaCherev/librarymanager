package ru.elenacherev.librarymanager.util.JSON;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class DtoGrid<T> {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private Page<T> dtoData;
}
