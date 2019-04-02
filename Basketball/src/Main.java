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
			
			for(String line:lines) {
				double[] stats = {0,0,0,0};
				parts = line.split(",");
				
				for(int i = 0; i<stats.length;i++) {
					stats[i] = Double.parseDouble(parts[i]);
				}
				Player p = new Player(stats,pos);
				players.add(p);
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
		double[] idealG = {.6,.5,.78,.345};
		double[] idealF = {.6,.5,.72,.3};
		double[] zion = {27.3,10.5,.646,.338};
		Player zionW = new Player(zion,"F");
		Player idealGuard = new Player(idealG,"G");
		Player idealFoward = new Player(idealF,"F");
		ArrayList<Player> codeBook = new ArrayList<Player>();
		ArrayList<Player> test = new ArrayList<Player>();
		test.add(zionW);
		codeBook.add(idealGuard);
		codeBook.add(idealFoward);
		System.out.println("hey");
		System.out.println();
		double[] test1 = {.7,.4,.5,.2};
		double[] test2 = {.7,.5,.5,.2};
		Player p1 = new Player(test1,"G");
		Player p2 = new Player(test2,"F");
		
		System.out.println(p1.getDistance(p2));
		LVQ model = new LVQ(trainingData,codeBook,test);
		model.train(.7,200);
		model.train(.3, 200);
		model.train(.1, 20000);

		System.out.println();
		System.out.println(model.classify(zionW));
		
		System.out.println("\n\n");
		
	
		model.unNormalize(model.getCodeBook());
		model.unNormalize(model.getTestData());
		for(int i = 0; i<p1.getStats().length;i++) {
			System.out.println(zionW.getStats()[i]);
		}
		
		for(int i = 0; i<p1.getStats().length;i++) {
			System.out.println(model.getCodeBook().get(1).getStats()[i]);
		}
		System.out.println();
		for(int i = 0; i<p1.getStats().length;i++) {
			System.out.println(model.getCodeBook().get(0).getStats()[i]);
		}
		
		
		
	}

}
