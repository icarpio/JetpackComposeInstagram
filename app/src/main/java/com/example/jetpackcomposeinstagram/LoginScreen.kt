package com.example.jetpackcomposeinstagram

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize().padding(8.dp)
    ) {
        Header(modifier =Modifier.align(Alignment.TopEnd).padding(top = 30.dp) )
    }
}
@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close, // Ícono a mostrar
        contentDescription = "Close App",
        modifier = modifier.clickable {activity.finish()},
        tint = Color.Blue // Ajusta el color del ícono
    )
}