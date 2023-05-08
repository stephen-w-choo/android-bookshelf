package com.example.bookshelf

import com.example.bookshelf.data.Book
import com.example.bookshelf.data.BookSearch
import com.example.bookshelf.network.BookshelfApiService

class FakeBookshelfApiService: BookshelfApiService {
    override suspend fun getData(searchQuery: String): BookSearch {
        return when (searchQuery) {
            "valid search" -> FakeApiResponse.validBookSearchResponse
            "empty search" -> FakeApiResponse.emptyBookSearchResponse
            "partially valid search" -> FakeApiResponse.partiallyValidBookSearchResponse
            else -> throw Exception("Test case not found")
        }
    }

    override suspend fun getBook(id: String): Book {
        return FakeApiResponse.bookHashMap[id]!!
        // !! is the non-null assertion operator
        // we know the id will be in the hashmap for the test cases
        // if it fails, something is wrong with the code, and the test will pick it up
    }
}