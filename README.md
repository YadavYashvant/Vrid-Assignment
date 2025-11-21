# Vrid Blog Reader - Android App
An Android blog reading application built with Jetpack Compose, implementing MVVM architecture, offline-first approach with SQLite caching, and pagination support.

## Features

### Core Features
- **Blog List Screen**: Displays a list of blog posts fetched from the Vrid blog API
- **Blog Detail Screen**: Shows full blog content using WebView
- **Clean Navigation**: Navigation between screens using Navigation Compose
- **MVVM Architecture**: Separation of concerns with ViewModel, Repository, and UI layers

### Bonus Features
- **Offline Support**: SQLite database caching for offline blog reading
- **Pagination**: Automatic loading of more blogs as user scrolls
- **Error Handling**: Error handling with retry mechanism
- **Loading States**:Loading indicators for better UX
- **Image Loading**: Image loading with Coil library

## Tech Stack

### Core Technologies
- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern declarative UI toolkit
- **Navigation Compose**: Type-safe navigation
- **ViewModel**: Lifecycle-aware data holder
- **Coroutines & Flow**: Asynchronous programming

### Networking
- **Retrofit**: REST API client
- **OkHttp**: HTTP client with logging interceptor
- **Gson**: JSON serialization/deserialization

### Local Storage
- **Room Database**: SQLite abstraction for offline caching
- **Type Converters**: Custom converters for complex objects

### UI & UX
- **Material 3**: Modern Material Design components
- **Coil**: Image loading library
- **WebView**: Display full blog content


## App Flow

1. **Launch**: App opens with the blog list screen
2. **Browse**: User can scroll through blog posts with automatic pagination
3. **Read**: Tap on any blog to open it in a WebView
4. **Offline**: Previously loaded blogs are cached and available offline
5. **Navigate Back**: Use the back button to return to the list