import es.ucm.fdi.ici.PacManParallelEvaluator;
import es.ucm.fdi.ici.Scores;

public class Evaluate {

	public static void main(String[] args) {
		PacManParallelEvaluator evaluator = new PacManParallelEvaluator();
		Scores scores = evaluator.evaluate();
		scores.printScoreAndRanking();

	}

}
