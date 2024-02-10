package com.eduramza.personaldiaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.eduramza.personaldiaryapp.navigation.AppScreen
import com.eduramza.personaldiaryapp.navigation.SetupNavGraph
import com.eduramza.personaldiaryapp.ui.theme.PersonalDiaryAppTheme
import com.eduramza.personaldiaryapp.util.Constants.MONGO_SERVICE_APP_ID
import io.realm.kotlin.mongodb.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PersonalDiaryAppTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    startDestination = getStartDestination().route,
                    navController = navController
                )
            }
        }
    }
}

private fun getStartDestination(): AppScreen {
    val currentUser = App.create(MONGO_SERVICE_APP_ID).currentUser
    return if (currentUser != null && currentUser.loggedIn){
        AppScreen.Home
    } else {
        AppScreen.Authentication
    }
}