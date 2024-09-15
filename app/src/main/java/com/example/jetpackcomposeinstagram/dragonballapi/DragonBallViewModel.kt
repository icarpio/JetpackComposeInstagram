package com.example.jetpackcomposeinstagram.dragonballapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeinstagram.network.DragonBallApi
import kotlinx.coroutines.launch

class DragonBallViewModel: ViewModel() {
    private val api = DragonBallApi.service

    private val _characters = MutableLiveData<List<Item>>() // Aquí debe ser una lista de Items
    val characters: LiveData<List<Item>> = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val response = api.getCharacters()
                if (response.isSuccessful) {
                    _characters.value = response.body()?.items ?: emptyList() // Asigna la lista de personajes
                } else {
                    _error.value = "Error: ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
    }

    fun getCharacter(characterId: String): LiveData<Item> {
        val _character = MutableLiveData<Item>()
        viewModelScope.launch {
            try {
                val response = api.getCharacter(characterId)
                if (response.isSuccessful) {
                    _character.value = response.body()
                } else {
                    _error.value = "Error: ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
        return _character
    }
}




