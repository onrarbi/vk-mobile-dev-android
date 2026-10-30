package com.example.myapp.ui.applist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.R
import com.example.myapp.domain.model.AppInfo
import com.example.myapp.ui.components.AppCard
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapp.domain.repository.AppsRepository
import kotlinx.coroutines.launch

@Composable
fun AppListScreen(onAppClick: (AppInfo) -> Unit, repository: AppsRepository) {
    val factory = remember(repository) {
        viewModelFactory {
            initializer {
                AppsViewModel(repository)
            }
        }
    }

    val viewModel: AppsViewModel = viewModel(factory = factory)
    val apps by viewModel.apps.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AppsToolbar(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Under development")
                    }
                }
            )

            AppsList(apps = apps, onAppClick = onAppClick)
        }
    }
}

@Composable
private fun AppsToolbar(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(56.dp).background(MaterialTheme.colorScheme.primary).clickable(onClick = onClick).padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Current app icon",
            modifier = Modifier.size(36.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = "Applications",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun AppsList(apps: List<AppInfo>, onAppClick: (AppInfo) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(apps, key = { it.id }) { app ->
            AppCard(app = app, onClick = {onAppClick(app)})
        }
    }
}