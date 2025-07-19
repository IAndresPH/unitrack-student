## Proyecto: UniTrack - Management API

### Descripción

**UniTrack** es una API REST desarrollada en Java con Spring Boot para la gestión académica universitaria. La arquitectura se basa en los principios de **Clean Architecture** y el modelo de **Arquitectura Hexagonal (puertos y adaptadores)**, promoviendo un sistema desacoplado, mantenible y escalable.

---

### Tecnologías

* Java 21
* Spring Boot 3
* Maven
* MySQL
* JPA (Jakarta Persistence)

---

### Arquitectura por Paquetes

| Paquete                                 | Rol o responsabilidad principal                                                       |
| --------------------------------------- | ------------------------------------------------------------------------------------- |
| `domain.model`                          | Contiene los modelos puros del dominio sin dependencias externas.                     |
| `application.dto.request/response`      | Define los objetos que se usan para la comunicación con el exterior.                  |
| `application.mapper`                    | Mappers manuales entre modelos del dominio y DTOs.                                    |
| `application.port.in`                   | Interfaces de entrada utilizadas por los controladores para activar los casos de uso. |
| `application.port.out`                  | Interfaces que representan las dependencias externas requeridas por el dominio.       |
| `application.service`                   | Implementaciones de los casos de uso del negocio.                                     |
| `application.shared.constants`          | Constantes reutilizables, como mensajes de validación.                                |
| `application.shared.exception`          | Manejo global de errores y excepciones.                                               |
| `infrastructure.persistence.entity`     | Entidades JPA que representan la estructura de la base de datos.                      |
| `infrastructure.persistence.repository` | Interfaces JPA para el acceso a datos.                                                |
| `infrastructure.persistence.mapper`     | Mappers entre entidades JPA y modelos del dominio.                                    |
| `infrastructure.adapter`                | Implementaciones concretas de `port.out`.                                             |
| `infrastructure.security`               | Configuración de seguridad, filtros, tokens y gestión JWT.                            |
| `infrastructure.config`                 | Archivos de configuración general de la aplicación.                                   |
| `web.controller`                        | Endpoints HTTP expuestos al cliente.                                                  |

---

### Estructura del Proyecto

```bash
src/
├── application/
│   ├── dto/
│   │   ├── request/
│   │   └── response/
│   ├── mapper/
│   ├── port/
│   │   ├── in/
│   │   └── out/
│   ├── service/
│   └── shared/
│       ├── constants/
│       └── exception/
├── domain/
│   └── model/
├── infrastructure/
│   ├── adapter/
│   ├── config/
│   ├── jwt/
│   ├── mapper/
│   ├── persistence/
│   │   ├── entity/
│   │   └── repository/
│   └── security/
├── web/
│   └── controller/
```

---

### Relaciones entre Entidades (Base de Datos)

* **Usuario - Estudiante:** Relación uno a uno opcional.
* **Usuario - Token:** Relación uno a muchos (un usuario puede tener varios tokens).
* **Estudiante - Materia:** Relación muchos a muchos a través de una entidad intermedia (`student_subjects`) que permite registrar notas.

---

### Flujo General

1. El cliente (Postman, Angular, etc.) envía una solicitud HTTP.
2. Un controlador en `web.controller` recibe la solicitud y activa el caso de uso a través de un puerto de entrada (`port.in`).
3. El servicio ejecuta la lógica y, si necesita persistencia u otro servicio, llama a un puerto de salida (`port.out`).
4. El adaptador correspondiente en `infrastructure.adapter` implementa la interfaz `port.out` y accede a la base de datos a través de un repositorio JPA.
5. Los datos se transforman entre entidades, modelos y DTOs mediante mappers manuales.
6. El servicio retorna la respuesta al controlador, que la devuelve al cliente.

---

### Decisiones de Diseño

* **Arquitectura Hexagonal + Clean Architecture:** separación clara entre lógica de negocio y detalles técnicos.
* **Validación centralizada:** uso de anotaciones y constantes comunes para validar DTOs.
* **Mappers manuales:** mayor control del proceso de transformación sin dependencia de MapStruct.
* **Control de errores:** respuestas estandarizadas ante errores de negocio o validaciones.
* **Autenticación JWT:** con tokens persistidos, revocables y soporte para refresh tokens.

---

### Estado Actual

* [x] Gestión de usuarios, estudiantes y materias.
* [x] Asociación estudiante ↔ materias (bidireccional).
* [x] Validaciones de entrada centralizadas.
* [x] Mapeo manual DTO ↔ modelo ↔ entidad.
* [x] Autenticación con JWT y roles.
* [x] Manejador global de excepciones (`ErrorResponse`).
* [x] Control y persistencia de tokens.
* [x] Implementación de refresh tokens.

---

### Próximos Pasos

* [ ] Implementar gestión de calificaciones.
* [ ] Implementar de jwt con llave publica y privada.
* [ ] Modulo para la matricula.