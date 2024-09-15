package com.example.jetpackcomposeinstagram.dragonballapi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpackcomposeinstagram.R


@OptIn(ExperimentalCoilApi::class)
@Composable
fun DragonBallScreen(
    viewModel: DragonBallViewModel = DragonBallViewModel(), navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.getAllCharacters()
    }

    val characters by viewModel.characters.observeAsState()
    val error by viewModel.error.observeAsState()

    if (error != null) {
        Text(text = error!!)
    } else if (characters != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            itemsIndexed(characters!!) { index, character -> // Aquí characters es la lista de Items
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            navController.navigate("detail/${character.id}")
                        }

                ) {
                    FullImageFromURLWithPlaceHolder(character.image)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "ID: ${character.id}",
                            fontSize = 30.sp
                        )
                        Text(
                            text = character.name,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "Strength: ${character.ki}",
                            fontSize = 15.sp
                        )


                    }
                }
            }
        }

    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun FullImageFromURLWithPlaceHolder(imageUrl: String){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_dots),
        contentDescription = "Texto Imagen",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(100.dp)
    )
}



@Composable
fun DetailScreen(
    viewModel: DragonBallViewModel = DragonBallViewModel(),
    navController: NavController,
    characterId: String
) {
    val character by viewModel.getCharacter(characterId).observeAsState()

    if (character != null) {
        println(character)
        Column(
            modifier = Modifier
                .fillMaxSize() // Ocupa toda la pantalla
                .padding(16.dp),
            verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
        ) {
            DisplayImageDetail(character!!.image)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "ID: ${character!!.id}",
                fontSize = 30.sp
            )
            Text(
                text = character!!.name,
                fontSize = 15.sp
            )
            Text(
                text = "Strength: ${character!!.ki}",
                fontSize = 15.sp
            )
            // Botón de "volver" en la esquina superior izquierda
            IconButton(
                onClick = { navController.popBackStack() }, // Vuelve a la pantalla anterior
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, // Usa el ícono de flecha hacia atrás
                    contentDescription = "Volver"
                )
            }
            val transformations = character!!.transformations
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.CenterStart)

            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(transformations) { transformation ->
                        // your item composable function here
                        // for example, you could display the transformation name or details
                        Text(text = transformation.name)
                        FullImageFromURLWithPlaceHolder(imageUrl = transformation.image)
                    }
                }
            }


        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Centra el CircularProgressIndicator
        ) {
            CircularProgressIndicator()
        }

    }
}

@Composable
fun DisplayImageDetail(imageUrl: String){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_dots),
        contentDescription = "Texto Imagen",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
