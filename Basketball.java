
public class AgzCompetitor extends Competitor {
	
	private String country;
	private static final int NUM_SCORES = 5;

	public AgzCompetitor(String a, int i, Name n, String l, String country, int [] s) {
					
		super(a, i, n, l, s);
		this.country = country;
		}	
			
	public String getCountry() {
		return country; 
		}
			
	public int getScoreArray () {
		int scoreArray = 0;
			for (int scoreIndex = 0; scoreIndex < score.length; scoreIndex++) {
					scoreArray += score[scoreIndex]; }
			return scoreArray;
		}
			
	public double getAverageScore () {
		int total = 0;
			for (int scoreIndex = 0; scoreIndex < score.length; scoreIndex++) {
				total += score[scoreIndex]; }
			return (double) total/score.length;
			}
	
	public double getOverallScore () {
		int total = 0;
		double average = 0;
			for (int scoreIndex = 0; scoreIndex < score.length; scoreIndex++) {
				total += score[scoreIndex]; }
			average += total/score.lenght;
		int maxScore = score[0];
		for(int i=1; i<score.length; i++) {
			if (score[i] > maxScore) {
				maxScore = score[i];
			}
		}
		int minScore = score[0];
		for (int i=1; i<score.length; i++) {
			if(score[i] < minScore) {
				minScore = score[i];
			}
		}
		return (double) = (average + maxScore + minScore)/3
	}
		
		
		
			
	public String getFullDetails() {
		String fullDetails = "";
			fullDetails += ("Full details for " + c.getNumber());
			fullDetails += ("Competitor number " + c.getNumber() + ", name " + c.getName().getFullName() + ", country " + c.getCountry() +"\n");
			fullDetails += (c.getName().getFirstName() + "is a " + c.getLevel() + " and received these scores: " + c.getScoreArray()+ "\n");
			fullDetails += ("This gives the competitor an overall score of " + c.getOverallScore());
		return fullDetails;
		}
	
	
	public String getShortDetails () {
		String shortDetails = "";
			shortDetails += ("CN " + c.getNumber() + "(" + c.getName().getInitName() + ") has overall score " + c.getOverallScore() + ".");
		return shortDetails; 
		}
	
	
	//frequency calculation
	public void getFrequencyOfScores() {
		int [] frq = new int [score.length];
		int counted = -1;
		for (int i=0; i<score.length; i++) {
			int count = 1;
			for (int j = i+1; j<score.length; j++) {
				if(score[i]==score[j]) {
					count++;
					frq[j] = counted;
					}
				}
			if(frq[i] != counted)
				frq[i] = count;
			}
		System.out.println("Score | Frequency");
		for (int i=0; i<frq.length; i++) {
			if(frq[i] != counted)
				System.out.println(" " + score[i] + " | " + frq[i]);

		}
	}
	//gets maximum score value
	public int getMaxScore() {
		int maxScore = score[0];
		for(int i=1; i<score.length; i++) {
			if (score[i] > maxScore) {
				maxScore = score[i];
			}
		}
		return maxScore;
	}
	
	//gets minimum score value
	public int getMinScore () {
		int minScore = score[0];
		for (int i=1; i<score.length; i++) {
			if(score[i] < minScore) {
				minScore = score[i];
			}
		}
		return minScore;
	}
			

		}
	
