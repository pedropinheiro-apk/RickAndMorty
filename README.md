# Rick and Morty App

An Android application built with **Kotlin** and following **clean architecture principles**, leveraging **best practices** such as a multi-layered architecture (**data**, **domain**, **presentation**), **MVVM**, and **Dependency Injection** with **Hilt**.  
The app consumes the [Rick and Morty API](https://rickandmortyapi.com/), displaying characters with **pagination**, **offline caching**, and a **favorites management** system — all implemented with a focus on **scalability**, **testability**, and **maintainability**.
## ✨ Features

- 📄 **Character listing** with automatic pagination using [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview).  
- 🔄 **Offline cache support** powered by Room and `RemoteMediator`.  
- ❤️ **Favorites screen** to view only favorited characters.  
- 🎨 Modern **Jetpack Compose UI** with Material 3.  
- ⚡ Optimized image loading using Coil.  
- ✅ Dependency injection and state management with Hilt.  

---

## 🛠️ Tech Stack

- [Kotlin](https://kotlinlang.org/)  
- [Jetpack Compose](https://developer.android.com/jetpack/compose)  
- [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)  
- [Room](https://developer.android.com/jetpack/androidx/releases/room)  
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)  
- [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/)  
- [Coil](https://coil-kt.github.io/coil/compose/) for image loading  

---

## 🗂️ Project Structure

```plaintext
app/
 ├── data/                 # Data layer: API services, Room database, DAOs, RemoteMediator
 │    ├── local/           # Local persistence (Room entities, DAOs)
 │    ├── remote/          # Network layer (Retrofit services, DTOs)
 │    └── repository/      # Repository implementations combining local and remote data
 │
 ├── domain/               # Domain layer: models and repository interfaces
 │
 ├── features/             # Feature-based UI modules/screens
 │    ├── home/            # Home screen
 │    ├── favorites/       # Favorites screen
 │    └── details/         # Character details screen (future)
 │
 ├── di/                   # Dependency Injection setup using Hilt
 │
 ├── utils/                # Utility classes, extensions, and helpers
 │
 └── MainActivity.kt       # Application entry point
```

## ✅ Roadmap

- [x] Pagination with Paging 3  
- [x] Offline caching  
- [x] Favorites feature  
- [X] Character detail screen  
- [ ] Unit and UI tests  
- [ ] CI/CD with GitHub Actions  
