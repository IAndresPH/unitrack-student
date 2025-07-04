---

## Proyecto: UniTrack - Management API

### Descripción

**UniTrack** es una API REST desarrollada en Java con Spring Boot que permite gestionar estudiantes y sus asignaturas en una universidad. Está diseñada con **arquitectura hexagonal (puertos y adaptadores)** combinada con los principios de **Clean Architecture**, para garantizar bajo acoplamiento, alta cohesión y facilidad de mantenimiento.

---

### Arquitectura General

El proyecto está dividido en capas bien definidas. A continuación, una tabla que resume los principales elementos y sus responsabilidades:

| Elemento                            | Ubicación                            | Rol                                                      |
| ----------------------------------- | ------------------------------------ | -------------------------------------------------------- |
| `Student`, `Subject`                | `domain.model`                       | Representan el negocio puro, sin dependencias.           |
| `StudentEntity`                     | `infrastructure.persistence.entity`  | Representa las tablas de la base de datos (JPA).         |
| `StudentResponse`, `StudentRequest` | `application.dto.response/request`   | DTOs que intercambian datos con el cliente.              |
| `StudentService`                    | `application.service`                | Contiene los casos de uso (reglas del negocio).          |
| `IStudentPersistencePort`           | `application.port.out`               | Define lo que el dominio necesita de la infraestructura. |
| `StudentPersistenceAdapter`         | `infrastructure.persistence.adapter` | Implementa los puertos usando la base de datos.          |
| `StudentEntityMapper`               | `infrastructure.persistence.mapper`  | Convierte entre `Student` y `StudentEntity`.             |
| `IStudentMapper`                    | `application.mapper`                 | Convierte entre `Student` y sus DTOs.                    |

---

### Estructura del Proyecto

```
src/
├── application/
│   ├── dto/
│   │   ├── request/
│   │   └── response/
│   ├── mapper/
│   ├── port/
│   │   └── out/
│   └── service/
├── domain/
│   └── model/
├── infrastructure/
│   ├── adapter/
│   ├── mapper/
│   ├── persistence/
│   │   ├── entity/
│   │   └── repository/
├── web/
│   └── controller/
```

---

### Tecnologías

* Java 21
* Spring Boot 3
* Maven
* MapStruct
* MySQL
* JPA (Jakarta Persistence)
* IntelliJ IDEA

---

### Flujo General

1. El cliente (Postman, frontend, etc.) hace una petición a un controlador (`web.controller`).
2. El controlador llama al servicio (`application.service`) para ejecutar un caso de uso.
3. El servicio usa puertos (`application.port.out`) para acceder a los datos que necesita.
4. Los adaptadores (`infrastructure.adapter`) implementan los puertos usando repositorios y entidades JPA.
5. Se usan mappers (`mapstruct`) para convertir entre modelos de dominio, entidades y DTOs.
6. La respuesta final llega al cliente en un DTO limpio y estructurado.

---

### Decisiones de Diseño

* **Separación clara de responsabilidades:** Cada capa tiene un rol único y bien definido.
* **Desacoplamiento:** El dominio no depende de la base de datos ni de frameworks externos.
* **MapStruct:** Facilita la transformación entre modelos sin lógica manual innecesaria.
* **Ports & Adapters:** Implementa la arquitectura hexagonal, lo que permite cambiar la infraestructura (base de datos, REST, etc.) sin afectar el dominio.

---

### Estado Actual

* [x] Gestión de estudiantes y materias.
* [x] Relación estudiante ↔ materias (bidireccional).
* [x] Validación básica.
* [x] MapStruct configurado para evitar recursividad y `StackOverflowError`.

---
