package com.example.jetpackcomposeinstagram

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.jetpackcomposeinstagram.ui.theme.JetpackComposeInstagramTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeInstagramTheme {
                // Convertir el Color de Jetpack Compose a un color entero (Int)
                //window.statusBarColor = colorResource(id = R.color.teal_200).toArgb()
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Scaffold(
                    topBar = { MyTopAppBar { scope.launch { snackbarHostState.showSnackbar("Has pulsado $it") } } },
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = { MyBottomNavigation()}

                ) {
                }
            }
        }
    }
}


