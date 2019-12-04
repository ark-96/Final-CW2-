public class Basketball extends ParentCompetitor {
	
	private String country;
/** Constructor for Basketball
 * creates the basketball competitor while extending the parent competitor list
 * instance variables ad methods
 * @param a an identifier string that helps to distinguish the type of a children competitor
 * @param i competitor's ID
 * @param n competitor's name
 * @param l competitor's level
 * @param country competitor's country
 * @param s  the array of competitors scores 
 */
	public Basketball(String a, int i, Name n, String l, String country, int [] s) {
					
		super(a, i, n, l, s);
		this.country = country;
		}	
/**getCountry			
 * @return country of the competitor
 */
	public String getCountry() {
		return country; 
		}
/**getAverageScore	
 * sums all scores of a competitor and divides by the number of scores				
 * @return average of a competitor scores
 */
	public double getAverageScore () {
		int total = 0;
			for (int scoreIndex = 0; scoreIndex < getScoreArray().length; scoreIndex++) {
				total += getScoreArray()[scoreIndex]; }
			return (double) total/getScoreArray().length;
			}
	
/**getOverallScore
 * sums the average of the competitor scores
 * and finds the minimum score that the competitor received
 * and finds the maximum score that the competitor received
 * then sums these three values and divides by three
 * @return	overall score of the competitor calculated as previously mentioned
 */
	public double getOverallScore () {
		int total = 0;
		double average = 0;
			for (int scoreIndex = 0; scoreIndex < getScoreArray().length; scoreIndex++) {
				total += getScoreArray()[scoreIndex]; }
			average += total/getScoreArray().length;
		int maxScore = getScoreArray()[0];
		for(int i=1; i<getScoreArray().length; i++) {
			if (getScoreArray()[i] > maxScore) {
				maxScore = getScoreArray()[i];
			}
		}
		int minScore = getScoreArray()[0];
		for (int i=1; i<getScoreArray().length; i++) {
			if(getScoreArray()[i] < minScore) {
				minScore = getScoreArray()[i];
			}
		}
		return (double) (average + maxScore + minScore)/3;
	}
		
		
/**getFullDetails
 * @return the full details of the competitor		
 */			
	public String getFullDetails() {
		String fullDetails = "";
			fullDetails += ("Full details for " + getId());
			fullDetails += (". \nCompetitor number " + getId() + ", name " + getName().getFullName() + ", country " + getCountry() +"\n");
			fullDetails += (getName().getFirstName() + "is a " + getLevel() + " and received these scores: " + getScoreString()+ "\n");
			fullDetails += ("This gives the competitor an overall score of " + getOverallScore());
		return fullDetails;
		}
	
/**getMaxScore
 * @return the maximal score that the competitor received
 */
	public int getMaxScore() {
		int maxScore = getScoreArray()[0];
		for(int i=1; i<getScoreArray().length; i++) {
			if (getScoreArray()[i] > maxScore) {
				maxScore = getScoreArray()[i];
			}
		}
		return maxScore;
	}
	
/**
 * getMinScore
 * @return the minimal score that the competitor received
 */
	public int getMinScore () {
		int minScore = getScoreArray()[0];
		for (int i=1; i<getScoreArray().length; i++) {
			if(getScoreArray()[i] < minScore) {
				minScore = getScoreArray()[i];
			}
		}
		return minScore;
	}
			
		}
