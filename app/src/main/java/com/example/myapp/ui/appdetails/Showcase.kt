package com.example.myapp.ui.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapp.data.apps
import com.example.myapp.ui.components.AppCard

@Composable
fun Showcase(
    onAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 3 })

    Column(modifier = modifier) {
        Text(
            text = "Showcase",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(12.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().height(520.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 12.dp
        ) { page ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                apps.subList(
                    page * 3,
                    page * 3 + 3
                ).forEach { app ->
                    AppCard(app = app, onClick = onAppClick)
                }
            }
        }

        Text(
            text = "${pagerState.currentPage + 1} / 3",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}