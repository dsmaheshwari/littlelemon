package com.example.littlelemon.framework.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.CoralRed
import com.example.navigations.NavigationManager

@Composable
fun Header(navigationManager: NavigationManager) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CoralRed),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(R.drawable.restaurant_logo), contentDescription = "Restaurant Logo")
    }
}

@Preview
@Composable
fun HeaderPreview() {
//    Header(navigationManager)
}