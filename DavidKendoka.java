import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.Collections;

public class DavidKendoka extends ParentCompetitor
/** Class of DavidKendoka
 * provides data structure to hold Kendoka members and several get & set methods
 * @param 
 * 	 
 */
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
		/** Constructor of DavidKendoka
		 * builds a Kendoka member 
		 * @param 
		 * 	 
		 */
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
			int[] temp = getScoreArray();
			double totalMarks = 0;
			double minScore = getScoreArray()[0];
			for (int i = 0 ; i <= 4 ; i++ )  
				{
				totalMarks += getScoreArray()[i];
				if (temp[i] < minScore)
					{ minScore = getScoreArray()[i];	}
				}
			return  ( totalMarks - minScore - danGrade );	
		}
		
		
		public int getAge()
		/** Calculates 'Age'
		 * parameterless, returns the Age of the object using Period.between LocalDate.
		 */
			{return Period.between( dateOfBirth , LocalDate.now() ).getYears();}
		
		
		public String getIdAsString() 
		/** Returns objects ID as a string
		 */
			{ return String.valueOf(memberShipNumber) ; }
	
		public int getIdAsInt()
		/** Returns Membership Number (ID)
		 * parameterless. 
		 */
			{ return memberShipNumber ;}
		
		public int getDanGrade()
		/** Returns DanGrade of object
		 * parameterless. 
		 */
		{
			return danGrade;
		}
		
		public int [] getLast5Scores()
		/** returns an array of last5Scores
		 * parameterless. 
		 */
		{
			return last5Scores;
		}
		
		public String getFullDetails()
		/** Returns a string of Full Details
		 * @param parameterless
		 */
		{
			String scores = "";
			for (int s = 0 ; s <=4 ; s++)
				{scores += getScoreArray()[s] + " , ";}
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
					
	public int compareDan(DavidKendoka a , DavidKendoka b)
	/** Compares two Kendokas Dan grades, used to sort by grade
	 * @param a,b both DavidKendokas
	 */
	{	return Integer.compare(a.getDanGrade(), b.getDanGrade());	}	
		
	public int compareAge(DavidKendoka a , DavidKendoka b)
	/** Compares two Kendokas Ages, used to sort by Age
	 * @param a,b both DavidKendokas
	 */
	{	return Integer.compare(a.getAge() , b.getAge() );	}
	
	public int compareScore(DavidKendoka a , DavidKendoka b)
	/** Compares two Kendokas Overall Scores, used to sort by Score
	 * @param a,b both DavidKendokas
	 */
	{	return Double.compare( a.getOverallScore() , b.getOverallScore() );	}
		
}
