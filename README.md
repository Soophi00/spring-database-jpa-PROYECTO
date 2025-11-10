
# 🎯 User Subscription App

Sistema completo de **suscripción de usuarios**, desarrollado con **Spring Boot (backend)** y **React + Vite (frontend)**.
Permite **autenticación con JWT**, manejo de usuarios, y gestión de suscripciones con base de datos H2.

---

## 🧩 Tecnologías utilizadas

### 🔹 Backend

* **Java 17 / Spring Boot 3.3.6**
* Spring Security + JWT
* Spring Data JPA + Hibernate
* Base de datos **H2** (en memoria)
* Swagger UI para documentación de endpoints

### 🔹 Frontend

* **React + Vite**
* Hooks (useState)
* Fetch API
* CSS modular
* Comunicación con el backend vía REST API

---

## ⚙️ Instalación

### 📦 Clonar el proyecto

```bash
git clone https://github.com/tuusuario/user-subscription-app.git
cd user-subscription-app
```

---

## 🚀 Backend (Spring Boot)

### 📁 Ir al backend

```bash
cd user-subscription-api
```

### 🔧 Compilar y ejecutar

```bash
mvn clean package
mvn spring-boot:run
```

El servidor se iniciará en:

```
http://localhost:8080
```

---

### 🌐 Endpoints principales

| Endpoint             | Método | Descripción                             |
| -------------------- | ------ | --------------------------------------- |
| `/auth/login`        | POST   | Iniciar sesión con email y contraseña   |
| `/api/users`         | POST   | Crear nuevo usuario                     |
| `/api/users/{id}`    | GET    | Obtener usuario por ID                  |
| `/api/subscriptions` | POST   | Crear suscripción                       |
| `/swagger-ui.html`   | GET    | Abrir documentación interactiva Swagger |

---

### 🧠 Credenciales iniciales

| Usuario             | Contraseña | Rol   |
| ------------------- | ---------- | ----- |
| `admin@example.com` | `admin123` | ADMIN |
| (Usuarios nuevos)   | `user123`  | USER  |

---

### 💾 Base de datos H2

Acceso a la consola:

```
http://localhost:8080/h2-console
```

Configuración:

* **JDBC URL:** `jdbc:h2:mem:userssubscriptiondb`
* **User:** `sa`
* **Password:** *(vacío)*

---

## 💻 Frontend (React + Vite)

### 📁 Ir al frontend

```bash
cd user-subscription-frontend
```

### 📦 Instalar dependencias

```bash
npm install
```

### ▶️ Ejecutar

```bash
npm run dev
```

Abrir en el navegador:

```
http://localhost:5173/
```

---

### 🪄 Funcionalidades actuales

✅ Iniciar sesión con usuario registrado
✅ Validación y almacenamiento de token JWT
✅ Interfaz responsive y limpia
🚧 (Opcional) Registro de usuarios y vista de suscripciones futuras

---

## 📚 Estructura del proyecto

```
user-subscription-app/
│
├── user-subscription-api/         # Backend (Spring Boot)
│   ├── src/main/java/sv/edu/udb/
│   │   ├── controller/            # Controladores REST
│   │   ├── service/               # Interfaces de servicio
│   │   ├── service/impl/          # Implementaciones
│   │   ├── domain/                # Entidades (User, Subscription)
│   │   ├── security/              # JWT y Configuración de seguridad
│   │   └── configuration/         # Beans y SecurityConfig
│   └── resources/
│       └── application.properties
│
└── user-subscription-frontend/    # Frontend (React)
    ├── src/
    │   ├── pages/Login.jsx
    │   ├── api/api.js
    │   ├── App.jsx
    │   ├── main.jsx
    │   └── index.css
```

---

## 👩‍💻 Autores
  Authors
Sophia Marcela Guzman Ayala  GA231577
Marcelo jose Almendarez Ramirez AR241049

