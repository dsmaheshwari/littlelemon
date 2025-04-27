package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.common.preferences.SharedPreferenceManager
import com.example.littlelemon.onboarding.Onboarding
import com.example.littlelemon.ui.theme.MediterraneanRestaurantTheme
import com.example.navigations.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPreferenceManager.init(this)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            MediterraneanRestaurantTheme {
                Box(
                    modifier = Modifier
                        .safeDrawingPadding()
                ) {

                    Navigation(navController)
//                    Onboarding()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MediterraneanRestaurantTheme {
//        Onboarding(navController)
//    }
//}