import java.util.ArrayList;

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
	public void changePPG(double delta) {
		stats[0] += delta;
	}
	public void changeRPG(double delta) {
		stats[1] += delta;
	}
	public void change3pt(double delta) {
		stats[3] += delta;
	}
	public void changeFT(double delta) {
		stats[2] += delta;
	}
	
	public double getDistance(Player p) {
		double sum = 0;
		for(int i = 0; i<stats.length; i++) {
			sum += Math.pow((stats[i] + p.getStats()[i]), 2);
		}
		return Math.sqrt(sum);
	}
	public Player getClosest(ArrayList<Player> players) {
		double min = getDistance(players.get(0));
		Player closestPlayer = players.get(0);
		for(Player p: players) {
			if(getDistance(p) < min) {
				min = getDistance(p);
				closestPlayer = p;
			}
		}
		return closestPlayer;
	}
}
