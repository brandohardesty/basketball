import java.util.ArrayList;

public class LVQ {
	private ArrayList<Player> trainingData;
	private ArrayList<Player> codeBookVects;
	private double alpha;
	private int maxEpoch;
	
	public LVQ(ArrayList<Player> td, double alpha, int maxEpoch,ArrayList<Player> cbv) {
		trainingData = td;
		this.alpha = alpha;
		codeBookVects = cbv;
		
	}
	

}
