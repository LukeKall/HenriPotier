package com.example.henripotierbookpurchase.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.henripotier.bookpurchase.R
import com.example.henripotier.designsystem.component.Loader
import com.example.henripotier.designsystem.component.Price
import com.example.henripotier.designsystem.component.ToolbarText
import com.example.henripotier.designsystem.theme.*
import com.example.henripotier.domain.model.Book
import com.example.henripotier.mvi.EventListener
import java.util.*

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun BookCatalogScreen(
    bookCatalogViewModel: BookCatalogViewModel,
    goToBookDetail: (Book.ISBN) -> Unit
) {
    val state = bookCatalogViewModel.state.collectAsStateWithLifecycle().value
    EventListener(bookCatalogViewModel.events) { event, _ ->
        when (event) {
            is BookCatalogEvent.GoToBookDetail -> goToBookDetail(event.isbn)
        }
    }
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { ToolbarText(stringResource(R.string.catalog_name)) }) },
        modifier = Modifier.statusBarsPadding()
    ) {
        when (state) {
            is BookCatalogState.Loading -> Loader()
            is BookCatalogState.Loaded -> Catalog(
                books = state.books,
                onBookClicked = bookCatalogViewModel::seeBookDetail,
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Composable
private fun Catalog(
    books: List<Book>,
    onBookClicked: (Book.ISBN) -> Unit,
    modifier: Modifier = Modifier
) = if (books.isEmpty()) {
    EmptyCatalog(modifier)
} else {
    BookList(
        books = books,
        onBookClicked = onBookClicked,
        modifier = modifier
    )
}

@Composable
private fun BookList(
    books: List<Book>,
    onBookClicked: (Book.ISBN) -> Unit,
    modifier: Modifier = Modifier
) = LazyColumn(
    contentPadding = PaddingValues(padding16),
    verticalArrangement = Arrangement.spacedBy(padding10),
    modifier = modifier
) {
    items(books) {
        BookItem(it) {
            onBookClicked(it.isbn)
        }
    }
}

@Composable
private fun EmptyCatalog(modifier: Modifier = Modifier) =
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.empty_catalog),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun BookItem(book: Book, onClick: () -> Unit) =
    Card(
        Modifier
            .height(BookCardHeight)
            .clickable { onClick() }) {
        Row {
            GlideImage(
                model = book.coverUrl,
                contentDescription = book.title,
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .padding(padding10)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(text = book.title, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.size(padding8))
                    book.synopsis.joinToString("\n\n").let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Spacer(Modifier.size(padding12))
                Price(
                    priceInCents = book.priceInCents,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }

