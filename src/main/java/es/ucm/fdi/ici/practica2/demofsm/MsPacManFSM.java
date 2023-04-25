package es.ucm.fdi.ici.practica2.demofsm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ucm.fdi.ici.fsm.CompoundState;
import es.ucm.fdi.ici.fsm.FSM;
import es.ucm.fdi.ici.fsm.Input;
import es.ucm.fdi.ici.fsm.SimpleState;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.fsm.observers.GraphFSMObserver;
import es.ucm.fdi.ici.practica2.demofsm.pacman.MsPacManInput;
import es.ucm.fdi.ici.practica2.demofsm.pacman.actions.RandomAction;
import es.ucm.fdi.ici.practica2.demofsm.pacman.transitions.RandomTransition;
import pacman.controllers.PacmanController;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/*
 * The Class NearestPillPacMan.
 */
public class MsPacManFSM extends PacmanController {

	FSM fsm;
	public MsPacManFSM() {
    	fsm = new FSM("MsPacMan");
    	
    	GraphFSMObserver observer = new GraphFSMObserver(fsm.toString());
    	fsm.addObserver(observer);
    	
    	
    	SimpleState state1 = new SimpleState("state1", new RandomAction());
    	SimpleState state2 = new SimpleState("state2", new RandomAction());
    	SimpleState state3 = new SimpleState("state3", new RandomAction());
    	
    	Transition tran1 = new RandomTransition(.3);
    	Transition tran2 = new RandomTransition(.2);
    	Transition tran3 = new RandomTransition(.1);
    	Transition tran4 = new RandomTransition(.01);
    	
    	
    	FSM cfsm1 = new FSM("Compound1");
    	GraphFSMObserver c1observer = new GraphFSMObserver(cfsm1.toString());
    	cfsm1.addObserver(c1observer);
    	
    	SimpleState cstate1 = new SimpleState("cstate1", new RandomAction());
    	SimpleState cstate2 = new SimpleState("cstate2", new RandomAction());
    	Transition ctran1 = new RandomTransition(.35);
    	Transition ctran2 = new RandomTransition(.25);
    	cfsm1.add(cstate1, ctran1, cstate2);
    	cfsm1.add(cstate2, ctran2, cstate1);
    	cfsm1.ready(cstate1);
    	CompoundState compound1 = new CompoundState("compound1", cfsm1);
    	
    	fsm.add(state1, tran1, state2);
    	fsm.add(state2, tran2, state3);
    	fsm.add(state3, tran3, compound1);
    	fsm.add(compound1, tran4, state1);

    	fsm.ready(state1);
    	
    	JFrame frame = new JFrame();
    	JPanel main = new JPanel();
    	main.setLayout(new BorderLayout());
    	main.add(observer.getAsPanel(true, null), BorderLayout.CENTER);
    	main.add(c1observer.getAsPanel(true, null), BorderLayout.SOUTH);
    	frame.getContentPane().add(main);
    	frame.pack();
    	frame.setVisible(true);
	}
	
	
	public void preCompute(String opponent) {
    		fsm.reset();
    }
	
	
	
    /* (non-Javadoc)
     * @see pacman.controllers.Controller#getMove(pacman.game.Game, long)
     */
    @Override
    public MOVE getMove(Game game, long timeDue) {
    	Input in = new MsPacManInput(game); 
    	return fsm.run(in);
    }
    
    
}