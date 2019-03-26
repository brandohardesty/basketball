
public class Player {
	
	private String pos;
	private double[] stats;
	
	public Player(double [] stats, String pos ) {
	
		this.pos = pos;
		this.stats = stats;
	}
	public double[] getStats() {
		return stats;
	}
	public String getPos() {
		return pos;
	}
	
	public double getDistance(Player p) {
		double sum = 0;
		for(int i = 0; i<stats.length; i++) {
			sum += Math.pow((stats[i] + p.getStats()[i]), 2);
		}
		return Math.sqrt(sum);
	}
}
