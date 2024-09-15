package com.example.jetpackcomposeinstagram.model
import RandomUserResponse
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
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpackcomposeinstagram.R
import com.example.jetpackcomposeinstagram.network.RandomUserApi
import kotlinx.coroutines.launch

class RandomUserViewModel : ViewModel() {
    private val api = RandomUserApi.service

    private val _randomUser = MutableLiveData<RandomUserResponse>()
    val randomUser: LiveData<RandomUserResponse> = _randomUser

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getRandomUsers() {
        viewModelScope.launch {
            try {
                val response = api.getRandomUsers()
                if (response.isSuccessful) {
                    _randomUser.value = response.body()
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
fun RandomUserScreen(viewModel: RandomUserViewModel = RandomUserViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getRandomUsers()
    }

    val randomUser by viewModel.randomUser.observeAsState()
    val error by viewModel.error.observeAsState()

    if (error != null) {
        Text(text = error!!)
    } else if (randomUser != null) {
        //Log.d("RandomUser", "RandomUser: $randomUser")
        //Log.d("RandomUser", "Results: ${randomUser!!.results.size}")
        //Log.d("RandomUser", "Results: ${randomUser!!.results}")
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            itemsIndexed(randomUser!!.results) { index, user ->
                Log.d("RandomUser", "User $index: $user")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    DisplayImageFromURLWithPlaceHolder(user.picture.large)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "${index + 1}. ${user.name.first}",
                            fontSize = 30.sp
                        )
                        Text(
                            text = user.email,
                            fontSize = 15.sp
                        )
                        Text(
                            text = user.phone,
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
fun DisplayImageFromURLWithPlaceHolder(imageUrl: String){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_dots),
        contentDescription = "Texto Imagen",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(100.dp)
    )
}