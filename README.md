# Juego de Asteroides - Proyecto Aplicativo Final

**Curso:** Lenguaje Orientado a Objetos
**Nombre de Grupo:** PAF803_3
**Fecha de Entrega:** 20 de mayo del 2025 a las 13:00

## Integrantes del Equipo

*   Yadi Alejandro Landa Cossio - 545958
*   Santiago Elin Mandujano Aguilar - 564640
*   Armando Díaz Castillo - 00562897
*   Jorge Carlos Zapata Villanueva - 543478

## Mejoras Implementadas

Durante el desarrollo del proyecto, se implementaron varias mejoras adicionales para enriquecer la experiencia de juego:

*   **Controles Mejorados:** Ahora el jugador puede mover la nave tanto con las teclas de flecha como con las teclas **WASD**.
*   **Disparo Mejorado:** Además de disparar con la tecla **P**, ahora también se puede disparar haciendo **click izquierdo** del mouse.
*   **Botón de Controles:** Se añadió un botón en el **menú principal** para acceder a una pantalla de controles, donde se explica cómo jugar y qué teclas usar.
*   **Modos de Dificultad:** Se implementaron **tres modos de juego**: Easy, Medium y Hardcore. Dependiendo del modo seleccionado:
    *   El color de la nave cambia: **Azul** (Easy), **Verde** (Medium), **Rojo** (Hardcore).
    *   La velocidad de los asteroides y OVNIs, así como la frecuencia y dificultad de sus disparos, aumenta en los modos más difíciles.
    *   El juego se vuelve progresivamente más desafiante.
*   **Pausa Mejorada:** Ahora se puede pausar el juego con la **barra espaciadora** en tiempo real. Dentro del menú de pausa, también se puede consultar la pantalla de controles para recordar cómo jugar.

## Descripción del Proyecto

Este proyecto es una recreación del clásico juego "Asteroides" desarrollado en Java, aplicando los principios de la Programación Orientada a Objetos. El jugador controla una nave espacial con el objetivo de destruir asteroides y OVNIs enemigos, evitando colisiones para sobrevivir y obtener la puntuación más alta.

El desarrollo se basó en el material de referencia proporcionado en el curso, con adaptaciones y mejoras significativas para cumplir con los objetivos pedagógicos y enriquecer la experiencia del usuario.

## Características Implementadas

*   **Control de Nave:** Movimiento (WASD y teclas de flecha), rotación y disparo (tecla 'P' y clic del ratón).
*   **Múltiples Niveles de Dificultad (Fácil, Medio, Extremo):**
    *   Ajuste de la velocidad y comportamiento de los OVNIs.
    *   Cambio dinámico del color de la nave del jugador y sus láseres (Azul: Fácil, Verde: Medio, Rojo: Extremo).
*   **Sistema de Estados del Juego:**
    *   Pantalla de Carga
    *   Menú Principal (con opciones para Jugar, Puntuaciones, Dificultad, Controles, Salir)
    *   Pantalla de Controles (muestra las teclas para las acciones del juego)
    *   Estado de Juego (partida en curso)
    *   Menú de Pausa (accesible con la barra espaciadora, opciones para Reanudar, Reiniciar, Volver al Menú)
    *   Pantalla de Puntuaciones (muestra los puntajes más altos guardados)
    *   Pantalla de Game Over
*   **Entidades del Juego:**
    *   Jugador (nave espacial)
    *   Asteroides (se dividen al ser destruidos)
    *   OVNIs (naves enemigas con diferentes comportamientos según dificultad, disparan láseres)
    *   Láseres (proyectiles del jugador y de los OVNIs; los láseres enemigos dañan al jugador)
    *   Power-ups (Escudo, Doble Puntuación, Disparo Rápido, Doble Cañón)
*   **Gestión de Colisiones:** Detección y manejo de colisiones entre todas las entidades relevantes.
*   **Sistema de Puntuación:** Acumulación de puntos por destruir asteroides y OVNIs.
*   **Persistencia de Puntuaciones:** Guardado y carga de las puntuaciones más altas usando archivos JSON.
*   **Gráficos y Sonido:** Carga y uso de sprites, animaciones para explosiones y efectos, música de fondo y efectos de sonido (con capacidad de pausa y reanudación).

## Estructura del Proyecto (Paquetes)

El código fuente está organizado en los siguientes paquetes para promover la modularidad y mantenibilidad:

*   `main`: Contiene la clase `Window` que es el punto de entrada y gestiona el bucle principal del juego.
*   `gameObjects`: Contiene todas las entidades interactivas del juego (`GameObject`, `MovingObject`, `Player`, `Meteor`, `Ufo`, `Laser`, `PowerUp`, `Message`, `Constants`, `Size`, `PowerUpTypes`).
*   `states`: Implementa el patrón de diseño State para manejar los diferentes estados del juego (`State`, `GameState`, `MenuState`, `ScoreState`, `LoadingState`, `ControlsState`, `PauseMenuState`).
*   `graphics`: Clases para la carga y renderizado de gráficos y animaciones (`Animation`, `Assets`, `Loader`, `Sound`, `Text`).
*   `input`: Clases para manejar la entrada del usuario (`KeyBoard`, `MouseInput`).
*   `io`: Clases para la entrada/salida de datos, específicamente para la persistencia de puntuaciones (`JSONParser`, `ScoreData`).
*   `math`: Clases de utilidad matemática (`Vector2D`).
*   `ui`: Clases para elementos de la interfaz de usuario (`Action`, `Button`).

## Rúbrica del Proyecto Aplicativo Final (PAF 1 - Código)

A continuación, se detalla cómo el proyecto busca cumplir con los criterios de la rúbrica:

**1. Funcionamiento (20 puntos)**
*   **Excelente:** El juego compila y ejecuta exitosamente. Todas las funcionalidades descritas anteriormente han sido implementadas y probadas. Se incluyen los archivos `.java`, el archivo `mymanifest.mf` para la creación del JAR, y la librería `json-20180813.jar`.
    *   **Compilación:** `javac -d bin -cp ".\src;.\json-20180813.jar" src/main/Window.java src/gameObjects/*.java src/graphics/*.java src/input/*.java src/io/*.java src/math/*.java src/states/*.java src/ui/*.java`
    *   **Ejecución (JAR):** `jar cvfm AsteroidsGame.jar mymanifest.mf -C bin . -C res .` seguido de `java -jar AsteroidsGame.jar` (asegurándose que la librería JSON esté accesible).

**2. Entorno Gráfico (20 puntos)**
*   **Excelente:** Se implementan las librerías estándar de Java AWT y Swing (`JFrame`, `Canvas`, `Graphics`, `BufferedImage`, etc.) para crear y gestionar el entorno gráfico del juego. Los recursos gráficos (imágenes, sprites) se cargan y muestran adecuadamente.

**3. Diseño (20 puntos)**
*   **Excelente:** El diseño del software se considera completo y detallado:
    *   **Interfaz de Usuario:** Se han diseñado múltiples pantallas (menús, juego, puntuaciones, controles, pausa) con una navegación clara y elementos interactivos como botones.
    *   **Estructura de Datos:** Se utilizan `ArrayList` para gestionar colecciones de objetos del juego (MovingObjects, Messages, Buttons, etc.), `Vector2D` para posiciones y velocidades, y `PriorityQueue` para ordenar las puntuaciones.
    *   **Algoritmos:** Se implementan algoritmos para el movimiento, detección de colisiones (con optimizaciones y diferenciación entre tipos de láseres), división de asteroides, comportamiento de OVNIs, sistema de puntuación, gestión de vidas, power-ups, y el patrón de diseño State para la gestión de los estados del juego.
    *   **Principios POO:** Se aplica herencia (`GameObject` -> `MovingObject` -> `Player`, `Meteor`, etc.), polimorfismo (en el manejo de `MovingObject` en `GameState`), encapsulamiento (uso de modificadores de acceso) y abstracción.

**4. Implementación (20 puntos)**
*   **Excelente:** La implementación se considera eficiente y sigue buenas prácticas:
    *   **Código Limpio:** Se ha buscado mantener un código organizado en paquetes, con nombres de clases y métodos descriptivos. Se han eliminado comentarios innecesarios y se han añadido Javadoc donde correspondía (traducidos al español).
    *   **Mantenibilidad:** La estructura modular facilitada por los paquetes y el patrón State permite que el código sea más fácil de entender y extender.
    *   **Manejo de Recursos:** La clase `Assets` centraliza la carga de recursos para evitar redundancias.
    *   **Manejo de Errores:** Aunque no exhaustivo, se han considerado aspectos como la carga de archivos y la gestión de estados.

**5. GitHub (20 puntos)**
*   **Si:** Este repositorio de GitHub contiene el código fuente completo del proyecto, incluyendo este archivo `README.md`.

## Cómo Compilar y Ejecutar

1.  **Requisitos:**
    *   JDK (Java Development Kit) 8 o superior.
    *   La librería `json-20180813.jar` (incluida en el repositorio o en la raíz del proyecto al compilar/ejecutar).

2.  **Estructura de Carpetas:**
    ```
    JuegoDeAsteroides/
    ├── src/
    │   ├── gameObjects/
    │   ├── graphics/
    │   ├── input/
    │   ├── io/
    │   ├── main/
    │   ├── math/
    │   ├── states/
    │   └── ui/
    ├── bin/      (Se creará durante la compilación)
    ├── res/      (Contiene las imágenes y sonidos)
    ├── mymanifest.mf
    ├── json-20180813.jar
    └── README.md
    ```

3.  **Ejecución del JAR (desde la raíz del proyecto `JuegoDeAsteroides/`):
    ```bash
    java -jar AsteroidsGame.jar
    ```
4. **Compilación en eclipse (desde la raíz del proyecto `JuegoDeAsteroides/`):
   Se puede compilar tanto como java proyect o maven proyect

5.  **Compilación (desde la raíz del proyecto `JuegoDeAsteroides/`):
    ```bash
    javac -d bin -cp ".\src;.\json-20180813.jar" src/main/Window.java src/gameObjects/*.java src/graphics/*.java src/input/*.java src/io/*.java src/math/*.java src/states/*.java src/ui/*.java
    ```

6.  **Creación del Archivo JAR "opcional" (desde la raíz del proyecto `JuegoDeAsteroides/`):
    ```bash
    jar cvfm AsteroidsGame.jar mymanifest.mf -C bin . -C res .
    ```
    (Asegúrate que el archivo `mymanifest.mf` tenga la siguiente línea como mínimo, terminando con una nueva línea:
    `Main-Class: main.Window`
    )

## Nuevos Aprendizajes y Conclusiones

Este proyecto ha sido una experiencia de aprendizaje integral, permitiéndonos aplicar y profundizar en:

*   **Programación Orientada a Objetos:** Diseño de clases, herencia, polimorfismo, encapsulamiento y abstracción en un contexto práctico.
*   **Patrones de Diseño:** Implementación efectiva del patrón State para la gestión de las diferentes pantallas y lógicas del juego.
*   **Gestión de Recursos:** Carga y manejo eficiente de imágenes, sonidos y fuentes.
*   **Manejo de Entrada:** Captura y procesamiento de eventos de teclado y ratón.
*   **Físicas y Colisiones:** Implementación de movimiento vectorial, detección de colisiones y respuesta a las mismas.
*   **Interfaz Gráfica de Usuario (GUI):** Creación de menús interactivos y visualización de información en pantalla.
*   **Persistencia de Datos:** Uso de JSON para guardar y cargar datos del juego (puntuaciones).
*   **Desarrollo de Videojuegos:** Comprensión del bucle principal del juego, manejo del tiempo (delta time), y la lógica general de un videojuego 2D.
*   **Trabajo en Equipo y Control de Versiones:** Coordinación, resolución de conflictos y uso de Git/GitHub para el desarrollo colaborativo.

En conclusión, el desarrollo del Juego de Asteroides nos ha proporcionado una base sólida en la aplicación de la POO al desarrollo de software interactivo, enfrentando y superando desafíos técnicos que han enriquecido nuestra formación como programadores. 
