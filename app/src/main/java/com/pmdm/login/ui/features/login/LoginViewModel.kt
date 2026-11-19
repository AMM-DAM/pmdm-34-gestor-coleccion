package com.pmdm.login.ui.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    val validadorLogin = ValidadorLogin()
    var loginUiState by mutableStateOf(LoginUiState())

    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> {
                val validacionCorreo = validadorLogin.validadorCorreo.valida(event.email)
                loginUiState = loginUiState.copy(
                    email = event.email,
                    validacion = loginUiState.validacion.copy(validacionCorreo = validacionCorreo)
                )
            }
            is LoginEvent.OnPasswordChanged -> {
                val validacionContrasena = validadorLogin.validadorContrasena.valida(event.password)
                loginUiState = loginUiState.copy(
                    password = event.password,
                    validacion = loginUiState.validacion.copy(validacionContrasena = validacionContrasena)
                )
            }
            LoginEvent.OnRecordarSesionChanged -> {
                loginUiState = loginUiState.copy(recordarSesion = !loginUiState.recordarSesion)
            }
            LoginEvent.OnLogin -> {
                loginUiState = loginUiState.copy(
                    validacion = validadorLogin.valida(loginUiState)
                )
            }
            LoginEvent.OnNavigateToSignup -> {
                // pantallaActual = Pantalla.SIGNUP
            }
        }
    }
}