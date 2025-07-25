# User Info Task - Android Application

## Project Overview

This is a professional Android application built using Kotlin that demonstrates Clean Architecture principles, MVVM pattern, and modern Android development practices. The app allows users to input personal information and view a list of all stored users.

### Features

- **User Input Screen**: Form to enter name, age, job title, and gender
- **User Display Screen**: List view showing all stored users
- **Data Validation**: Input validation with user-friendly error messages
- **Local Storage**: Room database for persistent data storage
- **Modern UI**: Material Design components with clean, professional appearance

## Architecture

This application follows **Clean Architecture** principles with a clear separation of concerns across three main layers:

### 1. Domain Layer (`domain` package)
- **Models**: Core business entities (`User.kt`)
- **Repository Interfaces**: Contracts for data operations (`UserRepository.kt`)
- **Use Cases**: Business logic encapsulation (`InsertUserUseCase.kt`, `GetAllUsersUseCase.kt`)

### 2. Data Layer (`data` package)
- **Entities**: Room database entities (`UserEntity.kt`)
- **DAO**: Data Access Objects for database operations (`UserDao.kt`)
- **Database**: Room database configuration (`AppDatabase.kt`)
- **Repository Implementation**: Concrete implementation of repository interfaces (`UserRepositoryImpl.kt`)

### 3. Presentation Layer (`presentation` package)
- **Fragments**: UI components (`InputFragment.kt`, `DisplayFragment.kt`)
- **ViewModels**: Business logic for UI (`InputViewModel.kt`, `DisplayViewModel.kt`)
- **Adapters**: RecyclerView adapters (`UserAdapter.kt`)

### Design Patterns Used

1. **MVVM (Model-View-ViewModel)**: Separation of UI logic from business logic
2. **Repository Pattern**: Abstraction layer for data operations
3. **Use Case Pattern**: Encapsulation of business rules
4. **Dependency Injection**: Hilt for managing dependencies
5. **Observer Pattern**: LiveData and StateFlow for reactive programming
6. **Factory Pattern**: Room database creation
7. **Singleton Pattern**: Database instance management

## Technologies Used

### Core Technologies
- **Kotlin**: Primary programming language
- **Android SDK**: Target API 35, Minimum API 21

### Architecture Components
- **Room Database**: Local data persistence
- **Manual Dependency Injection**: Custom DI container for managing dependencies
- **Navigation Component**: Fragment-based navigation
- **ViewModel & StateFlow**: MVVM pattern implementation
- **ViewBinding**: Safe view access

### UI Components
- **Material Design 3**: Modern UI components with Light theme
- **RecyclerView**: Efficient list display with ListAdapter
- **ConstraintLayout**: Flexible layout system
- **TextInputLayout**: Enhanced input fields with validation

### Reactive Programming
- **Kotlin Coroutines**: Asynchronous programming
- **StateFlow**: Reactive state management
- **Flow**: Stream processing

## Project Structure

```
app/src/main/java/com/example/userinfotask/
├── App.kt                          # Hilt Application class
├── MainActivity.kt                 # Single activity hosting fragments
├── data/                          # Data layer
│   ├── dao/
│   │   └── UserDao.kt
│   ├── database/
│   │   └── AppDatabase.kt
│   ├── entity/
│   │   └── UserEntity.kt
│   └── repository/
│       └── UserRepositoryImpl.kt
├── di/                            # Dependency injection
│   ├── DatabaseModule.kt
│   └── RepositoryModule.kt
├── domain/                        # Domain layer
│   ├── model/
│   │   └── User.kt
│   ├── repository/
│   │   └── UserRepository.kt
│   └── usecase/
│       ├── GetAllUsersUseCase.kt
│       └── InsertUserUseCase.kt
├── navigation/                    # Navigation
│   └── nav_graph.xml
└── presentation/                  # Presentation layer
    ├── adapter/
    │   └── UserAdapter.kt
    ├── fragment/
    │   ├── DisplayFragment.kt
    │   └── InputFragment.kt
    └── viewmodel/
        ├── DisplayViewModel.kt
        └── InputViewModel.kt
```

## SOLID Principles Implementation

### Single Responsibility Principle (SRP)
- Each class has a single, well-defined responsibility
- Use cases handle specific business operations
- ViewModels manage UI state only

### Open/Closed Principle (OCP)
- Repository interfaces allow for different implementations
- Use cases can be extended without modification

### Liskov Substitution Principle (LSP)
- Repository implementations can be substituted without affecting the domain layer

### Interface Segregation Principle (ISP)
- Repository interfaces contain only necessary methods
- Clean separation between different concerns

### Dependency Inversion Principle (DIP)
- Domain layer depends on abstractions (repository interfaces)
- Concrete implementations are injected via Hilt

## Clean Code Practices

1. **Meaningful Names**: Clear, descriptive variable and function names
2. **Small Functions**: Functions with single responsibilities
3. **Comments**: Comprehensive documentation for complex logic
4. **Error Handling**: Proper exception handling with user-friendly messages
5. **Consistent Formatting**: Following Kotlin coding conventions
6. **Separation of Concerns**: Clear boundaries between layers

## Instructions to Run

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 21 or higher
- Kotlin 2.0.21 or higher

### Setup Steps

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd userinfotask
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Open the project folder
   - Wait for Gradle sync to complete

3. **Build and Run**
   - Connect an Android device or start an emulator
   - Click the "Run" button (green play icon)
   - Select your target device
   - Wait for the app to install and launch

### Alternative: Command Line Build
```bash
# Build the debug APK
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

### Testing the Application

1. **Input Screen**
   - Enter a name (e.g., "John Doe")
   - Enter an age (e.g., "25")
   - Enter a job title (e.g., "Software Engineer")
   - Select gender (Male/Female)
   - Click "Save User"

2. **Display Screen**
   - After saving, you'll be automatically navigated to the display screen
   - View the list of all saved users
   - Use the back button to return to input screen

3. **Validation Testing**
   - Try submitting empty fields to see validation messages
   - Enter invalid age values to test input validation

## Key Features for Senior Developers

### Advanced Architecture Patterns
- **Clean Architecture**: Clear separation of concerns
- **SOLID Principles**: Proper object-oriented design
- **Dependency Injection**: Hilt for testable, maintainable code

### Modern Android Development
- **Kotlin Coroutines**: Asynchronous programming
- **StateFlow**: Reactive state management
- **ViewBinding**: Type-safe view access
- **Navigation Component**: Fragment-based navigation
- **Manual DI**: Custom dependency injection container

### Database Design
- **Room Database**: Type-safe SQLite abstraction
- **Entity Relationships**: Proper data modeling
- **Migration Support**: Database version management

### Code Quality
- **Comprehensive Comments**: Every class and method documented
- **Error Handling**: Robust exception management
- **Testing Ready**: Architecture supports unit testing
- **Maintainable Code**: Clear structure and naming conventions

## Contact Information

**Developer**: Senior Android Developer  
**Email**: [Your Email]  
**LinkedIn**: [Your LinkedIn]  
**GitHub**: [Your GitHub]

---

*This project demonstrates professional Android development practices suitable for senior-level positions. The codebase is production-ready and follows industry best practices for maintainability, scalability, and testability.* 