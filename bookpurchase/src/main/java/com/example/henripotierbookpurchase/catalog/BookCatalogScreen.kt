package com.example.henripotierbookpurchase.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.henripotier.bookpurchase.R
import com.example.henripotier.designsystem.component.Loader
import com.example.henripotier.designsystem.component.ToolbarText
import com.example.henripotier.designsystem.theme.*
import com.example.henripotier.domain.model.Book
import com.example.henripotier.domain.model.CENT
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BookCatalogScreen(bookCatalogViewModel: BookCatalogViewModel) {
    val state = bookCatalogViewModel.state.collectAsStateWithLifecycle().value
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { ToolbarText(stringResource(R.string.catalog_name)) }) },
        modifier = Modifier.statusBarsPadding()
    ) {
        when (state) {
            is BookCatalogState.Loading -> Loader()
            is BookCatalogState.Loaded -> BookList(state.books, Modifier.padding(it))
        }
    }
}

@Composable
private fun BookList(books: List<Book>, modifier: Modifier = Modifier) =
    LazyColumn(contentPadding = PaddingValues(padding16), verticalArrangement = Arrangement.spacedBy(padding10), modifier = modifier) {
        items(books) {
            BookItem(it)
        }
    }

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun BookItem(book: Book) =
    Card(Modifier.height(BookCardHeight)) {
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
                    Spacer(Modifier.size(padding12))
                }
                Text(
                    text = formatPrice(book.priceInCents),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }

fun formatPrice(priceInCents: Int): String =
    NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
        currency = Currency.getInstance(Locale.FRANCE)
    }.format(priceInCents / CENT.toDouble())