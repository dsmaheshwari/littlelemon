package com.example.littlelemon.profile

import androidx.compose.runtime.Composable
import com.example.littlelemon.framework.composable.CommonLayout
import com.example.navigations.NavigationManager

@Composable
fun Profile(navigationManager: NavigationManager) {
    CommonLayout(navigationManager) {
        ProfileView(navigationManager)
    }
}

//@Preview
//@Composable
//fun ProfilePreview() {
//    Profile(navController)
//}