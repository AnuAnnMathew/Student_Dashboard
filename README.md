Student Dashboard App

This project is an Android application built using Kotlin and Jetpack Compose. It is a single-activity app that recreates a student dashboard screen. The dashboard consists of a custom navigation bar that displays the student's name, followed by two sections: a timetable(Events and submission) for the day and a list of available car parks. The data is represented locally using fictitious values.

Features

Single Activity Architecture: The app is built with a single-activity structure using Jetpack Compose.
Custom Navigation Bar: Displays the student’s name at the top of the screen.
Timetable Section: Shows the student's timetable for the day in a list.
Car Park Section: Displays a list of available parking spots.
Local Data Source: The timetable and car park information are sourced from local, fictitious data, not from an external service.

Design Considerations

Pixel-Perfect Design: Careful attention to design details, including offsets, corners, and consistent use of fonts, as per the task requirements.
Code Reusability: Components and UI elements are designed for reusability across the app.
Consistency: The app maintains a consistent design language and font across all UI elements.

Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern:
Model: Holds the data (timetable and car park information) that is displayed in the UI.
ViewModel: Provides data to the UI and handles any business logic.
View: Composed using Jetpack Compose to display the data in a modern, declarative way.

How to Run the Project

Clone the repository:
git clone https://github.com/yourusername/student-dashboard-app.git
Open the project in Android Studio.
Sync Gradle and ensure all dependencies are installed.
Run the app on an emulator or physical device.

Unit Testing
Unit tests are provided for the ViewModel to ensure the correct data is presented to the UI.

GitHub Version Control
The development process has been tracked using GitHub. Each feature and functionality is committed separately to maintain clean and understandable version control.

Dependencies
Jetpack Compose: Used for building the UI components.
Kotlin Coroutines: For managing asynchronous operations.
Material Design Components: For styling the UI.
JUnit: For unit testing the ViewModel.

Future Improvements
Add interactivity to the views for navigation. Implement data fetching from an actual API.

