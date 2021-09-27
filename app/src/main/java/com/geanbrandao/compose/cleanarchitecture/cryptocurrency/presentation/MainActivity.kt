package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.CoinDetailScreen
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_list.CoinListScreen
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.components.BottomNavigationCryptocurrency
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.components.NavGraph
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.ui.theme.CleanArchitectureCryptocurrencyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureCryptocurrencyTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val bottomNavigationState = remember {
                    mutableStateOf(value = false)
                }
                navController.addOnDestinationChangedListener { controller, destination, arguments ->
                    Log.d("DEBUG2", "${destination.route}")
                    when (destination.route) {
                        Screen.CoinListScreen().route -> {
                            bottomNavigationState.value = true
                        }
                        Screen.CoinSavedScreen().route -> {
                            bottomNavigationState.value = true
                        }
                        else -> {
                            bottomNavigationState.value = false
                        }
                    }
                }
                Scaffold(
                    bottomBar = {
                        if (bottomNavigationState.value) {
                            BottomNavigationCryptocurrency(navController = navController)
                        }
                    }
                ) {
                    Surface(color = MaterialTheme.colors.background) {
                        NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}