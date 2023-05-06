package com.example.bookshelf.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookshelf.ui.BookshelfViewModel

@Composable
fun ErrorScreen(
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier
) {
    Text(text = "Error!")
}
