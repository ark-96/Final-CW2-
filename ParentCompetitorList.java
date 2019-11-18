import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class ParentCompetitorList {
  private ArrayList<Competitor> competitorList; 



 public ParentCompetitorList() {
	 competitorList = new ArrayList<Competitor> ();
 }

public String findShortDetails(int id)
	{
		for (Competitor c : competitorList )
			{
			
			return ( c.getShortDetails() ) ;
			}
		return ( "Sorry despite double validation we could not retrieve this members records.\n" );
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
	  System.exit(0);
	 }
	}



public  void writeToFile(String filename, String report, String report1, String report2, String report3, String report4) {
		 FileWriter fw;
		 try {
		    fw = new FileWriter(filename);
		    fw.write("THE REPORT\n");
		    fw.write(report);
		    fw.write("\n");
		    fw.write("STATISTICAL\nThere are ");
		    fw.write(report1); 
		    fw.write(" competitors.");
		    fw.write("\n\n");
		    fw.write(report2);
		    fw.write("\n\n");
		    fw.write(report3);
		    fw.write("\n\n");
		    fw.write(report4);
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
  try {
			
	typeOfCompetitor = charAt(0).line; //set flag as first character of input line
	String a = charAt(0);  
 	line = substring( 2 ).line
      
        switch typeOfCompetitor
	{
          case(b)
          String parts [] = line.split(", ");
			String numb = parts[0];
			//trim function deletes all spaces which could interfere with values
			numb = numb.trim();
			int num = Integer.parseInt(numb);
			Name name = new Name (parts[1]);
			String level = parts[2];
			level = level.trim();
			String country = parts[3];
			country = country.trim();
			// scores are integer therefore an appropriate conversion from String is necessary
		    	int scoreLenght= parts.length - 4;
			String[] scoress = new String [scoreLenght];
			System.arraycopy(parts, 4, scoress, 0, scoreLenght);
			int array = scoress.length;
			int [] scores = new int [array];
			for(int i=0; i<array; i++) {
				scores[i] = Integer.parseInt(scoress[i].trim()); 
			}
			
			//information from the line read is implemented into an AgzCompetitor parameter and new competitor is created
			AgzCompetitor c = new AgzCompetitor(a, num, name, level, country, scores); 
			//after information had been assigned, then newly created object is added to the list
			this.add(c);
          break;
			
        switch(t)
          String parts [] = line.split(",");
	  int num = Integer.parseInt(parts[0]);    
	  Name name = new Name (parts[1]);
	  String tier = parts[2];
	  for(int f=0;f<tier.length();f++) {
	  String nationality = parts[4];
	  for(int f=0;f<nationality.length();f++) {
	  int age = Integer.parseInt(parts[3]);
	  int scoreLength = parts.length - 5;
	  int score[]=new int[scoreLength];
	  String sc[]=new String[scoreLength];  
	  System.arraycopy(parts, 5, sc, 0, scoreLength);
	  for(int scoreIndex = 0; scoreIndex < sc.length; scoreIndex++){
	   score[scoreIndex]=Integer.parseInt(sc[scoreIndex]);
	  }
	  ArkCompetitor c = new ArkCompetitor(num, fullName, tier, age, nationality, score);
	  this.add(c);
          break;
		  
        switch(k)
          String parts [] = line.split(",");
			
			// handle the easy int conversions for memberShipNo and danGrade
			int mNo = Integer.valueOf(parts[0]);
			int dan = Integer.valueOf(parts[3]);
			
			// identify if they are a coach			
			boolean coach = false;
			if ( parts[5].equals("t") )
				{coach = true ;}
			else if ( parts[5].equals("n") )
				{coach = false;}
			else 
				{
				System.out.println("Error in coach indicator, should be 'n' or 't'. I found '" + parts[5]+ "' in line:");
				System.out.println( line );	
				System.exit(0);
				}
			
			// process Shogo field
			String shogo = null;
			if ( parts[4].equals("n") )
				{shogo = null ;}
			else if ( parts[4].toUpperCase().equals("RENSHI") )
				{shogo = "Renshi";}
			else if ( parts[4].toUpperCase().equals("KYOSHI") )
				{shogo = "Kyoshi";}
			else if ( parts[4].toUpperCase().equals("HANSHI") )
				{shogo = "Hanshi";}
			else 
				{
				System.out.println("Error in shogo, should be 'n','Renshi','Kyoshi' or 'Hanshi' only. '" + parts[4]+ "'. Found in line:");
				System.out.println( line );	
				System.exit(0);
				}
			// process Names
			String bitsOfNames[] = parts[1].split(" ");
			int howManyNames = bitsOfNames.length ;
			
			String firstName = bitsOfNames[0];				// initialise first,middle,last assuming only two names
			String middleName = null;
			String lastName = bitsOfNames[1];
			if ( howManyNames == 3 )  						// ie two spaces in the name field, separating three names
				{											// then shuffle names to reflect 3 names
				lastName = bitsOfNames[2];
				middleName = bitsOfNames[1];
				}
			Name nameOnLine = new Name(firstName, middleName, lastName);
			
			// Competitors status
			Char shortStatus = parts[6];
			String longStatus = "";
			if (shortStatus = 'A') { longStatus = "Amateur" ; }
			else if (shortStatus = 'P') {longStatus = "Professional";}
			else 
				{
				System.out.println("Error in level, should be 'A' or 'P' " + parts[6]+ "'. Found in line:");
				System.out.println( line );	
				System.exit(0);
				}
			
			// recent scores	
			int recentScores [] = new int [5];
			for (int s=0 ; s < 5 ; s++)
				{ recentScores[s] = Integer.parseInt( parts[( s+7) ].trim() ) ; }
			// Date of birth
			LocalDate doB = LocalDate.parse(parts[2]); 
			
			// now construct this Kendoka			
			Kendoka memberReadFromFile = new Kendoka(mNo, nameOnLine , doB , dan, shogo , coach , longStatus , recentScores);
			// and finally add to membershipList
			this.addOneKendoka( memberReadFromFile );
          break;
		  
        switch(v)
          String parts [] = line.split(",");
			
			String id = parts[0];
			id = id.trim();
			int numid = Integer.parseInt(id);
			Name cName = new Name(parts[1].trim());
			String cLevel = parts[2];
			cLevel = cLevel.trim();
			String cPosition = parts[3];
			cPosition = cPosition.trim();
			
			//the scores are at the end of the line
			int competitorScoreLength = parts.length - 4;
			int []competitorScore = new int[competitorScoreLength];
			//System.arraycopy(parts, 4, competitorScore, 0, competitorScoreLength);
			for(int i = 0; i < competitorScore.length; i++) {
				if (parts[4 +i].trim().isEmpty()) {
					continue; 
				} 
				else {
					competitorScore[i] = Integer.parseInt(parts[4 + i].trim());
				}
			}
			
			//create competitor object and add to the list
			Competitors c = new Competitors(numid , cName , cLevel , cPosition, competitorScore);
			this.add(c);
          break;
    default:
      system.
	}
