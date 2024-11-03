/**
 * Dependency Injection Module for utility classes in the student dashboard application.
 *
 * This module is responsible for providing singleton instances of utility classes
 * that can be used throughout the application. By utilizing Koin for dependency injection,
 * the application enhances modularity and testability.
 */

package com.studentdashboard.di

import com.google.gson.Gson
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Koin module defining the singleton instance of the Gson utility.
 *
 * The `utilsModule` provides the following dependency:
 * - [Gson]: A library for converting Java Objects into their JSON representation
 *   and vice versa. It is widely used for serializing and deserializing data
 *   structures in the application.
 */
val utilsModule = module {
    singleOf(::Gson)
}
