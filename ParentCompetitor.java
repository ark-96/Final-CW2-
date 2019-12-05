import java.util.Comparator;
/**
 * abstract class Competitor
 * @author jessi
 *
 */
abstract public class ParentCompetitor {

	private String identifier;
	private int id;
	private Name name;
	private String level;
	private int [] score;
	
	/**Constructor of the ParentCompetitor type
	 * 
	 * @param a identifier to distinguish between the children classes that inherit from ParentCompetitor
	 * @param i competitor id
	 * @param n competitor name
	 * @param l competitor level
	 * @param s competitor scores
	 */
	public ParentCompetitor (String a, int i, Name n, String l, int [] s) 
	{
		identifier = a;
		id = i;
		name = n;
		level = l;
		score = s;		
	}
	
	
	/**gets the identifier of competitor object
	 * @return the sport that competitor belongs to
	 */
	public String getIdentifier() { return identifier; }
	
	/**
	 * get id of competitor
	 * @return id
	 */
	public int getId() { return id; }
	
	/**
	 * get name of competitor
	 * @return name
	 */
	public Name getName() {return name; }
	
	/**
	 * get level of competitor
	 * @return level
	 */
	public String getLevel() {return level; }
	
	
	/**
	 * get array of scores
	 * @return scores
	 */
	public int [] getScoreArray() { return score ;}
	
	/**
	 * sets scores to another array of scores
	 * @param sc different array of string
	 */
	public void setScoreArray(int [] sc) { score=sc;}
	
	/**get string of scores
	 * checks the string and if the length of score is greater than score index, adds score
	 * @return string of scores
	 */
	public String getScoreString()
	{
		String  report = "";
		 for(int scoreIndex = 0; scoreIndex < score.length; scoreIndex++)
	 	{
			 report += score[scoreIndex] + " " ;
	 	}
	 return report;
	}
	
	/**abstract method to get double overall score
	 */
	public abstract double getOverallScore();
	
	/**abstract method to get full details
	 */
	public abstract String getFullDetails();
	
	/**
	 * get short details of competitor
	 * @return competitor initials, overall score
	 */
	public String getShortDetails()
	{
		String compNo = String.format("%s", id);
		 return "CN " + compNo + " (" + getName().getInitials() + ") has an overall score " + String.format("%.4s", getOverallScore());
	}
	
	
	/** get age if it is a kendoka
	 * @return age, if not 0
	 */
	public int getAge()
	{
		if (this instanceof DavidKendoka )
		{return ((DavidKendoka) this).getAge();}
		else
		{return 0;}
	}	
	
	/** gets Dan if it is a kendoka
	 * @return dan, otherwise -1
	 */
	public int getDan()
	{
		if (this instanceof DavidKendoka )
			{return ((DavidKendoka) this).getDanGrade();}
		else
			{return -1;}
	}
	
	/**get position if it's a volleyball player
	 * @return volleyball position, otherwise null
	 */
	public String getP()
	{
		if (this instanceof Volleyball ) {
			return ((Volleyball) this).getPosition();
			}
		else {
			return null;
			}
	}
	
	/**get nationality if it's a table tennis player
	 * @return nationality, otherwise null
	 */
	protected String getN() 
	{
		if (this instanceof TableTennis )
		{
		return ((TableTennis) this).getCountry();
		}
	else
		{return null;}
	}
	
	/** compares name of competitors and sorts them alphabetically
	 * @param pc1 pc2 2 parent competitors
	 * @return returns int after comparing first letter of pc1 name and pc2 name, negative number means pc1 unicode is lower than pc2, positive number vice versa, 0 same unicode 
	 */
	public static Comparator<ParentCompetitor> compareName = new Comparator<ParentCompetitor>()
	{
		@Override
		public int compare(ParentCompetitor pc1, ParentCompetitor pc2)
		{
		return (int) (pc1.getName().getFullName()).compareTo(pc2.getName().getFullName());
		}
	};
	
	/**compares ages of competitors and sorts in ascending order
	 * @param pc1 pc2 ages of parent competitor
	 * @return sorted age
	 */
	public static Comparator<ParentCompetitor> compareAge = new Comparator<ParentCompetitor>()
	{
		@Override
		public int compare(ParentCompetitor pc1, ParentCompetitor pc2)
		{
		return  ( (pc1.getAge() ) - (pc2.getAge() ) );
		}
	};
	
	/**compares scores of competitor and sorts in ascending order
	 * @param pc1 pc2 overall scores of parent competitor
	 * @return sorted overall score
	 */
	public static Comparator<ParentCompetitor> compareScore = new Comparator<ParentCompetitor>()
	{
		@Override
		public int compare(ParentCompetitor pc1, ParentCompetitor pc2)
		{
		return 	Double.compare(pc1.getOverallScore(), pc2.getOverallScore());
		//return  (int) ((pc1.getOverallScore() ) - (pc2.getOverallScore() )) ;
		}
	};
	
	
	/**compares dan of competitor and sorts in ascending order
	 * @param pc1 pc2 dan of parent competitor
	 * @return sorted dan
	 */
	public static Comparator<ParentCompetitor> compareDan = new Comparator<ParentCompetitor>()
	{
		@Override
		public int compare(ParentCompetitor pc1, ParentCompetitor pc2)
		{
		return  (int) ( (pc1.getDan() ) - (pc2.getDan() ) );
		}
	};
	
	/**compares id of competitor and sorts in ascending order
	 * @param pc1 pc2 id of parent competitor
	 * @return sorted id
	 */
	public static Comparator<ParentCompetitor> compareID = new Comparator<ParentCompetitor>()
	{
		@Override
		public int compare(ParentCompetitor pc1, ParentCompetitor pc2)
		{
		return  ( (pc1.getId() ) - (pc2.getId() ) );
		}
	};
	
	
	/**compares position of competitor if instance of volleyball and sorts alphabetically
	 * @param pc1 pc2 position of volleyball player
	 * @return negative if first letter of pc1 position is lower than pc2's first letter of position in unicode
	 */
	public static Comparator<ParentCompetitor> comparePosition = new Comparator<ParentCompetitor>()
	{
		@Override
		public int compare(ParentCompetitor pc1, ParentCompetitor pc2)
		{
			if ((pc1 instanceof Volleyball) & (pc2 instanceof Volleyball))
			{return (int) ( (pc1.getP()).compareTo( pc2.getP() )) ;}
			else {return 0;}
		}
	};
	
	/**compares nationality of competitor if instance of table tennis and sorts alphabetically
	 * @param pc1 pc2 nationality of table tennis player
	 * @return negative if first letter of pc1 nationality is lower than pc2's first letter of nationality in unicode
	 */
	public static Comparator<ParentCompetitor> compareNationality = new Comparator<ParentCompetitor>()
	{
		@Override
		public int compare(ParentCompetitor pc1, ParentCompetitor pc2)
		{
			if ((pc1 instanceof TableTennis) & (pc2 instanceof TableTennis))
			{return (int) ( pc1.getN().compareTo( pc2.getN() )) ;}
			else {return 0;}
		}
	};
	
	/**compares level of competitor if instance of table tennis and sorts alphabetically
	 * @param pc1 pc2 level of table tennis player
	 * @return negative if first letter of pc1 level nationality is lower than pc2's first letter of level in unicode
	 */
	public static Comparator<ParentCompetitor> compareLevel = new Comparator<ParentCompetitor>()
	{
		@Override
		public int compare(ParentCompetitor pc1, ParentCompetitor pc2)
		{
			// if ((pc1 instanceof TableTennis) & (pc2 instanceof TableTennis))
			return (int) ( pc1.getLevel().compareTo( pc2.getLevel() )) ;
			// else {return 0;}
		}
	};

	

}
