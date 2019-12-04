import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**Class of ParentCompetitorList
 *reads in file, writes to file, has getter methods
 */
public class ParentCompetitorList {
  protected ArrayList<ParentCompetitor> competitorList; 

  /**Constructor of ParentCompetitorList
   * creates an array list of ParentCompetitors
   */
 public ParentCompetitorList() {
	 competitorList = new ArrayList<ParentCompetitor> ();
 }
 
 /**getCompetitorList
  * @return list of a competitors
  */
 public ArrayList<ParentCompetitor> getCompetitorList(){
	 return competitorList;
 }
 
 /**findShortDetails
  * generate short details of competitor in the list
  * @param id id of the competitor
  * @return short details of the competitor
  */
public String findShortDetails(int id)
	{
	String report = "";
	for (ParentCompetitor c : competitorList )
		if (c.getId() == (id)) {
			report = c.getShortDetails();
			report += "\nThe scores received by " + c.getName().getLastName() + " are: " ;
			report += c.getScoreString();
		}
	if (report.equals("")) {
		report = "Sorry despite validation we could not retrieve this members records.\n"; 
		}		
		return report;
	}	

/** findFullDetails
 * generate full details of competitor in the list
 * @param id id of the competitor
 * @return full details of the competitor
 */
public String findFullDetails(int id)
{
String report = "";
for (ParentCompetitor c : competitorList )
	if (c.getId() == (id)) {
		report = c.getFullDetails();
		
	}
if (report.equals("")) {
	report = "Sorry despite validation we could not retrieve this members records.\n"; 
	}		
	return report;
}

/**setScoreArray
 * alter the score array of a competitor
 * @param sc score array of a competitor
 * @param id id of the competitor
 */ 
public void setScoreArray(int [] sc, int id)
{ 
	for (ParentCompetitor c : competitorList ){
		if (c.getId() == (id)) {
			c.setScoreArray(sc);
		}
	}
}

/**getOverallScore
 * generate the overall score of the competitor
 * @param id id of the competitor
 * @return overall score of the competitor
 */
public double getOverallScore( int id)
{ 
	
	double report=0;
	for (ParentCompetitor c : competitorList ){
		if (c.getId() == (id)) {
			report = c.getOverallScore();
		}
	}
	return report;
}

/**readFile
 * reads an information from the input file
 * @param filename name of the file
 */
public void readFile(String filename){
	 try{
	  File f = new File(filename);
	  Scanner sc = new Scanner(f);
	  while(sc.hasNextLine()){
	   String inputLine = sc.nextLine();
	   if(inputLine.length() != 0){
	    processLine(inputLine);
	   }
	  }
	 }
	 catch (FileNotFoundException fnf){
	  System.out.println("'" + filename + "' not found");
	  System.exit(3);
	 }
	}

/**add
 * adds a competitor to the competitor list
 * @param c competitor
 * @return check if competitor is in the list
 */
public boolean add(ParentCompetitor c){
 int num = c.getId();
 ParentCompetitor inList = this.findByNum(num);
 if (inList == null){
  competitorList.add(c);
  return true;
 }
 return false;
}

/**findByNum
 * finds a competitor by its ID number
 * @param num competitors ID
 * @return returns a competitor by the number entered
 */
public ParentCompetitor findByNum(int num){
 for (ParentCompetitor c : competitorList){
  if (c.getId() == (num)){
   return c;
  }
 }
 return null;
}

/**writeToFile
 * writes the information to a file
 * @param filename name of the file the information will be written to
 * @param report name of the String that will be written
 * @param highestScore name of the String that will be written
 */
public  void writeToFile(String filename, String report, String highestScore) {  
		 FileWriter fw;
		 try {
		    fw = new FileWriter(filename);
		    fw.write("THE REPORT\n");
		    fw.write(report);
		    fw.write("\n");
		    fw.write(highestScore);
		    fw.write(".");
		    fw.close();
		 }
		 //message and stop if file not found
		 catch (FileNotFoundException fnf){
			 System.out.println(filename + " not found ");
			 System.exit(0);
		 }
		 //stack trace here because we don't expect to come here
		 catch (IOException ioe){
		    ioe.printStackTrace();
		    System.exit(1);
		 }
	}

/**processLine
 * reads in information from the file by processing line by line
 * @param line the line that is currently being processed
 */
private void processLine(String line) {
 
	  String parts [] = line.split(",");	
	   String a = parts[0];  
        switch (a)
	{
          case "b":
			String numb = parts[1];
			numb = numb.trim();
			int num = Integer.parseInt(numb);
			Name name = new Name (parts[2]);
			String level = parts[3];
			level = level.trim();
			String country = parts[4];
			country = country.trim();
		    	int scoreLenght= parts.length - 5;
			String[] scoress = new String [scoreLenght];
			System.arraycopy(parts, 5, scoress, 0, scoreLenght);
			int array = scoress.length;
			int [] scores = new int [array];
			for(int i=0; i<array; i++) {
				scores[i] = Integer.parseInt(scoress[i].trim()); 
			}
			
			//information from the line read is implemented into an AgzCompetitor parameter and new competitor is created
			Basketball b = new Basketball(a, num, name, level, country, scores); 
			//after information had been assigned, then newly created object is added to the list
			this.add(b);
          break;
			
          case "t":
	  int num1 = Integer.parseInt(parts[1]);    
	  Name name1 = new Name (parts[2]);
	  String tier = parts[3];
	  String nationality = parts[5];
	  int age = Integer.parseInt(parts[4]);
	  int scoreLength = parts.length - 6;
	  int score[]=new int[scoreLength];
	  String sc[]=new String[scoreLength];  
	  System.arraycopy(parts, 6, sc, 0, scoreLength);
	  for(int scoreIndex = 0; scoreIndex < sc.length; scoreIndex++){
	   score[scoreIndex]=Integer.parseInt(sc[scoreIndex]);
	  }
	  TableTennis t = new TableTennis(a, num1, name1, tier, age, nationality, score);
	  this.add(t);
          break;
		  
          case "k":
        	int mNo = Integer.valueOf(parts[1]);
			int dan = Integer.valueOf(parts[4]);
			
			// identify if they are a coach			
			boolean coach = false;
			if ( parts[6].equals("t") )
				{coach = true ;}
			else if ( parts[6].equals("n") )
				{coach = false;}
			else 
				{
				System.out.println("Error in coach indicator, should be 'n' or 't'. I found '" + parts[6]+ "' in line:");
				System.out.println( line );	
				System.exit(0);
				}
			
			// process Shogo field
			String shogo = null;
			if ( parts[5].equals("n") )
				{shogo = null ;}
			else if ( parts[5].toUpperCase().equals("RENSHI") )
				{shogo = "Renshi";}
			else if ( parts[5].toUpperCase().equals("KYOSHI") )
				{shogo = "Kyoshi";}
			else if ( parts[5].toUpperCase().equals("HANSHI") )
				{shogo = "Hanshi";}
			else 
				{
				System.out.println("Error in shogo, should be 'n','Renshi','Kyoshi' or 'Hanshi' only. '" + parts[5]+ "'. Found in line:");
				System.out.println( line );	
				System.exit(0);
				}
			// process Names
			String bitsOfNames[] = parts[2].split(" ");
			int howManyNames = bitsOfNames.length ;
			
			String firstName = bitsOfNames[0];				// initialise first,middle,last assuming only two names
			String middleName = "";
			String lastName = bitsOfNames[1];
			if ( howManyNames == 3 )  						// ie two spaces in the name field, separating three names
				{											// then shuffle names to reflect 3 names
				lastName = bitsOfNames[2];
				middleName = bitsOfNames[1];
				}
			Name nameOnLine = new Name(firstName, middleName, lastName);
			
			// Competitors status
			char shortStatus = parts[7].charAt(0);
			String longStatus = "";
			if (shortStatus == 'A') { longStatus = "Amateur" ; }
			else if (shortStatus == 'P') {longStatus = "Professional";}
			else 
				{
				System.out.println("Error in level, should be 'A' or 'P' " + parts[7]+ "'. Found in line:");
				System.out.println( line );	
				System.exit(0);
				}
			
			// recent scores
			int recentScores [] = new int [5];
			for (int s = 0 ; s <= 4 ; s++)
				{ recentScores[s] = Integer.parseInt( parts[( s+8) ].trim() ) ; }
			// Date of birth
			LocalDate doB = LocalDate.parse(parts[3]); 
			
			// now construct this Kendoka			
			DavidKendoka k = new DavidKendoka(a, mNo, nameOnLine , doB , dan, shogo , coach , longStatus , recentScores);
			// and finally add to membershipList
			this.add(k);
			break;
		  
          case "v":
			
			String id = parts[1];
			id = id.trim();
			int numid = Integer.parseInt(id);
			Name cName = new Name(parts[2].trim());
			String cLevel = parts[3];
			cLevel = cLevel.trim();
			String cPosition = parts[4];
			cPosition = cPosition.trim();
			
			//the scores are at the end of the line
			int competitorScoreLength = parts.length - 5;
			int []competitorScore = new int[competitorScoreLength];
			//System.arraycopy(parts, 4, competitorScore, 0, competitorScoreLength);
			for(int i = 0; i < competitorScore.length; i++) {
				if (parts[5 +i].trim().isEmpty()) {
					continue; 
				} 
				else {
					competitorScore[i] = Integer.parseInt(parts[5 + i].trim());
				}
			}
			
			//create competitor object and add to the list
			Volleyball v = new Volleyball (a, numid , cName , cLevel , cPosition, competitorScore);
			this.add(v);
          break;
    default:
      
      System.out.println("Could not process");
      System.exit(1);
		}
	 }

/**getallMembers
 * generates the short details of all competitors in the list
 * @return short details of all competitors in the list
 */
public String getallMembers(){
	String report = "The short details of the competitors is as follows:\n";
	for (ParentCompetitor c: competitorList) {
		report += c.getShortDetails();
		report += "\n";
	}
	return report;
}

/**getallBasketball
 * generates short details of all Basketball competitors
 * @return short details of Basketball competitors
 */
public String getallBasketball(){
	String report = "The short details of the basketball competitors is as follows:\n";
	for (ParentCompetitor c: competitorList) {
		if(c instanceof Basketball){
		report += c.getShortDetails();
		report += "\n";
		}
	}
	return report;
}

/**getallDavidKendoka
 * generates short details of DavidKendoka competitors
 * @return short details of DavidKendoka competitors
 */
public String getallDavidKendoka(){
	String report = "The short details of the kendo competitors is as follows:\n";
	for (ParentCompetitor c: competitorList) {
		if(c instanceof DavidKendoka){
		report += c.getShortDetails();
		report += "\n";
		}
	}
	return report;
}

/**getallTableTennis
 * generates short details of TableTennis competitors
 * @return short details of TableTennis competitors
 */
public String getallTableTennis(){
	String report = "The short details of the table-tennis competitors is as follows:\n";
	for (ParentCompetitor c: competitorList) {
		if(c instanceof TableTennis){
		report += c.getShortDetails();
		report += "\n";
		}
	}
	return report;
}

/**getallVolleyball
 * generates short details of Volleyball competitors
 * @return short details of Volleyball competitors
 */
public String getallVolleyball(){
	String report = "The short details of the volleyball competitors is as follows:\n";
	for (ParentCompetitor c: competitorList) {
		if(c instanceof Volleyball){
		report += c.getShortDetails();
		report += "\n";
		}
	}
	return report;
}

/**getHighestScore
 * finds the highest overall score of all competitors
 * @return the highest overall score from competitor list
 */
public String getHighestScore(){
	double highestScore=-999;
	int count = 0;
	String scoreReport = "";
	for (ParentCompetitor c: competitorList) {
		double score = c.getOverallScore();
		if(score > highestScore){
			highestScore = score;	
		}
}
	for (ParentCompetitor c: competitorList) {	
		if (highestScore == c.getOverallScore()){ 			
			if(count>0){
				scoreReport += " and ";				
			}
			count++;
			scoreReport +=  c.getName().getLastCommaMiddleCommaFirst() + " with a score of " + String.format("%.4s",highestScore) ;
		}
	}
	if(count>1){
		scoreReport = "There are " + count + " competitors with the highest overall score.\nThey are: " + scoreReport;
	}
	else {
		scoreReport = "The competitor with the highest overall score is: " + scoreReport;
	}
	return scoreReport;
}

/**getallBasketballScoreReport
 * finds the basketball competitor with a highest score
 * @return the name and the score of a basketball competitor with the highest overall score
 */
public String getallBasketballScoreReport(){
	double highestScore=0;
	int count = 0;
	String scoreReport = "";
	for (ParentCompetitor c: competitorList) {
		if(c instanceof Basketball){
		double score = c.getOverallScore();
		if(score > highestScore){
			highestScore = score;	
		}
		}
}
	for (ParentCompetitor c: competitorList) {	
		if(c instanceof Basketball){
		if (highestScore == c.getOverallScore()){ 			
			if(count>0){
				scoreReport += " and ";				
			}
			count++;
			scoreReport +=  c.getName().getLastCommaMiddleCommaFirst() + " with a score of " + String.format("%.4s",highestScore) ;
		}
	}
	}
	if(count>1){
		scoreReport = "There are " + count + " competitors with the highest overall score.\nThey are: " + scoreReport;
	}
	else {
		scoreReport = "The competitor with the highest overall score is: " + scoreReport;
	}
	return scoreReport;
}

/**getallDavidKendokaScoreReport
 *finds the DavidKendoka competitor with a highest score
 * @return the name and the score of a DavidKendoka competitor with the highest overall score
 */
public String getallDavidKendokaScoreReport()
{
	double highestScore=-99;
	int count = 0;
	String scoreReport = "";
	for (ParentCompetitor c: competitorList) 
		{
		if(c instanceof DavidKendoka)
			{
			double score = c.getOverallScore();
			if(score > highestScore)
				{ highestScore = score;  }
			}
		}
	for (ParentCompetitor c: competitorList) 
		{	
		if(c instanceof DavidKendoka)
		{
			if (highestScore == c.getOverallScore())
			{ 			
				if(count>0)
					{ scoreReport += " and ";  }
				count++;
				scoreReport +=  c.getName().getLastCommaMiddleCommaFirst() + " with a score of " + String.format("%.4s",highestScore) ;
			}
		}
	}
	if(count>1)
		{ scoreReport = "There are " + count + " competitors with the highest overall score.\nThey are: " + scoreReport; }
	else { scoreReport = "The competitor with the highest overall score is: " + scoreReport; }
	return scoreReport;
}

/**getalltableTennisScoreReport
 * finds the table tennis competitor with a highest score
 * @return the name and the score of a table tennis competitor with the highest overall score
 */
public String getallTableTennisScoreReport(){
	double highestScore=0;
	int count = 0;
	String scoreReport = "";
	for (ParentCompetitor c: competitorList) {
		if(c instanceof TableTennis){
		double score = c.getOverallScore();
		if(score > highestScore){
			highestScore = score;	
		}
		}
}
	for (ParentCompetitor c: competitorList) {	
		if(c instanceof TableTennis){
		if (highestScore == c.getOverallScore()){ 			
			if(count>0){
				scoreReport += " and ";				
			}
			count++;
			scoreReport +=  c.getName().getLastCommaMiddleCommaFirst() + " with a score of " + String.format("%.4s",highestScore) ;
		}
	}
	}
	if(count>1){
		scoreReport = "There are " + count + " competitors with the highest overall score.\nThey are: " + scoreReport;
	}
	else {
		scoreReport = "The competitor with the highest overall score is: " + scoreReport;
	}
	return scoreReport;
}

/**getallVolleyballScoreReport
 * finds the volleyball competitor with a highest score
 * @return the name and the score of a volleyball competitor with the highest overall score
 */
public String getallVolleyballScoreReport(){
	double highestScore=0;
	int count = 0;
	String scoreReport = "";
	for (ParentCompetitor c: competitorList) {
		if(c instanceof Volleyball){
		double score = c.getOverallScore();
		if(score > highestScore){
			highestScore = score;	
		}
		}
}
	for (ParentCompetitor c: competitorList) {	
		if(c instanceof Volleyball){
		if (highestScore == c.getOverallScore()){ 			
			if(count>0){
				scoreReport += " and ";				
			}
			count++;
			scoreReport +=  c.getName().getLastCommaMiddleCommaFirst() + " with a score of " + String.format("%.4s",highestScore) ;
		}
	}
	}
	if(count>1){
		scoreReport = "There are " + count + " competitors with the highest overall score.\nThey are: " + scoreReport;
	}
	else {
		scoreReport = "The competitor with the highest overall score is: " + scoreReport;
	}
	return scoreReport;
}

/**getCompetitorCount
 * counts the number of competitors
 * @return the number of competitors in the list
 */
public int getCompetitorCount(){
	int count = 0;
	for (ParentCompetitor c: competitorList) {
		count ++;
	}
	return count;	
}

/**getBasketballFullDetails
 * generates full details of the basketball category players sorted in a different ways
 * @param key the category by which the competitors re sorted
 * @return sorted list of basketball competitors
 */
public String getBasketballFullDetails(String key) {
	Sorter sortedList;
	sortedList = new Sorter(competitorList);
	ArrayList<ParentCompetitor> sortedCompetitors = sortedList.sortByID();	// default
	switch(key)
	{
		case "ID":		sortedCompetitors = sortedList.sortByID();		break;
		case "score":	sortedCompetitors = sortedList.sortByScore();	break;
		case "level":	sortedCompetitors = sortedList.sortByLevel(); 	break;
		case "name": 	sortedCompetitors = sortedList.sortByName(); 	break;	
	}
	String report = "Number               " + "Name                   " + "Country        " + "Level          " + "Scores            " + "Overall Score            \n";
	for (ParentCompetitor c : sortedCompetitors) {
		if(c instanceof Basketball){
		report += String.format("%-6d", c.getId());
		report += String.format("%-37s", c.getName().getFullName());
		report += String.format("%-15s", ((Basketball) c).getCountry());
		report += String.format("%-15s", c.getLevel());
		report += String.format("%-20s", c.getScoreString());
		report += String.format("%-3s", Math.round(c.getOverallScore()*100.0)/100.0);
		report += "\n"; 
		}
	}
	return report;
}

/**getBasketballStats
 * generates statistical reports of basketball players
 * @return statistical report of baskeball players
 */
public String getBasketballStats() {
	String statistics = "";
	statistics += getBasketballFullDetails("ID");
	statistics += ("There are " + getSizeOfBasketball() + " competitors. \n");
	statistics += ("There are " + getCountOfPeopleAtLevel("Novice") + " competitor at Novice Level. \n" );
	statistics += ("There are " + getCountOfPeopleAtLevel("Standard") + " competitors at Standard Level. \n");
	statistics += ("There are " + getCountOfPeopleAtLevel("Veteran") + " competitors at Veteran Level. \n");
	statistics += ("The winner(s) of this competition is (are) " + getallBasketballScoreReport() );
	statistics += "." + getFrequencyOfScoresBasketball();			
	return statistics; 
}

/**getSizeOfBasketball
 * counts the number of competitors in a basketball category
 * @return the number of competitors in a basketball category
 */
public  int getSizeOfBasketball() {
	int count=0;
	for (ParentCompetitor c:competitorList) {
		if (c instanceof Basketball) {
			count++;
		}
		}
	return count;
}

/**getCountOfPeopleAtLevel
 * counts the number of people at different basketball category levels
 * @param level level of a competitor
 * @return count of a competitors at each basketball level
 */
public int getCountOfPeopleAtLevel(String level) {
	int countLevel = 0;
	for (ParentCompetitor c:competitorList) {
		if (c instanceof Basketball) {
		if(c.getLevel().equals(level)) {
			countLevel++; }
	}
	}
	return countLevel; 
}

/**getFrequencyOfScoresBasketball
 * generates the score frequency report
 * @return score frequency
 */
public String getFrequencyOfScoresBasketball() {
	String report="";
	int [] freqOfScores = new int [6];
	 for(ParentCompetitor c : competitorList){
		 if (c instanceof Basketball) { 
		 String scoreArray = c.getScoreString();
	  scoreArray.replaceAll(" ","");
	  for (int s = 0; s < scoreArray.length() ; s++){
	   String sc=scoreArray.substring(s, s+1);
	    if (s%2==0){
	     int sco=Integer.valueOf(sc);
	     freqOfScores[sco]++;
	    }
	   }  
	  }
	 }
	 report+= "\n";
	 report +="The following individual scores were awarded:";
	 report +="\nScore:      0  1  2  3  4  5";
	 report +="\nFrequency:  ";
	 for(int scoreIndex = 0; scoreIndex < freqOfScores.length; scoreIndex++){
	  report += freqOfScores[scoreIndex];
	  if(String.valueOf(freqOfScores[scoreIndex]).length() == 1){
		  report += "  ";
	  }
	  else{
		  report += " ";
	  }
}
	 return report;
}

/**getKendoHighestScorer
 * finds the competitor with a highest score in DavidKendoka
 * @return competitor with the highest score in DavidKendoka
 */
private ParentCompetitor getKendoHighestScorer()
{
	ParentCompetitor highMember = competitorList.get(0);
	Double highScore = (double) -999;      // initialise with silly low value
	
	for(ParentCompetitor c: competitorList)
	{
		if (c instanceof DavidKendoka) 
		{	
			if (c.getOverallScore() > highScore)
			{				// then replace hm with current member, and highScore with their score
				highMember = c;
				highScore = c.getOverallScore();
			}
		}	
	}
	return highMember;							// send back the highest scoring member
}

/**getKendoLowestScorer
 * finds the competitor with a lowest score in DavidKendoka
 * @return competitor with the lowest score in DavidKendoka
 */
private ParentCompetitor getKendoLowestScorer()
{
	ParentCompetitor lowMember = competitorList.get(0);
	Double lowScore = (double) +999;      // initialise with silly high value
	
	for (ParentCompetitor c : competitorList)
	{
		if (c instanceof DavidKendoka )
		{
			double membersScore = c.getOverallScore();
			if (c.getOverallScore() < lowScore )
			{								// then replace low member with current member, and lowScore with their score
				lowMember = c ;
				lowScore = membersScore;
			}
		}
	}
	return lowMember;							// send back the lowest scoring member
}

/**getFullKendoDetails
 * generates full details of a DavidKendoka competitor
 * @param a competitor
 * @return full details of a DavidKendoka competitor
 */
public String getFullKendoDetails(ParentCompetitor a)
{
	String fullDetails = "";
	if (a instanceof DavidKendoka)
	{
		String scores = "";
		for (int s : a.getScoreArray() )
			{scores += a.getScoreArray()[s] + " , ";}
		scores = scores.substring(0, scores.length() -1) + "\n";			// ie strip off last comma & space and add new line
		
		fullDetails = "Competitor number " + Integer.toString(a.getId()) 
						+ ", name " + a.getName()
						+ " is a " + ((DavidKendoka) a).getDanGrade() + " dan "
						+ "aged " + ((DavidKendoka) a).getAge() 
						+ " and received these scores : "
						+ scores
						+ " and has an overall score of " + a.getOverallScore() + ".\n";
	}
	else { fullDetails = "Error: " + a.getName() + " is not a Kendoka" ;}
	return fullDetails;					
}

/**etKendoStatistics
 *  generates the statistical report of DavidKendoka competitors
 * @return statistical report of DavidKendoka competitors
 */
public String getKendoStatistics()
{
	String Sreport = getDavidKendokaFullDetails("ID");
	Sreport += "\nHighest Scoring Member\n";
	Sreport +=       "----------------------\n";
	Sreport += "The details of the member with the highest overall score is :\n";
	ParentCompetitor high = getKendoHighestScorer();
	Sreport += high.getFullDetails();
	Sreport += "\n\n";
	Sreport += buildScoreFrequency();
	Sreport += buildAvgScoresByDan();
	return Sreport;
}

/**buildScoreFrequency
 * counts the frequency of scores awarded to DavidKendoka competitors
 * @return score frequency of DavidKendoka competitors
 */
private String buildScoreFrequency()
{
	ParentCompetitor winner = getKendoHighestScorer();
	double maxScore = winner.getOverallScore();
	int high = (int) Math.round(maxScore);
			
	ParentCompetitor loser = getKendoLowestScorer();
	double lowScore = loser.getOverallScore();
	int low = (int) Math.round(lowScore);
	
	
	int range = high - low;
	int cumCount = 0 ;     			// cumulative count of participants
	double totalScores = 0 ;		// Total of all scores 
			
	//	create array, starting from low score, ie [0] represents low score rounded down
	int [] counts = new int [ range + 1];
		
	// count scores
	double score;                      // temporary variable 
	for (ParentCompetitor k : competitorList)
	{
		if (k instanceof DavidKendoka)
		{
			score = k.getOverallScore();
			// System.out.println(range + " " + score + "index = " + ((int) (Math.round(score) - low )));
			counts[ (int) (Math.round(score) - low ) ] ++ ;
			totalScores += score ;
			cumCount ++ ;
		}
	}		
	// Start by defining title of table
	String title = " Frequency Count of Overall (Handicapped) Scores ";
	int tableTop = range * 6;       // Allow 6 characters for each frequency bucket
	String heading =  "---------------------------------------".substring(0,(int) ((tableTop - title.length()) / 2)) ;
	String frequencyTable = "\n" + "       " + heading + title + heading + "\n";
	
	//build intervals into table
	frequencyTable += "Score ";
	for (int n = 0; n < range + 1 ; n++)
		{ frequencyTable += String.format("%4s" , Integer.toString(n + low )) + "  "; }
	frequencyTable += "\n";
	
	// now build counts into table
	frequencyTable += "Count ";
	for (int n = 0; n < range + 1 ; n++)
		{ frequencyTable += String.format("%4s" ,counts[n]) + "  "; }
	frequencyTable += "\n\n\n";
	
	
	frequencyTable += "Summary Statistics for these " + cumCount + " members:\n";
	frequencyTable += "----------------------------------------\n";
	// calculate median
	frequencyTable += "    Median Score is :   ";
	int countToHalf = 0;
	int index = 0;
	while ( countToHalf < cumCount / 2)
		{
		countToHalf += counts[index];
		index ++ ;
		}
	if ( index%2 == 1 )
		{ frequencyTable += Integer.toString( index + low - 1) ; }
	else
		{ frequencyTable += Integer.toString( ( (index + low -1) + (index +low - 1) - 1) / 2 );}
	
	frequencyTable += "\n";
	
	// calculate Median
	double mean = totalScores / cumCount;
	String stringToAdd = String.format( "%.1f" , mean);
	frequencyTable += "    Mean Score is :   " + stringToAdd + "\n";
	
	// calculate Mode
	int maxFreqAt = 0;
	int maxCount = 0;
	boolean multiModal = false;
	
	for (int i = 0 ; i < counts.length ; ++ i )
		{
		if (( counts[i] == maxCount) && ( i != maxFreqAt))
			{ multiModal = true ; 
			continue ;}
		
		if (counts[i] > maxCount)
			{
			maxCount = counts[i];
			maxFreqAt = i;
			multiModal = false;
			}
		}
	frequencyTable += "    Modal Score is :   ";
	frequencyTable += (maxFreqAt + low) ;
	if ( multiModal ) frequencyTable += "  But beware this data is multimodal.";
	frequencyTable += "\n" + "    Minimum Score is : " + low + "\n";
	frequencyTable += "    Maximum Score is : " + high;
	frequencyTable += "\n\n";
			
	return frequencyTable ;
}

/**buildAvgScoresByDan
 * 	calculates the average scores of DavidKendoka competitors by dan grade
 * @return average score of a dan grade
 */
 private String buildAvgScoresByDan()
{
	// create a two-dimensional array of size [8][2]
	// [8] represents the possible Dan grades 0-8, the highest rank possible in Kendo is 8th Dan, beginners start with no dan (mudan), or zero
	// [2] represents:
		//[0] is a count of members with a grade corresponding to the index/Dan grade
		//[1] is a total (by grade) of Overall Score of members with that grade
	 	//[2] will hold mean score by grade
		
	// Although Dan grades are integers, and our marking scheme only applies integer results, we will have to work in FP to allow for averages
			 
	 double [][] memberScores = new double [9][3];
	 
	 int count = 0;		// array index (to make code more readable)
	 int cumScores = 1; // array index
	 int avgScores = 2; // array index
	 int g = 0; 		// simple grade pointer/counter
	
	 // iterate over memberList and populate array
	 
	 for (ParentCompetitor m : competitorList)
		 {
		 if (m instanceof DavidKendoka)
		 	{
			 memberScores[((DavidKendoka) m).danGrade][count] ++ ; 							// Increment the dan grade count
			 memberScores[((DavidKendoka) m).danGrade][cumScores] +=  m.getOverallScore() ; 	// Add m's overall score to the total for their grade
			 }
		 }
	 // calculate averages
	 for ( g = 0 ; g <= 8 ; g++ )
	 	{
		 if ( memberScores[g][count] != 0 )
	 		{ memberScores[g][avgScores] = memberScores[g][cumScores] / memberScores[g][count]; }
	 		
	 	else										// deal with zero denominator by simplifying result to zero
	 		{memberScores[g][avgScores] = 0 ;}
	 	}
		 
	 
	 // Build output table.
	 // Start by defining title of table
	String title = "Average Overall (Handicapped) Scores by Dan Grade";
	int tableTop = 9 * 7;       // Allow 7 characters for each frequency bucket, nb 9 grades hard coded, as there are only 9 grades
	String heading =  "---------------------------------------".substring( 0 , (int) ((tableTop - title.length()) / 2)) ;
	String avgMarksTable = "\n" + "          " + heading + title + heading + "\n";
	
	//build grades into table
	avgMarksTable += "Dan Grade ";
	for ( g = 0; g <= 8; g ++)
		{ avgMarksTable += String.format("%5d" , (int) g) + "  "; }
	avgMarksTable += "\n";
	
	// now build counts into table
	avgMarksTable += "No.Members";
	for ( g = 0; g <= 8; g ++)
		{ avgMarksTable += String.format("%5d" , (int) memberScores[g][count]) + "  "; }
	avgMarksTable += "\n";
	
	// now build averages into table
	avgMarksTable += "Mean Score";
	for ( g = 0; g <= 8; g ++)
		{ 
		String stringToAdd = String.format( "%.1f" , memberScores[g][avgScores] );
		avgMarksTable += "         ".substring(0, 7 - stringToAdd.length() ) + stringToAdd ;
		}
	avgMarksTable += "\n\n";
	
	
	// Find & Summarise best & Worst
	int danGradeBestScore = 0 ;
	double bestScore = -99 ;
	int danGradeWorstScore = 0 ;
	double worstScore = +99 ;
	
	
	for ( g = 0 ; g<=8 ; g ++)
		{
		if ( memberScores[g][avgScores] > bestScore )		// nb does not deal with multi modal, but rewards junior grades for matching seniors
			{
			bestScore = memberScores[g][avgScores];
			danGradeBestScore = g;
			}
		if ( memberScores[g][avgScores] < worstScore )		// nb does not deal with multi equal low frequencies, but penalises senior grades for not beating juniors
			{
			worstScore = memberScores[g][avgScores];
			danGradeWorstScore = g;
			}
		}
		
	avgMarksTable += "The best  scoring (handicapped) dan group is " + danGradeBestScore;
	avgMarksTable += " with an average performance of :" + bestScore + "\n";
	
	avgMarksTable += "The worst scoring (handicapped) dan group is " + danGradeWorstScore;
	avgMarksTable += " with an average performance of :" + worstScore + "\n\n";
	
	avgMarksTable += "End of Report.\n\n\n";
	return avgMarksTable;
		
}

 /**getDavidKendokaFullDetails
  * generates full details for DavidKendoka competitors
  * and sorts them according to different categories
  * @param key category to sort by
  * @return sorted list of DavidKendoka competitors
  */
public String getDavidKendokaFullDetails(String key)
{	
	Sorter sortedList;
	String report =    "_____________________ Membership List ____________________" + "\n" ;
	// Char positions   0-2     4-23                25  28     30-38         40-42
	report +=          "M.No.   NAME                Age Dan   SCORES    OVERALL" + "\n";
//	Collections.sort( competitorList , compareN() );
//	Collections.sort( competitorList ,  compareN() );
	// Iterate over the list of members and build text table
	
	sortedList = new Sorter(competitorList);
	ArrayList<ParentCompetitor> sortedCompetitors = sortedList.sortByID();	// default
	System.out.println(key);
	switch(key)
	{
		case "name":
			sortedCompetitors = sortedList.sortByName();
			break;
		case "age":
			sortedCompetitors = sortedList.sortByAge();
			break;
		case "dan":
			sortedCompetitors = sortedList.sortByDan();
			break;
		case "score":
			sortedCompetitors = sortedList.sortByScore();
			break;
		case "ID":
			sortedCompetitors = sortedList.sortByID();
			break;	
			
	}
	for (ParentCompetitor m : sortedCompetitors)
		{
		if(m instanceof DavidKendoka)
			{
			// get individuals name in the longest format which fits the field available
			String person =  m.getName().getFullName();
			
			if (person.length() > (23-4+1))					// ie check name length > 20 chars
				{person = m.getName().getFirstILast();     // if so get a shorter version of name
				if (person.length() > (23-4+1))
					{person = m.getName().getFirstAndLastName();  // and a shorter version
					if (person.length() > (23-4+1))
						{person = m.getName().getInitPeriodLast();}// and the shortest version
					else
						{person = person.substring(0, 23-4+1);}    // OK give up and truncate the name
					}
				}
			if (person.length() < 23-4+1)						// add spaces to pad out field if name is shorter
				{person += "              ".substring(0,23-4+1 - person.length());  }
			String scores = m.getScoreString() ; 
					
			// now build the report
			report += " " + Integer.toString (m.getId()) + "   " ;
			report += person + " ";
			report += Integer.toString( ((DavidKendoka) m).getAge()) + "   ";
			report += Integer.toString( ((DavidKendoka) m).getDanGrade() ) + "   ";
			report += scores + "   ";
			report += String.format("%5s" ,Double.toString( m.getOverallScore()));
			report += "\n" ;
			}
		}
	report += "\nN.B. Overall score is the sum of the members 4 highest recent scores,\n";
	report += "less their dan grade handicap.\n\n" ;
	return report;
}

/**getTableTennisFullDetails
 * generates a list of TableTennis competitors and sorts them according different categories
 * @param key category to sort by
 * @return sorted list of TableTennis competitors
 */
public String getTableTennisFullDetails(String key){	
	Sorter sortedList;
	sortedList = new Sorter(competitorList);
	ArrayList<ParentCompetitor> sortedCompetitors = sortedList.sortByID();	// default
	switch(key)
	{
		case "ID":		sortedCompetitors = sortedList.sortByID();		break;
		case "score":	sortedCompetitors = sortedList.sortByScore();	break;
		case "name":	sortedCompetitors = sortedList.sortByName(); 	break;
		case "nationality": sortedCompetitors = sortedList.sortByNationality(); 	break;	
	}
	String report = "Competitor Number  Competitor Name         Level       Age  	 Nationality     Scores    OVERALL\n";
	 for(ParentCompetitor c : sortedCompetitors){
		 if(c instanceof TableTennis){
	  report +=String.format("%s", c.getId());
	  report +="                ";
	  report +=String.format("%-22s", c.getName().getFullName());
	  report +="  ";
	  report +=String.format("%-7s", c.getLevel());
	  report +="     ";
	  report +=String.format("%-10s", c.getAge());
	  report +="  ";
	  report +=String.format("%-14s", c.getN());
	  report +="";
	  report +=String.format("%-12s", c.getScoreString());
	  report +="  ";
	  report +=String.format("%.3s",  ((TableTennis) c).getOverallScore());
	  report +="\n";
		 }
	 }
	 return report;  
}

/**getTableTennisStatistics
 * generates a statistical report of TableTennis competitors
 * @return statistical report of TableTennis competitors
 */
public String getTableTennisStatistics(){
	 String report = "\nSTATISTICS\n";
	 double avg = 0;
	 int count = 0;
	 for(ParentCompetitor c : competitorList){
		 if (c instanceof TableTennis){
		 avg += c.getOverallScore();
		 count++;
		 }
	 }	 
	 report += "\nThere are " + String.format("%s", count) + " competitors." ;
	 report += "\nThe average score scored by the competitors is ";
	 report += String.format("%.3s", avg/count);
	 report += ".";
	 String maxCompName = "";
	 double maxScore = 0;
	 for (ParentCompetitor c : competitorList) { 
		 if (c instanceof TableTennis){
		 double sc = c.getOverallScore();
		 if(sc> maxScore) {
			 maxScore= sc;
			 maxCompName=c.getName().getFullName();
	 		}
		 }
	 	}	 
	 String minCompName = "";
	 double minScore = 5;
	 for (ParentCompetitor c : competitorList) {
		 if (c instanceof TableTennis){
		 double sc = c.getOverallScore();
		 if(sc<minScore) {
			 minScore= sc;
			 minCompName=c.getName().getFullName();
		 }
	 }
	 } 
	 report += "\nThe competitor with the highest score is " + maxCompName + " with an overall score of " + String.format("%.3s", maxScore) + ".";
	 report += "\nThe competitor with the lowest score is " + minCompName + " with an overall score of " + String.format("%.3s", minScore) + ".";	   
	 int [] freqOfScores = new int [6];
	 for(ParentCompetitor c : competitorList){
		 if (c instanceof TableTennis){
	  String scoreArray = c.getScoreString();
	  scoreArray.replaceAll(" ","");
	  //report +="\n" + scoreArray;
	  for (int s = 0; s < scoreArray.length() ; s++){
	   String sc=scoreArray.substring(s, s+1);
	    if (s%2==0){
	     int sco=Integer.valueOf(sc);
	     freqOfScores[sco]++;
	    }
	   } 
		 }
	  }
	 report+= "\n";
	 report +="The following individual scores were awarded:";
	 report +="\nScore:      0  1  2  3  4  5";
	 report +="\nFrequency:  ";
	 for(int scoreIndex = 0; scoreIndex < freqOfScores.length; scoreIndex++){
	  report += freqOfScores[scoreIndex] ;
	  if(String.valueOf(freqOfScores[scoreIndex]).length() == 1){
		  report += "  ";
	  }
	  else{
		  report += " ";
	  }
	  }
	 return report;
	}

/**getTableTennisStat
 * generates a report consisting of full detail of TableTennis competitors
 * and statistical information of the TableTennis category
 * @return full details of TableTennis competitors and TableTennis statistical report
 */
	public String getTableTennisStat(){
		String report = "";
		report += getTableTennisFullDetails("ID");
		report += getTableTennisStatistics();		
		return report; 	
	}

/**getVolleyballFullDetails
 * generates a list of Volleyball competitors
 * and allows to sort it by different categories
 * @param key category by which the list should be sorted
 * @return sorted list of Volleyball competitors
 */
public String getVolleyballFullDetails(String key)
{
Sorter sortedList;
sortedList = new Sorter(competitorList);
ArrayList<ParentCompetitor> sortedCompetitors = sortedList.sortByID();	// default
switch(key)
{
	case "name": 	sortedCompetitors = sortedList.sortByName();	break;
	case "score":	sortedCompetitors = sortedList.sortByScore();	break;
	case "ID":		sortedCompetitors = sortedList.sortByID();		break;
	case "position":sortedCompetitors = sortedList.sortByPosition();break;	
}	
String report = "ID    NAME                     LEVEL         POSITION              SCORES     OVERALL\n";
for (ParentCompetitor c: sortedCompetitors) {
	if(c instanceof Volleyball){
		report += String.format("%-6s", c.getId());
		report += String.format("%-25s", c.getName().getFullName());
		report += String.format("%-14s", c.getLevel());
		report += String.format("%-22s", ((Volleyball) c).getPosition());
		report += String.format("%-5s", c.getScoreString()) + "   ";
		report += String.format("%-7s",  c.getOverallScore());
		report += "\n";
	}
}
return report;
}

/**etHighestOverallScoreVolleyball
 * finds the competitor with the highest overall core in Volleyball category
 * @return competitor with the highest overall score in Volleyball category
 */
public String getHighestOverallScoreVolleyball() {
	double maxOverallScore = 0;
	String winner="";
	for (ParentCompetitor c : competitorList) {
		 if (c instanceof Volleyball){
		double mxos = c.getOverallScore();
		if (mxos> maxOverallScore) {
			maxOverallScore= mxos;
			winner=c.getName().getFullName();
		}
		 }
	}	
	return "The competitor with the highest score is " + winner + " with an overall score of " + maxOverallScore;
}

/**getLowestOverallScoreVolleyball
 * finds the competitor with the lowest overall core in Volleyball category
 * @return competitor with the lowest overall score in Volleyball category
 * @return
 */
public String getLowestOverallScoreVolleyball() {
	String loser = "";
;	double minOverallScore = 5;
	for (ParentCompetitor c : competitorList) {
		 if (c instanceof Volleyball){
		double mnos = c.getOverallScore();
		if (mnos < minOverallScore) {
			minOverallScore = mnos;
			loser= c.getName().getFullName();
		}
		 }
		 }
	return "The competitor with the lowest score is " + loser + " with an overall score of " + minOverallScore;
}

/**getScoreFrequencyReportVolleyball
 * generates the frequency report of volleyball scores
 * @return the frequency report of volleyball scores
 */
public String getScoreFrequencyReportVolleyball() {
	int [] freqScores = new int [5];
	for (ParentCompetitor c : competitorList) {
		 if (c instanceof Volleyball){
		for(int i = 0; i < c.getScoreArray().length-1; i++){
			if (c.getScoreArray()[i] == 1) {
				freqScores[0]++;
			} else if (c.getScoreArray()[i]==2) {
				freqScores[1]++;
			} else if (c.getScoreArray()[i]==3){
				freqScores[2]++;
			} else if (c.getScoreArray()[i]==4){
				freqScores[3]++;
			}else if (c.getScoreArray()[i]==5){
				freqScores[4]++;
			}
		}
		}
	}
	String report2 = "FREQUENCY OF SCORES\n";
	for (int i = 0; i < freqScores.length; i++) {
		report2 += "Score " + (i+1) + " : "  + freqScores[i] + "\n";
		}
	return report2;
}

/**getTotalCompetitorsVolleyball
 * counts how many competitors are in the Volleyball category
 * @return the number of competitors in Volleyball category
 */
public String getTotalCompetitorsVolleyball(){
	int count=0;
	String report1 = "";
	for (ParentCompetitor c:competitorList) {
		 if (c instanceof Volleyball){
			 count++;
		 }
	}
	report1 =  Integer.toString(count);
	return report1;
}

/**getVolleyballStat
 * generates a statistical report of volleyball category
 * including total number of competitors, score frequency
 *  as well as competitor with the highest overall score
 *  and lowest overall score
 * @return statistical report of Volleyball category
 */
public String getVolleyballStat(){
	String report = "";
	report += getVolleyballFullDetails("ID");
	report += "\nThere are a total of " + getTotalCompetitorsVolleyball() + " volleyball players.\n";
	report += "\n" + getScoreFrequencyReportVolleyball();
	report += "\n" + getHighestOverallScoreVolleyball();
	report += "\n" + getLowestOverallScoreVolleyball();
	return report; 	
}

}
