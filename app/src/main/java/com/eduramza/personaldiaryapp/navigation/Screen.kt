package com.eduramza.personaldiaryapp.navigation


internal const val WRITE_SCREEN_ARGUMENT_KEY = "diaryId"

sealed class Screen(val route: String) {
    object Authentication : Screen(route = ScreenName.AUTHENTICATION.name)
    object Home : Screen(route = ScreenName.HOME.name)
    object Write : Screen(
        route = "${ScreenName.WRITE.name}?$WRITE_SCREEN_ARGUMENT_KEY={diaryId}"
    ) {
        fun passDiaryId(diaryId: String) =
            "${ScreenName.WRITE.name}?$WRITE_SCREEN_ARGUMENT_KEY=$diaryId"
    }
}