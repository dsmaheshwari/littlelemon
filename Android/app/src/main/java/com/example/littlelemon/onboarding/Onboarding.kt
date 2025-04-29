package com.example.littlelemon.onboarding

import androidx.compose.runtime.Composable
import com.example.littlelemon.framework.composable.CommonLayout
import com.example.navigations.NavigationManager

@Composable
fun Onboarding(navigationManager: NavigationManager) {
    CommonLayout(navigationManager) {
        OnBoardingView(navigationManager)
    }
}

//@Preview
//@Composable
//fun OnboardingPreview() {
//    Onboarding(navController)
//}