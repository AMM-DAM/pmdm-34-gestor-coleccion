package com.pmdm.login.ui.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.pmdmiesbalmis.components.ui.composables.CheckboxWithLabel
import com.pmdm.login.ui.components.ForumTextField
import com.pmdm.login.ui.components.PasswordTextField
import com.pmdm.login.ui.theme.LoginTheme

@Composable
fun LoginForm(
    uiState: LoginUiState,
    onLoginEvent: (LoginEvent) -> Unit
) {
    val modifierFillMaxWidth = Modifier.fillMaxWidth()
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            ForumTextField(
                value = uiState.email,
                onValueChanged = { onLoginEvent(LoginEvent.OnEmailChanged(it)) },
                label = "Correo electrónico",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email"
                    )
                },
                errorMessage = uiState.validacion.validacionCorreo.mensajeError,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            PasswordTextField(
                uiState.password,
                onPasswordValueChanged = { onLoginEvent(LoginEvent.OnPasswordChanged(it)) },
                errorMessage = uiState.validacion.validacionContrasena.mensajeError,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(16.dp))
            CheckboxWithLabel(
                label = "Recordar sesión",
                checkedState = uiState.recordarSesion,
                onStateChange = { onLoginEvent(LoginEvent.OnRecordarSesionChanged) },
            )
            Spacer(Modifier.height(16.dp))
            if (uiState.error != null) {
                Text(uiState.error, color = MaterialTheme.colorScheme.error)
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = { onLoginEvent(LoginEvent.OnLogin) }, modifierFillMaxWidth) {
                Text("Iniciar sesión")
            }
        }
    }
}


@Composable
fun LoginScreen(
    loginUiState: LoginUiState,
    onLoginEvent: (LoginEvent) -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginForm(
            uiState = loginUiState,
            onLoginEvent = onLoginEvent
        )
        Spacer(Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("¿No tienes cuenta?")
            TextButton(onClick = { onLoginEvent(LoginEvent.OnNavigateToSignup) }) {
                Text("Regístrate")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val loginViewModel: LoginViewModel = viewModel()

    LoginTheme {
        LoginScreen(
            loginUiState = loginViewModel.loginUiState,
            onLoginEvent = loginViewModel::onLoginEvent
        )
    }
}