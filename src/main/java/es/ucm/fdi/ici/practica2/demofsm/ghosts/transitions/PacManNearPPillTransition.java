package es.ucm.fdi.ici.practica2.demofsm.ghosts.transitions;

import es.ucm.fdi.ici.fsm.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.practica2.demofsm.ghosts.GhostsInput;

public class PacManNearPPillTransition implements Transition {

	public static double thresold = 30;
	
	public PacManNearPPillTransition() {
		super();
	}


	@Override
	public boolean evaluate(Input in) {
		GhostsInput input = (GhostsInput) in;
		return input.getMinPacmanDistancePPill() < thresold;
	}


	@Override
	public String toString() {
		return "MsPacman near PPill";
	}

	
	
}
