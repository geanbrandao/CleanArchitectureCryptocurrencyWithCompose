package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_saved

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.Screen
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_list.components.CoinListItem

@Composable
fun CoinSavedScreen() {
//    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(state.coins) { coin ->
//                CoinListItem(
//                    coin = coin,
//                    onItemClick = {
//                        navController.navigate(Screen.CoinDetailScreen().route + "/${coin.id}")
//                    },
//                )
//            }
//        }
//        if (state.error.isNotBlank()) {
//            Text(
//                text = state.error,
//                color = MaterialTheme.colors.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .align(alignment = Alignment.Center)
//            )
//        }
//
//        if (state.isLoading) {
//            CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
//        }
    }
}