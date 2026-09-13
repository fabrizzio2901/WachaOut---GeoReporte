# WachaOut — GeoReporte for Android

[Español](README.md)

An Android application for recording local issues with a title, description, photo and coordinates. It serves as a local log for the person capturing the report.

## Implemented features

- Welcome screen and reports listed newest first.
- Form for saving a title and description.
- Camera app integration and photo preview.
- Location readings through `LocationManager`.
- SQLite persistence and a list showing titles, dates, coordinates and thumbnails.

Reports are stored on the device. There is no submission to authorities, server synchronization, interactive map or remote issue tracking.

## Stack and requirements

Java, XML layouts, AndroidX, Material Components and SQLite through `SQLiteOpenHelper`.

| Repository setting | Version |
|---|---|
| Minimum Android | API 24, Android 7.0 |
| Compile and target SDK | API 36 |
| Android Gradle Plugin | 8.13.1 |
| Gradle Wrapper | 8.13 |
| Java source compatibility | Java 11 |

Use Android Studio with a JDK compatible with AGP 8.13 and Android SDK API 36. Java 11 source compatibility does not specify the JDK needed to run Gradle.

## Setup and execution

```bash
git clone https://github.com/fabrizzio2901/WachaOut---GeoReporte.git
cd WachaOut---GeoReporte/GeoReporte
```

1. Open the **GeoReporte** directory in Android Studio.
2. Configure SDK API 36 and sync Gradle.
3. Select an emulator or device running Android 7.0 or later with camera and location support.
4. Run the `app` module.

Terminal build, from `GeoReporte/`:

```powershell
# Windows PowerShell
.\gradlew.bat assembleDebug
```

```bash
# macOS / Linux
bash gradlew assembleDebug
```

A successful build writes the debug APK to `app/build/outputs/apk/debug/app-debug.apk`. No API key or external database service is required.

## Example

Open the app, enter the list, tap the add button and enter **Test streetlight** with a fictional description. Take a photo without identifiable people or information. Wait for coordinates to appear and save. The report should appear in the list.

Use an emulator's simulated location for a recorded demonstration.

## Structure

Files under `GeoReporte/app/src/main/java/com/example/georeporte/`:

- `BienvenidaActivity.java`: application entry screen.
- `MainActivity.java`: SQLite query and report list.
- `NuevoReporteActivity.java`: form, camera, location and persistence.
- `AdminSQLiteOpenHelper.java`: `reportes` table.
- `Reporte.java` and `ReporteAdapter.java`: data model and list rendering.

## Status and limitations

Code, manifest and configuration were reviewed. On September 11, 2026, `assembleDebug --offline --no-daemon` completed successfully with Gradle 8.13 and the installed SDK API 36, producing the debug APK. The build reported deprecated API usage in `NuevoReporteActivity`. The application was not tested on a device. Included tests are basic template examples and do not validate the main workflows.

Pending work identified in the code:

- Validate the title and location before saving; coordinates can currently be saved as `0,0`.
- Complete camera/location permission handling and resume capture after permission is granted.
- Stop location updates when leaving the screen.
- Replace the table deletion in `onUpgrade` with migrations that preserve reports.
- Add screenshots and tests covering creation, persistence and permissions.

## Team

Credits retained from the repository:

- Luis Fabrizzio Ramírez Romero.
- Diego Rosales Benítez.
- David Benítez Muñoz.

The described scope belongs to the team project. My role covered application development across the interface, logic and local persistence. This contribution reflects my full-stack approach; all three team members retain credit.
