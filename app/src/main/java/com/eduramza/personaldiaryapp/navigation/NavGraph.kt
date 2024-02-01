package com.eduramza.personaldiaryapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.eduramza.personaldiaryapp.presentation.screens.auth.AuthenticationScreen
import com.eduramza.personaldiaryapp.presentation.screens.auth.AuthenticationViewModel
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState

@Composable
fun SetupNavGraph(
    startDestination: String,
    navController: NavHostController
) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        authenticationRoute()
        homeRoute()
        writeRoute()
    }
}

fun NavGraphBuilder.authenticationRoute() {
    composable(route = Screen.Authentication.route) {
        val authenticationViewModel = viewModel<AuthenticationViewModel>()
        val loadingState by authenticationViewModel.loadingState

        val oneTapState = rememberOneTapSignInState()
        val messageBarState = rememberMessageBarState()

        AuthenticationScreen(
            loadingState = false,
            oneTapState = oneTapState,
            messageBarState = messageBarState,
            onTokenIdReceived = { tokenId ->
                authenticationViewModel.signInWithMongoAtlas(
                    tokenId = tokenId,
                    onSuccess = {
                        if (it){
                            Log.d("Auth", tokenId)
                            messageBarState.addSuccess("User authenticated with success!")
                        }
                    },
                    onError = {
                        Log.e("Auth", it.message.toString())
                        messageBarState.addError(Exception(it))
                    }
                )
            },
            onDialogDismissed = { message ->
                messageBarState.addError(Exception(message))
            },
            onLoginClicked = {
                oneTapState.open()
                authenticationViewModel.setLoading(true)
            },
        )
    }
}

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.Home.route) {

    }
}

fun NavGraphBuilder.writeRoute() {
    composable(
        route = Screen.Write.route,
        arguments = listOf(
            navArgument(name = WRITE_SCREEN_ARGUMENT_KEY) {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )
    ) {

    }
}