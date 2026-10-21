package com.example.myapp

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.data.apps
import com.example.myapp.ui.applist.AppListScreen
import com.example.myapp.ui.appdetails.AppDetailsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "apps"
    ) {
        composable("apps") {
            AppListScreen(
                onAppClick = { app ->
                    navController.navigate("details/${app.id}")
                }
            )
        }

        composable("details/{appId}") { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId")
            val app = apps.find { it.id == appId }

            if (app != null) {
                AppDetailsScreen(
                    app = app,
                    onBackClick = {navController.popBackStack()}
                )
            } else {
                Text("Application not found")
            }
        }
    }
}