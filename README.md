## Proyecto: UniTrack - Management API

### Descripción

**UniTrack** es una API REST desarrollada en Java con Spring Boot que permite gestionar actividades en una universidad. Está diseñada siguiendo la **arquitectura hexagonal (puertos y adaptadores)** combinada con los principios de **Clean Architecture**, para garantizar bajo acoplamiento, alta cohesión y facilidad de mantenimiento.

---

### Tecnologías

* Java 21
* Spring Boot 3
* Maven
* MySQL
* JPA (Jakarta Persistence)
* MapStruct

---

### Arquitectura por Paquetes

| Paquete                                 | Rol o responsabilidad principal                                                              |
| --------------------------------------- | -------------------------------------------------------------------------------------------- |
| `domain.model`                          | Contiene los modelos del dominio (entidades puras del negocio) sin dependencias externas.    |
| `application.dto.request/response`      | Define los objetos que intercambian datos con el exterior (cliente).                         |
| `application.service`                   | Implementa los casos de uso del negocio (lógica de aplicación).                              |
| `application.mapper`                    | Transforma entre entidades del dominio y los DTOs.                                           |
| `application.port.in`                   | Define las interfaces de entrada que los controladores usan para activar casos de uso.       |
| `application.port.out`                  | Define lo que el dominio necesita de la infraestructura (por ejemplo, acceder a la BD).      |
| `infrastructure.persistence.entity`     | Contiene las entidades JPA que representan las tablas de la base de datos.                   |
| `infrastructure.persistence.repository` | Interfaces de acceso a datos (repositorios JPA estándar).                                    |
| `infrastructure.persistence.mapper`     | Mappers que convierten entre entidades JPA y modelos del dominio.                            |
| `infrastructure.adapter`                | Adaptadores concretos que implementan los puertos de salida (`port.out`) accediendo a datos. |
| `web.controller`                        | Define los endpoints expuestos al cliente (capa HTTP).                                       |

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

### Flujo General

1. El cliente (Postman, frontend, etc.) hace una petición al controlador (`web.controller`).
2. El controlador invoca el caso de uso definido en `application.service` a través de una interfaz de `port.in`.
3. El servicio utiliza interfaces de `port.out` para solicitar acceso a datos o infraestructura externa.
4. Estas interfaces son implementadas por adaptadores en `infrastructure.adapter`, que se apoyan en repositorios JPA.
5. Se utilizan mappers para convertir entre:

    * Entidades JPA ↔ Modelos del dominio.
    * Modelos del dominio ↔ DTOs.
6. El servicio retorna una respuesta limpia y estructurada al cliente.

---

### Decisiones de Diseño

* **Separación de capas:** cada paquete tiene una única responsabilidad clara.
* **Desacoplamiento:** la lógica del dominio no depende de detalles técnicos (como Spring, JPA o controladores).
* **MapStruct:** mapeo automático entre entidades y modelos/DTOs sin código repetitivo.
* **Arquitectura hexagonal:** permite reemplazar la infraestructura sin afectar el negocio.

---

### Estado Actual

* [x] Gestión de estudiantes y materias.
* [x] Relación estudiante ↔ materias (bidireccional).
* [x] Validaciones básicas de entrada.
* [x] MapStruct configurado para evitar `StackOverflowError` por recursividad.

---