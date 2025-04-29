package com.example.navigations

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.common.preferences.SharedPreferenceManager
import com.example.littlelemon.profile.Profile
import com.example.littlelemon.home.Home
import com.example.littlelemon.onboarding.Onboarding

@Composable
fun Navigation(
    navController: NavHostController
) {
    val isUserRegistered = SharedPreferenceManager.getValue("isRegistered", Boolean::class.java, false)

    println("Value ios ::::::::::::::::::::::::::::::::::::::::::::::::::::: $isUserRegistered")
    var navigationManager = NavigationManager()

    navigationManager.setNavController(navController)

    NavHost(
        navController = navController,
        startDestination = if(isUserRegistered) {
            "Home"
        } else {
            "Onboarding"
        },
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(500))
//            EnterTransition.None
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(500))
//            ExitTransition.None
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        }
    ) {
        composable(HomeScreen.route) {
            Home(navigationManager)
        }

        composable(ProfileScreen.route) {
            Profile(navigationManager)
        }

        composable(OnboardingScreen.route) {
            Onboarding(navigationManager)
        }
    }
}