package com.pmdm.login.ui.features.login

sealed interface LoginEvent {
    data class OnEmailChanged(val email: String) : LoginEvent
    data class OnPasswordChanged(val password: String) : LoginEvent
    data object OnRecordarSesionChanged : LoginEvent
    data object OnLogin : LoginEvent
    data object OnNavigateToSignup
}
