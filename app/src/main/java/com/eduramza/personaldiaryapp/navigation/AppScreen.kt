package com.eduramza.personaldiaryapp.navigation


internal const val WRITE_SCREEN_ARGUMENT_KEY = "diaryId"

sealed class AppScreen(val route: String) {
    object Authentication : AppScreen(route = ScreenName.AUTHENTICATION.name)
    object Home : AppScreen(route = ScreenName.HOME.name)
    object Write : AppScreen(
        route = "${ScreenName.WRITE.name}?$WRITE_SCREEN_ARGUMENT_KEY={diaryId}"
    ) {
        fun passDiaryId(diaryId: String) =
            "${ScreenName.WRITE.name}?$WRITE_SCREEN_ARGUMENT_KEY=$diaryId"
    }
}