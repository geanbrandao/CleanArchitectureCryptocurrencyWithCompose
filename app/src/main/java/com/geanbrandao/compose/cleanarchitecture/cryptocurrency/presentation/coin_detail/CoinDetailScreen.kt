package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.toCoinDetailDb
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.animation.HeartAnimationDefinition
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.animation.components.AnimatedHeartButton
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.components.CoinTag
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.components.TeamListItem
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
    onArrowBackClickListener: () -> Unit,
) {
    val coinState = viewModel.coinDetailsState.value
    val insertCoinState = viewModel.insertCoinInDbState.value
    val removeCoinState = viewModel.removeCoinFromDbState.value
    val checkCoinExistLocalState = viewModel.checkCoinExistLocalState.value
    val buttonState = remember {
        mutableStateOf(HeartAnimationDefinition.HeartState.IDLE)
    }

    buttonState.value = if (checkCoinExistLocalState.exists!!) {
        HeartAnimationDefinition.HeartState.SELECTED
    } else {
        HeartAnimationDefinition.HeartState.IDLE
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack, contentDescription = "Arrow back icon",
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .clickable {
                        onArrowBackClickListener()
                    }
            )
            Text(
                text = "Details",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
            AnimatedHeartButton(
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd),
                buttonState = buttonState,
            ) {
                Log.d("DEBUG1", "ButtonToogle")
                coinState.coin?.let {
                    if (buttonState.value == HeartAnimationDefinition.HeartState.IDLE) {
                        buttonState.value = HeartAnimationDefinition.HeartState.SELECTED
                        viewModel.insertCoinInDb(coinDetailDb = it.toCoinDetailDb())
                    } else {
                        buttonState.value = HeartAnimationDefinition.HeartState.IDLE
                        viewModel.removeCoinFromDb(coinDetailDb = it.toCoinDetailDb())
                    }
                    viewModel.checkCoinExistLocal(it.coinId)
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            coinState.coin?.let { coin ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(all = 20.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                style = MaterialTheme.typography.h4,
                                modifier = Modifier.weight(weight = 8f),
                            )
                            Text(
                                text = if (coin.isActive) "Active" else "Inactive",
                                color = if (coin.isActive) Color.Green else Color.Red,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .weight(weight = 2f)
                            )
                        }
                        Spacer(modifier = Modifier.height(height = 16.dp))
                        Text(
                            text = coin.description,
                            style = MaterialTheme.typography.body2,
                        )
                        Spacer(modifier = Modifier.height(height = 16.dp))
                        Text(
                            text = "Tags",
                            style = MaterialTheme.typography.h5,
                        )
                        Spacer(modifier = Modifier.height(height = 16.dp))
                        FlowRow(
                            mainAxisSpacing = 8.dp,
                            crossAxisSpacing = 8.dp,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            coin.tags.forEach { tag ->
                                CoinTag(tag = tag)
                            }
                        }
                        Spacer(modifier = Modifier.height(height = 16.dp))
                        Text(
                            text = "Team members",
                            style = MaterialTheme.typography.h5,
                        )
                        Spacer(modifier = Modifier.height(height = 16.dp))
                    }
                    items(coin.team) { teamMember ->
                        TeamListItem(
                            teamMember = teamMember,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 10.dp)
                        )
                        Divider()
                    }
                }
            }
            if (coinState.error.isNotBlank()) {
                ShowTextError(
                    message = coinState.error,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                )
            }
            if (insertCoinState.error.isNotBlank()) {
                ShowTextError(
                    message = coinState.error,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                )
            }
            if (removeCoinState.error.isNotBlank()) {
                ShowTextError(
                    message = coinState.error,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                )
            }

            if (coinState.isLoading || insertCoinState.isLoading || removeCoinState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
            }
        }
    }
}

@Composable
fun ShowTextError(message: String, modifier: Modifier) {
    Text(
        text = message,
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    )
}