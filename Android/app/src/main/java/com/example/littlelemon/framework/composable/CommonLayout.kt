package com.example.littlelemon.framework.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.App_Background
import com.example.navigations.NavigationManager

@Composable
fun CommonLayout(
    navigationManager: NavigationManager,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(App_Background) // Set background
    ) {
        Column {
            Header(navigationManager)
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp)
            ) {
                item {
                    content()
                }
            }
        }
    }

//    Column {
//        val itemList = (0..5).toList()
//
//        Header()
//
//        LazyColumn(
//            modifier = Modifier.fillMaxHeight()
//        ) {
//            item{
////                Text("Item is $it")
//                content()
//            }
//        }
//    }
}