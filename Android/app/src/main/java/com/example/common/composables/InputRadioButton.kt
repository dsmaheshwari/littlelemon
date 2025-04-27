package com.example.common.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.Label_Text

@Composable
fun InputRadioButton(
    label: String? = null,
    listOptions: List<String>,
    selection: String,
    onSelectionChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column {
        label?.let {
            Label(
                label = it
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Row(
            modifier = Modifier.selectableGroup()
        ) {
            listOptions.forEach { it ->
                Column(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .selectable(
                            selected = (it == selection),
                            onClick = { onSelectionChanged(it) }
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (it == selection),
                            onClick = null, // Handled by parent Column's selectable modifier
                            modifier = modifier,
                            enabled = enabled,
                        )
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.labelLarge,
                            color = Label_Text
                        )
                    }
                }
            }
        }
    }
}