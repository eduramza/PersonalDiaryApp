package com.eduramza.personaldiaryapp.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.eduramza.personaldiaryapp.presentation.screens.auth.AuthenticationScreen
import com.eduramza.personaldiaryapp.presentation.screens.auth.AuthenticationViewModel
import com.eduramza.personaldiaryapp.util.Constants
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SetupNavGraph(
    startDestination: String,
    navController: NavHostController
) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        authenticationRoute(
            navigateToHome = {
                navController.popBackStack()
                navController.navigate(AppScreen.Home.route)
            }
        )
        homeRoute()
        writeRoute()
    }
}

fun NavGraphBuilder.authenticationRoute(
    navigateToHome: () -> Unit
) {
    composable(route = AppScreen.Authentication.route) {
        val authenticationViewModel = viewModel<AuthenticationViewModel>()
        val authenticated by authenticationViewModel.authenticated
        val loadingState by authenticationViewModel.loadingState

        val oneTapState = rememberOneTapSignInState()
        val messageBarState = rememberMessageBarState()

        AuthenticationScreen(
            authenticated = authenticated,
            loadingState = false,
            oneTapState = oneTapState,
            messageBarState = messageBarState,
            onTokenIdReceived = { tokenId ->
                authenticationViewModel.signInWithMongoAtlas(
                    tokenId = tokenId,
                    onSuccess = {
                        messageBarState.addSuccess("User authenticated with success!")
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
            navigateToHome = navigateToHome
        )
    }
}

fun NavGraphBuilder.homeRoute() {
    composable(route = AppScreen.Home.route) {
        val scope = rememberCoroutineScope()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                scope.launch(Dispatchers.IO) {
                    App.create(Constants.MONGO_SERVICE_APP_ID).currentUser?.logOut()
                }
            }) {
                Text(text = "Logout")
            }
        }
    }
}

fun NavGraphBuilder.writeRoute() {
    composable(
        route = AppScreen.Write.route,
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