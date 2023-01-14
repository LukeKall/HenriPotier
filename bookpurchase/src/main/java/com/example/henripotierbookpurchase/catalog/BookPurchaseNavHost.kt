package com.example.henripotierbookpurchase.catalog

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BookPurchaseNavHost(navController: NavHostController = rememberNavController()) =
    NavHost(navController = navController, startDestination = BookPurchaseDestinations.CATALOG.path) {
        composable(BookPurchaseDestinations.CATALOG.path) {
            BookCatalogScreen(bookCatalogViewModel = hiltViewModel())
        }
    }

enum class BookPurchaseDestinations(val path: String) {
    CATALOG("catalog");
}