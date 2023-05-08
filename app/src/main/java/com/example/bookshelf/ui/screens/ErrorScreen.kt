package com.example.bookshelf.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookshelf.ui.BookshelfUiState
import com.example.bookshelf.ui.BookshelfViewModel

@Composable
fun ErrorScreen(
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier
) {
    val errorUiState = bookshelfViewModel.bookshelfUiState as BookshelfUiState.Error
    val error = errorUiState.error
    Text(text = error)
}
