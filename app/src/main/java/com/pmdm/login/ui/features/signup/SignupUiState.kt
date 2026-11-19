package com.pmdm.login.ui.features.signup

import com.pmdm.login.model.User

data class SignupUiState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val passwordRepeat: String = "",
    val acceptToC: Boolean = false,
    val validacion: ValidacionSignupUiState = ValidacionSignupUiState(),
    var error: String? = null
)

fun SignupUiState.toUser() = User(
    email = this.email,
    username = this.username,
    password = this.password
)