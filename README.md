# Intelligent Behavior Competition (IBC) #
Repositorio del simulador del juego MsPAcMan vs Ghosts usado en la IBC, basado en el que se usa en la [Competición Internacional de Comportamientos Inteligentes](https://gaia.fdi.ucm.es/research/mspacman/competicion/) de la Universidad Complutense de Madrid.

## ¿Cómo instalar? ##

### Requisitos previos ###
Se desconoce la versión del JDK con la que se realizó el simulador, pero este repositorio fue usado con el JDK 15. Se recomienda esta versión para su uso.   

De igual forma, esta proyecto se realizó con el IDE Eclipse, que cuenta con varias herramientas para facilitar el clonar el repositorio y comenzar a programar sin ningún otro requisito (de ahí archivos como .classpath y .project/ sean incluidos en el repositorio).

### Clonar el repositorio ###

```bash
$ git clone 
```

### Configuración ###

Importar proyecto en Eclipse. Automáticamente debería comenzar la descargar de dependencias. Comprueba este paso al verificar la existencia de una carpeta "Maven Dependencies" en la pestaña "Package Explorer" ubicada a la izquierda del IDE.   

De igual forma existirá una carpeta llamada Referenced Libraries, con los siguientes archivos dentro:   
* ici.c1920.practica1.jar
* ici.c2021.practica1.jar
* ici.c2021.practica2.jar
* PacManEngineFSM.jar   

Los primeros tres archivos son paquetes compilados de las tareas realizadas por competidores anteriores de la UCM. El archivo PacManEngineFSM.jar es una librería que permite la realización de comportamiento inteligentes basados en Máquinas de Estado Finito (FSM's, por sus siglas en inglés).


## ¿Cómo usar? ###

### Reglas de la competición ###
Durante el concurso, cada uno de los equipos participan con dos comportamientos inteligentes: uno para Ms Pacman y otro para los fantasmas. Luego, cada comportamiento de pacman juega 100 partidas contra cada comportamiento de los fantasmas, y las puntuaciones generadas se promedian.   

Ejemplo: El compartamiento PacMan A juega 100 partidad contra el comportmiento FantasmasA, y las puntuaciones se promedian y se guarda el resultado. Esta puntuación es una comparación del performance entre estos dos comportamientos. Luego, PacManA juega contra FantasmasB, promedia puntuaciones y guarda el resultado. Esto se repite para FantasmasC, D, etc. Al final, los resultados promediados de PacManA se vuelven a promediar, obteniendo la puntuación final para ese comportamiento. Lo mismo sucede para los otros comportamientos de PacMan (y por extensión, para los comportamientos de los Fantasmas).  

Los ganadores de la competición son aquellos comportamiento de PacMan que maximizen su puntuación (es decir, que consigan muchos puntos contra lso fantasmas) y aquellos comportamientos de Fantasmas que minimicen su puntuación (dando por hecho que ese comportamiento es eficiente para vencer a PacMan).   
Estas reglas de la competición son necesarias para entender la estructura del proyecto.

### Simulación ###
En el paquete por defecto de Eclipse, existe una clase llamada Evaluate.java, que se encarga de ejecutar las simulaciones y conseguir las puntuaciones de los comportamientos. Sólo es necesario ejecutarlo para su funcionamiento correcto (darle click al botón de "Run" en Eclipse). En este caso, está preconfigurado para enfrentar a los 3 mejores comportamientos de competiciones pasadas (ver archivo Resultados.txt). Cuando se ejecuta se puede ver cómo realiza las partidas automáticametne y al finalizar presenta una lista de puntuaciones en la consola/terminal.

Si se desea incluir más comportamientos en la simulación, se puede editar el archivo config.properties, añadiendo el "fully qualified name"  de la clase que implementa el comportamiento a evaluar. Este nombre es del tipo (paquete de la clase + nombre de la clase), y se recomienda guardar tales comportamientos con la siguiente nomenclatura:
 * aaaimx.genXXXX.nombreDelComportamientoPacMan.MsPacMan
 * aaaimx.genXXXX.nombreDelComportamientoGhosts.Ghosts   
 s
donde XXXX indica el año de los equipos en la competición y nombreDelComportamiento es una descripción muy breve de las caracteríticas más destacables del comportamiento. Dentro de estas carpetas/paquetes deben existir uno de dos archivos: MsPacMan.java o Ghosts.java. Ejemplos para estos nombres son:
 * aaaimx.gen2021.reactivePacMan.MsPacMan
 * aaaimx.gen2021.reactiveGhosts.Ghosts   
 
donde "reactive" indica la característica más importante del comportamiento, en este caso, una IA que sólo reacciona a los estímulos del entorno, sin pensar en movimientos futuros o aprender de alguna forma. 

### Diseñando nuevos comportamientos ###

Si se desea realizar un nuevo comportamiento de PacMan, se debe crear una clase que extienda la clase pacman.controllers.PacManController. Para comportamientos de fantasmas, debe extender la clase pacman.controllers.GhostController.   

En el primer caso, la clase debe implementar un método getMove(Game game, long timeDue) que devuelva alguna constante de la enumeración MOVE (véase pacman.game.Constants.java). Esta constante puede ser MOVE.DOWN, MOVE.UP, MOVE.LEFT, etc.  

En el segundo caso, este mismo método getMove debe devolver una enumeración que relacione un valor de la enumeración GHOST con otro valor de la enumeración MOVE. Suena muy difícil, pero en realidad es sólo devolver 4 movimientos en vez de 1, como en el caso anterior, y englobar estos movimientos en una enumeración que normalmente se verá así:
* {GHOST.PINKY : MOVE.UP, GHOST.SUE : MOVE.DOWN, GHOST.INKY : MOVE.UP, GHOST.BLINKY : MOVE.RIGHT}   

Véase las clases en pacman.controllers.examples para unos ejemplos. Se recomienda guardar los nuevos comportamientos en paquetes con nomenclatura
* aaaimx.genXXXX.nombreDelComportamientoPacMan.MsPacMan
* aaaimx.genXXXX.nombreDelComportamientoGhosts.Ghosts  

como se indicó anteriormente

La elección de los movimientos dependerá de la situación del videojuego, como la cercanía de los enemigos, la presencia de frutas que dan puntos extra, la cantidad de power pills restantes, etc. Toda esta información viene dentro de la clase Game, cuya instancia es recibida por getMove y de la que hablaremos en la siguiente sección.   

 

### La clase Game ###


Léase el archivo Archivos/game.pdf para más información sobre las variables y métodos con los que esta clase cuenta para representar el estado de una partida de MsPacMan.    



## Bugs encontrados ##

### Alguna clase no es reconocida por el IDE ###
Cierra el proyecto en Eclipse, después cierra el IDE y vuelve a abrirlo después de unos segundos. Abre el proyecto y deja que recompile. Esto debería resolver los problemas. De no ser así, revisa el siguiente bug.

### Alguna clase no es reconocida por el IDE (y ya he aplicado el método de la sección anterior) ###
Asegúrate de descargar las librerías necesarias para que el proyecto funciones. Esto se realiza automáticamente por el IDe cuando abre el proyecto por primera vez, pero de no ser así, puedes forzar la descarga haciendo "maven compile" en la consola. Para esto es necesario descargar e instalar Maven de la página oficial.













