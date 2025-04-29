package com.example.littlelemon.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val lightColors = lightColorScheme(
    primary = Base_Primary,
    secondary = Base_Secondary,
    tertiary = Base_Tertiary,
    background = App_Background,
    surface = Neutral_Accent,
    onPrimary = SoftWhite,
    onSecondary = DeepCharcoal,
    onBackground = DeepCharcoal,
    onSurface = DeepCharcoal,
    error = Alert,
)

@Composable
fun MediterraneanRestaurantTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColors,
        typography = appTypography, // We'll define font styles next
        content = content,
    )
}