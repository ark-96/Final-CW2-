import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.Collections;

/** Class of DavidKendoka
 * provides data structure to hold Kendoka members and several get & set methods
 * @param 
 * 	 
 */
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
	/** Constructor of DavidKendoka
		 * builds a Kendoka member 
		 * @param 
		 * 	 
		 */
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
		

		/** Calculates 'Overall Score'
		 * Takes best 4 of the 5 recent scores, deducts dan grade as a handicap.
		 * @param queryIndividual the Kendoka whose score we want to interrogate
		 * returns the Overall Score.	 
		 */
		public double getOverallScore()
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
		
		
		/** Calculates 'Age'
		 * parameterless, returns the Age of the object using Period.between LocalDate.
		 */
		public int getAge()
		{	return Period.between( dateOfBirth , LocalDate.now() ).getYears();	}
		
		
		/** Returns objects ID as a string
		 */
		public String getIdAsString() 
		{ return String.valueOf(memberShipNumber) ; }
	
		/** Returns Membership Number (ID)
		 * parameterless. 
		 */
		public int getIdAsInt()
			{ return memberShipNumber ;}
		
		/** Returns DanGrade of object
		 * parameterless. 
		 */
		public int getDanGrade()
		{
			return danGrade;
		}
		
		/** returns an array of last5Scores
		 * parameterless. 
		 */
		public int [] getLast5Scores()
		{
			return last5Scores;
		}
		
		/** Returns a string of Full Details
		 * @param parameterless
		 */
		public String getFullDetails()
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
					
	/** Compares two Kendokas Dan grades, used to sort by grade
	 * @param a,b both DavidKendokas
	 */
	public int compareDan(DavidKendoka a , DavidKendoka b)
	{	return Integer.compare(a.getDanGrade(), b.getDanGrade());	}	
		
	/** Compares two Kendokas Ages, used to sort by Age
	 * @param a,b both DavidKendokas
	 */
	public int compareAge(DavidKendoka a , DavidKendoka b)
	{	return Integer.compare(a.getAge() , b.getAge() );	}
	
	/** Compares two Kendokas Overall Scores, used to sort by Score
	 * @param a,b both DavidKendokas
	 */
	public int compareScore(DavidKendoka a , DavidKendoka b)
	{	return Double.compare( a.getOverallScore() , b.getOverallScore() );	}
		
}
