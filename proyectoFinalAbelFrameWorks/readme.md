# 🎮 The Binding of API - Proyecto Final Spring Boot

**Alumno:** Abel Ramírez  
**Módulo:** Acceso a Datos (2º DAM)  
**Tecnologías:** Spring Boot 3, JPA, MySQL, Maven

## 📋 Descripción del Proyecto

**The Binding of API** es una aplicación Backend diseñada para gestionar la información de partidas ("runs"), personajes, objetos y enemigos inspirada en el universo del videojuego *The Binding of Isaac*.

Este proyecto implementa una **API REST completa** siguiendo una arquitectura en capas, permitiendo realizar operaciones CRUD, gestionar relaciones complejas entre entidades y realizar consultas avanzadas con paginación. Incluye un **Panel de Administración Web** (Frontend) para interactuar con la API de forma visual.

## 🚀 Tecnologías Utilizadas

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 4.0.1
* **Base de Datos:** MySQL
* **Persistencia:** Spring Data JPA (Hibernate)
* **Mapeo:** MapStruct & Lombok
* **Documentación API:** SpringDoc OpenAPI (Swagger UI)
* **Frontend:** HTML5, CSS3, JavaScript (Vanilla)

## 🗄️ Modelo de Datos (Diagrama E-R)

El sistema cuenta con más de 5 entidades persistentes con las siguientes relaciones clave:

1.  **Partida (Transaccional):** Entidad central que registra una sesión de juego.
    * Relación **N:1** con `Jugador` y `Personaje`.
    * Relación **N:M** con `Objeto` (Inventario de la partida).
    * Relación **N:M** con `Enemigo` (Enemigos derrotados en la partida).
2.  **Categoria:** Clasificación de objetos (Pasivos, Activos, Familiares).
    * Relación **1:N** con `Objeto`.
3.  **Mapa:** Representa las salas del juego.
    * Relación **N:M** con `Objeto` (Objetos encontrados en la sala).
4.  **Entidades Auxiliares:** `Jugador`, `Personaje`, `Enemigo`.

> [cite_start]**Nota:** Se utilizan DTOs (`*Request` y `*Response`) y `@JsonIgnore` para evitar la recursividad infinita en las respuestas JSON[cite: 139, 118].

## ⚙️ Configuración e Instalación

### Prerrequisitos
* JDK 21 instalado.
* MySQL Server ejecutándose.

### Pasos
1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/abel-rv/ProyectoFinalAbel.git](https://github.com/abel-rv/ProyectoFinalAbel.git)
    cd ProyectoFinalAbel
    ```

2.  **Configurar la Base de Datos:**
    Abre el archivo `src/main/resources/application.properties` y ajusta tus credenciales si son diferentes:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/proyecto
    spring.datasource.username=root
    # spring.datasource.password=tu_contraseña
    ```
    *Asegúrate de crear una base de datos vacía llamada `proyecto` en tu MySQL.*

3.  **Ejecutar la aplicación:**
    Usa el wrapper de Maven incluido:
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Carga de Datos Iniciales:**
    Al arrancar, la clase `DataInitializer.java` cargará automáticamente datos de prueba (Isaac, Azazel, objetos, partidas de ejemplo) si la base de datos está vacía.

## 🔌 Documentación de la API (Endpoints)

Una vez iniciada la aplicación, puedes consultar la documentación interactiva generada por Swagger en:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

### Endpoints Principales

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/partidas` | Listar todas las partidas |
| `GET` | `/partidas/busqueda` | **Consulta Avanzada:** Filtrar por `tipoJuego` y `estadoJugador` con paginación |
| `POST` | `/partidas` | Crear una nueva partida (Valida corazones > 0) |
| `POST` | `/partidas/{id}/objetos/{idObj}` | Añadir un objeto a una partida (Relación N:M) |
| `GET` | `/personajes` | Listar personajes jugables |
| `GET` | `/objetos` | Listar objetos paginados |
| `GET` | `/mapas` | Gestión de salas |

## 🧠 Reglas de Negocio Implementadas

La lógica de negocio se encuentra en la capa `@Service`.

* **Validación de Vitalidad:** Al crear una partida, se verifica que el personaje seleccionado no tenga 0 corazones de inicio. Si tiene 0, la API devuelve una excepción controlada: *"No se pueden tener 0 corazones"* (`PartidaService.java`).

## 🖥️ Cliente Web (Frontend)

El proyecto incluye un panel de administración visual. Para usarlo:
1.  Arranca la aplicación Spring Boot.
2.  Abre el archivo `index.html` en tu navegador o accede a `http://localhost:8080/index.html` (si está en la carpeta static).
3.  Desde este panel puedes:
    * Gestionar Partidas, Mapas y Entidades.
    * Asignar Objetos a Partidas (N:M).
    * Filtrar partidas por Estado y Dificultad.

## ✒️ Autor

**Abel Ramírez** Desarrollador Backend Java  
Proyecto Final de Ciclo - Acceso a Datos