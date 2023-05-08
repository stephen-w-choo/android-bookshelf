package com.example.bookshelf

import com.example.bookshelf.data.Book
import com.example.bookshelf.data.BookshelfDataRepository
import com.example.bookshelf.data.DefaultBookshelfDataRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkBookshelfDataRepositoryTest {
    val repository = DefaultBookshelfDataRepository(
        bookshelfApiService = FakeBookshelfApiService()
    )
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun networkBookshelfDataRepository_getBookshelf_verifyBookList() {
        runTest {
            // valid search query should return the 2 books in the FakeDataSource
            val validSearchResponse = repository.getBooks("valid search")
            assertEquals(
                validSearchResponse.sortedBy { it.volumeInfo.title },
                FakeApiResponse.bookHashMap.values.toList().sortedBy { it.volumeInfo.title }
            )
        }
    }

    @Test
    fun networkBookshelfDataRepository_getBookshelf_verifyEmptyBookList() {
        runTest {
            // empty search query should return an empty list
            val emptySearchResponse = repository.getBooks("empty search")
            assertEquals(emptySearchResponse, emptyList<Book>())
        }
    }

    @Test
    fun networkBookshelfDataRepository_getBookshelf_verifyPartialBookList() {
        runTest {
            // partial search query should skip over the invalid book and return the valid book (id1)
            val partialSearchResponse = repository.getBooks("partially valid search")
            assertEquals(partialSearchResponse, listOf(FakeApiResponse.bookHashMap["id1"]))
        }
    }

}