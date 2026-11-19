package com.pmdm.login.ui.features.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
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
fun SignupForm(uiState: SignupUiState, onSignupEvent: (SignupEvent) -> Unit) {
    val modifierFillMaxWidth = Modifier.fillMaxWidth()
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(),
    ) {
        Column(
            Modifier.padding(8.dp)
        ) {
            ForumTextField(
                value = uiState.email,
                onValueChanged = { onSignupEvent(SignupEvent.OnEmailChanged(it)) },
                label = "Correo electrónico",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "icono email"
                    )
                },
                errorMessage = uiState.validacion.validacionEmail.mensajeError,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            ForumTextField(
                value = uiState.username,
                onValueChanged = { onSignupEvent(SignupEvent.OnUsernameChanged(it)) },
                label = "Nombre usuario",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "icono nombre usuario"
                    )
                },
                errorMessage = uiState.validacion.validacionUsername.mensajeError,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            PasswordTextField(
                uiState.password,
                onPasswordValueChanged = { onSignupEvent(SignupEvent.OnPasswordChanged(it)) },
                errorMessage = uiState.validacion.validacionPassword.mensajeError,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            PasswordTextField(
                uiState.passwordRepeat,
                onPasswordValueChanged = { onSignupEvent(SignupEvent.OnPasswordRepeatChanged(it)) },
                label = "Repetir contraseña",
                errorMessage = uiState.validacion.validacionPasswordRepeat.mensajeError,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(16.dp))
            CheckboxWithLabel(
                label = "Acepto los términos y condiciones",
                checkedState = uiState.acceptToC,
                onStateChange = { onSignupEvent(SignupEvent.OnAcceptToCChanged) },
            )
            Spacer(Modifier.height(16.dp))
            if (uiState.error != null) {
                Text(uiState.error, color = MaterialTheme.colorScheme.error)
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = { onSignupEvent(SignupEvent.OnSignup) }, modifierFillMaxWidth) {
                Text("Crear cuenta")
            }
        }
    }
}

@Composable
fun SignupScreen(uiState: SignupUiState, onSignupEvent: (SignupEvent) -> Unit) {
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignupForm(uiState, onSignupEvent)
        Spacer(Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("¿Ya tienes una cuenta?")
            TextButton(onClick = { onSignupEvent(SignupEvent.OnNavigateToLogin) }) {
                Text("Iniciar sesión")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    val signupViewModel: SignupViewModel = viewModel()
    LoginTheme {
        SignupScreen(
            uiState = signupViewModel.signupUiState,
            onSignupEvent = signupViewModel::onSignupEvent
        )
    }
}


