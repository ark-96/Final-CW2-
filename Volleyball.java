import java.util.Arrays;

public class Volleyball extends ParentCompetitor {   //not sure if changing this to volleyball class or...
		//instance variables

	private String volleyballPosition;
	
	private int [] volleyballScore;

	//constructor of the competitor object
	public Volleyball(String a, int i, Name n, String l, String vPos, int [] s){  
		super(a, i, n, l, s);
		volleyballPosition = vPos;
		volleyballScore = s;
	}
	
	
	public String getPosition() {return volleyballPosition;}
	
	public double getOverallScore() {
		double total = 0;
		for (int volleyballScoreIndex = 0; volleyballScoreIndex < getScoreArray().length; volleyballScoreIndex++) 
			{ total +=getScoreArray()[volleyballScoreIndex];			}
		double avg = (total/getScoreArray().length);
		
		return (avg);
	}
	
	//probably have to change this as it is calling the parent's instance variable?  need to look at David's name class if we are using his code
	public String getFullDetails() { 
		return ("\nCompetitor Number: ") + getId() + ", " + ("Name: ") + getName().getFullName() +".\n" + getName().getFirstName() + " is an " + getLevel() + " " + volleyballPosition + " " + ("and has scores of: ") + getScoreString() + " with an overall score of " + getOverallScore();
	}
	public int comparePosition(Volleyball a , Volleyball b)
	{	return  a.getPosition().compareTo( b.getPosition() );	}

	}
