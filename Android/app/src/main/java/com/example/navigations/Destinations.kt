package com.example.navigations

interface Destinations {
    val route: String
    val label: String
}

object HomeScreen: Destinations {
    override val route = "Home"
    override val label = "Home"
}

object ProfileScreen: Destinations {
    override val route = "Profile"
    override val label = "Profile"
}

object OnboardingScreen: Destinations {
    override val route = "Onboarding"
    override val label = "Onboarding"
}