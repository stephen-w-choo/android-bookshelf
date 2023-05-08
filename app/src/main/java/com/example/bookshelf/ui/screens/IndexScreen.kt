package com.example.bookshelf.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookshelf.ui.BookshelfUiState
import com.example.bookshelf.ui.BookshelfViewModel

@Composable
fun IndexScreen(
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy((-30).dp)
    ) {
        SearchBar(
            bookshelfViewModel = bookshelfViewModel,
            modifier = modifier
        )
        Box(
            modifier = modifier
                .padding(16.dp)
        ) {
            when (bookshelfViewModel.bookshelfUiState) {
                is BookshelfUiState.NoBooks -> {}
                is BookshelfUiState.Loading -> {
                    LoadingScreen(modifier = modifier)
                }
                is BookshelfUiState.Error -> {
                    ErrorScreen(
                        bookshelfViewModel = bookshelfViewModel,
                        modifier = modifier
                    )
                }
                is BookshelfUiState.Success -> {
                    // log success
                    Log.d("BookshelfViewModel", "Loading bookshelf screen")
                    BookshelfScreen(
                        bookshelfUiState = bookshelfViewModel.bookshelfUiState,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier
) {
    // text field that updates the searchQuery val in the viewModel
    // when the user presses enter, the searchQuery is used to update the bookshelfUiState
    // the bookshelfUiState is used to update the UI
    // the UI is updated with the new bookshelfUiState
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement
            .Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        TextField(
            value = bookshelfViewModel.currentSearchQuery,
            onValueChange = {
                bookshelfViewModel.updateSearchQuery(it)
            }
        )
        Button(
            onClick = {
                bookshelfViewModel.searchBooks()
            }
        ) {
            Text(text = "Search")
        }
    }
}

