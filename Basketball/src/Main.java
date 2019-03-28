import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<Player> readData(File file) {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<String> lines = new ArrayList<String>();
		String pos = "";
		if(file.getName().equals("F.csv")) {
			pos = "F";
		}else {
			pos = "G";
		}
		try {
			Scanner reader = new Scanner(file);
			while(reader.hasNextLine()) {
				lines.add(reader.nextLine());
			}
			String[] parts = new String[4];
			double[] stats = {0,0,0,0};
			for(String line:lines) {
				parts = line.split(",");
				for(int i = 0; i<stats.length;i++) {
					stats[i] = Double.parseDouble(parts[i]);
				}
				
				players.add(new Player(stats,pos));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File g = new File("G.csv");
		File f = new File("F.csv");
		ArrayList<Player> guards = readData(g);
		ArrayList<Player> fowards = readData(f);
		ArrayList<Player> trainingData = new ArrayList<Player>();
		trainingData.addAll(guards);
		trainingData.addAll(fowards);
		System.out.println("hey");
		System.out.println(guards.size());
	}

}
