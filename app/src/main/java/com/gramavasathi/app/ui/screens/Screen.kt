package com.gramavasathi.app.ui.screens

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Listing : Screen("listing")
    object Detail : Screen("detail/{farmId}") {
        fun createRoute(farmId: String) = "detail/$farmId"
    }
    object HostTraining : Screen("host_training")
    object CulturalGuide : Screen("cultural_guide")
    object Explore : Screen("explore")
    object FarmStayDetail : Screen("farm_stay_detail")
    object Booking : Screen("booking")
    object BookingConfirm : Screen("booking_confirm")
}
