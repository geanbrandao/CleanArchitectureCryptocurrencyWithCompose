package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.Screen

val screenItems = listOf(
    Screen.CoinListScreen(),
    Screen.CoinSavedScreen(),
)

@Composable
fun BottomNavigationCryptocurrency(
    navController: NavHostController,
) {

    val bottomNavigationHeight by animateDpAsState(
        targetValue = 55.dp,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )


    Box {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestionation = navBackStackEntry?.destination

            screenItems.forEachIndexed { index, screen ->
                BottomNavigationItem(
                    icon = {
                        BottomNavigationIcon(screen = screen)
                    },
                    selected = currentDestionation?.hierarchy?.any {
                        it.route == screen.route
                    } == true,
                    label = { BottomNavigationLabel(screen = screen) },
                    onClick = {
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun BottomNavigationIcon(screen: Screen) {
    when (screen) {
        is Screen.CoinDetailScreen -> {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "Hearth icon"
            )
        }
        is Screen.CoinListScreen -> {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "Home icon"
            )
        }
        is Screen.CoinSavedScreen -> {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = "Home icon"
            )
        }
    }
}

@Composable
fun BottomNavigationLabel(screen: Screen) {
    when (screen) {
        is Screen.CoinDetailScreen -> {
            Text(
                text = stringResource(id = screen.screenNameId),
                fontSize = 10.sp
            )
        }
        is Screen.CoinListScreen -> {
            Text(
                text = stringResource(id = screen.screenNameId),
                fontSize = 10.sp
            )
        }
        is Screen.CoinSavedScreen -> {
            Text(
                text = stringResource(id = screen.screenNameId),
                fontSize = 10.sp
            )
        }
    }
}