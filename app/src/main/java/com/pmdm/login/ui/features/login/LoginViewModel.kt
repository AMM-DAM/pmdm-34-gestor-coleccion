package com.pmdm.login.ui.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pmdm.login.data.UserRepository

class LoginViewModel: ViewModel() {
    val userRepository = UserRepository()
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
                val validacion = validadorLogin.valida(loginUiState)
                val foundUser = userRepository.getByEmail(loginUiState.email)

                loginUiState = loginUiState.copy(
                    validacion = validacion
                )

                if (validacion.hayError) {
                    loginUiState = loginUiState.copy(
                        error = "Hay errores en el formulario"
                    )
                } else if(foundUser == null) {
                    loginUiState = loginUiState.copy(
                        error = "No existe un usuario con ese correo"
                    )
                } else {
                    // Iniciar sesión...
                }
            }
            LoginEvent.OnNavigateToSignup -> {
                // pantallaActual = Pantalla.SIGNUP
            }
        }
    }
}