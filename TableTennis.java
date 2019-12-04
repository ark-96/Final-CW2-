public class TableTennis extends ParentCompetitor {
private String nationality;
private int age;  
/**Constructor for TableTennis
 * creates the table tennis object while extending the parent competitor class 
 * instance variables and methods
 * @param a arbitrary string constant to identify object when reading from file
 * @param i id of the competitor
 * @param n name  of the competitor
 * @param l level of the competitor
 * @param age age of the competitor 
 * @param country nationality of the competitor
 * @param s score array of the competitor
 */
public TableTennis(String a, int i, Name n, String l, int age, String country, int [] s){
 super(a, i, n, l, s);	
 nationality=country;
 this.age = age;
}

	/**getAge
	 * @return age of the competitor 
	 */
	public int getAge(){
	 return age;
	}
	
	/**getCountry
	 * @return nationality of the competitor
	 */
	public String getCountry(){
	 return nationality;
	}
	
	/** getOverallScore
	 * returns the overall score of the competitor
	 * which is a weighted average of the scores 
	 * where even indexed score have a weight of 3 and
	 * odd indexed scores have a weight of 2.
	 * @return overall score of the competitor calculated as previously mentioned 
	 */
	public double getOverallScore(){
		 int divider= 0;
		 double overall = 0;
		 for(int scoreIndex = 0; scoreIndex < getScoreArray().length; scoreIndex++){
		 	if (scoreIndex%2==0){
			 overall +=(getScoreArray()[scoreIndex])*3;
			 divider +=3;
		 	}
		 else{
		  overall +=(getScoreArray()[scoreIndex])*2;
		  divider +=2;
		 }
		 }
		 double overall1 = (overall/(divider));
		 return (overall1);
		}
	
	/**getFullDetails
	 * @return the full details of the competitor
	 */
	public String getFullDetails(){
	 String compNo = String.format("%s", getId());
	 String years = String.format("%s", age);
	 return "Competitor number " + compNo + ", name " + getName().getFullName() + ", country " + nationality + ".\n" + getName().getFirstName() + " is a " + getLevel() + " aged " + years + " and received these scores " + getScoreString() + ".\nThis gives him an overall score of " + String.format("%.3s", getOverallScore()) + ".";
	}
	
	/** getShortDetails
	 * @return the short details of the copetitor 
	 */
	public String getShortDetails(){
	 String compNo = String.format("%s", getId());
	 return "CN " + compNo + " (" + getName().getInitials() + ") has an overall score " + String.format("%.3s", getOverallScore());
	}
	
	/** compareNationality
	 * compares the nationality of the 2 competitors
	 * @param a first competitor 
	 * @param b second competitor
	 * @return positive or negative values if the first object is larger or smaller respectively
	 */
	public int compareNationality(TableTennis a , TableTennis b)
	{	return  a.getCountry().compareTo( b.getCountry() );	}
}
