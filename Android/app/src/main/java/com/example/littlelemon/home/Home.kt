package com.example.littlelemon.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.navigations.NavigationManager

@Composable
fun Home(navigationManager: NavigationManager) {
    Text(
        "This is Home"
    )
}

//@Preview
//@Composable
//fun HomePreview() {
//    Home(navController)
//}