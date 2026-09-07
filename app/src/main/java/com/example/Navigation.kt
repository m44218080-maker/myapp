package com.example

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.AppDatabase
import com.example.data.FavoriteRepository

/**
 * Navigation destination routes for the app.
 */
object Destinations {
    const val HOME = "home"
    const val DETAIL = "detail/{verbId}"

    fun detailRoute(verbId: Int): String = "detail/$verbId"
}

/**
 * Main application navigation host connecting the Home Screen and Detail Screen.
 */
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    favoriteRepository: FavoriteRepository? = null
) {
    val context = LocalContext.current
    val repository = favoriteRepository ?: remember(context) {
        val db = AppDatabase.getDatabase(context)
        FavoriteRepository(db.favoriteDao())
    }

    NavHost(
        navController = navController,
        startDestination = Destinations.HOME,
        modifier = modifier
    ) {
        composable(
            route = Destinations.HOME,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(300)
                )
            }
        ) {
            HomeScreen(
                favoriteRepository = repository,
                onVerbClick = { verbId ->
                    navController.navigate(Destinations.detailRoute(verbId))
                }
            )
        }

        composable(
            route = Destinations.DETAIL,
            arguments = listOf(
                navArgument("verbId") {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                )
            }
        ) { backStackEntry ->
            val verbId = backStackEntry.arguments?.getInt("verbId") ?: 1
            DetailScreen(
                verbId = verbId,
                favoriteRepository = repository,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

