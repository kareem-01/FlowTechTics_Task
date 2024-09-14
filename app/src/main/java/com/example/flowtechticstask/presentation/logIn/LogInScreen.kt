package com.example.flowtechticstask.presentation.logIn

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flowtechticstask.LocalNavController
import com.example.flowtechticstask.R
import com.example.flowtechticstask.TextType
import com.example.flowtechticstask.presentation.composables.AuthenticationButton
import com.example.flowtechticstask.presentation.composables.AuthenticationField
import com.example.flowtechticstask.presentation.composables.SnackBar
import com.example.flowtechticstask.presentation.navigation.navigateToHome
import com.example.flowtechticstask.presentation.navigation.navigateToSignUp
import com.example.flowtechticstask.ui.theme.Radius24
import com.example.flowtechticstask.ui.theme.Space16
import com.example.flowtechticstask.ui.theme.Space24
import com.example.flowtechticstask.ui.theme.Space8
import com.example.flowtechticstask.ui.theme.lightBackground
import com.example.flowtechticstask.ui.theme.lightHintText
import com.example.flowtechticstask.ui.theme.lightPrimary
import com.example.flowtechticstask.ui.theme.lightTextColor
import com.example.flowtechticstask.utils.noRippleClick
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LogInScreen(viewModel: LogInViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()

    LogInContent(state, viewModel)
    LaunchedEffect(key1 = true) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is LogInUiEffect.NavigateToSignUp -> navController.navigateToSignUp()
                is LogInUiEffect.NavigateToHome -> navController.navigateToHome()
            }
        }
    }
}

@Composable
private fun LogInContent(state: LogInUiState, interaction: LogInInteraction) {
    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }) {
        Image(
            painter = painterResource(id = R.drawable.rick),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.37f),
            contentScale = ContentScale.FillBounds,
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.66f)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = Radius24, topEnd = Radius24),
            colors = CardDefaults.cardColors(containerColor = lightBackground)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = Space24, start = Space16, end = Space16),
                verticalArrangement = Arrangement.spacedBy(Space16)
            ) {
                Text(
                    text = stringResource(id = R.string.Login),
                    style = typography.titleLarge,
                    color = lightTextColor
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(Space16)
                ) {
                    AuthenticationField(
                        onDoneClick = interaction::updateUiState,
                        label = stringResource(id = R.string.email),
                        text = state.email,
                        iconPainter = painterResource(id = R.drawable.email_icon),
                        textType = TextType.EMAIL,
                    )
                    AuthenticationField(
                        onDoneClick = interaction::updateUiState,
                        label = stringResource(id = R.string.password),
                        text = state.password,
                        iconPainter = painterResource(id = R.drawable.lock_icon),
                        textType = TextType.PASSWORD,
                    )
                    AuthenticationButton(
                        text = stringResource(id = R.string.Login),
                        onClick = interaction::onLogInClicked,
                        isLoading = state.isLoading
                    )
                }
                val signUp = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = lightHintText)) {
                        append(stringResource(id = R.string.dontHaveAccount))
                    }
                    withStyle(style = SpanStyle(color = lightPrimary)) {
                        append(stringResource(id = R.string.signUp))
                    }
                }
                Text(
                    text = signUp,
                    modifier = Modifier
                        .padding(top = Space8)
                        .noRippleClick { interaction.onSignUpClicked() })
            }
        }
        state.message?.let {
            SnackBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .systemBarsPadding(),
                text = it,
                onClear = interaction::clearMessage
            )
        }
    }
}