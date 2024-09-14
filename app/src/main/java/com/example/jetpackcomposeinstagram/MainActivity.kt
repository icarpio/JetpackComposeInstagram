package com.example.jetpackcomposeinstagram

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposeinstagram.model.Routes
import com.example.jetpackcomposeinstagram.ui.theme.JetpackComposeInstagramTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeInstagramTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(DrawerValue.Closed)

                //NAVIGATION
                val navController = rememberNavController()

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet {
                            MyModalDrawer {
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        }
                    },
                    drawerState = drawerState
                ) {
                    Scaffold(
                        topBar = {
                            MyTopAppBar {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Has pulsado $it")
                                }
                            }
                        },
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                        bottomBar = { MyBottomNavigation() }
                    ) { padding ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(padding) // Constrain to available space
                        ) {
                            //NAVIGATION
                            /*
                            NavHost(navController, startDestination = "list") {
                                composable("list") {
                                    DragonBallScreen(navController = navController)
                                }
                                composable("detail/{characterId}") { backStackEntry ->
                                    val characterId = backStackEntry.arguments?.getString("characterId")
                                    DetailScreen(viewModel = DragonBallViewModel(), navController = navController, characterId = characterId!!)
                                }
                            }*/
                            NavHost(
                                navController = navController,
                                startDestination = Routes.Screen1.route
                            ) {
                                composable(Routes.Screen1.route) { Screen1(navController) }
                                composable(Routes.Screen2.route) { Screen2(navController) }
                                composable(Routes.Screen3.route) { Screen3(navController) }
                                composable(Routes.Screen4.route, arguments = listOf(navArgument("age"){type=
                                    NavType.IntType})) { backStackEntry ->
                                    Screen4(
                                        navController,
                                        backStackEntry.arguments?.getInt("age") ?: 0
                                    )
                                }
                                composable(Routes.Screen5.route,
                                    arguments = listOf(navArgument("name") { defaultValue = " " })) {
                                        backStackEntry ->
                                    Screen5(
                                        navController,
                                        backStackEntry.arguments?.getString("name")
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

