package com.jrprofessor.productlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.jrprofessor.productlist.ui.navigation.AppNav
import com.jrprofessor.productlist.ui.theme.ProductListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductListTheme {
//                    ProductListScreen(
//                        modifier = Modifier.padding(innerPadding)
//                    )
                val navController = rememberNavController()
                AppNav(navController)
            }
        }
    }
}