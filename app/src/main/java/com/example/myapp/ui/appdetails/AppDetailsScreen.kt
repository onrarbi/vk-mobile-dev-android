package com.example.myapp.ui.appdetails

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.myapp.domain.model.AppInfo

@Composable
fun AppDetailsScreen(
    app: AppInfo,
    showcaseApps: List<AppInfo>,
    onBackClick: () -> Unit
) {
    var descriptionExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { contentPadding ->
        AppDetailsContent(
            appDetails = app,
            showcaseApps = showcaseApps,
            descriptionExpanded = descriptionExpanded,
            onBackClick = onBackClick,
            onShareClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "${app.name}\n${app.description}"
                    )
                }

                context.startActivity(
                    Intent.createChooser(intent, "Share app")
                )
            },
            onInstallClick = {
                Toast.makeText(
                    context,
                    "Under development",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onReadMoreClick = {
                descriptionExpanded = true
            },
//          onSimilarAppDownloadClick = {
//          },
            onDeveloperClick = {
                Toast.makeText(
                    context,
                    "Under development",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        )
    }
}