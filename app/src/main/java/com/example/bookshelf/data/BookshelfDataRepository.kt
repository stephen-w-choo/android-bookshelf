package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService

interface BookshelfDataRepository {
    suspend fun getBooks(searchQuery: String): List<Book>
}

class DefaultBookshelfDataRepository(
    private val bookshelfApiService: BookshelfApiService
): BookshelfDataRepository {
    override suspend fun getBooks(searchQuery: String): List<Book> {
        return try {
            bookshelfApiService.getData(searchQuery).items.mapNotNull { bookSummary ->
                try {
                    bookshelfApiService.getBook(bookSummary.id)
                } catch (e: Exception) {
                    // if any BookObject is invalid, currently we just return null
                    // this will be filtered out by mapNotNull and skips the object
                    null
                }
            }
        } catch (e: Exception) {
            // if no books are found, there will be no items property in the BookSearch object and will error
            // we catch the error and return an empty list
            emptyList()
        }
    }
}