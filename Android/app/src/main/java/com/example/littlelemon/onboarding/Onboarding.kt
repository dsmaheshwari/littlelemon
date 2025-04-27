package com.example.littlelemon.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.littlelemon.framework.composable.CommonLayout

@Composable
fun Onboarding(navController: NavHostController) {
    CommonLayout {
        OnBoardingView(navController)
    }
}

//@Preview
//@Composable
//fun OnboardingPreview() {
//    Onboarding(navController)
//}