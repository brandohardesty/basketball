import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public ArrayList<Player> readData(File file) {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<String> lines = new ArrayList<String>();
		try {
			Scanner reader = new Scanner(file);
			while(reader.hasNextLine()) {
				lines.add(reader.nextLine());
			}
			String[] parts = new String[5];
			double[] stats = {0,0,0,0};
			for(String line:lines) {
				parts = line.split(";");
				for(int i = 0; i<stats.length;i++) {
					stats[i] = Double.parseDouble(parts[i]);
				}
				players.add(new Player(stats,parts[4]));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
