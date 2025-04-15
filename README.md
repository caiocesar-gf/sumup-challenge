# Toast Catalog - Android Coding Challenge

This is the solution for the **SumUp Mobile Coding Challenge**, focused on building a Toast Catalog app for Android using modern development principles.

---

## Challenge Tasks Completed

### Task 1: Connect App to Backend
- Implemented `NetworkClient` using Retrofit and `HttpLoggingInterceptor`
- Used `BuildConfig` for flexible base URL management
- Injected dependencies via Koin
- Connected to `ItemsActivity` using MVVM and LiveData

### Task 2: Show “Last Sold” Date and Currency Format
- `lastSold` is formatted using `SimpleDateFormat` with locale awareness
- `price` is displayed using `NumberFormat.getCurrencyInstance()` for correct currency formatting

### Task 3: Add Tests to NetworkClient
- Unit tests implemented for both success and failure scenarios
- Used JUnit and MockWebServer

---

## Features

- Displays a scrollable list of Toasts
- Each item shows:
  - Name
  - Price with correct currency symbol
  - Last sold date
- Loading indicator (progress bar) while image loads
- ViewModel uses `launchFromNetwork` extension for clean async handling
- Network layer wraps responses using the `Result` sealed class

---

## Tech Stack

| Technology            | Purpose                                     |
|-----------------------|---------------------------------------------|
| Kotlin                | Programming language                        |
| MVVM Architecture     | Separation of concerns                      |
| Jetpack Navigation    | Single Activity with Fragment navigation    |
| LiveData              | UI reactivity                               |
| Coil                  | Image loading and caching                   |
| Retrofit              | HTTP client for API calls                   |
| OkHttp Logging        | Network call debugging                      |
| Koin                  | Dependency injection                        |
| JUnit                 | Unit testing                                |

---

## Project Structure

## Project Structure

```plaintext
toast-catalog/
└── app/
    ├── build.gradle.kts
    └── src/
        └── main/
            ├── AndroidManifest.xml
            ├── java/
            │   └── com/sumup/challenge/toastcatalog/
            │       ├── data/
            │       │   ├── ItemRepository.kt
            │       │   ├── ItemRepositoryImpl.kt
            │       │   └── ItemResponse.kt
            │       ├── di/
            │       │   ├── AppModule.kt
            │       │   └── NetworkModule.kt
            │       ├── network/
            │       │   ├── Networking.kt
            │       │   └── Service.kt
            │       ├── ui/
            │       │   ├── adapter/
            │       │   │   └── ItemsAdapter.kt
            │       │   ├── view/
            │       │   │   ├── ItemsActivity.kt
            │       │   │   ├── ItemsFragment.kt
            │       │   │   └── ItemDetailFragment.kt
            │       │   └── viewmodel/
            │       │       └── ItemsViewModel.kt
            │       ├── util/
            │       │   ├── Result.kt
            │       │   └── util.kt
            │       └── ToastApplication.kt


## Key Classes

### Result.kt

A sealed class used to represent UI and network response states:

```kotlin
sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}

fun <T> ViewModel.launchFromNetwork(
    request: suspend () -> T,
    onResult: (Result<T>) -> Unit
) {
    viewModelScope.launch {
        onResult(Result.Loading)
        try {
            val result = request()
            onResult(Result.Success(result))
        } catch (e: Exception) {
            onResult(Result.Error(e))
        }
    }
}
