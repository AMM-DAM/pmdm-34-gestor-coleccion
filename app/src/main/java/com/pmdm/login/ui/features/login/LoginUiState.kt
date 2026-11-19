package com.pmdm.login.ui.features.login

import kotlin.String

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val recordarSesion: Boolean = false,
    val validacion: ValidacionLoginUiState = ValidacionLoginUiState(),
    val error: String? = null
)
