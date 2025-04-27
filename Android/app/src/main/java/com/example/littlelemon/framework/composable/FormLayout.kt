package com.example.littlelemon.framework.composable

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.Header_Primary_Text
import com.example.littlelemon.ui.theme.Header_Secondary_Text

@Composable
fun FormLayout(
    heading: String,
    subHeading: String? = null,
    formBody: @Composable () -> Unit) {
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = heading,
            style = MaterialTheme.typography.displayLarge.copy(color = Header_Primary_Text),
            modifier = Modifier.padding(top = 24.dp)
        )

        if(subHeading.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            Column(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = subHeading,
                    style = MaterialTheme.typography.displaySmall.copy(color = Header_Secondary_Text)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            formBody()
        }
    }
}