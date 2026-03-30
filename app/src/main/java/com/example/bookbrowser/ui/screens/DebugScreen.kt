package com.example.bookbrowser.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbrowser.BuildConfig
import com.example.bookbrowser.BuildConfigHelper

/**
 * Задание 5 - Экран тестировщика (debug only)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DebugScreen(onBack: () -> Unit) {
    var customBaseUrl by remember { mutableStateOf(BuildConfigHelper.API_BASE_URL) }
    var requestTimeout by remember { mutableStateOf("30") }
    var enableMockData by remember { mutableStateOf(false) }
    var logLevel by remember { mutableStateOf("BODY") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Debug Panel") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Build info
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Build Info", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    DebugInfoRow("Build Type", if (BuildConfig.DEBUG) "DEBUG" else "RELEASE")
                    DebugInfoRow("API URL", BuildConfigHelper.API_BASE_URL)
                    DebugInfoRow("Is Premium", BuildConfigHelper.IS_PREMIUM.toString())
                    DebugInfoRow("Version", "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
                    DebugInfoRow("Application ID", BuildConfig.APPLICATION_ID)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Network settings
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Network Settings", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = customBaseUrl,
                        onValueChange = { customBaseUrl = it },
                        label = { Text("Base URL") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = requestTimeout,
                        onValueChange = { requestTimeout = it },
                        label = { Text("Request timeout (sec)") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Log level: $logLevel")
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(onClick = {
                            logLevel = when (logLevel) {
                                "NONE" -> "BASIC"
                                "BASIC" -> "HEADERS"
                                "HEADERS" -> "BODY"
                                else -> "NONE"
                            }
                        }) {
                            Text("Change")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mock data
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Test Data", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Use mock data")
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = enableMockData,
                            onCheckedChange = { enableMockData = it }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Apply settings */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Apply Settings")
            }
        }
    }
}

@Composable
private fun DebugInfoRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(text = "$label: ", fontWeight = FontWeight.Medium, fontSize = 14.sp)
        Text(text = value, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}