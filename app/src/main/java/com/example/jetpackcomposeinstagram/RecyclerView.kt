package com.example.jetpackcomposeinstagram

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeinstagram.model.SuperHero
import kotlinx.coroutines.launch

@Composable
fun SuperHeroView() {
    val context = LocalContext.current //Context fuera de la funcion
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperheros()) { superhero ->
            ItemHero(superHero = superhero)
            {
                Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current //Context fuera de la funcion
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(getSuperheros()) { superhero ->
                ItemHero(superHero = superhero)
                { Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show() }
            }
        },
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun ItemHero(superHero: SuperHero, onItemSelected: (SuperHero) -> Unit) {
    Card(border = BorderStroke(2.dp, Color.Red), modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemSelected(superHero) }) {
        Column {
            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = "Avatar" + superHero.superheroName,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superHero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superHero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun SuperHeroViewWithControls() {
    val rvState = rememberLazyListState()
    val coroutinescope = rememberCoroutineScope()
    val context = LocalContext.current //Context fuera de la funcion
    Column {
        LazyColumn(
            state = rvState, verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperheros()) { superhero ->
                ItemHero(superHero = superhero)
                { Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show() }
            }
        }
        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }
        if (showButton) {
            Button(
                onClick = { coroutinescope.launch { rvState.animateScrollToItem(0) }}, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "Click")
            }
        }
    }
}
fun getSuperheros(): List<SuperHero> {
    return listOf(
        SuperHero("Spiderman", "Peter", "Marvel", R.drawable.spiderman),
        SuperHero("Thor", "Thor", "Marvel", R.drawable.thor),
        SuperHero("WhonderWhoman", "Diana", "DC", R.drawable.wonder_woman),
        SuperHero("Daredevil", "Juan", "Marvel", R.drawable.daredevil),
        SuperHero("Flash", "Jay Garrik", "Marvel", R.drawable.flash),
        SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman)
    )
}