
abstract public class Competitor {

	private String indentifier;
	private int id;
	private Name name;
	private String level;
	private int [] score;
	
	public Competitor (String a, int i, Name n, String l, int [] s) {
		indentifier = a;
		id = i;
		name = n;
		level = l;
		score = s;		
	}
	
	public String getIdentifier() { return a; }
	public int getId() { return id; }
	public Name getName() {return name; }
	public String getLevel() {return level; }
	
	public abstract int getScoreArray();
	public abstract int getOverallScore();
	public abstract String getFullDetails();
	public abstract String getShortDetails();
	

}
