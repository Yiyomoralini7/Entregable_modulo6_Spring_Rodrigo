# 🍞 Panadería Rodrigo

Sistema web de gestión interna para una panadería artesanal, desarrollado como proyecto académico del **Módulo 6 — Bootcamp**.

---

## 📋 Descripción

Aplicación full-stack construida con **Spring Boot** que permite gestionar productos, clientes y pedidos de una panadería. Incluye autenticación con dos roles diferenciados, API REST, y una interfaz web con Thymeleaf.

---

## 🚀 Tecnologías

| Capa | Tecnología |
|------|------------|
| Backend | Java 17 · Spring Boot 3.2.5 |
| Persistencia | Spring Data JPA · Hibernate |
| Base de datos | H2 (desarrollo) · MySQL (producción) |
| Seguridad | Spring Security 6 |
| Frontend | Thymeleaf · CSS personalizado |
| Build | Maven |
| Utilidades | Lombok |

---

## ⚙️ Requisitos previos

- **Java 17** (JDK 17 LTS)
- **Maven 3.8+**
- IntelliJ IDEA (recomendado) o cualquier IDE con soporte Maven

---

## ▶️ Cómo ejecutar

```bash
# 1. Clonar el repositorio
git clone https://github.com/tu-usuario/panaderia-rodrigo.git
cd panaderia-rodrigo

# 2. Ejecutar con Maven
./mvnw spring-boot:run
```

O desde IntelliJ: botón ▶ sobre `RodrigoApplication.java`

La aplicación arranca en: **http://localhost:8080**

---

## 🔐 Usuarios de prueba

| Rol | Usuario | Contraseña | Permisos |
|-----|---------|-----------|----------|
| ADMIN | `rodrigo` | `admin123` | Acceso total |
| EMPLEADO | `empleado` | `emp123` | Ver y gestionar pedidos/clientes |

---

## 🗄️ Consola H2 (base de datos en desarrollo)

Acceder en: **http://localhost:8080/h2-console**

| Campo | Valor |
|-------|-------|
| JDBC URL | `jdbc:h2:mem:panaderiadb` |
| Usuario | `sa` |
| Contraseña | *(vacía)* |

---

## 📁 Estructura del proyecto

```
src/
└── main/
    ├── java/com/panaderia/rodrigo/
    │   ├── RodrigoApplication.java       ← Clase principal
    │   ├── config/
    │   │   └── SecurityConfig.java       ← Configuración de seguridad
    │   ├── controller/                   ← Controladores MVC
    │   │   ├── HomeController.java
    │   │   ├── ProductoController.java
    │   │   ├── ClienteController.java
    │   │   └── PedidoController.java
    │   ├── rest/                         ← Controladores REST
    │   │   ├── ProductoRestController.java
    │   │   ├── ClienteRestController.java
    │   │   └── PedidoRestController.java
    │   ├── service/                      ← Capa de negocio
    │   │   ├── ProductoService.java
    │   │   ├── ClienteService.java
    │   │   └── PedidoService.java
    │   ├── repository/                   ← Repositorios JPA
    │   │   ├── ProductoRepository.java
    │   │   ├── ClienteRepository.java
    │   │   └── PedidoRepository.java
    │   ├── model/                        ← Entidades JPA
    │   │   ├── Producto.java
    │   │   ├── Cliente.java
    │   │   └── Pedido.java
    │   └── loader/
    │       └── DataLoader.java           ← Datos de prueba iniciales
    └── resources/
        ├── application.properties
        ├── static/css/style.css
        └── templates/                    ← Vistas Thymeleaf
            ├── index.html
            ├── login.html
            ├── layout/base.html
            ├── productos/
            ├── clientes/
            └── pedidos/
```

---

## 🌐 Vistas disponibles

| URL | Descripción | Acceso |
|-----|-------------|--------|
| `/` | Dashboard principal con estadísticas | Público |
| `/login` | Inicio de sesión | Público |
| `/productos` | Catálogo con filtros por categoría | Público |
| `/productos/nuevo` | Crear producto | Solo ADMIN |
| `/clientes` | Listado de clientes con búsqueda | ADMIN / EMPLEADO |
| `/pedidos` | Gestión de pedidos con cambio de estado | ADMIN / EMPLEADO |

---

## 🔌 API REST

### Productos

| Método | Endpoint | Descripción | Acceso |
|--------|----------|-------------|--------|
| GET | `/api/productos` | Listar todos (filtro `?categoria=`) | Público |
| GET | `/api/productos/{id}` | Obtener por ID | Público |
| POST | `/api/productos` | Crear producto | ADMIN |
| PUT | `/api/productos/{id}` | Actualizar producto | ADMIN |
| DELETE | `/api/productos/{id}` | Eliminar producto | ADMIN |
| GET | `/api/productos/stock-bajo` | Productos con stock < 10 | ADMIN / EMPLEADO |

### Clientes

| Método | Endpoint | Descripción | Acceso |
|--------|----------|-------------|--------|
| GET | `/api/clientes` | Listar todos | ADMIN / EMPLEADO |
| GET | `/api/clientes/{id}` | Obtener por ID | ADMIN / EMPLEADO |
| POST | `/api/clientes` | Crear cliente | ADMIN |
| PUT | `/api/clientes/{id}` | Actualizar cliente | ADMIN |
| DELETE | `/api/clientes/{id}` | Eliminar cliente | ADMIN |

### Pedidos

| Método | Endpoint | Descripción | Acceso |
|--------|----------|-------------|--------|
| GET | `/api/pedidos` | Listar todos (filtro `?estado=`) | ADMIN / EMPLEADO |
| GET | `/api/pedidos/{id}` | Obtener por ID | ADMIN / EMPLEADO |
| PATCH | `/api/pedidos/{id}/estado` | Cambiar estado | ADMIN / EMPLEADO |
| DELETE | `/api/pedidos/{id}` | Eliminar pedido | ADMIN |

---

## 📦 Datos de prueba (DataLoader)

Al iniciar la aplicación se cargan automáticamente:

**Productos:** Medialunas de Manteca · Pan Francés · Torta de Chocolate · Croissant · Bizcochuelo Vainilla · Facturas Surtidas · Pan de Campo

**Clientes:** María González · Juan Pérez · Ana Rodríguez

**Pedidos:** 3 pedidos con distintos estados (EN_PREPARACION · ENTREGADO · PENDIENTE)

---

## 🔄 Estados de pedido

```
PENDIENTE → EN_PREPARACION → LISTO → ENTREGADO
                                    ↘ CANCELADO
```

---

## 🛠️ Configurar MySQL para producción

En `application.properties`, comentar la sección H2 y descomentar MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/panaderia_rodrigo?useSSL=false&serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

También descomentar la dependencia MySQL en `pom.xml` y comentar H2.

---

## 🎨 Diseño

- **Paleta:** dorado pan `#C8893A` · marrón tostado `#9B5E1A` · crema `#FFF8F0`
- **Tipografía:** Playfair Display (títulos) · Lato (cuerpo)
- **Responsive:** adaptado para escritorio y móvil

---

## 📚 Conceptos aplicados

- Arquitectura en capas: Controller → Service → Repository
- Spring MVC con Thymeleaf (server-side rendering)
- Spring Data JPA con relaciones `@ManyToOne` y `@ManyToMany`
- Spring Security con roles `ROLE_ADMIN` y `ROLE_EMPLEADO`
- Bean Validation con `@Valid` y `BindingResult`
- API REST con `@RestController` y `ResponseEntity`
- Mensajes flash con `RedirectAttributes`
- Datos de prueba con `CommandLineRunner`

---

## 👩‍💻 Autor

Desarrollado por Docente **Rodrigo Morales** · Módulo 6 · Marzo 2026
