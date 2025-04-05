# 📚 StudentManagerApp

**StudentManagerApp** es una aplicación móvil desarrollada en **Java** utilizando **Android Studio**, diseñada para gestionar estudiantes y sus calificaciones de manera eficiente. La app implementa el patrón de arquitectura **MVC (Model-View-Controller)** y utiliza **SQLite** como base de datos local.

---

## 🧩 Tecnologías Utilizadas

- 🧠 **Java**: Lenguaje principal de desarrollo.
- 📱 **Android Studio**: Entorno de desarrollo.
- 🗃️ **SQLite**: Base de datos embebida para almacenamiento local.
- 🧱 **MVC**: Patrón de diseño para mantener una estructura clara y mantenible.

---

## ✨ Funcionalidades

### 🔹 Gestión de Estudiantes (CRUD)
- Crear nuevos estudiantes
- Visualizar lista de estudiantes
- Editar datos de un estudiante
- Eliminar estudiantes

### 🔸 Gestión de Notas (CRUD)
- Asignar notas a estudiantes
- Ver historial de calificaciones
- Modificar notas existentes
- Eliminar notas específicas
## 🗂️ Estructura del Proyecto

```plaintext
app/
│
├── manifests/
│
├── java/
│   └── com.example.ejercicio_clase_6/
│       ├── controller/
│       │   ├── EstudianteController.java
│       │   └── NotaController.java
│       │
│       ├── model/
│       │   ├── DataBaseHelper.java
│       │   ├── Estudiante.java
│       │   └── Nota.java
│       │
│       └── view/
│           ├── AgregarEstudiantesActivity.java
│           ├── DetallesEstudianteActivity.java
│           ├── EditarEstudianteActivity.java
│           ├── EditarNotaActivity.java
│           ├── EstudianteListaAdapter.java
│           ├── MainActivity.java
│           └── NotaListaAdapter.java
├── res/
│   ├── drawable/
│   ├── layout/
│   │   ├── activity_agregar_estudiantes.xml
│   │   ├── activity_detalles_estudiante.xml
│   │   ├── activity_editar_estudiante.xml
│   │   ├── activity_editar_nota.xml
│   │   ├── activity_main.xml
│   │   ├── item_estudiante.xml
│   │   └── item_notas.xml
│   │
│   ├── mipmap/
│   ├── values/
│   └── xml/
│
├── Gradle Scripts/
```
- **Model:** Clases que representan la lógica de datos y la gestión de la base de datos (SQLite).
- **View:** Interfaces gráficas que interactúan con el usuario (archivos XML).
- **Controller:** Lógica de control que conecta las vistas con los modelos.

## 👤 Autor
**Salomon Montilla Luna**  
💼 GitHub: [@salomontilla](https://github.com/salomontilla)  

---


