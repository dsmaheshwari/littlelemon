package com.example.littlelemon.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.framework.composable.CommonLayout
import com.example.littlelemon.framework.composable.Header

@Composable
fun Onboarding() {
    CommonLayout {
        OnBoardingView()
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    Onboarding()
}