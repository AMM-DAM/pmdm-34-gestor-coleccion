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
import com.pmdm.login.ui.theme.LoginTheme

enum class Pantalla {
    PRESENTACION,
    LOGIN,
    SIGNUP
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginViewModel: LoginViewModel by viewModels();
        setContent {
            LoginTheme {
                GestorColeccionesApp(loginViewModel)
            }
        }
    }
}

@Composable
fun GestorColeccionesApp(
    loginViewModel: LoginViewModel
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
        Pantalla.SIGNUP -> {}
//        Pantalla.SIGNUP -> {
//            SignupScreen(
//                uiState = signupUiState,
//                onSignupEvent = { event ->
//                    when (event) {
//                        is SignupEvent.OnEmailChanged -> {
//                            val validacion = validadorSignup.validadorEmail.valida(event.email)
//                            signupUiState = signupUiState.copy(
//                                email = event.email,
//                                validacion = signupUiState.validacion.copy(validacionEmail = validacion)
//                            )
//                        }
//                        is SignupEvent.OnUsernameChanged -> {
//                            val validacion = validadorSignup.validadorUsername.valida(event.username)
//                            signupUiState = signupUiState.copy(
//                                username = event.username,
//                                validacion = signupUiState.validacion.copy(validacionUsername = validacion)
//                            )
//                        }
//                        is SignupEvent.OnPasswordChanged -> {
//                            val validacion = validadorSignup.validadorPassword.valida(event.password)
//                            signupUiState = signupUiState.copy(
//                                password = event.password,
//                                validacion = signupUiState.validacion.copy(validacionPassword = validacion)
//                            )
//                        }
//                        is SignupEvent.OnPasswordRepeatChanged -> {
//                            val validacion = validadorSignup.validadorPasswordRepeat.valida(
//                                event.passwordRepeat,
//                                signupUiState.password
//                            )
//                            signupUiState = signupUiState.copy(
//                                passwordRepeat = event.passwordRepeat,
//                                validacion = signupUiState.validacion.copy(validacionPasswordRepeat = validacion)
//                            )
//                        }
//                        SignupEvent.OnAcceptToCChanged -> {
//                            signupUiState = signupUiState.copy(
//                                acceptToC = !signupUiState.acceptToC
//                            )
//                        }
//                        SignupEvent.OnSignup -> {
//                            val validacion = validadorSignup.valida(signupUiState)
//                            signupUiState = signupUiState.copy(validacion = validacion)
//                        }
//                        SignupEvent.OnNavigateToLogin -> {
//                            pantallaActual = Pantalla.LOGIN
//                        }
//                    }
//                }
//            )
//        }
    }
}
