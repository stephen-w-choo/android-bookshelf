package com.example.bookshelf.ui

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.Book
import com.example.bookshelf.data.BookshelfDataRepository
import com.example.bookshelf.data.DataLayerContainer
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookshelfUiState {
    object NoBooks : BookshelfUiState
    object Loading : BookshelfUiState
    data class Error(val error: String) : BookshelfUiState
    data class Success(val books: List<Book>) : BookshelfUiState
}

class BookshelfViewModel(
    private val bookshelfRepository: BookshelfDataRepository
): ViewModel() {
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.NoBooks)
        private set

    var currentSearchQuery: String by mutableStateOf("")
        private set

    fun updateSearchQuery(searchQuery: String) {
        currentSearchQuery = searchQuery
    }

    fun searchBooks() {
        getBooks(searchQuery = currentSearchQuery)
    }

    private fun getBooks(searchQuery: String) {
        bookshelfUiState = BookshelfUiState.Loading
        viewModelScope.launch {
            bookshelfUiState = try {
                val apiResult = bookshelfRepository.getBooks(searchQuery)
                // log success
                Log.d("BookshelfViewModel", "Success: $apiResult")
                if (apiResult.isEmpty()) {
                    BookshelfUiState.Error("No books found")
                } else {
                    BookshelfUiState.Success(apiResult)
                }
            } catch (e: IOException) {
                // log error
                Log.e("BookshelfViewModel", "Error: $e")
                BookshelfUiState.Error(e.toString())
            } //catch (e: Exception) {
                // catch other exceptions here
                //BookshelfUiState.Error
            //}
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.dataLayer.bookshelfRepository
                BookshelfViewModel(bookshelfRepository)
            }
        }
    }
}


