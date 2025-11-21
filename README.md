# Vrid Blog Reader - Android App

A modern Android blog reading application built with Jetpack Compose, implementing MVVM architecture, offline-first approach with SQLite caching, and pagination support.

## 📱 Features

### Core Features
- ✅ **Blog List Screen**: Displays a list of blog posts fetched from the Vrid blog API
- ✅ **Blog Detail Screen**: Shows full blog content using WebView
- ✅ **Clean Navigation**: Smooth navigation between screens using Navigation Compose
- ✅ **MVVM Architecture**: Clean separation of concerns with ViewModel, Repository, and UI layers

### Bonus Features
- ✅ **Offline Support**: SQLite database caching for offline blog reading
- ✅ **Pagination**: Automatic loading of more blogs as user scrolls
- ✅ **Error Handling**: Graceful error handling with retry mechanism
- ✅ **Loading States**: Proper loading indicators for better UX
- ✅ **Image Loading**: Efficient image loading with Coil library

## 🏗️ Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture pattern:

```
┌─────────────────────────────────────────────────┐
│                   UI Layer                       │
│  (Jetpack Compose Screens & MainActivity)       │
└────────────────┬────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────┐
│              ViewModel Layer                     │
│         (BlogViewModel + State)                  │
└────────────────┬────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────┐
│            Repository Layer                      │
│  (BlogRepository - Single Source of Truth)       │
└────────┬───────────────────────┬────────────────┘
         │                       │
┌────────▼────────┐    ┌────────▼────────────────┐
│  Remote Data    │    │    Local Data           │
│  (Retrofit API) │    │  (Room Database)        │
└─────────────────┘    └─────────────────────────┘
```

## 🛠️ Tech Stack

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

## 📦 Project Structure

```
app/src/main/java/com/yashvant/vrid_assignment/
├── MainActivity.kt                    # Main entry point
├── data/
│   ├── model/
│   │   └── BlogPost.kt               # Data models
│   ├── local/
│   │   ├── BlogDao.kt                # Room DAO
│   │   ├── BlogDatabase.kt           # Room Database
│   │   └── Converters.kt             # Type converters
│   ├── remote/
│   │   ├── BlogApiService.kt         # Retrofit API interface
│   │   └── RetrofitInstance.kt       # Retrofit configuration
│   └── repository/
│       └── BlogRepository.kt          # Data repository
└── ui/
    ├── screens/
    │   ├── BlogListScreen.kt         # List screen with pagination
    │   └── BlogDetailScreen.kt       # Detail screen with WebView
    ├── viewmodel/
    │   └── BlogViewModel.kt          # ViewModel with state management
    └── theme/
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- JDK 11 or later
- Android SDK 24+
- Gradle 8.0+

### Installation

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd VridAssignment
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the project directory

3. **Sync Gradle**
   - Wait for Gradle to sync all dependencies

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click Run ▶️ or press Shift+F10

## 📱 App Flow

1. **Launch**: App opens with the blog list screen
2. **Browse**: User can scroll through blog posts with automatic pagination
3. **Read**: Tap on any blog to open it in a WebView
4. **Offline**: Previously loaded blogs are cached and available offline
5. **Navigate Back**: Use the back button to return to the list

## 🔑 Key Features Implementation

### 1. Pagination
- Automatically loads more blogs when user scrolls near the bottom
- Uses `LazyColumn` with scroll state monitoring
- Efficient loading with loading indicators

### 2. Offline Caching
- Room database caches all fetched blogs
- Offline-first approach: tries network first, falls back to cache
- Seamless experience even without internet

### 3. State Management
- Single source of truth with `StateFlow`
- Proper loading, success, and error states
- Reactive UI updates with Compose

### 4. Error Handling
- Network error handling with retry mechanism
- Graceful fallback to cached data
- User-friendly error messages

## 🌐 API Details

**Base URL**: `https://blog.vrid.in/`

**Endpoint**: `/wp-json/wp/v2/posts`

**Query Parameters**:
- `per_page`: Number of posts per page (default: 10)
- `page`: Page number for pagination

## 📝 Code Quality

- ✅ Clean code with proper separation of concerns
- ✅ Kotlin best practices and conventions
- ✅ Proper error handling throughout
- ✅ Efficient resource management
- ✅ Memory-efficient image loading
- ✅ Lifecycle-aware components

## 🎨 UI/UX Features

- Material 3 Design
- Responsive layouts
- Smooth animations and transitions
- Loading indicators
- Pull-to-refresh capability (can be added)
- Error state handling with retry buttons

## 🔒 Permissions

- `INTERNET`: Required for fetching blog data
- `ACCESS_NETWORK_STATE`: Check network availability

## 📄 License

This project is created as an assignment for Vrid.

## 👨‍💻 Author

Yashvant - [GitHub Profile]

## 🙏 Acknowledgments

- Vrid Blog API for providing the data
- Android Jetpack team for excellent libraries
- Material Design for beautiful components

