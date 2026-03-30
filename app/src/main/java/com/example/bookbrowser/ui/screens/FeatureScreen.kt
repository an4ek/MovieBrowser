package com.example.bookbrowser.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbrowser.BuildConfig

/**
 * Задание 7 - Экран, который показывает разный контент
 * в зависимости от flavor (IS_PREMIUM)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (BuildConfig.IS_PREMIUM) "Premium Recommendations" else "Premium")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (BuildConfig.IS_PREMIUM) {
            // Paid flavor
            PremiumContent(modifier = Modifier.padding(padding))
        } else {
            // Free flavor
            FreeContent(modifier = Modifier.padding(padding), onBack = onBack)
        }
    }
}

@Composable
private fun PremiumContent(modifier: Modifier = Modifier) {
    val recommendations = listOf(
        "Clean Code — Robert Martin",
        "Design Patterns — Gang of Four",
        "Kotlin in Action — Dmitry Jemerov",
        "Effective Java — Joshua Bloch",
        "Refactoring — Martin Fowler"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Personal Recommendations",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        recommendations.forEachIndexed { index, book ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${index + 1}.",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.width(32.dp)
                    )
                    Text(text = book)
                }
            }
        }
    }
}

@Composable
private fun FreeContent(modifier: Modifier = Modifier, onBack: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Premium Feature",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Personal book recommendations are\navailable only in the premium version.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(onClick = onBack) {
            Text("Back")
        }
    }
}