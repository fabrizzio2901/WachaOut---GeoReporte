# WachaOut (GeoReporte)

## Descripción
**WachaOut** (originalmente concebido como *GeoReporte*) es una aplicación móvil nativa para Android enfocada en el reporte ciudadano. Permite a los usuarios registrar incidencias o problemas en su entorno, adjuntando evidencia fotográfica y capturando de manera automática la ubicación exacta (coordenadas GPS) del evento. Toda la información se gestiona localmente en el dispositivo.

## Características Principales
* **Registro de Incidencias:** Creación de reportes mediante formularios que incluyen título y descripción detallada del problema.
* **Evidencia Fotográfica:** Integración nativa con la cámara del dispositivo para tomar fotografías que respalden el reporte.
* **Geolocalización Automática:** Captura automática de la latitud y longitud a través del GPS del dispositivo en el momento de crear el reporte.
* **Historial Interactivo:** Visualización de todos los reportes generados en una lista (RecyclerView), mostrando miniaturas de imágenes, fechas y coordenadas.
* **Almacenamiento Local (Offline):** Persistencia de datos sin necesidad de conexión a internet mediante una base de datos SQLite.

## Tecnologías y Herramientas
* **Lenguaje:** Java
* **Base de Datos:** SQLite (`AdminSQLiteOpenHelper`)
* **Interfaz de Usuario:** XML, RecyclerView, CardView, Material Design
* **SDK de Android:** API Mínima 24 / API Objetivo 36
* **Arquitectura:** Monolítica / Actividades Independientes

## Permisos Requeridos
Para el correcto funcionamiento de las funciones principales, la aplicación solicita en tiempo de ejecución los siguientes permisos:
* `android.permission.CAMERA`: Necesario para abrir la cámara y guardar la foto en el almacenamiento.
* `android.permission.ACCESS_FINE_LOCATION` y `ACCESS_COARSE_LOCATION`: Necesarios para leer las coordenadas GPS con alta precisión.

## Estructura de la Base de Datos
El sistema utiliza una tabla local denominada `reportes` con el siguiente esquema:
* `id` (INTEGER PRIMARY KEY AUTOINCREMENT)
* `titulo` (TEXT NOT NULL)
* `descripcion` (TEXT)
* `latitud` (REAL NOT NULL)
* `longitud` (REAL NOT NULL)
* `foto_uri` (TEXT)
* `fecha` (TEXT DEFAULT CURRENT_TIMESTAMP)

## Instalación y Uso
1. Clona este repositorio en tu máquina local.
2. Abre el proyecto utilizando **Android Studio**.
3. Sincroniza el proyecto con los archivos de Gradle (`build.gradle.kts`).
4. Compila y ejecuta la aplicación en un emulador o en un dispositivo físico con Android 7.0 (API 24) o superior.

## Equipo de Desarrollo
* Luis Fabrizzio Ramírez Romero
* Diego Rosales Benítez
* David Benítez Muñoz
