package com.example.henripotierbookpurchase.catalog

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.henripotierbookpurchase.detail.BookDetailScreen

@Composable
fun BookPurchaseNavHost(navController: NavHostController = rememberNavController()) =
    NavHost(navController = navController, startDestination = BookPurchaseDestinations.CATALOG.path) {
        composable(BookPurchaseDestinations.CATALOG.path) {
            BookCatalogScreen(
                bookCatalogViewModel = hiltViewModel(),
                goToBookDetail = { navController.navigate("detail/${it.value}") }
            )
        }
        composable(
            route = BookPurchaseDestinations.BOOK_DETAIL.path,
            arguments = listOf(navArgument(BookPurchaseArgumentKeys.ISBN.key) { type = NavType.StringType })
        ) {
            BookDetailScreen(
                bookDetailViewModel = hiltViewModel(),
                goBack = navController::navigateUp
            )
        }
    }

enum class BookPurchaseDestinations(val path: String) {
    CATALOG("catalog"),
    BOOK_DETAIL("detail/{${BookPurchaseArgumentKeys.ISBN.key}}");
}

enum class BookPurchaseArgumentKeys(val key: String) {
    ISBN("isbn");
}