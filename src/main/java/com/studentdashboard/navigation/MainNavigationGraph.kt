package com.studentdashboard.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.studentdashboard.screens.home.HomeScreenUI

/**
 * Main navigation graph for the student dashboard application.
 *
 * This Composable function sets up the navigation host and defines the navigation routes
 * within the app, starting from the specified start destination.
 *
 * @param modifier Modifier for styling the NavHost.
 * @param startDestination The initial destination to navigate to.
 * @param navHostController The NavHostController for managing navigation.
 */
@Composable
fun MainNavigationGraph(
    modifier: Modifier = Modifier,
    startDestination: AppDestination,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable<AppDestination.Home> {
            HomeScreenUI()
        }
    }
}
