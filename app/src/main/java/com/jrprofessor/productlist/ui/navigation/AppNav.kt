package com.jrprofessor.productlist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jrprofessor.productlist.ui.screen.ProductDetailsScreen
import com.jrprofessor.productlist.ui.screen.ProductListScreen

sealed class Screen(route: String) {
    object Dashboard : Screen("dashboard")
    object ProductDetail : Screen("productDetail")
}

@Composable
fun AppNav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Dashboard.toString()) {
        composable(Screen.Dashboard.toString()) {
            ProductListScreen(itemClick = {
                navController.navigate(Screen.ProductDetail.toString())
            })
        }
        composable(Screen.ProductDetail.toString()) {
            ProductDetailsScreen()
        }
    }
}