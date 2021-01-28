# ULessonTask
Allows users interact with educational content.

This Android application pulls subject/media content information from an API, and display them accurately in a user interface.

## Libraries Used

[Exoplayer](https://exoplayer.dev/) - For playing media (videos).

[Room](https://developer.android.com/training/data-storage/room?gclid=Cj0KCQiAjKqABhDLARIsABbJrGlhX314LEn435ZIr0Rky-1UW7anoWLcB9xCQyoS01GPHp1W5MUjtrYaAs12EALw_wcB&gclsrc=aw.ds) - Local database used to implement offline-first feature.  
When a user opens the app for the first time, data is fetched from the server and then saved to the local database.  
Subsequently, data is fetched from the local database. However, the user needs internet connection to play videos.  
The database has a table which contains all content - subjects, chapters, and lessons.

The app was structured using [MVVM](https://developer.android.com/jetpack/guide#recommended-app-arch), for proper separation of concerns.
