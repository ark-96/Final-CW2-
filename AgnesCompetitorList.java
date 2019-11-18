//@author Agne Zainyte (az66@hw.ac.uk) 

/*Checked exceptions are these that are checked during the compilation time
 * and therefore it is excepted from the programmer to deal with these
 * exception when writing the program.
 * These exceptions can be found in the Java API,
 * however they do have to be imported using 'import' keyword.
 */

//This class implements all optional list operations and permits all elements, including null
import java.util.ArrayList;
//This class is used to get user input, such as from external files or keyboard
import java.util.Scanner;
//This class allows us to work with files
import java.io.File;
//This class allows us to write characters to an external file
import java.io.FileWriter;
//This class is the general class of exceptions produced by failed input/output operations
import java.io.IOException;
//This class shows an exception that the input file was not found
import java.io.FileNotFoundException;
/*This class is of exception that occurs when an attempt to store the wrong type of
 * of object into an array of objects */
import java.lang.ArrayStoreException;
//This class is of exception when it is tried to parse a non-numeric String to a number
import java.lang.NumberFormatException;


 public class CompetitorList {
	
/*Instance variables of the object (in this case CompetitorList) are usually private
 * however, they can be accessed by using methods.
 * Since access to instance variables allow to fetch or set its value it is preferred
 * to keep it private only accessible to developer */
	private ArrayList<AgzCompetitor>competitorList;
	private Scanner scan;
	
	/*Competitor list is being created using array list from input file.
	*It is specified that the list will contain the list of AgzCompetitor's
	*/
	public CompetitorList() {
		competitorList = new ArrayList<AgzCompetitor> (); }

	/*The 'add' method is being created.
	 * In the parameters the class (AgzCompetitor) and the variable name (c) are written
	 * As a result the program knows that the 'c' object has to be added to the competitor list
	 * Since we do not need anything returned from this method we use 'void'
	 */
	public void add(AgzCompetitor c) {
		competitorList.add(c);
	}
	
	/* Originally, this assignment required the array list to be imported from the external
	 * text file, however, my program fail to do so, therefore as an alternative I made a decision
	 * to populate my competitor list using 'populate' method (which is called by manager).
	 * Since AgzCompetitor is a class I can create many objects using that class simply
	 * by declaring different values for the parameters.
	 * The scores are declared in arrays using int [] arr = (int, int int, int, int} style
	 */
	public void populate () {
		int [] score1 = {2, 3, 1, 3, 4};
		AgzCompetitor c1 = new AgzCompetitor(20, new Name("Thomas Robert Daley"),"Veteran","United Kingdom", score1);
		competitorList.add(c1);
		int [] score2 = {4, 2, 4, 1, 3};
		AgzCompetitor c2 = new AgzCompetitor(21, new Name("Gregory Efthimios Louganis"), "Veteran", "USA", score2);
		competitorList.add(c2);
		int [] score3 = {3, 1, 2, 5, 5};
		AgzCompetitor c3 = new AgzCompetitor(22, new Name("Matthew Mitcham"), "Veteran", "Australia", score3);
		competitorList.add(c3);
		int [] score4 = {2, 4, 5, 3, 5};
		AgzCompetitor c4 = new AgzCompetitor(23, new Name("Chen Ruolin"), "Standard", "China", score4);
		competitorList.add(c4);
		int [] score5 = {3, 2, 4, 2, 4};
		AgzCompetitor c5 = new AgzCompetitor(24, new Name ("Laura Ann Wilkinson"), "Novice", "New Zeland", score5);
		competitorList.add(c5);
		int [] score6 = {5, 4, 3, 5, 1};
		AgzCompetitor c6 = new AgzCompetitor(25, new Name ("Jennifer Abel"), "Veteran", "Canada", score6);
		competitorList.add(c6);
		int [] score7 = {4, 2, 5, 2, 1};
		AgzCompetitor c7 = new AgzCompetitor(26, new Name ("Juan Botaella Medina"), "Novice", "Mexico", score7);
		competitorList.add(c7);
		int [] score8 = {4, 1, 5, 1, 1};
		AgzCompetitor c8 = new AgzCompetitor(27, new Name ("Patrick Hausding"), "Standard", "Germany", score8);
		competitorList.add(c8);
		int [] score9 = {5, 4, 3, 5, 3};
		AgzCompetitor c9 = new AgzCompetitor(28, new Name("Chantelle Lee Filion"), "Novice", "France", score9);
		competitorList.add(c9);
		int [] score10 = {4, 3, 5, 2, 1};
		AgzCompetitor c10 = new AgzCompetitor(29, new Name ("Birte Christoffersen Ekberg"), "Veteran", "Denmark", score10);
		competitorList.add(c10);
		int [] score11 = {5, 4, 2, 5, 5};
		AgzCompetitor c11 = new AgzCompetitor(30, new Name ("Elina Elisabeth Eggers"), "Standard", "Sweden", score11);
		competitorList.add(c11);
		int [] score12 = {5, 3, 5, 2, 1};
		AgzCompetitor c12 = new AgzCompetitor(31, new Name ("Joaquin Capilla Perez"), "Standard", "Argentina", score12);
		competitorList.add(c12);
		int [] score13 = {4, 4, 4, 1, 3};
		AgzCompetitor c13 = new AgzCompetitor(32, new Name("Gleb Sergeyevich Galperin"), "Veteran", "Russia", score13);
		competitorList.add(c13);
		int [] score14 = {3, 5, 4, 3, 5};
		AgzCompetitor c14 = new AgzCompetitor(33, new Name("Dmitriy Mikhailovich Dobroskok"), "Novice", "Ukraine", score14);
		competitorList.add(c14);
		
			
		
	}
	
	// 'readFile' method is being created
	/* scan was previously declared as an instance variable
	 * Scanner class can read text from any object which implements readable interface,
	 * in this case this is '.txt' file that the program is trying to read
	 */
	//since we do not this method to return a value it is set to be 'void'
	public void readFile (String AgzCompetitorList) {
				scan = new Scanner("AgzCompetitors.txt");
				//the loop is set run reading line by line until it approaches empty line
					while(scan.hasNextLine() ) {
						String inputLine = scan.nextLine();
						if(inputLine.length()!=0) {
							processLine(inputLine);
						}
					}
			}
	

	/*  When  the input file was being read, the program has no knowledge what the information
	 * written in the file represents.
	 *  'processLine' method consists of two steps:
	 * Firstly, it cuts the line into pieces using 'parts' method,
	 * the instruction says that each string is separated by comma
	 * Secondly, it assigns a value for separated String
	 */
	private void processLine (String line) {
		// 'try' is an opening statement when trying to account for checked exceptions
		try {
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
			AgzCompetitor c = new AgzCompetitor(num, name, level, country, scores); 
			//after information had been assigned, then newly created object is added to the list
			this.add(c);
			}
		/*As mentioned above, if the error occurs when trying to store a non-numerical string as a number
		 * then the message will be printed, showing the line where the error occurred
		 */
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '" + line + "' -" + nfe.getMessage();
			System.out.println(error);
		}
		//This exception occurs when the index used to address array items is different from its declared value (e.g.it exceeds it)
		catch (ArrayIndexOutOfBoundsException aindx) {
			String error = "Not enough items in : " + line + " index position: " + aindx.getMessage();
			System.out.println(error); }
		
		/*This exception is similar to that of NumberFormatException but this exception
		 * is used when the wrong type of object is being stored into an array
		*/
		catch (ArrayStoreException ase) {
			String error = "An attempt has been made to store the wrong type of object into an array of scores in : " + line + ase.getMessage();
			System.out.println(error);
		}
		}

	/* this method creates the report of all competitors and displays their
	 * number, full name, level, country and scores they received
	 */
	// String.format function and "%- " allows to format the spaces between String when they are printed
	public String getAllCompetitors() {
		String report = "Number               " + "Name                          " + "Country           " + "Level          " + "Scores               " + "Overall Score             ";
		for (AgzCompetitor c : competitorList) {
			report += String.format("%-6d", c.getNumber());
			report += String.format("%-37s", c.getName().getFullName());
			report += String.format("-15s", c.getCountry());
			report += String.format("%-15s", c.getLevel());
			report += String.format("%-20d", c.getScoreArray());
			report += String.format("%-6", c.getOverallScore());
			report += "\n"; }
		return report;
		}
	
	
	//This method returns a competitor at its number
	public AgzCompetitor findByNumber(int num) {
		for(AgzCompetitor c: competitorList) {
			if (c.getNumber()==(num)) {
				return c; }
		}
		return null; }
			
	
		
	//This method returns a the number of competitors that are stored in the list
	public  int getSize() {
		return competitorList.size(); }
	
	//this method counts how many competitors are in each level
	public int getCountOfPeopleAtLevel(String level) {
		int countLevel = 0;
		for (AgzCompetitor c:competitorList) {
			if(c.getLevel()==level) {
				countLevel++; }
		}
		return countLevel; }
	

		
	//This method calculates and returns the competitor with the best overall score - the winner
	public String getBestScore() {
		String winner = "";
		double bestScore = Integer.MIN_VALUE;
		for (AgzCompetitor c : competitorList) {
			bestScore = Math.max(bestScore, c.getOverallScore()); }
		for (AgzCompetitor c : competitorList) {
			if (c.getOverallScore()==bestScore) {
			 winner += (c.getName().getLastCommaFirst() + "with an overall score of " + bestScore + "\n");	
			}
		}
		return winner;
	}



	
	//This method returns a statistical information
		public String getStatisticalInfo() {
			String statistics = "";
			statistics += ("There are " + getSize() + " competitors. \n");
			statistics += ("There are " + getCountOfPeopleAtLevel("Novice") + " competitor at Novice Level" );
			statistics += ("There are " + getCountOfPeopleAtLevel("Standard") + " competitors at Standard Level");
			statistics += ("There are " + getCountOfPeopleAtLevel("Veteran") + " competitors at Veteran Level");
			statistics += ("The winner(s) of this competition is (are) " + getBestScore());
			statistics += "The following individual scores were awarded: ";
					for (AgzCompetitor c : competitorList) {
						c.getFrequencyOfScores();
					}
			return statistics; }
			
			
			


			
	// 'writeToFile' method is created to write an output into a text file
	public void writeToFile(String outputfile, String report) {
		// we are calling a constructor class to writer output into a text file
		FileWriter fw;
		// This is where exception might occur therefore we have to account for it by using try/catch
		try {
			fw = new FileWriter(outputfile);
			fw.write("The report\n" + getAllCompetitors());
			fw.write(report);
			fw.write("STATISTICAL\n" + getStatisticalInfo());
			fw.write(report);
			fw.close(); }
		/* if the output file has been moved or deletal and system cannot access it
		 * then the program will display the file name and a message that the file is not found
		 */
		catch (FileNotFoundException fnf) {
			System.out.println(outputfile + "not found");
			System.exit(1); }
	// read more about this	
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1); }
	}
 }
		
	



