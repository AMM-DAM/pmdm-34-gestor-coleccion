package com.pmdm.login.ui.features.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pmdm.login.data.UserRepository

class SignupViewModel: ViewModel() {
    val userRepository = UserRepository()

    val validadorSignup = ValidadorSignup()

    var signupUiState by mutableStateOf(SignupUiState())

    fun onSignupEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.OnEmailChanged -> {
                val validacion = validadorSignup.validadorEmail.valida(event.email)
                signupUiState = signupUiState.copy(
                    email = event.email,
                    validacion = signupUiState.validacion.copy(validacionEmail = validacion)
                )
            }
            is SignupEvent.OnUsernameChanged -> {
                val validacion = validadorSignup.validadorUsername.valida(event.username)
                signupUiState = signupUiState.copy(
                    username = event.username,
                    validacion = signupUiState.validacion.copy(validacionUsername = validacion)
                )
            }
            is SignupEvent.OnPasswordChanged -> {
                val validacion = validadorSignup.validadorPassword.valida(event.password)
                signupUiState = signupUiState.copy(
                    password = event.password,
                    validacion = signupUiState.validacion.copy(validacionPassword = validacion)
                )
            }
            is SignupEvent.OnPasswordRepeatChanged -> {
                val validacion = validadorSignup.validadorPasswordRepeat.valida(
                    event.passwordRepeat,
                    signupUiState.password
                )
                signupUiState = signupUiState.copy(
                    passwordRepeat = event.passwordRepeat,
                    validacion = signupUiState.validacion.copy(validacionPasswordRepeat = validacion)
                )
            }
            SignupEvent.OnAcceptToCChanged -> {
                signupUiState = signupUiState.copy(
                    acceptToC = !signupUiState.acceptToC
                )
            }
            SignupEvent.OnSignup -> {
                val validacion = validadorSignup.valida(signupUiState)
                signupUiState = signupUiState.copy(validacion = validacion)

                if (validacion.hayError) {
                    signupUiState = signupUiState.copy(
                        error = "Hay errores en el formulario"
                    )

                // NOTA: El repository no tiene ninguna validacion.
                //  por eso validamos aqui, para mostrar un error
                } else if (userRepository.getByEmail(signupUiState.email) != null) {
                    signupUiState = signupUiState.copy(
                        error = "Ya existe un usuario con ese correo electrónico"
                    )

                } else {
                    userRepository.insert(signupUiState.toUser())
                }
            }
            SignupEvent.OnNavigateToLogin -> {
                // pantallaActual = Pantalla.LOGIN
            }
        }
    }
}