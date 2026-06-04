package com.gramavasathi.app.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gramavasathi.app.viewmodel.MainViewModel
import com.gramavasathi.app.viewmodel.GramaVasathiViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()
    val gvViewModel: GramaVasathiViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(onSplashFinished = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Listing.route) {
            ListingScreen(navController = navController, viewModel = mainViewModel)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("farmId") { type = NavType.StringType })
        ) { backStackEntry ->
            val farmId = backStackEntry.arguments?.getString("farmId")
            val farm = mainViewModel.farmStays.find { it.id == farmId }
            farm?.let {
                DetailScreen(navController, it, mainViewModel)
            }
        }
        composable(Screen.HostTraining.route) {
            HostTrainingScreen(navController = navController, viewModel = mainViewModel)
        }
        composable(Screen.CulturalGuide.route) {
            CulturalGuideScreen(navController = navController, viewModel = mainViewModel)
        }
        
        composable(Screen.Explore.route) {
            ExploreScreen(navController = navController, viewModel = gvViewModel)
        }
        composable(Screen.FarmStayDetail.route) {
            FarmStayDetailScreen(navController = navController, viewModel = gvViewModel)
        }
        composable(Screen.Booking.route) {
            BookingScreen(navController = navController, viewModel = gvViewModel)
        }
        composable(Screen.BookingConfirm.route) {
            BookingConfirmScreen(navController = navController, viewModel = gvViewModel)
        }
    }
}
