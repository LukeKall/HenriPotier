package com.example.henripotier

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavHost(navController: NavHostController = rememberNavController()) =
    NavHost(navController = navController, startDestination = BookPurchaseDestinations.BOOK_PURCHASE.path) {
        composable(BookPurchaseDestinations.BOOK_PURCHASE.path) {
            com.example.henripotierbookpurchase.catalog.BookPurchaseNavHost()
        }
    }

enum class BookPurchaseDestinations(val path: String) {
    BOOK_PURCHASE("bookPurchase");
}