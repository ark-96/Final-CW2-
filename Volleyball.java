package volleyball;

import java.util.Arrays;

public class Volleyball extends Competitor {   //not sure if changing this to volleyball class or...
		//instance variables

	private String volleyballPosition;
	private static final int NUM_SCORE = 5;
	private int [] volleyballScore;
	
	//constructor of the competitor object
	public Volleyball(String a, int i, Name n, String l, String vPos, int [] vScore){   //int  
		super(a, i, n, l, s);
		volleyballPosition = vPos;
		volleyballScore = vScore;
	}
	
	
	public String getPosition() {return volleyballPosition;}
	public int [] getScoreArray() {return volleyballScore;}
	
	public double getOverallScore() {
		int total = 0;
		for (int volleyballScoreIndex = 0; volleyballScoreIndex < volleyballScore.length;
		                    volleyballScoreIndex++) {
			total +=volleyballScore[volleyballScoreIndex];
		}
		return (double) total/volleyballScore.length;
	}
	
	public String getArraytoString(){
		return Arrays.toString(volleyballScore);
	}
	
	
	//probably have to change this as it is calling the parent's instance variable?  need to look at David's name class if we are using his code
	public String getFullDetails() { 
		return ("\nCompetitor Number: ") + competitorId + ", " + ("Name: ") + competitorName.getFullName() +".\n" + competitorName.getFirstName() + " is an " + competitorLevel + " " + competitorPosition + " " + ("and has scores of: ");
	}
	
	public String getShortDetails() {  
		return ("\nCID ") + competitorId + " (" + competitorName.getInitials() + ") " + "has an overall score of ";
	}




	
}
