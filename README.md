# Android PIN manager

The application allows to create and save a PIN code. PIN code is generated automatically. You cannot change the PIN code, only delete it.

# Tech Stack
The PIN manager built with Jetpack Compose to build UI elements.<br />
This project uses Navigation Compose for navigating screens and Hilt Navigation Compose for injecting dependencies.<br />
For dependency injection is used Hilt to reduce the boilerplate of doing manual DI and standardize components.<br />
Kotlin Coroutines have been used entirely in this project to handle tasks asynchronously.<br />
Room Database is used for local data storage.

# Architecture

The PIN code manager app has been fully modularized.<br />
The overall architecture is composed of three layers: a data layer, a domain layer and UI layer.<br />
The app architecture follows unidirectional data flow implemented using Kotlin Flows.<br />
The UI Layer consists of UI elements and ViewModel that holds app state.<br />
The domain layer is responsible for encapsulating business logic that is reused by ViewModels.<br />
The data Layer consists of Repository, which include logic for persisting and querying data from the database.

The minimum SDK version is 26 (Android 8.0).
