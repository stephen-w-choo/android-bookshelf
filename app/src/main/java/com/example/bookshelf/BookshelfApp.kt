package com.example.bookshelf

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookshelf.data.DataLayerContainer
import com.example.bookshelf.ui.BookshelfViewModel
import com.example.bookshelf.ui.screens.IndexScreen

// the bookshelf application class
class BookshelfApplication: Application() {
    // the default app container is the connection to the data layer
    lateinit var dataLayer: DataLayerContainer

    override fun onCreate() {
        super.onCreate()
        dataLayer = DataLayerContainer()
    }
}

// the bookshelf app root composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Bookshelf") },
            )
        },
        content = { contentPadding ->
            IndexScreen(
                bookshelfViewModel = bookshelfViewModel,
                modifier = Modifier.padding(contentPadding)
            )
        }
    )
}