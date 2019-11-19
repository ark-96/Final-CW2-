
public class Basketball extends ParentCompetitor {
	
	private String country;

	public Basketball(String a, int i, Name n, String l, String country, int [] s) {
					
		super(a, i, n, l, s);
		this.country = country;
		}	
			
	public String getCountry() {
		return country; 
		}
					
	public double getAverageScore () {
		int total = 0;
			for (int scoreIndex = 0; scoreIndex < getScoreArray().length; scoreIndex++) {
				total += getScoreArray()[scoreIndex]; }
			return (double) total/getScoreArray().length;
			}
	
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
		
		
		
			
	public String getFullDetails() {
		String fullDetails = "";
			fullDetails += ("Full details for " + getId());
			fullDetails += ("Competitor number " + getId() + ", name " + getName().getFullName() + ", country " + getCountry() +"\n");
			fullDetails += (getName().getFirstName() + "is a " + getLevel() + " and received these scores: " + getScoreArray()+ "\n");
			fullDetails += ("This gives the competitor an overall score of " + getOverallScore());
		return fullDetails;
		}
	
	
	public String getShortDetails () {
		String shortDetails = "";
			shortDetails += ("CN " + getId() + "(" + getName().getInitials() + ") has overall score " + getOverallScore() + ".");
		return shortDetails; 
		}
	
	//gets maximum score value
	public int getMaxScore() {
		int maxScore = getScoreArray()[0];
		for(int i=1; i<getScoreArray().length; i++) {
			if (getScoreArray()[i] > maxScore) {
				maxScore = getScoreArray()[i];
			}
		}
		return maxScore;
	}
	
	//gets minimum score value
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