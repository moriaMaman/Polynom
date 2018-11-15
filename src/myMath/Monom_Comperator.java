package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	@Override
	public int compare(Monom arg0, Monom arg1) {
	    int ans=arg0.get_power()-arg1.get_power();
		return ans;
	}

	// ******** add your code below *********

}
