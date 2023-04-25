

import pacman.Executor;
import pacman.controllers.GhostController;
import pacman.controllers.PacmanController;
import pacman.controllers.HumanController;
import pacman.game.internal.POType;
import pacman.controllers.KeyBoardInput;


public class ExecutorTestDefault {

    public static void main(String[] args) {
        Executor executor = new Executor.Builder()
                .setTickLimit(4000)
                .setGhostPO(false)
                .setPacmanPO(false)
                .setPacmanPOvisual(false) 
                .setVisual(true)
                .setPOType(POType.RADIUS)
                .setScaleFactor(1.5)
                .build();

        PacmanController pacMan = new HumanController(new KeyBoardInput());
        		//new pacman.controllers.examples.PacManRandom();
        		//new es.ucm.fdi.ici.c2021.practica1.grupo01.MsPacMan();
        		//new es.ucm.fdi.ici.c2021.practica2.grupo09.MsPacMan();
        		//new es.ucm.fdi.ici.c2021.practica1.grupo03.MsPacMan();
        		//new aaaimx.gen2021.fakeInjuryPacman.MsPacMan();
        GhostController ghosts = new es.ucm.fdi.ici.c2021.practica2.grupo09.Ghosts();
        		//new pacman.controllers.examples.GhostsRandom();
        		//new es.ucm.fdi.ici.c2021.practica2.grupo03.Ghosts();
        		//new es.ucm.fdi.ici.c2021.practica2.grupo09.Ghosts();
        		//new es.ucm.fdi.ici.c2021.practica1.grupo09.Ghosts();
        
        System.out.println( 
        		executor.runGame(pacMan, ghosts, 40)
        );
        
    }
}
