package ru.elenacherev.librarymanager.api.dto

import java.util.Date

data class BookUsing (
     var bookUsingId: Long? = null,
     val startDate : Date,
     val endDate: Date? = null,
     val readerId: Long,
     val bookId: Long
)