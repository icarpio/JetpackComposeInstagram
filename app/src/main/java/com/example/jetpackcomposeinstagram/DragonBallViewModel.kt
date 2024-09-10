package com.example.jetpackcomposeinstagram

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpackcomposeinstagram.model.DragonBall
import com.example.jetpackcomposeinstagram.model.Item
import com.example.jetpackcomposeinstagram.network.DragonBallApi
import kotlinx.coroutines.launch

class DragonBallViewModel: ViewModel() {
    private val api = DragonBallApi.service

    private val _character = MutableLiveData<DragonBall>()
    val character: LiveData<DragonBall> = _character

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val response = api.getCharacters()
                if (response.isSuccessful) {
                    _character.value = response.body()
                } else {
                    _error.value = "Error: ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
    }
}




@OptIn(ExperimentalCoilApi::class)
@Composable
fun DragonBallScreen(viewModel: DragonBallViewModel = DragonBallViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getAllCharacters()
    }

    val character by viewModel.character.observeAsState()
    val error by viewModel.error.observeAsState()

    if (error != null) {
        Text(text = error!!)
    } else if (character != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            itemsIndexed(character!!.items) { index, character ->
                Log.d("DB Character", "DB Character $index: $character")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
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
