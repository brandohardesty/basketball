import java.util.ArrayList;
import java.io.*;

public class BruteForce {

	public static void readData() throws NumberFormatException, IOException {
		System.out.println("starting read data");
		ArrayList<ArrayList<Integer>> guards = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> forwards = new ArrayList<ArrayList<Integer>>();
		File forwardFile = new File ("F.csv");
		BufferedReader brf = new BufferedReader(new FileReader(forwardFile));
		String st;
		String line;
		while ((st = brf.readLine()) != null){
			line = brf.readLine(); 
			for (int j=0 ; j<5 ; j++) {
				ArrayList<Integer> playerStatF = new ArrayList<Integer>();
				for (int i=0 ; i<line.split(",").length ; i++) {
					playerStatF.add(Integer.parseInt(line.split(",")[i]));
				}
				forwards.add(playerStatF);
			}
		}
		
		File guardsFile = new File ("G.csv");
		BufferedReader brg = new BufferedReader(new FileReader(guardsFile));
		while ((st = brg.readLine()) != null) {
			line = brg.readLine();
			for (int x=0 ; x<5 ; x++) {
				ArrayList<Integer> playerStatG = new ArrayList<Integer>();
				for (int y=0 ; y<line.split(",").length ; y++) {
					playerStatG.add(Integer.parseInt(line.split(",")[y]));
				}
				guards.add(playerStatG);
			}
		}
		
		System.out.println(forwards);
		System.out.println(guards);
	}
	
	
	public static void main() throws NumberFormatException, IOException {
		BruteForce bf = new BruteForce();
		bf.readData();
	}
}
