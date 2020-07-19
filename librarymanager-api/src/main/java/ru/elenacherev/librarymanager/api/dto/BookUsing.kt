package ru.elenacherev.librarymanager.api.dto

import java.time.Instant
import java.util.*

data class BookUsing(
    var bookUsingId: UUID? = null,
    val startDate: Instant,
    val endDate: Instant? = null,
    val readerId: UUID,
    val bookId: UUID
)