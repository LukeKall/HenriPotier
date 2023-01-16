package com.example.henripotierbookpurchase.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.henripotier.bookpurchase.R
import com.example.henripotier.designsystem.component.*
import com.example.henripotier.designsystem.theme.padding12
import com.example.henripotier.designsystem.theme.padding16
import com.example.henripotier.domain.model.Book

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
internal fun BookDetailScreen(bookDetailViewModel: BookDetailViewModel, goBack: () -> Unit) =
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { ToolbarText(stringResource(R.string.book_detail)) },
                navigationIcon = { ToolbarNavigationIcon(goBack) }
            )
        },
        modifier = Modifier.systemBarsPadding()
    ) {
        val modifier = Modifier.padding(it)
        when (val state = bookDetailViewModel.state.collectAsStateWithLifecycle().value) {
            is BookDetailState.Loading -> Loader()
            is BookDetailState.Loaded -> BookDetailContent(state.book, modifier)
            is BookDetailState.Error -> ErrorContent(bookDetailViewModel::reload, modifier)
        }
    }

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun BookDetailContent(book: Book, modifier: Modifier = Modifier) =
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        GlideImage(
            model = book.coverUrl,
            contentDescription = book.title,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .wrapContentHeight()
                .padding(padding16)
        ) {
            Text(text = book.title, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.size(padding16))
            Column(verticalArrangement = Arrangement.spacedBy(padding12)) {
                book.synopsis.forEach {
                    Text(text = it, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(Modifier.size(padding16))
            Text(
                text = stringResource(R.string.book_isbn, book.isbn.value),
                style = MaterialTheme.typography.labelMedium,
                fontStyle = FontStyle.Italic
            )
            Spacer(Modifier.size(padding16))
            Price(
                priceInCents = book.priceInCents,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

@Composable
private fun ErrorContent(retry: () -> Unit, modifier: Modifier = Modifier) =
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding16),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.book_loading_error),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(padding16))
        HPButton(text = stringResource(R.string.retry), onClick = retry)
    }