/**
 * Dependency Injection Module for ViewModels in the student dashboard application.
 *
 * This module is responsible for providing instances of ViewModels that manage UI-related
 * data in a lifecycle-conscious way. By leveraging Koin for dependency injection,
 * the application ensures that ViewModels are instantiated and managed efficiently,
 * improving code modularity and testability.
 */

package com.studentdashboard.di

import com.studentdashboard.screens.home.HomeScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Koin module defining the ViewModels used in the application.
 *
 * The `viewModelsModule` provides the following dependency:
 * - [HomeScreenViewModel]: A ViewModel that holds and manages UI-related data for
 *   the home screen. It is responsible for preparing data for the UI and handling
 *   user interactions.
 */
val viewModelsModule = module {
    viewModelOf(::HomeScreenViewModel)
}
