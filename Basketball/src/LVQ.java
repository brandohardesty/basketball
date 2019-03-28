import java.util.ArrayList;

public class LVQ {
	private ArrayList<Player> trainingData;
	private ArrayList<Player> codeBookVects;
	private double alpha;
	private int maxEpoch;
	
	public LVQ(ArrayList<Player> td, double alpha, double maxEpoch,ArrayList<Player> cbv) {
		trainingData = td;
		this.alpha = alpha;
		codeBookVects = cbv;
		
	}

	
	
	public void run() {
		double learningRate = 0;
		for(double epoch = 1; epoch <= maxEpoch;epoch++) {
			learningRate = alpha*(1-(epoch/maxEpoch));
			for(Player p:trainingData) {
				Player currentCBV = p.getClosest(codeBookVects);
				if(currentCBV.getPos().equals(p.getPos())){
					currentCBV.changePPG(learningRate*(p.getStats()[0] - currentCBV.getStats()[0]));
					currentCBV.changeRPG(learningRate*(p.getStats()[1] - currentCBV.getStats()[1]));
					currentCBV.changeFT(learningRate*(p.getStats()[2] - currentCBV.getStats()[2]));
					currentCBV.change3pt(learningRate*(p.getStats()[3] - currentCBV.getStats()[3]));
				} else {
					currentCBV.changePPG(-1*(learningRate*(p.getStats()[0] - currentCBV.getStats()[0])));
					currentCBV.changeRPG(-1*(learningRate*(p.getStats()[1] - currentCBV.getStats()[1])));
					currentCBV.changeFT(-1*(learningRate*(p.getStats()[2] - currentCBV.getStats()[2])));
					currentCBV.change3pt(-1*(learningRate*(p.getStats()[3] - currentCBV.getStats()[3])));
				}
			}
		}
	}
	

}
