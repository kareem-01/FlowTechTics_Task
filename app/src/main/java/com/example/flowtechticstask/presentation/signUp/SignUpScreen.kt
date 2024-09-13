package com.example.flowtechticstask.presentation.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flowtechticstask.LocalNavController
import com.example.flowtechticstask.R
import com.example.flowtechticstask.TextType
import com.example.flowtechticstask.presentation.composables.AuthenticationButton
import com.example.flowtechticstask.presentation.composables.AuthenticationField
import com.example.flowtechticstask.presentation.composables.SnackBar
import com.example.flowtechticstask.presentation.navigation.navigateToHome
import com.example.flowtechticstask.presentation.navigation.navigateToLogIn
import com.example.flowtechticstask.ui.theme.Radius24
import com.example.flowtechticstask.ui.theme.Space16
import com.example.flowtechticstask.ui.theme.Space8
import com.example.flowtechticstask.ui.theme.lightBackground
import com.example.flowtechticstask.ui.theme.lightHintText
import com.example.flowtechticstask.ui.theme.lightPrimary
import com.example.flowtechticstask.ui.theme.lightTextColor
import com.example.flowtechticstask.utils.noRippleClick
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by signUpViewModel.state.collectAsState()

    SignUpContent(
        state = state,
        interaction = signUpViewModel,
    )
    LaunchedEffect(key1 = signUpViewModel.effect) {
        signUpViewModel.effect.collectLatest { effect ->
            when (effect) {
                is SignUpUiEffect.NavigateToLogin -> navController.navigateToLogIn()
                is SignUpUiEffect.NavigateToHome -> navController.navigateToHome()
            }
        }

    }

}


@Composable
private fun SignUpContent(
    state: SignUpUiState,
    interaction: SignUpInteraction,
) {
    val typography = MaterialTheme.typography
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
    ) {
        Image(
            painter = painterResource(id = R.drawable.rick_and_morty),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.37f),
            contentScale = ContentScale.FillBounds
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.69f)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = Radius24, topEnd = Radius24),
            colors = CardDefaults.cardColors(containerColor = lightBackground)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Space8),
                verticalArrangement = Arrangement.spacedBy(Space16)
            ) {
                Text(
                    text = stringResource(id = R.string.signUp),
                    style = typography.titleLarge,
                    color = lightTextColor
                )
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = Space16),
                    verticalArrangement = Arrangement.spacedBy(Space16),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    item {
                        AuthenticationField(
                            onDoneClick = interaction::updateUiState,
                            label = stringResource(id = R.string.userName),
                            text = state.name,
                            iconPainter = painterResource(id = R.drawable.profile_icon),
                            textType = TextType.USERNAME,
                        )
                    }
                    item {
                        AuthenticationField(
                            onDoneClick = interaction::updateUiState,
                            label = stringResource(id = R.string.email),
                            text = state.email,
                            iconPainter = painterResource(id = R.drawable.email_icon),
                            textType = TextType.EMAIL,
                        )
                    }
                    item {
                        AuthenticationField(
                            onDoneClick = interaction::updateUiState,
                            label = stringResource(id = R.string.age),
                            text = state.age,
                            iconPainter = painterResource(id = R.drawable.age_icon),
                            textType = TextType.AGE,
                        )
                    }
                    item {
                        AuthenticationField(
                            onDoneClick = interaction::updateUiState,
                            label = stringResource(id = R.string.password),
                            text = state.password,
                            iconPainter = painterResource(id = R.drawable.lock_icon),
                            textType = TextType.PASSWORD,
                        )
                    }
                    item {
                        AuthenticationField(
                            onDoneClick = interaction::updateUiState,
                            label = stringResource(id = R.string.confirmPassword),
                            text = state.confirmPassword,
                            iconPainter = painterResource(id = R.drawable.lock_icon),
                            textType = TextType.CONFIRM_PASSWORD,
                        )
                    }
                    item {
                        AuthenticationButton(
                            text = stringResource(id = R.string.signUp),
                            onClick = interaction::signUp,
                            isLoading = state.isLoading
                        )
                    }
                    item {
                        val logInText = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = lightHintText)) {
                                append(stringResource(id = R.string.haveAccount))
                            }
                            withStyle(style = SpanStyle(color = lightPrimary)) {
                                append(stringResource(id = R.string.Login))
                            }
                        }

                        Text(
                            text = logInText,
                            modifier = Modifier
                                .padding(top = Space8)
                                .noRippleClick { interaction.onLogInClick() })
                    }
                }
            }


        }
        state.message?.let {
            SnackBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .systemBarsPadding(),
                text = it
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    SignUpScreen()
}
