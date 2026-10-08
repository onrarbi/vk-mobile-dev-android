package com.example.myapp

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

data class AppInfo(
    val icon : Int,
    val name : String,
    val description : String,
    val category : String
)

private val apps = listOf(
    AppInfo(
        icon = R.drawable.youtube_icon,
        name = "YouTube",
        description = "Official application for the world's largest video-sharing platform.",
        category = "Video"
    ),
    AppInfo(
        icon = R.drawable.twitch_icon,
        name = "Twitch",
        description = "Official application for the world's leading live-streaming and gaming community platform.",
        category = "Streaming"
    ),
    AppInfo(
        icon = R.drawable.steam_icon,
        name = "Steam",
        description = "Official application for the world's premier PC gaming, store, and community platform.",
        category = "Games"
    )
)
private val toolbarColor = Color(0xFF3F51B5)
private val screenColor = Color(0xFFFAFAFA)

@Composable
fun AppsMainScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(screenColor).statusBarsPadding().navigationBarsPadding()
    ) {
        AppsToolbar()
        AppsList(apps = apps)
    }
}

@Composable
private fun AppsToolbar() {
    Row(
        modifier = Modifier.fillMaxWidth().height(56.dp).background(toolbarColor).padding(horizontal = 16.dp),
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
private fun AppsList(apps : List<AppInfo>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(apps) { app ->
            AppCard(app = app)
        }
    }
}

@Composable
private fun AppCard(app : AppInfo) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(icon = app.icon)

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = app.name,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = app.description,
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                    overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (isExpanded) "Show less" else "Show more",
                    color = toolbarColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable {
                        isExpanded = !isExpanded
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = app.category,
                    color = toolbarColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun AppIcon(icon : Int) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = null,
        modifier = Modifier.size(48.dp)
    )
}