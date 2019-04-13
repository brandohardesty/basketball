import java.io.File;
import java.util.Timer;
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
		//Set up
		
		File g = new File("G.csv");
		File f = new File("F.csv");
		ArrayList<Player> guards = readData(g);
		ArrayList<Player> fowards = readData(f);
		
		//LVQ
		ArrayList<Player> trainingData = new ArrayList<Player>();
		trainingData.addAll(guards);
		trainingData.addAll(fowards);
		double[] idealG = {.6,.5,.78,.345};
		double[] idealF = {.6,.5,.72,.3};
		double[] zion = {27.3,10.5,.646,.338};
		Player idealGuard = new Player(idealG,"G");
		Player idealFoward = new Player(idealF,"F");
		ArrayList<Player> codeBook = new ArrayList<Player>();
		ArrayList<Player> test = new ArrayList<Player>();
		codeBook.add(idealGuard);
		codeBook.add(idealFoward);

		double[] test1 = {.7,.4,.5,.2};
		double[] test2 = {.7,.5,.5,.2};
		Player p1 = new Player(test1,"G");
		Player p2 = new Player(test2,"F");
		
		LVQ model = new LVQ(trainingData,codeBook,test);
		model.train(.7,200);
		model.train(.3, 200);
		model.train(.1, 20000);

		model.unNormalize(model.getCodeBook());
		model.unNormalize(model.getTestData());
		
		//Brute Force
		ArrayList<Player> guardData = readData(g);
		ArrayList<Player> fowardData = readData(f);
		BruteForce bfRun = new BruteForce();
		ArrayList<double[]> forwardStats= new ArrayList<double[]>();
		ArrayList<double[]> guardStats= new ArrayList<double[]>();
		for (int i=0; i<20; i++) {
			forwardStats.add(fowardData.get(i).getStats());
			guardStats.add(guardData.get(i).getStats());
		}
		
		//K's
		
		
		//UI ppg rpg ft% 3pt%
		
		System.out.println("Welcome to the basketball position classifier. Follow the prompts to enter a players stats and see which position, forward or guard, their skills are best suited for.");
		
		System.out.println("Are this player's stats from 36 minute games? Enter Y for yes, N for no");
		Scanner s1 = new Scanner(System.in);
		String gameLen=s1.nextString();
		
		if (gameLen != "n" || gameLen != "N" || gameLen != "y" || gameLen != "Y") {
			System.out.println("You did not enter y or n, please try again: ");
			Scanner t = new Scanner(System.in);
			gameLen=t.nextString();
		}
		
		if (gameLen != "y" || gameLen != "Y") {
			System.out.println("How many minutes long are this player's games?");
			Scanner s2 = new Scanner(System.in);
			double gameTime=s2.nextInt();
			
			System.out.println("Enter Player's Averarge Points Per Game: ");
			Scanner s3 = new Scanner(System.in);
			double ppgInput=s3.nextDouble();
			
			System.out.println("Enter Player's Averarge Rebounds Per Game: ");
			Scanner s4 = new Scanner(System.in);
			double rpgInput=s4.nextDouble();
			
			System.out.println("Enter Player's Free Throw Percentage: ");
			Scanner s5 = new Scanner(System.in);
			double ftpInput=s5.nextDouble();
			
			System.out.println("Enter Player's Three Point Percentage: ");
			Scanner s6 = new Scanner(System.in);
			double tppInput=s6.nextDouble();
			
			ppgInput = (ppgInput/gameTime)/36;
			rpgInput = (rpgInput/gameTime)/36;
			ftpInput = (ftpInput/gameTime)/36;
			ttpInput = (ttpInput/gameTime)/36;
			
			System.out.println("What position does this player normally play? Enter f or g");
			Scanner s7 = new Scanner(System.in);
			String positionInput=s7.nextInt();
			
			double[] inputStats = new double[] {ppgInput, rpgInput, ftpInput, ttpInput};
			Player inputPlayer = new Player (inputStats, positionInput);
		}
		else {
		
			System.out.println("Enter Player's Averarge Points Per Game: ");
			Scanner s3 = new Scanner(System.in);
			double ppgInput=s3.nextDouble();
			
			System.out.println("Enter Player's Averarge Rebounds Per Game: ");
			Scanner s4 = new Scanner(System.in);
			double rpgInput=s4.nextDouble();
			
			System.out.println("Enter Player's Free Throw Percentage: ");
			Scanner s5 = new Scanner(System.in);
			double ftpInput=s5.nextDouble();
			
			System.out.println("Enter Player's Three Point Percentage: ");
			Scanner s6 = new Scanner(System.in);
			double tppInput=s6.nextDouble();
			
			System.out.println("What position does this player normally play? Enter f or g");
			Scanner s7 = new Scanner(System.in);
			String positionInput=s7.nextInt();
			
			double[] inputStats = new double[] {ppgInput, rpgInput, ftpInput, ttpInput};
			Player inputPlayer = new Player (inputStats, positionInput);
		}
		System.out.println("Running Brute Force Algorithm");
		long bfStartTime = System.nanoTime();
		String bfResult = bf.bruteForceClassification(forwardStats, guardStats, inputPlayer.getStats());
		long bfEndTime = System.nanoTime();
		System.out.println("Brute Force classified this player as a "+ bfResult+ "in "+ ((bfStartTime+bfEndTime)/1000000) + " milliseconds" );
		
		System.out.println("Running K's Nearest Neighbor Algorithm");
		long kStartTime = System.nanoTime();
		//Run K's
		long kEndTime = System.nanoTime();
		System.out.println("K's Nearest Neighbor classified this player as a "+ kResult+ "in "+ ((kStartTime+kEndTime)/1000000) + " milliseconds" );
		
		System.out.println("Running Linear Vector Quantization Algorithm");
		long lvqStartTime = System.nanoTime();
		//RunLVQ
		long lvqEndTime = System.nanoTime();
		System.out.println("Linear Vector Quantization classified this player as a "+ lvqResult+ "in "+ ((lvqStartTime+lvqEndTime)/1000000) + " milliseconds" );
		
		
		
	}

}
