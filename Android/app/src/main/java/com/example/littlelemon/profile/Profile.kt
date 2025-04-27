package com.example.littlelemon.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.littlelemon.framework.composable.CommonLayout

@Composable
fun Profile(navController: NavHostController) {
    CommonLayout {
        ProfileView(navController)
    }
}

//@Preview
//@Composable
//fun ProfilePreview() {
//    Profile(navController)
//}