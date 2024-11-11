package com.studentdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.studentdashboard.data.RandomData
import com.studentdashboard.data.source.local.EventsDatastore
import com.studentdashboard.data.source.local.ParkingSpotsDatastore
import com.studentdashboard.data.source.local.UserDataStore
import com.studentdashboard.navigation.AppDestination
import com.studentdashboard.navigation.MainNavigationGraph
import com.studentdashboard.screens.TopAppBarUI
import com.studentdashboard.ui.theme.StudentDashboardTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.compose.koinInject

/**
 * MainActivity serves as the entry point of the Student Dashboard application.
 *
 * This activity sets up the UI content using Jetpack Compose and manages the
 * overall navigation within the application. It utilizes a Scaffold to include
 * a top app bar and a main content area that is filled based on the current
 * navigation destination.
 *
 * Dependencies:
 * - UserDataStore: Handles user-related data such as the user's name.
 * - EventsDatastore: Manages the storage and retrieval of event data.
 * - ParkingSpotsDatastore: Handles the storage and retrieval of parking spot information.
 * - RandomData: Provides mock data for testing and initialization.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectRandomData()
        setContent {

            val navHostController = rememberNavController()
            val userDataStore: UserDataStore = koinInject()
            val userName by userDataStore.userName.collectAsStateWithLifecycle(initialValue = "")

            StudentDashboardTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp),

                    topBar = {
                        TopAppBarUI(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(62.dp), userName = userName
                        )
                    }
                ) { innerPadding ->
                    MainNavigationGraph(
                        startDestination = AppDestination.Home,
                        navHostController = navHostController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    /**
     * Injects random data into the application's data stores for testing purposes.
     * This function sets mock data for the user's name, events, submissions,
     * and parking spots using coroutines to avoid blocking the main thread.
     */
    private fun injectRandomData() {
        val userDataStore by inject<UserDataStore>()
        val eventsDatastore by inject<EventsDatastore>()
        val parkingSpotsDatastore by inject<ParkingSpotsDatastore>()

        lifecycleScope.launch(Dispatchers.IO) {
            userDataStore.setUserName(RandomData.userDisplayName)
            eventsDatastore.setCurrentEvents(RandomData.events)
            eventsDatastore.setCurrentSubmissions(RandomData.submissions)
            parkingSpotsDatastore.setParkingSpots(RandomData.parkingSpots)
        }
    }
}
