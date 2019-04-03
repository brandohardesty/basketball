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
		double[] ja = {24.1,5.6,.813,.363};
		double[] cam = {20.3,6.9,.818,.457};
		double[] rj = {20.3,8.7,.726,.335};
		Player zionW = new Player(zion,"F");
		Player jaM = new Player(ja,"G");
		Player camJ = new Player(cam, "F");
		Player rjB = new Player(rj, "G");
		Player idealGuard = new Player(idealG,"G");
		Player idealFoward = new Player(idealF,"F");
		ArrayList<Player> codeBook = new ArrayList<Player>();
		ArrayList<Player> test = new ArrayList<Player>();
		test.add(zionW);
		test.add(jaM);
		test.add(camJ);
		test.add(rjB);
		codeBook.add(idealGuard);
		codeBook.add(idealFoward);
//		System.out.println();
		LVQ model = new LVQ(trainingData,codeBook,test);
		model.train(.7,2000);
		model.train(.3, 2000);

		System.out.println();
		System.out.println(model.classify(zionW));
		System.out.println(model.classify(jaM));
		System.out.println(model.classify(camJ));
		System.out.println(model.classify(rjB));
		
		System.out.println("\n\n");
		
	
		model.unNormalize(model.getCodeBook());
		model.unNormalize(model.getTestData());
		for(int i = 0; i<zionW.getStats().length;i++) {
			System.out.println(zionW.getStats()[i]);
		}
		
		for(int i = 0; i<zionW.getStats().length;i++) {
			System.out.println(model.getCodeBook().get(1).getStats()[i]);
		}
		System.out.println();
		for(int i = 0; i<zionW.getStats().length;i++) {
			System.out.println(model.getCodeBook().get(0).getStats()[i]);
		}
		
		
		/*
		 * ANYTHING BELOW IS JUST FOR SPICY ***DO NOT EDIT ABOVE PLEASE***
		 */
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		ArrayList <Player> testData = new ArrayList <Player>();
		double[] nassir = {21.5,10.1,.77,.269};
		Player nassirL = new Player(nassir,"F");
		testData.add(nassirL);
		KsNearestNeighbor KNN = new KsNearestNeighbor(trainingData,testData);		
		ArrayList <Player> close = KNN.ClosestPlayers(nassirL);
		KNN.unNormalize(KNN.getNormalized());
		for (Player p:close) {
			System.out.println(p.getPos() + "\n\n");
			for(int i = 0; i<p.getStats().length;i++) {
				System.out.println(p.getStats()[i]);
			}
			System.out.println("\n\n");
		}
	}

}
