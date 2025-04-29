package com.example.littlelemon.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.littlelemon.framework.composable.CommonLayout
import com.example.navigations.NavigationManager

@Composable
fun Onboarding(navigationManager: NavigationManager) {
    CommonLayout {
        OnBoardingView(navigationManager)
    }
}

//@Preview
//@Composable
//fun OnboardingPreview() {
//    Onboarding(navController)
//}