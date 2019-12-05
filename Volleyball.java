/**Volleyball Class
 * Volleyball competitor type
 * @author jky1
 *
 */

public class Volleyball extends ParentCompetitor {   //not sure if changing this to volleyball class or...
	
	private String volleyballPosition;
	private int [] volleyballScore;

	/**constructor of the volleyball object
	 * has instance variables that are inherited from ParentCompetitor by calling super
	 * has additional variable
	 * @param a used to identify competitor type from other sports
	 * @param i id of the competitor
	 * @param n name of the competitor
	 * @param l level of the competitor
	 * @param vPos the position of the competitor
	 * additional attribute
	 * @param s scores of the competitor
	 */
	public Volleyball(String a, int i, Name n, String l, String vPos, int [] s){  
		super(a, i, n, l, s);
		volleyballPosition = vPos;
		volleyballScore = s;
	}
	
	/** gets the position of the volleyball competitor
	 * @return position of volleyball competitor
	 */
	public String getPosition() {return volleyballPosition;}

	/**calculates the overall (or average) score of competitor
	 * goes through index of scores and adds them together then divides by the total number of scores
	 * @return overall score of volleyball competitor
	 */
	public double getOverallScore() {
		double total = 0;
		for (int volleyballScoreIndex = 0; volleyballScoreIndex < getScoreArray().length; volleyballScoreIndex++) 
			{ total +=getScoreArray()[volleyballScoreIndex];			}
		double avg = (total/getScoreArray().length);
		
		return (avg);
	}
	
	
	/**get string of competitor full details
	 * @return full details of competitor
	 */
	public String getFullDetails() { 
		return ("\nCompetitor Number: ") + getId() + ", " + ("Name: ") + getName().getFullName() +".\n" + getName().getFirstName() + " is an " + getLevel() + " " + volleyballPosition + " " + ("and has scores of: ") + getScoreString() + " with an overall score of " + getOverallScore();
	}
	
	
	/**compares positions of 2 competitors
	 * @param a volleyball position of competitor
	 * @param b another position of volleyball competitor
	 * @return sorts the competitors depending on which one comes first alphabetically respective to both of those positions compared
	 */
	public int comparePosition(Volleyball a , Volleyball b)
	{	return  a.getPosition().compareTo( b.getPosition() );	}

	}
