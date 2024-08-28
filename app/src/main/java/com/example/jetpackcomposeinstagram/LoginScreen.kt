package com.example.jetpackcomposeinstagram

import android.app.Activity
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Header(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 30.dp)
        )
        Body(Modifier.align(Alignment.Center))
        Footer(Modifier.align(Alignment.BottomCenter))

    }
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close, // Ícono a mostrar
        contentDescription = "Close App",
        modifier = modifier.clickable { activity.finish() },
        tint = Color.Blue // Ajusta el color del ícono
    )
}

@Composable
fun Body(modifier: Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isloginEnable by rememberSaveable { mutableStateOf(false) }
    Column(modifier = modifier) {
        ImageLogo()
        Spacer(modifier = Modifier.size(16.dp))
        Email(email) {
            email = it
            isloginEnable = enabledLogin(email,password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            password = it
            isloginEnable = enabledLogin(email,password)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isloginEnable)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()

    }
}

@Composable
fun SocialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Social login facebbok",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continue with facebook",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color.LightGray)
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 6.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray
        )

        Divider(
            Modifier
                .background(Color.LightGray)
                .height(1.dp)
                .weight(1f)
        )

    }
}

@Composable
fun LoginButton(isloginEnable: Boolean) {
    Button(
        onClick = { },
        enabled = isloginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor =  Color(0xff335bff), // Cambia el color de fondo del botón
            disabledContainerColor = Color(0xff33beff ), //Cuando esta deshabilitado
            contentColor = Color(0xFFFFFFFF), // Cambia el color del contenido (texto o íconos)
            disabledContentColor =  Color(0xFFFFFFFF),
        )
    ) {
        Text(text = "Log in")
    }
}

fun enabledLogin(email:String,password:String):Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot Password",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue,
        modifier = modifier
    )
}

@Composable
fun ImageLogo() {
    Image(painter = painterResource(id = R.drawable.present), contentDescription = "Logo")
}


@Composable
fun Email(email: String, onTextChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Email") },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFFAFAFA), // Color de fondo cuando está enfocado
            unfocusedContainerColor = Color(0xFFFAFAFA), // Color de fondo cuando no está enfocado
            focusedTextColor = Color(0xFFB2B2B2), // Color del texto cuando está enfocado
            unfocusedTextColor = Color(0xFFB2B2B2), // Color del texto cuando no está enfocado
            focusedIndicatorColor = Color.Transparent, // Color del indicador cuando está enfocado
            unfocusedIndicatorColor = Color.Transparent // Color del indicador cuando no está enfocado
        )
    )
}

@Composable
fun Password(pwd: String, onTextChange: (String) -> Unit) {
    var pwdVisibility by remember { mutableStateOf(false) }
    TextField(
        value = pwd,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Password") },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (pwdVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { pwdVisibility = !pwdVisibility }) {
                Icon(imageVector = image, contentDescription = "show password")
            }
        },
        visualTransformation = if (pwdVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(Color.LightGray)
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun SignUp() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Don't have an account", fontSize = 12.sp, color = Color.LightGray)
        Text(
            text = "Sign Up.",
            Modifier.padding(horizontal = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
    }
}
