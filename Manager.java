import java.io.File;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Manager Class
 * sets up a ParentCompetitorList then delegates to a GUI
 *
 */
public class Manager{ 

	protected ParentCompetitorList allMembers;
	
	/**
	 * Manager method
	 */
	public Manager(){
		 allMembers = new ParentCompetitorList();
		 }
	
//	public void run() { 
//		allMembers.readFile("ArkCompetitorInput.txt");
//		allMembers.readFile("Input File Davids Updated.dat");
//		allMembers.readFile("Volleyball_Input_data.txt");
//		allMembers.readFile("AgzCompetitors.txt");
//
//		String report = allMembers.getallMembers();
//		String highestScore = allMembers.getHighestScore();
//		allMembers.writeToFile("Output.txt", report, highestScore);
//		}
	/**
	 * showGUI()
	 */
	public void showGUI(){
		CompetitorListGUI gui= new CompetitorListGUI(allMembers);
	 }
	
}
