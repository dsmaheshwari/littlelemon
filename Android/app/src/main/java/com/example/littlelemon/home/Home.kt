package com.example.littlelemon.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.framework.composable.CommonLayout
import com.example.littlelemon.ui.theme.Header_Primary_Text
import com.example.littlelemon.ui.theme.Header_Secondary_Text
import com.example.littlelemon.ui.theme.Label_Text
import com.example.navigations.NavigationManager

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun HeroSection() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = screenHeight * 0.3f),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF3E0) // soft warm tone; adjust as needed
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Little Lemon",
                    style = MaterialTheme.typography.displayLarge,
                    fontSize = 48.sp,
                    color = Header_Primary_Text
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            Row {
                Text(
                    text = "Chicago",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 36.sp,
                    color = Header_Secondary_Text
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.66f)
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Label_Text
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(0.33f)
                        .fillMaxHeight()
                        .padding(start = 12.dp)
                ) {
                    GlideImage(
                        model = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true", // Replace with actual image URL
                        contentDescription = "Little Lemon Restaurant Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun Home(navigationManager: NavigationManager) {
    CommonLayout(
        navigationManager = navigationManager,
        preContent = { HeroSection() },
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