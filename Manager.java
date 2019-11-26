import java.io.File;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Manager{ 
/** Offers the user a number of options.
 * Performs very basic validation:
 * 1. Valid option.
 * 2 Context valid - eg cannot report until imported or populated data
 * 
 */		

	protected ParentCompetitorList allMembers;
	public Manager (){
		 allMembers = new ParentCompetitorList();
		 }
	
	public void run() { 
		allMembers.readFile("ArkCompetitorInput.txt");
		allMembers.readFile("Input File Davids Updated.dat");
		allMembers.readFile("Volleyball_Input_data.txt");
		allMembers.readFile("AgzCompetitors.txt");

		System.out.println("Its alive!!!!!");
		String report = allMembers.getallMembers();
		String highestScore = allMembers.getHighestScore();
		allMembers.writeToFile("Output.txt", report, highestScore);
		}
	
	public void showGUI(){
		System.out.println("started");
		CompetitorListGUI gui= new CompetitorListGUI();
		 gui.setVisible(true);
		 
		 System.out.println("ended ");
	 }
	
}
