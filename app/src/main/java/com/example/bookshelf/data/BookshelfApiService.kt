package com.example.bookshelf.network

import com.example.bookshelf.data.Book
import com.example.bookshelf.data.BookSearch
import com.example.bookshelf.data.BookshelfDataRepository
import com.example.bookshelf.data.DefaultBookshelfDataRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface BookshelfApiService {
    // returns a BookSearch object, where the items property is a list of BookSummary objects
    @GET("volumes")
    suspend fun getData(@Query("q") searchQuery: String): BookSearch

    // takes an id parameter and returns a Book object with nested VolumeInfo and ImageLinks objects
    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): Book

}