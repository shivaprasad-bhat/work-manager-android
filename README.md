# Android Work Manager - Notify

## About the app

> This application is developed to sending the notification periodically using android's WorkManager. Irrespective of the state (open / closed) the application sends a notification to the user. Every 16th minute, user will get a notification

## Dependencies used in app

-   WorkManager
-   Timber (for logging)

## Steps to Build the App

-   Clone the project from github
-   Open the project in Android Studio
-   Click the dropdown menu in the toolbar at the top (Open 'Edit Run/Debug configurations' dialog)
-   Select "Edit Configurations"
-   Click the "+" Button
-   Select "Gradle"
-   Choose your module as a Gradle project
-   In Tasks: enter assemble
-   Press Run

Your debug apk will be generated at `<Project Name>/app/build/outputs/apk/debug/`

## Setting Periodic Work Request

```kotlin
val worker = PeriodicWorkRequest.Builder(NotifyWorker::class.java, 15, TimeUnit.MINUTES)
                .build()

WorkManager.getInstance(applicationContext).enqueue(worker)
```

