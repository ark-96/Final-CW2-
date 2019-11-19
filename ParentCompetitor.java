
abstract public class ParentCompetitor {

	private String identifier;
	private int id;
	private Name name;
	private String level;
	private int [] score;
	
	public ParentCompetitor (String a, int i, Name n, String l, int [] s) {
		identifier = a;
		id = i;
		name = n;
		level = l;
		score = s;		
	}
	
	public String getIdentifier() { return identifier; }
	public int getId() { return id; }
	public Name getName() {return name; }
	public String getLevel() {return level; }
	
	public int [] getScoreArray() {
		return score ;}

	public abstract double getOverallScore();
	public abstract String getFullDetails();
	public abstract String getShortDetails();
	

}
