package com.example.myapp

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.domain.repository.AppsRepository
import com.example.myapp.ui.applist.AppListScreen
import com.example.myapp.ui.appdetails.AppDetailsScreen

@Composable
fun AppNavigation(repository: AppsRepository) {
    val navController = rememberNavController()

    val apps = remember(repository) { repository.getApps() }

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
                    showcaseApps = apps,
                    onBackClick = {navController.popBackStack()}
                )
            } else {
                Text("Application not found")
            }
        }
    }
}