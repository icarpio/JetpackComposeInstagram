package com.example.jetpackcomposeinstagram

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit) {
    TopAppBar(
        title = { Text(text = "Mi primera toolbar") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
            ),
        navigationIcon = {
            IconButton(onClick = { onClickIcon("Atrás") }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Atrás")
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("Buscar") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Close, contentDescription = "Cerrar"
                )
            }
        }

    )
}

@Composable
fun MyBottomNavigation() {
    var index by remember { mutableStateOf(0) }
    NavigationBar(containerColor = Color.Red) {
        NavigationBarItem(
            selected = index == 0,
            onClick = { index = 0 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home"
                )
            }, label = {
                Text(text = "Inicio", color = Color.White) },
            colors = NavigationBarItemDefaults.colors(Color.Blue)
        )
        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "fav"
                )
            }, label = {
                Text(text = "FAVORITO") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color.Red, unselectedIconColor = Color.Yellow, selectedTextColor = Color.Magenta, unselectedTextColor = Color.Green)
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person"
                )
            }, label = { Text(text = "Persona", color = Color.White) },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color.Blue, unselectedIconColor = Color.Cyan)
        )
    }
}


