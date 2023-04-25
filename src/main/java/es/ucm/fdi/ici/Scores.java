package es.ucm.fdi.ici;
import java.util.Collections;
import java.util.Vector;

import pacman.controllers.Controller;
import pacman.controllers.GhostController;
import pacman.controllers.PacmanController;
import pacman.game.util.Stats;

public class Scores {

	
	Vector<String> list_pacMan; 
	Vector<String> list_ghosts;
	Stats[][] stats;
	Vector<ScorePair> pacManRanking;
	Vector<ScorePair> ghostsRanking;

	public Scores(Vector<String> list_pacMan,Vector<String> list_ghosts)
	{
		this.list_pacMan = list_pacMan; 
		this.list_ghosts = list_ghosts;
		stats = new Stats[list_pacMan.size()][list_ghosts.size()];
	}

	void put(String pacMan, String ghosts, Stats score) {
		int posPacMan = list_pacMan.indexOf(pacMan);
		int posGhosts = list_ghosts.indexOf(ghosts);
		stats[posPacMan][posGhosts] = score;	
		System.out.println(String.format("Scores.put %s, %s, %s",pacMan,ghosts,score.toString()));
	}
	
	public Vector<ScorePair> getMsPacManRanking() {
		return pacManRanking;
	}
	
	public Vector<ScorePair> getGhostsRanking() {
		return ghostsRanking;
	}
	
	public void printScoreAndRanking()
	{
		System.out.println("Scores Table");
        for(Stats[] result_pacman : stats)
        {
        	for(Stats s: result_pacman)
        	{
        		System.out.print(s.getAverage()+";");
        	}
        	System.out.println();
        }      
        System.out.println("MsPacMan Ranking");
        for(ScorePair sp: pacManRanking)
        	System.out.println(sp);

        System.out.println("Ghosts Ranking");
        for(ScorePair sp: ghostsRanking)
        	System.out.println(sp);

	}
	
	void computeRanking()
	{
		double[] pacManScores = new double[list_pacMan.size()]; 
		double[] ghostScores = new double[list_ghosts.size()];
		
		for(int pc = 0; pc<pacManScores.length; pc++)
		{
			double score = 0;
			for(int g=0; g<ghostScores.length; g++)
				score+=stats[pc][g].getAverage();
			pacManScores[pc] = score/(double)ghostScores.length;
		}
		
		for(int g = 0; g<ghostScores.length; g++)
		{
			double score = 0;
			for(int pc=0; pc<pacManScores.length; pc++)
				score+=stats[pc][g].getAverage();
			ghostScores[g] = score/(double)pacManScores.length;
		}
		
		pacManRanking = new Vector<ScorePair>();
		int pos = 0;
		for(String c: list_pacMan)
			pacManRanking.add(new ScorePair(c,pacManScores[pos++]));
		
		ghostsRanking = new Vector<ScorePair>();
		pos = 0;
		for(String c: list_ghosts)
			ghostsRanking.add(new ScorePair(c,ghostScores[pos++]));
		
		Collections.sort(pacManRanking);
		Collections.sort(ghostsRanking);
		Collections.reverse(ghostsRanking);
	}
	
	public class ScorePair implements Comparable<ScorePair>{
		String controller;
		Double score;
		public ScorePair(String controller, Double score) {
			super();
			this.controller = controller;
			this.score = score;
		}

		public Double getScore() {
			return score;
		}

		public int compareTo(ScorePair o) {
			ScorePair other = (ScorePair)o;
			return (int) (other.getScore()-this.score);
		}
		public String toString()
		{
			return this.controller+": "+String.format("%.2f", this.score);
		}
		
	}
}
