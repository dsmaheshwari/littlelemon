package com.example.navigations

import androidx.navigation.NavController

class NavigationManager {
    private lateinit var navController: NavController

    fun getNavController(): NavController {
        return this.navController
    }

    fun setNavController(controller: NavController) {
        this.navController = controller
    }

    fun navigateTo(route: String) {
        navController?.navigate(route)
    }

    fun popBack() {
        navController?.popBackStack()
    }
}