package com.example.bookshelf.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookSearch(
    val items: List<BookSummary>
)

@Serializable
data class BookSummary(
    val id: String,
)

@Serializable
data class Book(
    val volumeInfo: BookInfo,
)

@Serializable
data class BookInfo(
    val title: String,
    val authors: List<String>,
    val imageLinks: ImageLinks,
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String,
)