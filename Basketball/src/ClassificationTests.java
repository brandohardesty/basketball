import java.io.File;
import java.util.ArrayList;
import java.lang.AssertionError;
import org.junit.Test;

import junit.framework.TestCase;

public class ClassificationTests extends TestCase {
	ArrayList<double[]> forwardStats= new ArrayList<double[]>();
	ArrayList<double[]> guardStats= new ArrayList<double[]>();
	BruteForce bf = new BruteForce();
	LVQ model;
	//LVQ lvq = new LVQ();
	ArrayList<String[]> analysis = new ArrayList <String[]> ();
	
	ClassificationTests(){
		File g = new File("G.csv"); //$NON-NLS-1$
		File f = new File("F.csv"); //$NON-NLS-1$
		double[] gStats = {22,5,.8,.3};
		double[] fStats = {20,10,.7,.2};
		Player guardCodeBook = new Player(gStats,"G");
		Player fowardCodeBook = new Player(fStats,"F");
		ArrayList<Player> cbv = new ArrayList<Player>();
		cbv.add(guardCodeBook);
		cbv.add(fowardCodeBook);
		ArrayList<Player> guardData = Main.readData(g);
		ArrayList<Player> fowardData = Main.readData(f);
		ArrayList<Player> allData = new ArrayList<Player>();
		allData.addAll(guardData);
		allData.addAll(fowardData);
		double[] pGStats= new double[] {22.88, 5.175, 0.8255, 0.3558};
		double[] pFStats= new double[] {21.72, 12, 0.74365, 0.23925};
		double[] mFStats= new double[] {22.2, 9, 0.9, 0.3};
		double[] mGStats= new double[] {22.2, 8, 0.9, 0.3};


		Player pureGuard = new Player(pGStats,"G");
		Player pureFoward = new Player(pFStats,"F");
		Player comboFoward = new Player(mFStats,"F");
		Player comboGuard = new Player(mGStats,"G");
		ArrayList<Player> testData = new ArrayList<Player>();
		testData.add(pureGuard);
		testData.add(pureFoward);
		testData.add(comboFoward);
		testData.add(comboGuard);
		
		
		
		model = new LVQ(allData,cbv,testData);
		model.train(.3,2000);
		
		for (int i=0; i<20; i++) {
			forwardStats.add(fowardData.get(i).getStats());
			guardStats.add(guardData.get(i).getStats());
		}
	}
	
	@Test
	public void PureGuardShouldBeGuard() {
		double[] inputStats= new double[] {22.88, 5.175, 0.8255, 0.3558};
		String summary[] = new String[2];
		
		try {
			assertEquals("Guard", bf.bruteForceClassification(forwardStats, guardStats, inputStats));
			summary[0]="Pass";
		}
		catch(Exception e) {
			summary[0]= "Fail";
		}
		
		try {
			assertEquals("G", model.classify(model.getTestData.get(0)));
			summary[1]="Pass";
		}
		catch(Exception e) {
			summary[1]= "Fail";
		}
		
		try {
			assertEquals("Guard", bf.bruteForceClassification(forwardStats, guardStats, inputStats));
			summary[2]="Pass";
		}
		catch(Exception e) {
			summary[2]= "Fail";
		}
		//Repeat try catch blocks for lvq and k's with the same test data
	}
	
	public void PureForwardShouldBeForward() {
		double[] inputStats= new double[] {21.72, 12, 0.74365, 0.23925};
		String summary[] = new String[2];
		
		try {
			assertEquals("Guard", bf.bruteForceClassification(forwardStats, guardStats, inputStats));
			summary[0]="Pass";
		}
		catch(Exception e) {
			summary[0]= "Fail";
		}
		
		try {
			assertEquals("F", model.classify(model.getTestData.get(1)));
			summary[1]="Pass";
		}
		catch(Exception e) {
			summary[1]= "Fail";
		}
		
		try {
			assertEquals("Guard", bf.bruteForceClassification(forwardStats, guardStats, inputStats));
			summary[2]="Pass";
		}
		catch(Exception e) {
			summary[2]= "Fail";
		}
		//Repeat try catch blocks for lvq and k's with the same test data
	}
	
	public void MixedForwardDominantStatsShouldBeForward() {
		double[] inputStats= new double[] {22.2, 9, 0.9, 0.3};
		String summary[] = new String[2];
		
		try {
			assertEquals("Guard", bf.bruteForceClassification(forwardStats, guardStats, inputStats));
			summary[0]="Pass";
		}
		catch(Exception e) {
			summary[0]= "Fail";
		}
		
		try {
			assertEquals("F", model.classify(model.getTestData.get(2)));
			summary[1]="Pass";
		}
		catch(Exception e) {
			summary[1]= "Fail";
		}
		
		try {
			assertEquals("Guard", bf.bruteForceClassification(forwardStats, guardStats, inputStats));
			summary[2]="Pass";
		}
		catch(Exception e) {
			summary[2]= "Fail";
		}
		//Repeat try catch blocks for lvq and k's with the same test data
	}
	
	public void MixedGuardDominantStatsShouldBeGuard() {
		double[] inputStats= new double[] {22.2, 8, 0.9, 0.3};
		String summary[] = new String[2];
		
		try {
			assertEquals("Guard", bf.bruteForceClassification(forwardStats, guardStats, inputStats));
			summary[0]="Pass";
		}
		catch(Exception e) {
			summary[0]= "Fail";
		}
		
		try {
			assertEquals("G", model.classify(model.getTestData.get(3)));
			summary[1]="Pass";
		}
		catch(Exception e) {
			summary[1]= "Fail";
		}
		
		try {
			assertEquals("Guard", bf.bruteForceClassification(forwardStats, guardStats, inputStats));
			summary[2]="Pass";
		}
		catch(Exception e) {
			summary[2]= "Fail";
		}
		//Repeat try catch blocks for lvq and k's with the same test data
	}
}
