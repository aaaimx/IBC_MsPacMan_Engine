

import pacman.Executor;
import pacman.controllers.GhostController;
import pacman.controllers.PacmanController;
import pacman.game.internal.POType;


public class ExecutorTestFSM {

    public static void main(String[] args) {
        Executor executor = new Executor.Builder()
                .setTickLimit(4000)
                .setGhostPO(false)
                .setPacmanPO(false)
                .setVisual(true)
                .setScaleFactor(1.0)
                .build();

        PacmanController pacMan = new es.ucm.fdi.ici.practica2.demofsm.MsPacManFSM();
        GhostController ghosts = new es.ucm.fdi.ici.practica2.demofsm.GhostsFSM();
        
        System.out.println( 
        		executor.runGame(pacMan, ghosts, 40)
        );
        
    }
}
