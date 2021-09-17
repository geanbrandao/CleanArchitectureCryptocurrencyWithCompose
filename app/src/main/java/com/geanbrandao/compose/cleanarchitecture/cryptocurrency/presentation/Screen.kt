package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation

import androidx.annotation.StringRes
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.R

sealed class Screen(val route: String) {
    class CoinListScreen(@StringRes val screenNameId: Int = R.string.screen_label_coin_list) :
        Screen("coin_list_screen")

    class CoinDetailScreen(@StringRes val screenNameId: Int = R.string.screen_label_coin_details) :
        Screen("coin_detail_screen")

    class CoinSavedScreen(@StringRes val screenNameId: Int = R.string.screen_label_coin_saved) :
        Screen("coin_saved_screen")
}
