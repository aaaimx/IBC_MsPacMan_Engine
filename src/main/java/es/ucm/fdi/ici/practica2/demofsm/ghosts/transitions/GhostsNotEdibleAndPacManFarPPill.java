package es.ucm.fdi.ici.practica2.demofsm.ghosts.transitions;

import es.ucm.fdi.ici.fsm.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.practica2.demofsm.ghosts.GhostsInput;
import pacman.game.Constants.GHOST;

public class GhostsNotEdibleAndPacManFarPPill implements Transition {

	GHOST ghost;
	public GhostsNotEdibleAndPacManFarPPill(GHOST ghost) {
		super();
		this.ghost = ghost;
	}
	
	@Override
	public boolean evaluate(Input in) {
		GhostsInput input = (GhostsInput)in;
		GhostsEdibleTransition edible = new GhostsEdibleTransition(ghost);
		PacManNearPPillTransition near = new PacManNearPPillTransition();
		return !edible.evaluate(input) && !near.evaluate(input);
	}

	@Override
	public String toString() {
		return "Ghost not edible and MsPacman far PPill";
	}

	
	
}
