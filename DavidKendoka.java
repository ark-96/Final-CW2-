
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;


public class DavidKendoka extends ParentCompetitor
	{
	// now build main data structure
	// Declare variables
	Integer memberShipNumber;
	Name individual;
	LocalDate dateOfBirth;
	Integer danGrade;
	String shogo;
	boolean coach;
	int [] last5Scores;
		
	// Constructor
	public DavidKendoka(String a, Integer i , 
					Name n ,
					LocalDate dob ,
					Integer dan , 
					String shogoAwarded , 
					boolean qualifiedCoach ,
					String l,
					int [] s ) 
		{
		super (a, i, n, l, s);
		memberShipNumber = i;
		individual = n;
		dateOfBirth = dob;
		danGrade = dan;
		shogo = shogoAwarded;
		coach = qualifiedCoach;	
		last5Scores = s;
		}
		

		public double getOverallScore()
		/** Calculates 'Overall Score'
		 * Takes best 4 of the 5 recent scores, deducts dan grade as a handicap.
		 * @param queryIndividual the Kendoka whose score we want to interrogate
		 * returns the Overall Score.	 
		 */
		{
			// sort the list of scores	
			Arrays.sort(last5Scores);
			// sum marks 5 to 1 (ignore score[0])
			double totalMarks = 0;
			for (int i = 5-1; i >= 1  ; i--)  
				{ totalMarks += last5Scores[i]; }
			
			return  ( totalMarks - danGrade );	
			
		}
		
		public int getAge()
			{return Period.between( dateOfBirth , LocalDate.now() ).getYears();}
		
		
		public String getIdAsString() 
			{ return String.valueOf(memberShipNumber) ; }
	
		public int getIdAsInt()
			{ return memberShipNumber ;}
		
		
		
		
		public String getFullDetails()
		{
			String scores = "";
			for (int s : last5Scores)
				{scores += last5Scores[s] + " , ";}
			scores = scores.substring(0, scores.length() -1) + "\n";			// ie strip off last comma & space and add new line
			
			String fullDetails = "Competitor number " + Integer.toString(memberShipNumber) 
									+ ", name " + individual.getFullName()
									+ " is a " + danGrade + " dan "
									+ "aged " + getAge() 
									+ " and received these scores : "
									+ scores
									+ " and has an overall score of " + getOverallScore() + ".\n";
			return fullDetails;					
		}
	
		
		
				
		public void printMemberDetails()
		{	System.out.println( getFullDetails()); }
	
	
		public void printMemberShortDetails()
		{	System.out.println( getShortDetails()); }
		
		public int[] getScoreArray()
		{	return last5Scores ;  }
		
		
//	public static void main(String[] args) 
//	{
//		// TODO Auto-generated method stub
//		
//		int[] lowScores = {1,1,1,1,1};
//		int[] highScores = {5,5,5,5,5};
//		int[] mixedScores = {5,2,4,3,1};
//		int[] rndMixedScores = {2,3,1,3,1};
//		
//		Kendoka member1 = new Kendoka(100, new Name("Bill",null,"Smith") , LocalDate.of(1966,1,3) , 2 , null , true , highScores);
//		Kendoka member2 = new Kendoka(101, new Name("Jane","Anne","Walters") , LocalDate.of(1995,1,1) , 3 , null , false , lowScores);
//		Kendoka member3 = new Kendoka(102, new Name("Frank",null,"Walters") , LocalDate.of(1976,3,5) , 1 , null , false , mixedScores);
//		Kendoka member4 = new Kendoka(103, new Name("Julie","Jane","Peters") , LocalDate.of(2002,1,1) , 3 , null , false , rndMixedScores);
//		Kendoka member5 = new Kendoka(104, new Name("Satoshi",null,"Yamazaki") , LocalDate.of(1990,1,1) , 4 , null , false , rndMixedScores);
//		Kendoka member6 = new Kendoka(105, new Name("Andrew","James","Wright") , LocalDate.of(1989,12,21) , 5 , null , true , highScores);
//		Kendoka member7 = new Kendoka(106, new Name("Steven","Peter","Bishop") , LocalDate.of(1968,5,17) , 6 , "Renshi" , true , highScores);
//		Kendoka member8 = new Kendoka(107, new Name("Gillian",null,"Matthewson") , LocalDate.of(1975,7,6) , 4 , null , true , rndMixedScores);
//		Kendoka member9 = new Kendoka(108, new Name("Sumi",null,"Masatake") , LocalDate.of(1950,1,10) , 8 , "Hanshi" , true , highScores);
//		Kendoka member10 = new Kendoka(109, new Name("Geoff","Frederick","Salmon") , LocalDate.of(1958,9,25) , 7 , "Kyoshi" , true , highScores);
//
//		
//		member1.printMemberDetails( member1 );
//		System.out.println( member2.getFullDetails( member2 ));
//		System.out.println( member3.getFullDetails( member3 ));
//		member4.printMemberShortDetails( member4 );
//	
//		
//	}

}
