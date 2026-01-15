# Patrón Proxy con Arquitectura Hexagonal en Java 17

## Descripción General

Esta implementación demuestra el uso del patrón Proxy combinado con la arquitectura hexagonal (también conocida como arquitectura limpia) en Java 17.

## Patrón Proxy

El patrón Proxy proporciona un sustituto o marcador de posición para otro objeto. Un proxy controla el acceso al objeto original, permitiendo realizar acciones antes o después de que se envíen solicitudes al objeto original.

En esta implementación:
- **Interfaz de Dominio (`FileService`)**: Define las operaciones que se pueden realizar.
- **Objeto Real (`FileServiceImpl`)**: Implementa la funcionalidad real del servicio.
- **Proxy (`FileServiceProxy`)**: Controla el acceso al objeto real, añadiendo lógica de seguridad y registro.

## Arquitectura Hexagonal

La arquitectura hexagonal separa la lógica de negocio (dominio) de los detalles técnicos (infraestructura). Tiene las siguientes capas:

1. **Dominio** (`domain`): Contiene la lógica de negocio pura.
2. **Aplicación** (`application`): Coordina los servicios de dominio y contiene la lógica de aplicación.
3. **Infraestructura** (`infrastructure`): Implementa dependencias externas y detalles técnicos.
4. **Interfaces** (`interfaces`): Expone funcionalidades al exterior (APIs, UI, etc.).

## Componentes

### Capa de Dominio
- `FileService.java`: Interfaz que define las operaciones de archivos

### Capa de Infraestructura
- `FileServiceImpl.java`: Implementación real del servicio de archivos
- `FileServiceProxy.java`: Proxy que añade control de acceso y registro

### Capa de Aplicación
- `FileApplicationService.java`: Orquesta el uso del servicio con el proxy

### Capa de Interfaces
- `FileController.java`: Controlador que expone las operaciones al exterior

## Características de la Implementación

1. **Control de Acceso Basado en Roles**: El proxy verifica los permisos del usuario antes de permitir operaciones.
   - `reader`: Solo puede leer archivos
   - `editor`: Puede leer y escribir archivos
   - `admin`: Puede leer, escribir y eliminar archivos

2. **Registro de Operaciones**: El proxy registra todas las operaciones realizadas.

3. **Separación de Responsabilidades**: Cada capa tiene responsabilidades bien definidas.

## Ventajas del Diseño

1. **Seguridad**: Control de acceso basado en roles
2. **Mantenibilidad**: Código modular y bien organizado
3. **Testabilidad**: Fácil de probar cada componente por separado
4. **Flexibilidad**: Fácil de extender con nuevas funcionalidades
5. **Adaptabilidad**: Fácil de cambiar implementaciones sin afectar otras capas

## Ejecución

Para compilar y ejecutar:

```bash
javac -cp src/main/java src/main/java/com/ia/coder/proxy/*.java src/main/java/com/ia/coder/proxy/domain/*.java src/main/java/com/ia/coder/proxy/infrastructure/*.java src/main/java/com/ia/coder/proxy/application/*.java src/main/java/com/ia/coder/proxy/interfaces/*.java
java -cp src/main/java com.ia.coder.proxy.App
```

## Uso del Proxy

El proxy intercepta todas las llamadas al servicio real y:

1. Verifica permisos según el rol del usuario
2. Registra intentos de operaciones
3. Llama al servicio real si los permisos son válidos
4. Registra operaciones completadas