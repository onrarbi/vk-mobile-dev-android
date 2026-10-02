package com.example.myapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var inputText by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            placeholder = { Text("...") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (inputText.isBlank()) {
                    Toast.makeText(context, "Введите текст", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(context, SecondActivity::class.java)
                    intent.putExtra("input_text", inputText)
                    context.startActivity(intent)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Открыть вторую Activity")
        }

        Button(
            onClick = {
                val phoneNumber = inputText.trim()

                if (phoneNumber.isBlank()) {
                    Toast.makeText(context, "Введите номер телефона", Toast.LENGTH_SHORT).show()
                } else if (!phoneNumber.matches(Regex("\\+?[0-9]{7,15}"))) {
                    Toast.makeText(context, "Некорректный номер телефона", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = "tel:$phoneNumber".toUri()
                    try {
                        context.startActivity(intent)
                    } catch (_: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "Приложение для звонков не найдено",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Позвонить другу")
        }

        Button(
            onClick = {
                if (inputText.isBlank()) {
                    Toast.makeText(context, "Введите текст", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_TEXT, inputText)
                    try {
                        context.startActivity(
                            Intent.createChooser(intent, "Поделиться с помощью")
                        )
                    } catch (_: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "Не удалось открыть меню отправки",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поделиться текстом")
        }
    }
}