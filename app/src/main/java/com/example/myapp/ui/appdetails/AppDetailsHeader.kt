package com.example.myapp.ui.appdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.myapp.R
import com.example.myapp.domain.model.AppInfo
import kotlin.math.roundToInt

@Composable
fun AppDetailsHeader(
    app: AppInfo,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val iconModifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(16.dp))

        if (app.iconUrl != null) {
            AsyncImage(
                model = app.iconUrl,
                contentDescription = app.name,
                modifier = iconModifier,
                contentScale = ContentScale.Crop
            )
        } else if (app.icon != null) {
            Image(
                painter = painterResource(id = app.icon),
                contentDescription = app.name,
                modifier = iconModifier
            )
        }

        Spacer(Modifier.width(16.dp))

        Column {
            Text(
                text = app.appCategory,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = app.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
            )

            app.developer?.let { developer ->
                Spacer(Modifier.height(4.dp))

                Text(
                    text = developer,
                    fontSize = 12.sp
                )
            }

            Spacer(Modifier.height(4.dp))

            if (app.ageRating != null || app.size != null) {
                Spacer(Modifier.height(4.dp))

                Row {
                    app.ageRating?.let { ageRating ->
                        Column(Modifier.width(IntrinsicSize.Max)) {
                            Text(
                                text = "$ageRating+",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(Modifier.height(4.dp))

                            Text(text = stringResource(R.string.app_details_age))
                        }
                    }

                    if (app.ageRating != null && app.size != null) {
                        Spacer(Modifier.width(12.dp))
                    }

                    app.size?.let { size ->
                        Column {
                            Text(text = "${size.roundToInt()} MB")

                            Spacer(Modifier.height(4.dp))

                            Text(text = stringResource(R.string.app_details_size))
                        }
                    }
                }
            }
        }
    }
}