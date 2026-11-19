package com.pmdm.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pmdm.login.data.PiezaRepository
import com.pmdm.login.ui.features.login.LoginScreen
import com.pmdm.login.ui.features.login.LoginViewModel
import com.pmdm.login.ui.features.presentacion.PresentacionScreen
import com.pmdm.login.ui.features.signup.SignupScreen
import com.pmdm.login.ui.features.signup.SignupViewModel
import com.pmdm.login.ui.theme.LoginTheme

enum class Pantalla {
    PRESENTACION,
    LOGIN,
    SIGNUP
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginViewModel: LoginViewModel by viewModels()
        val signupViewModel: SignupViewModel by viewModels()
        setContent {
            LoginTheme {
                GestorColeccionesApp(loginViewModel, signupViewModel)
            }
        }
    }
}

@Composable
fun GestorColeccionesApp(
    loginViewModel: LoginViewModel,
    signupViewModel: SignupViewModel
) {
    var pantallaActual by remember { mutableStateOf(Pantalla.PRESENTACION) }
    val piezaMostrar by remember {
        mutableStateOf(PiezaRepository().getPiezas().random())
    }

    when (pantallaActual) {
        Pantalla.PRESENTACION -> {
            PresentacionScreen(
                piezaMostrar = piezaMostrar,
                onCrearCuentaClick = { pantallaActual = Pantalla.SIGNUP },
                onIniciarSesionClick = { pantallaActual = Pantalla.LOGIN }
            )
        }
        Pantalla.LOGIN -> {
            LoginScreen(
                loginUiState = loginViewModel.loginUiState,
                onLoginEvent = loginViewModel::onLoginEvent
            )
        }
        Pantalla.SIGNUP -> {
            SignupScreen(
                uiState = signupViewModel.signupUiState,
                onSignupEvent = signupViewModel::onSignupEvent
            )
        }
    }
}
