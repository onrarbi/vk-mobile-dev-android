package com.example.myapp

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.ui.applist.AppListScreen
import com.example.myapp.ui.appdetails.AppDetailsScreen
import com.example.myapp.ui.applist.AppsViewModel
import androidx.compose.runtime.getValue

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val viewModel: AppsViewModel = hiltViewModel()
    val apps by viewModel.apps.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = "apps"
    ) {
        composable("apps") {
            AppListScreen(
                viewModel = viewModel,
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