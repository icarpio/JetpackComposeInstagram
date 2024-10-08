package com.example.jetpackcomposeinstagram.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorAnimationSimple(){
    var firstColor by rememberSaveable {
        mutableStateOf(false)
    }
    var realColor = if(firstColor) Color.Red else Color.Yellow
    Box(modifier = Modifier
        .size(100.dp)
        .background(realColor)
        .clickable { firstColor = !firstColor })
    Spacer(modifier = Modifier.size(200.dp))
    var secondColor by rememberSaveable {
        mutableStateOf(false)
    }
    val secondRealColor by animateColorAsState(targetValue = if(secondColor) Color.Red else Color.Yellow )
    Box(modifier = Modifier
        .size(100.dp)
        .background(secondRealColor)
        .clickable { secondColor = !secondColor })
}

@Composable
fun ColorAnimationPro(){
    var firstColor by rememberSaveable {
        mutableStateOf(false)
    }
    var showBox by rememberSaveable {
        mutableStateOf(true)
    }
    val realColor by animateColorAsState(
        targetValue = if(firstColor) Color.Red else Color.Yellow,
        animationSpec = tween(durationMillis = 2000), //2seg
        finishedListener = {showBox=false}
    )
    if(showBox){
        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor)
            .clickable { firstColor = !firstColor })
    }
}

@Composable
fun SizeAnimation(){
    var smallSize by rememberSaveable { mutableStateOf(true) }
    val size by animateDpAsState(
        targetValue = if(smallSize) 50.dp else 100.dp,
        animationSpec = tween(durationMillis = 500))
    Box(modifier = Modifier
        .size(size)
        .background(Color.Cyan)
        .clickable { smallSize = !smallSize })
}

@Composable
fun VisibilityAnimation(){
    var isVisible by remember{ mutableStateOf(true) }

    Column(Modifier.fillMaxSize()) {
        Button(onClick = {isVisible =!isVisible}) {
            Text(text = "Ocultar/Mostrar")
        }
        Spacer(modifier = Modifier.size(50.dp))
        AnimatedVisibility(isVisible){
            Box(modifier = Modifier
                .size(150.dp)
                .background(Color.Red))
        }
    }
}