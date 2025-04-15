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
## 📂 Project Structure

```plaintext
toast-catalog/
└── app/
    └── src/
        └── main/
            └── java/com/sumup/challenge/toastcatalog/
                ├── data/
                │   ├── ItemRepository.kt
                │   ├── ItemRepositoryImpl.kt
                │   └── ItemResponse.kt
                ├── di/
                │   ├── AppModule.kt
                │   └── NetworkModule.kt
                ├── network/
                │   ├── Networking.kt
                │   └── Service.kt
                ├── ui/
                │   ├── ItemDetailFragment.kt
                │   ├── ItemsActivity.kt
                │   ├── ItemsAdapter.kt
                │   ├── ItemsFragment.kt
                │   └── ItemsViewModel.kt
                ├── util/
                │   ├── Result.kt
                │   └── util.kt
                └── ToastApplication.kt

## 🔁 Features

- ✅ Displays a scrollable list of Toasts
- ✅ Each item shows:
  - Name
  - Price with currency
  - Last sold date
  - Circular icon with ID
- ✅ Loading indicator (progress bar) while image is loading

---

## 🧪 Tests

- `NetworkClient` test:
  - ✅ Successful response
  - ✅ 404 / error scenario
- ViewModel injection tested via Koin
- JSON loading covered with fixture validation

---

## ▶️ How to Run

```bash
git clone https://github.com/your-username/toast-catalog
cd toast-catalog
