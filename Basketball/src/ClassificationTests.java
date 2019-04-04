import java.io.File;
import java.util.ArrayList;
import java.lang.AssertionError;
import org.junit.Test;

import junit.framework.TestCase;

public class ClassificationTests extends TestCase {
	ArrayList<double[]> forwardStats= new ArrayList<double[]>();
	ArrayList<double[]> guardStats= new ArrayList<double[]>();
	BruteForce bf = new BruteForce();
	//LVQ lvq = new LVQ();
	ArrayList<String[]> analysis = new ArrayList <String[]> ();
	
	ClassificationTests(){
		File g = new File("G.csv"); //$NON-NLS-1$
		File f = new File("F.csv"); //$NON-NLS-1$
		ArrayList<Player> guardData = Main.readData(g);
		ArrayList<Player> fowardData = Main.readData(f);
		
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
		//Repeat try catch blocks for lvq and k's with the same test data
	}
}
