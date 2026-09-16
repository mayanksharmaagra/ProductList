package com.jrprofessor.productlist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jrprofessor.productlist.ui.screen.ProductDetailsScreen
import com.jrprofessor.productlist.ui.screen.ProductListScreen

sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard")
    data object ProductDetail : Screen("productDetail/{productId}") {
        fun createRoute(productId: String) = "productDetail/$productId"
    }
}

@Composable
fun AppNav(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Dashboard.route) {
        composable(Screen.Dashboard.route) {
            ProductListScreen(
                itemClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(
                navArgument("productId") { type = NavType.StringType }
            )
        ) {
            ProductDetailsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
