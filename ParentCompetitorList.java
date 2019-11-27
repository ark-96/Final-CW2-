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
	report += "N.B. Overall score is the sum of the members 4 highest recent scores, less their dan grade handicap.\n\n" ;
	return report;
}

public String getTableTennisFullDetails()
{
	
	String report = "\n \nDETAILS OF THE COMPETITOR WITH THE HIGHEST SCORE:\n";
	  for (ParentCompetitor c : competitorList){
		  if(c instanceof TableTennis){
			  report += "\nFull details for "+ c.getId() + ": \n";
			  report +=c.getFullDetails();
	//		  report +="\n \nShort details for " + c.getCompetitorNumber() + ":\n";
			  report +=c.getShortDetails();
			  report +="\n \n";
		  }
	  }
	  return report;  
}

}
