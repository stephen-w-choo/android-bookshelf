package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService

interface BookshelfDataRepository {
    suspend fun getBooks(searchQuery: String): List<Book>
}

class DefaultBookshelfDataRepository(
    private val bookshelfApiService: BookshelfApiService
): BookshelfDataRepository {
    override suspend fun getBooks(searchQuery: String): List<Book> {
        return bookshelfApiService.getData(searchQuery).items.mapNotNull { bookSummary ->
            try {
                bookshelfApiService.getBook(bookSummary.id)
            } catch (e: Exception) {
                // Log the error if needed
                null
            }
        }
    }
}