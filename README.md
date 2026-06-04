# Gramma Vaxi - Authentic Rural Living

Gramma Vaxi is a village tourism and farm-stay platform designed to bridge the gap between urban travelers and authentic rural experiences. The application allows users to discover local farm stays, understand rural etiquette through a cultural guide, and provides resources for hosts to prepare their homes for guests.

## Core Features

*   **Farm Stay Discovery**: Explore a curated list of authentic rural homestays with detailed descriptions of activities and amenities.
*   **Booking Management**: Integrated booking flow allowing users to reserve stays (currently running in a simulated offline mode for reliability).
*   **Host Readiness Program**: A dedicated module for village hosts to understand quality standards and prepare their spaces for guests.
*   **Cultural Guide**: An interactive guide to help visitors navigate local village etiquette and cultural nuances.
*   **Modern UI/UX**: Built entirely with Jetpack Compose, featuring a clean Material 3 design with smooth transitions and responsive layouts.

## Tech Stack & Dependencies

*   **Language**: Kotlin
*   **UI Framework**: Jetpack Compose (Material 3)
*   **Architecture**: MVVM (Model-View-ViewModel) with Repository Pattern
*   **Image Loading**: Coil (Compose)
*   **Navigation**: Navigation Compose
*   **Concurrency**: Kotlin Coroutines & Flow
*   **Minimum SDK**: API 24 (Android 7.0)
*   **Target SDK**: API 34 (Android 14.0)

*Note: This project is configured for offline/mock execution. All data is served from local repositories to ensure seamless evaluation without external database dependencies or API keys.*

## Project Structure

```text
com.gramavasathi.app
├── data
│   ├── model       # Data classes (FarmStay, Booking, etc.)
│   └── repository  # Data logic and simulated data sources
├── ui
│   ├── screens     # Individual screen Composables (Home, Listing, Booking, etc.)
│   └── theme       # Material 3 theme and color configurations
└── MainActivity.kt # Entry point and Navigation Host
```

## Setup & Installation

Follow these steps to get the project running on your local machine:

1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/ShettyAman/Grama-Vasathi.git
    ```
2.  **Open in Android Studio**:
    *   Launch Android Studio (Ladybug or newer recommended).
    *   Select **File > Open** and navigate to the cloned `GramaVasathi` directory.
3.  **Sync Project**:
    *   Wait for the IDE to finish the Gradle Sync process.
    *   Ensure you have the Android SDK 34 installed.
4.  **Run the App**:
    *   Connect a physical device or start an emulator (API 24+).
    *   Click the **Run** button (green play icon) in the toolbar.
5.  **Build via Command Line** (Optional):
    ```bash
    ./gradlew assembleDebug
    ```
