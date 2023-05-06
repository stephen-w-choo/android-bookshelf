package com.example.bookshelf.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
        when (bookshelfViewModel.bookshelfUiState) {
            is BookshelfUiState.NoBooks -> {}
            is BookshelfUiState.Loading -> {}
            is BookshelfUiState.Error -> {}
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

@Composable
fun BookshelfScreen(
    bookshelfUiState: BookshelfUiState,
    modifier: Modifier = Modifier
) {
    val books = (bookshelfUiState as BookshelfUiState.Success).books

    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
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