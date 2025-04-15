# 🍞 Toast Catalog - Android Coding Challenge

This is the solution for the **SumUp Mobile Coding Challenge** focused on building a Toast Catalog app in Android using modern development principles.

---

## ✅ Challenge Tasks Completed

### Task 1: Connect App to Backend
- ✅ Implemented `NetworkClient` using Retrofit with `HttpLoggingInterceptor`.
- ✅ Used **BuildConfig** for flexible base URL management.
- ✅ Injected dependencies via **Koin**.
- ✅ Connected to `ItemsActivity` using MVVM and LiveData.

### Task 2: Show “Last Sold” Date and Currency Format
- ✅ `lastSold` is formatted with `SimpleDateFormat` (locale-aware).
- ✅ `price` is displayed with correct currency using `NumberFormat.getCurrencyInstance()`.

### Task 3: Add Tests to NetworkClient
- ✅ Implemented unit tests for both success and failure scenarios.
- ✅ Tests use JUnit and MockWebServer.

### Task 4: Display Toast Icons
- ✅ `setIcon()` displays a circular avatar containing the Toast ID.
- ✅ Used Coil for image loading with lifecycle awareness.

---

## 🧰 Tech Stack

| Technology            | Purpose                                    |
|-----------------------|--------------------------------------------|
| Kotlin                | Programming language                       |
| MVVM Architecture     | Separation of concerns                     |
| Jetpack Navigation    | Single Activity + Fragment navigation      |
| LiveData              | UI reactivity                              |
| Coil                  | Image loading and caching                  |
| Retrofit              | HTTP client for network calls              |
| OkHttp Logging        | Debugging network requests                 |
| Koin                  | Dependency injection                       |
| JUnit                 | Unit testing network layer                 |

---

## 📸 UI Screenshot

![App Screenshot](./art/Toast_Catalog.png)

---

## 📂 Project Structure

