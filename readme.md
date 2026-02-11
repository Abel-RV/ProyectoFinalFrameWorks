# Proyecto Final Frameworks - Gestión de Juego RPG

Este repositorio contiene el proyecto final para la asignatura de Frameworks. Es una aplicación **Full Stack** diseñada para la gestión de recursos de un videojuego de rol (RPG), permitiendo administrar jugadores, personajes, enemigos, objetos, mapas y partidas.

El sistema está compuesto por una API REST segura construida con **Spring Boot** y una interfaz de usuario moderna desarrollada en **React**.

## 🚀 Tecnologías Utilizadas

### Backend (API REST)
Ubicado en el directorio `proyectoFinalAbelFrameWorks/`.
* **Java 21**
* **Spring Boot 3**: Framework principal.
    * **Spring Security**: Autenticación y autorización mediante **JWT** (JSON Web Tokens).
    * **Spring Data JPA**: Persistencia de datos y ORM (Hibernate).
    * **Spring Validation**: Validación de entradas de datos.
* **MySQL**: Base de datos relacional.
* **MapStruct**: Mapeo eficiente entre Entidades y DTOs.
* **Lombok**: Reducción de código repetitivo.
* **OpenAPI (Swagger)**: Documentación automática de la API.

### Frontend (Cliente Web)
Ubicado en el directorio `proyectoReactAbel/`.
* **React 19**: Biblioteca para construir la interfaz de usuario.
* **Vite**: Entorno de desarrollo de nueva generación.
* **Tailwind CSS 4**: Framework de diseño "utility-first".
* **Axios / Fetch**: Consumo de la API REST (implementado en servicios).

## 📂 Estructura del Proyecto

El repositorio se organiza en dos carpetas principales:

* `/proyectoFinalAbelFrameWorks`: Código fuente del servidor (Backend).
* `/proyectoReactAbel`: Código fuente del cliente (Frontend).

## 📋 Requisitos Previos

Asegúrate de tener instalado en tu entorno:
* Java JDK 21
* Node.js (v18 o superior) y npm
* MySQL Server
* Maven (opcional, el proyecto incluye `mvnw`)

## ⚙️ Instalación y Configuración

### 1. Configuración de la Base de Datos
Crea una base de datos en MySQL llamada `proyectoFrameworks`.

```sql
CREATE DATABASE proyectoFrameworks;