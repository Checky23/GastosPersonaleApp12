# Plan de Implementación - Solución de Error de Navegación "Inicio"

Este plan aborda el error `java.lang.IllegalArgumentException: navigation destination Inicio is not a direct child of this NavGraph`. El error ocurre porque se intenta navegar a una ruta que no está definida (o está comentada) en el `NavHost`.

## Revisión del Usuario Requerida

> [!IMPORTANT]
> Se habilitará la pantalla de Inicio en el sistema de navegación. Asegúrate de que la lógica de `VerificacionDeUsuario()` en `AppNavigation.kt` sea la deseada para el destino inicial.

## Cambios Propuestos

### Componente de Pantallas

#### [MODIFY] [InicioScreen.kt](file:///C:/Users/Lenovo/AndroidStudioProjects/GastosPersonales1/app/src/main/java/com/example/gastospersonales/Pantallas/Inicio/InicioScreen.kt)
- Se actualizará la función `InicioScreen` para que acepte un parámetro `navController: NavHostController`. Esto permitirá que la pantalla realice navegación interna en el futuro si es necesario.
- Se actualizará la `@Preview` para proporcionar un controlador de navegación de prueba.

### Componente de Navegación

#### [MODIFY] [AppNavigation.kt](file:///C:/Users/Lenovo/AndroidStudioProjects/GastosPersonales1/app/src/main/java/com/example/gastospersonales/Navegacion/AppNavegation.kt)
- Se importará `com.example.gastospersonales.Pantallas.Inicio.InicioScreen`.
- Se descomentará el bloque `composable(Screen.InicioScreen.ruta)` dentro del `NavHost` para registrar la ruta "Inicio".

---

## Plan de Verificación

### Pruebas Automatizadas
- Ejecutar `./gradlew app:assembleDebug` para asegurar que los cambios de sintaxis e importaciones sean correctos.

### Verificación Manual
- Iniciar la aplicación.
- Realizar el flujo de inicio de sesión y verificar que la transición a la pantalla de "Inicio" funcione sin errores.
