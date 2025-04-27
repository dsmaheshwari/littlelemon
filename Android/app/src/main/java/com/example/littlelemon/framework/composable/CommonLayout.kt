package com.example.littlelemon.framework.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.App_Background

@Composable
fun CommonLayout(
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(App_Background) // Set background
    ) {
        Column {
            Header()
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