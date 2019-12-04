import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.Collections;


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
			{return Period.between( dateOfBirth , LocalDate.now() ).getYears();}
		
		
		public String getIdAsString() 
			{ return String.valueOf(memberShipNumber) ; }
	
		public int getIdAsInt()
			{ return memberShipNumber ;}
		
		public int getDanGrade(){
			return danGrade;
		}
		
		public int [] getLast5Scores(){
			return last5Scores;
		}
		
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
					
	public int compareDan(DavidKendoka a , DavidKendoka b)
	{	return Integer.compare(a.getDanGrade(), b.getDanGrade());	}	
		
	public int compareAge(DavidKendoka a , DavidKendoka b)
	{	return Integer.compare(a.getAge() , b.getAge() );	}
	
	public int compareScore(DavidKendoka a , DavidKendoka b)
	{	return Double.compare( a.getOverallScore() , b.getOverallScore() );	}
		
}
