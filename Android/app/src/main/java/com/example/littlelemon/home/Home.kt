package com.example.littlelemon.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.example.littlelemon.framework.composable.CommonLayout
import com.example.navigations.NavigationManager

@Composable
fun Home(navigationManager: NavigationManager) {
    CommonLayout(
        navigationManager = navigationManager,
        lazyLoadingRequired = false
    ) {
        HomeView(navigationManager)
    }
}

//@Preview
//@Composable
//fun HomePreview() {
//    Home(navController)
//}