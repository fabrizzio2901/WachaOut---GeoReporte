# WachaOut — GeoReporte para Android

[English](README.en.md)

Aplicación Android para registrar incidencias del entorno con título, descripción, fotografía y coordenadas. Está pensada como una bitácora local para la persona que captura el reporte.

## Funciones implementadas

- Pantalla de bienvenida y listado de reportes, ordenados del más reciente al más antiguo.
- Formulario para guardar título y descripción.
- Apertura de la aplicación de cámara y previsualización de la fotografía.
- Lectura de ubicación mediante `LocationManager`.
- Persistencia en SQLite y visualización de título, fecha, coordenadas y miniatura.

Los reportes se almacenan en el dispositivo. No hay envío a autoridades, sincronización con un servidor, mapa interactivo ni seguimiento remoto de incidencias.

## Tecnologías y requisitos

Java, layouts XML, AndroidX, Material Components y SQLite mediante `SQLiteOpenHelper`.

La configuración del repositorio declara:

| Componente | Versión |
|---|---|
| Android mínimo | API 24, Android 7.0 |
| SDK de compilación y objetivo | API 36 |
| Android Gradle Plugin | 8.13.1 |
| Gradle Wrapper | 8.13 |
| Compatibilidad del código Java | Java 11 |

Utiliza Android Studio con un JDK compatible con AGP 8.13 y el SDK API 36 instalado. La compatibilidad Java 11 del código no indica la versión de JDK requerida para ejecutar Gradle.

## Instalación y ejecución

```bash
git clone https://github.com/fabrizzio2901/WachaOut---GeoReporte.git
cd WachaOut---GeoReporte/GeoReporte
```

1. Abre la carpeta **GeoReporte** en Android Studio.
2. Configura el SDK API 36 y sincroniza Gradle.
3. Selecciona un emulador o dispositivo con Android 7.0 o posterior, cámara y ubicación.
4. Ejecuta el módulo `app`.

Compilación por terminal, desde `GeoReporte/`:

```powershell
# Windows PowerShell
.\gradlew.bat assembleDebug
```

```bash
# macOS / Linux
bash gradlew assembleDebug
```

El APK de depuración, si la compilación termina correctamente, queda en `app/build/outputs/apk/debug/app-debug.apk`. No se necesita una clave de API ni un servicio de base de datos externo.

## Ejemplo de uso

Abre la app, entra al listado, pulsa el botón para agregar un reporte, escribe **Luminaria de prueba**, agrega una descripción ficticia y toma una foto sin personas ni datos identificables. Espera a que aparezcan las coordenadas y guarda. El reporte debe aparecer al volver al listado.

Para una demostración grabada, utiliza una ubicación simulada del emulador.

## Organización

- `BienvenidaActivity.java`: entrada a la aplicación.
- `MainActivity.java`: consulta de SQLite y listado.
- `NuevoReporteActivity.java`: formulario, cámara, ubicación y guardado.
- `AdminSQLiteOpenHelper.java`: tabla `reportes`.
- `Reporte.java` y `ReporteAdapter.java`: modelo y presentación de elementos.

Estos archivos están en `GeoReporte/app/src/main/java/com/example/georeporte/`.

## Estado y limitaciones

La revisión verificó código, manifiesto y configuración. El 11 de septiembre de 2026, `assembleDebug --offline --no-daemon` terminó correctamente con Gradle 8.13 y el SDK API 36 instalado, y generó el APK de depuración. La compilación avisó del uso de una API obsoleta en `NuevoReporteActivity`. No se probó la aplicación en un dispositivo. Los tests incluidos son ejemplos básicos de la plantilla y no validan los flujos principales.

Pendientes identificados en el código:

- Validar el título y comprobar que exista una ubicación válida antes de guardar; actualmente puede persistir `0,0`.
- Completar el manejo de permisos de cámara y ubicación y reanudar la captura al concederlos.
- Detener las actualizaciones de ubicación al salir de la pantalla.
- Sustituir la eliminación de la tabla en `onUpgrade` por migraciones que conserven reportes.
- Añadir capturas y pruebas de creación, persistencia y permisos.

## Equipo

Créditos conservados del repositorio:

- Luis Fabrizzio Ramírez Romero.
- Diego Rosales Benítez.
- David Benítez Muñoz.

El alcance descrito corresponde al proyecto del equipo. Mi rol fue de desarrollo integral de la aplicación: interfaz, lógica y persistencia local. Esta participación se enmarca en mi enfoque full stack; los créditos corresponden a los tres integrantes.
