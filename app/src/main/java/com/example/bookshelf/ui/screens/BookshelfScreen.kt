package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.data.Book
import com.example.bookshelf.ui.BookshelfUiState

@Composable
fun BookshelfScreen(
    bookshelfUiState: BookshelfUiState,
    modifier: Modifier = Modifier
) {
    val books = (bookshelfUiState as BookshelfUiState.Success).books

    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy((-40).dp)
        // note - this feels bad, but there's a source of vertical spacing somewhere in AsyncImage that I can't get rid of
    ) {
        items(books) { book ->
            BookCard(
                book = book,
                modifier = modifier
            )
        }
    }
}

@Composable
fun BookCard(
    book: Book,
    modifier: Modifier = Modifier
) {
    val url = book.volumeInfo.imageLinks.thumbnail
    val httpsUrl = url.replace("http", "https")
    val imageRequest = ImageRequest.Builder(context = LocalContext.current)
        .data(httpsUrl)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = book.volumeInfo.title,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        modifier = modifier
            .padding(4.dp)
    )
}