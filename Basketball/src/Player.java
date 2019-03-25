
public class Player {
	private double ppg;
	private double rpg;
	private double ftp;
	private double threeper;
	private String pos;
	
	public Player(double [] stats, String pos ) {
		ppg = stats[0];
		rpg = stats[1];
		ftp = stats[2];
		threeper = stats[3];
		this.pos = pos;
	}
}
