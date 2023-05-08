package com.example.bookshelf

import com.example.bookshelf.data.Book
import com.example.bookshelf.data.BookInfo
import com.example.bookshelf.data.BookSearch
import com.example.bookshelf.data.BookSummary
import com.example.bookshelf.data.ImageLinks

object FakeApiResponse {
    val validBookSearchResponse: BookSearch = BookSearch(
        items = listOf(
            BookSummary(
                id = "id1",
            ),
            BookSummary(
                id = "id2",
            )
        )
    )

    val partiallyValidBookSearchResponse: BookSearch = BookSearch(
        items = listOf(
            BookSummary(
                id = "id1",
            ),
            BookSummary(
                id = "id3",
            )
        )
    )

    val emptyBookSearchResponse: BookSearch = BookSearch(
        items = emptyList()
    )

    val bookHashMap: HashMap<String, Book> = hashMapOf(
        "id1" to Book(
            volumeInfo = BookInfo(
                title = "title1",
                authors = listOf("author1"),
                imageLinks = ImageLinks(
                    smallThumbnail = "smallThumbnail1",
                    thumbnail = "thumbnail1"
                )
            )
        ),
        "id2" to Book(
            volumeInfo = BookInfo(
                title = "title2",
                authors = listOf("author2"),
                imageLinks = ImageLinks(
                    smallThumbnail = "smallThumbnail2",
                    thumbnail = "thumbnail2"
                )
            )
        )
    )
}