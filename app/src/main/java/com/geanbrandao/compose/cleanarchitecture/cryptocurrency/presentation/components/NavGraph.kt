package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.Screen
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.CoinDetailScreen
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_list.CoinListScreen
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_saved.CoinSavedScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CoinListScreen().route,
    ) {
        composable(
            route = Screen.CoinListScreen().route,
        ) {
            CoinListScreen(navController = navController)
        }
        composable(
            route = Screen.CoinDetailScreen().route + "/{coinId}",
        ) {
            CoinDetailScreen {
                navController.navigateUp()
            }
        }
        composable(
            route = Screen.CoinSavedScreen().route
        ) {
            CoinSavedScreen()
        }
    }
}