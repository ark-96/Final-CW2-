import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;



import java.util.Arrays;

public class ParentCompetitorList {
  protected ArrayList<ParentCompetitor> competitorList; 



 public ParentCompetitorList() {
	 competitorList = new ArrayList<ParentCompetitor> ();
 }

 public ArrayList<ParentCompetitor> getCompetitorList(){
	 return competitorList;
 }
 
public String findShortDetails(int id)
	{
		for (ParentCompetitor c : competitorList )
			{			
			return ( c.getShortDetails() ) ;
			}
		return ( "Sorry despite due validation we could not retrieve this members records.\n" );
	}	

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

public boolean add(ParentCompetitor c){
 int num = c.getId();
 ParentCompetitor inList = this.findByNum(num);
 if (inList == null){
  competitorList.add(c);
  return true;
 }
 return false;
}

public ParentCompetitor findByNum(int num){
 for (ParentCompetitor c : competitorList){
  if (c.getId() == (num)){
   return c;
  }
 }
 return null;
}

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
			int recentScores [] = new int [6];
			for (int s=0 ; s < 5 ; s++)
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

public String getallMembers(){
	String report = "The short details of the competitors is as follows:\n";
	for (ParentCompetitor c: competitorList) {
		report += c.getShortDetails();
		report += "\n";
	}
	return report;
}

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

public String getallDavidKendoka(){
	String report = "The short details of the basketball competitors is as follows:\n";
	for (ParentCompetitor c: competitorList) {
		if(c instanceof DavidKendoka){
		report += c.getShortDetails();
		report += "\n";
		}
	}
	return report;
}

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

public String getHighestScore(){
	double highestScore=0;
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

public String getallDavidKendokaScoreReport(){
	double highestScore=0;
	int count = 0;
	String scoreReport = "";
	for (ParentCompetitor c: competitorList) {
		if(c instanceof DavidKendoka){
		double score = c.getOverallScore();
		if(score > highestScore){
			highestScore = score;	
		}
		}
}
	for (ParentCompetitor c: competitorList) {	
		if(c instanceof DavidKendoka){
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

public int getCompetitorCount(){
	int count = 0;
	for (ParentCompetitor c: competitorList) {
		count ++;
	}
	return count;	
}

public String getBasketballFullDetails() {
	String report = "Number               " + "Name                          " + "Country           " + "Level          " + "Scores               " + "Overall Score            \n ";
	for (ParentCompetitor c : competitorList) {
		if(c instanceof Basketball){
		report += String.format("%-6d", c.getId());
		report += String.format("%-37s", c.getName().getFullName());
		report += String.format("%-15s", ((Basketball) c).getCountry());
		report += String.format("%-15s", c.getLevel());
		report += String.format("%-20s", c.getScoreString());
		report += String.format("%-6s", c.getOverallScore());
		report += "\n"; 
		}
	}
	return report;
}

public String getBasketballStats() {
	String statistics = "";
	statistics += ("There are " + getSizeOfBasketball() + " competitors. \n");
	statistics += ("There are " + getCountOfPeopleAtLevel("Novice") + " competitor at Novice Level. \n" );
	statistics += ("There are " + getCountOfPeopleAtLevel("Standard") + " competitors at Standard Level. \n");
	statistics += ("There are " + getCountOfPeopleAtLevel("Veteran") + " competitors at Veteran Level. \n");
	statistics += ("The winner(s) of this competition is (are) " + getallBasketballScoreReport() );
	statistics += "." + getFrequencyOfScoresBasketball();			
	return statistics; 
}

public  int getSizeOfBasketball() {
	int count=0;
	for (ParentCompetitor c:competitorList) {
		if (c instanceof Basketball) {
			count++;
		}
		}
	return count;
}

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
	 report +="\nScore:  0 1 2 3 4 5";
	 report +="\nFrequency:  ";
	 for(int scoreIndex = 0; scoreIndex < freqOfScores.length; scoreIndex++){
	  report += freqOfScores[scoreIndex] + " " ;
}
	 return report;
}

//private DavidKendoka getHighestScorer()
//{
//	for(ParentCompetitor c: competitorList){
//	if(c instanceof DavidKendoka) {	
//	DavidKendoka hm = c.listOfMembers.get(0);
//	Double highScore = (double) -999;      // initialise with silly low value
//	
//	for (Kendoka m : listOfMembers)
//		{
//		double membersScore = m.getOverallScore(m);
//		if (membersScore > highScore)
//		{								// then replace hm with current member, and highScore with their score
//			hm = m;
//			highScore = membersScore;
//			}
//		}
//	return hm;							// send back the highest scoring member
//}
//
//private DavidKendoka getLowestScorer()
//{
//	DavidKendoka lm = listOfMembers.get(0);
//	Double lowScore = (double) +999;      // initialise with silly high value
//	
//	for (Kendoka m : listOfMembers)
//		{
//		double membersScore = m.getOverallScore(m);
//		if (membersScore < lowScore)
//		{								// then replace lm with current member, and lowScore with their score
//			lm = m;
//			lowScore = membersScore;
//			}
//		}
//	return lm;							// send back the lowest scoring member
//}
//
//public String getStatistics()
//{
//	String Sreport = "Highest Scoring Member\n";
//	Sreport +=       "----------------------\n";
//	Sreport += "The details of the member with the highest overall score is :\n";
//	DavidKendoka high = getHighestScorer();
//	Sreport += getFullDetails(high);
//	
//	Sreport += "\n\n";
//	Sreport += buildScoreFrequency();
//	Sreport += buildAvgScoresByDan();
//			
//	return Sreport;
//}
//
//
//private String buildScoreFrequency()
//{
//	Kendoka winner = getHighestScorer();
//	double maxScore = winner.getOverallScore(winner);
//	int high = (int) Math.round(maxScore);
//			
//	Kendoka loser = getLowestScorer();
//	double lowScore = loser.getOverallScore(loser);
//	int low = (int) Math.round(lowScore);
//	
//	
//	int range = high - low;
//	int cumCount = 0 ;     			// cumulative count of participants
//	double totalScores = 0 ;		// Total of all scores 
//			
//	//	create array, starting from low score, ie [0] represents low score rounded down
//	int [] counts = new int [ range + 1];
//		
//	// count scores
//	double score;                      // temporary variable 
//	for (Kendoka k : listOfMembers)
//		{
//		score = k.getOverallScore(k);
//		// System.out.println(range + " " + score + "index = " + ((int) (Math.round(score) - low )));
//		counts[ (int) (Math.round(score) - low ) ] ++ ;
//		totalScores += score ;
//		cumCount ++ ;
//		}
//			
//	// Start by defining title of table
//	String title = " Frequency Count of Overall (Handicapped) Scores ";
//	int tableTop = range * 6;       // Allow 6 characters for each frequency bucket
//	String heading =  "---------------------------------------".substring(0,(int) ((tableTop - title.length()) / 2)) ;
//	String frequencyTable = "\n" + "       " + heading + title + heading + "\n";
//	
//	//build intervals into table
//	frequencyTable += "Score ";
//	for (int n = 0; n < range + 1 ; n++)
//		{ frequencyTable += String.format("%4s" , Integer.toString(n + low )) + "  "; }
//	frequencyTable += "\n";
//	
//	// now build counts into table
//	frequencyTable += "Count ";
//	for (int n = 0; n < range + 1 ; n++)
//		{ frequencyTable += String.format("%4s" ,counts[n]) + "  "; }
//	frequencyTable += "\n\n\n";
//	
//	
//	frequencyTable += "Summary Statistics for these " + cumCount + " members:\n";
//	frequencyTable += "----------------------------------------\n";
//	// calculate median
//	frequencyTable += "    Median Score is :   ";
//	int countToHalf = 0;
//	int index = 0;
//	while ( countToHalf < cumCount / 2)
//		{
//		countToHalf += counts[index];
//		index ++ ;
//		//  System.out.println(index + " " + countToHalf + " " + cumCount + " " + index%2); - debugging
//		}
//	if ( index%2 == 1 )
//		{ frequencyTable += Integer.toString( index + low - 1) ; }
//	else
//		{ frequencyTable += Integer.toString( ( (index + low -1) + (index +low - 1) - 1) / 2 );}
//	
//	frequencyTable += "\n";
//	
//	// calculate Median
//	double mean = totalScores / cumCount;
//	String stringToAdd = String.format( "%.1f" , mean);
//	frequencyTable += "    Mean Score is :   " + stringToAdd + "\n";
//	
//	// calculate Mode
//	int maxFreqAt = 0;
//	int maxCount = 0;
//	boolean multiModal = false;
//	
//	for (int i = 0 ; i < counts.length ; ++ i )
//		{
//		if (( counts[i] == maxCount) && ( i != maxFreqAt))
//			{ multiModal = true ; 
//			continue ;}
//		
//		if (counts[i] > maxCount)
//			{
//			maxCount = counts[i];
//			maxFreqAt = i;
//			multiModal = false;
//			}
//		}
//	frequencyTable += "    Modal Score is :   ";
//	frequencyTable += (maxFreqAt + low) ;
//	if ( multiModal ) frequencyTable += "  But beware this data is multimodal.";
//	frequencyTable += "\n" + "    Minimum Score is : " + low + "\n";
//	frequencyTable += "    Maximum Score is : " + high;
//	frequencyTable += "\n\n";
//			
//	return frequencyTable ;
//}
//
//	
// private String buildAvgScoresByDan()
//{
//	// create a two-dimensional array of size [8][2]
//	// [8] represents the possible Dan grades 0-8, the highest rank possible in Kendo is 8th Dan, beginners start with no dan (mudan), or zero
//	// [2] represents:
//		//[0] is a count of members with a grade corresponding to the index/Dan grade
//		//[1] is a total (by grade) of Overall Score of members with that grade
//	 	//[2] will hold mean score by grade
//		
//	// Although Dan grades are integers, and our marking scheme only applies integer results, we will have to work in FP to allow for averages
//			 
//	 double [][] memberScores = new double [9][3];
//	 
//	 int count = 0;		// array index (to make code more readable)
//	 int cumScores = 1; // array index
//	 int avgScores = 2; // array index
//	 int g = 0; 		// simple grade pointer/counter
//	
//	 // iterate over memberList and populate array
//	 
//	 for (Kendoka m : listOfMembers)
//	 	{
//		 memberScores[m.danGrade][count] ++ ; 							// Increment the dan grade count
//		 memberScores[m.danGrade][cumScores] += m.getOverallScore(m) ; 	// Add m's overall score to the total for their grade
//		 }
//	 
//	 // calculate averages
//	 for ( g = 0 ; g <= 8 ; g++ )
//	 	{
//		 if ( memberScores[g][count] != 0 )
//	 		{ memberScores[g][avgScores] = memberScores[g][cumScores] / memberScores[g][count]; }
//	 		
//	 	else															// deal with zero denominator by simplifying result to zero
//	 		{memberScores[g][avgScores] = 0 ;}
//	 	}
//	 
//	 // Build output table.
//	 // Start by defining title of table
//	String title = "Average Overall (Handicapped) Scores by Dan Grade";
//	int tableTop = 9 * 7;       // Allow 7 characters for each frequency bucket, nb 9 grades hard coded, as there are only 9 grades
//	String heading =  "---------------------------------------".substring( 0 , (int) ((tableTop - title.length()) / 2)) ;
//	String avgMarksTable = "\n" + "          " + heading + title + heading + "\n";
//	
//	//build grades into table
//	avgMarksTable += "Dan Grade ";
//	for ( g = 0; g <= 8; g ++)
//		{ avgMarksTable += String.format("%5d" , (int) g) + "  "; }
//	avgMarksTable += "\n";
//	
//	// now build counts into table
//	avgMarksTable += "No.Members";
//	for ( g = 0; g <= 8; g ++)
//		{ avgMarksTable += String.format("%5d" , (int) memberScores[g][count]) + "  "; }
//	avgMarksTable += "\n";
//	
//	// now build averages into table
//	avgMarksTable += "Mean Score";
//	for ( g = 0; g <= 8; g ++)
//		{ 
//		String stringToAdd = String.format( "%.1f" , memberScores[g][avgScores] );
//		avgMarksTable += "         ".substring(0, 7 - stringToAdd.length() ) + stringToAdd ;
//		}
//	avgMarksTable += "\n\n";
//	
//	
//	// Find & Summarise best & Worst
//	int danGradeBestScore = 0 ;
//	double bestScore = -99 ;
//	int danGradeWorstScore = 0 ;
//	double worstScore = +99 ;
//	
//	
//	for ( g = 0 ; g<=8 ; g ++)
//		{
//		if ( memberScores[g][avgScores] > bestScore )		// nb does not deal with multi modal, but rewards junior grades for matching seniors
//			{
//			bestScore = memberScores[g][avgScores];
//			danGradeBestScore = g;
//			}
//		if ( memberScores[g][avgScores] < worstScore )		// nb does not deal with multi equal low frequencies, but penalises senior grades for not beating juniors
//			{
//			worstScore = memberScores[g][avgScores];
//			danGradeWorstScore = g;
//			}
//		}
//		
//	avgMarksTable += "The best  scoring (handicapped) dan group is " + danGradeBestScore;
//	avgMarksTable += " with an average performance of :" + bestScore + "\n";
//	
//	avgMarksTable += "The worst scoring (handicapped) dan group is " + danGradeWorstScore;
//	avgMarksTable += " with an average performance of :" + worstScore + "\n\n";
//	
//	avgMarksTable += "End of Report.\n\n\n";
//	return avgMarksTable;
//		
//}

public String getDavidKendokaFullDetails()
{	
	String report = "_________________ Membership List ________________" + "\n" ;
	
	
	// Char positions   0-2     4-23               25    27-35         37-39
	report +=          "M.No.   NAME               Dan   SCORES    OVERALL\n";
	// Iterate over the list of members and build text table
	for (ParentCompetitor m : competitorList)
		{
		if(m instanceof DavidKendoka){
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
		
		System.out.println( person );
		// build a string of the scores of m in for format specified (ie a b c d e instead of [a,b,c,d,e])
		String scores = Arrays.toString(m.getScoreArray()) + " ";
		scores = scores.substring(1, scores.length() - 1 - 1);  // ie trim leading and closing []
		scores = scores.replace("," , "");                      // strip out the ","
		
		
		// now build the report
		report += " " + Integer.toString (m.getId()) + "   " ;
		report += person + " ";
		report += Integer.toString( ((DavidKendoka) m).getDanGrade() ) + "   ";
		report += scores + "   ";
		//  
		report += String.format("%5s" ,Double.toString( m.getOverallScore()));
		report += "\n" ;
		}
		}
	report += "\nN.B. Overall score is the sum of the members 4 highest recent scores,\n";
	report += "less their dan grade handicap.\n\n" ;
	return report;
}

public String getTableTennisFullDetails(){	
	String report = "Competitor Number  Competitor Name         Level  	Age  	Nationality  Scores       OVERALL\n";
	 for(ParentCompetitor c : competitorList){
		 if(c instanceof TableTennis){
	  report +=String.format("%s", c.getId());
	  report +="                ";
	  report +=String.format("%-22s", c.getName().getFullName());
	  report +="  ";
	  report +=String.format("%-7s", c.getLevel());
	  report +="  ";
	  report +=String.format("%-6s", ((TableTennis) c).getAge());
	  report +="  ";
	  report +=String.format("%-11s", ((TableTennis) c).getCountry());
	  report +="  ";
	  report +=String.format("%-11s", c.getScoreString());
	  report +="  ";
	  report +=String.format("%.3s", c.getOverallScore());
	  report +="\n";
		 }
	 }
	 return report;  
}

public String getVolleyballFullDetails(){
	String report = "ID    NAME                     LEVEL         POSITION              SCORES\n";
	for (ParentCompetitor c: competitorList) {
		if(c instanceof Volleyball){
			report += String.format("%-6s", c.getId());
			report += String.format("%-25s", c.getName().getFullName());
			report += String.format("%-14s", c.getLevel());
			report += String.format("%-22s", ((Volleyball) c).getPosition());
			report += String.format("%-5s", c.getScoreString());
			report += "\n";
		}
	}
return report;
}

}
