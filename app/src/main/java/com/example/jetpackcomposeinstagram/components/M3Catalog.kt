package com.example.jetpackcomposeinstagram.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp


@Composable
fun MyFilledTonalButtonExample() {
    FilledTonalButton(onClick = { print("Hello") }) {
        Text(text = "Click me")
    }
}

@Composable
fun MyElevatedButton(){
    ElevatedButton(onClick = { print("Hello") }) {
        Text(text = "Click me")
    }
}

@Composable
fun FloatingActionButtonExample() {
    FloatingActionButton(onClick = { print("Hello") }, contentColor = Color.Red) {
        Icon(Icons.Filled.Favorite, "Floating action button.")
    }
}



