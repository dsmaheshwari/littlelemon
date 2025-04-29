package com.example.littlelemon.framework.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.CoralRed
import com.example.navigations.NavigationManager

@Composable
fun Header(navigationManager: NavigationManager) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(CoralRed),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.restaurant_logo),
            contentDescription = "Restaurant Logo",
            modifier = Modifier
                .fillMaxSize(0.8F)
        )
        Image(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            modifier = Modifier.clip(
                RoundedCornerShape(50)
            )
            .fillMaxSize(0.5F)
            .clickable {
                navigationManager.navigateTo("Profile")
            }
        )
    }
}

@Preview
@Composable
fun HeaderPreview() {
//    Header(navigationManager)
}