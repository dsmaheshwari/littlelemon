package com.example.common.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.littlelemon.ui.theme.Label_Text

@Composable
fun Label(label: String): Unit {
    Text(
        text = label,
        style = MaterialTheme.typography.labelLarge,
        color = Label_Text
    )
}