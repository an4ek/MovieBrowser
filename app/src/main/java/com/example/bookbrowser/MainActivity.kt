package com.example.bookbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.bookbrowser.ui.navigation.AppNavigation
import com.example.bookbrowser.ui.theme.BookBrowserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Задание 4 - инициализация debug-логирования сети
        initDebugNetworking()

        setContent {
            BookBrowserTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }

    private fun initDebugNetworking() {
        try {
            val clazz = Class.forName(
                "com.example.bookbrowser.network.NetworkDebugInitializer"
            )
            val method = clazz.getMethod("init")
            val instance = clazz.getDeclaredField("INSTANCE").get(null)
            method.invoke(instance)
        } catch (_: Exception) {

        }
    }
}