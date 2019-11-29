import java.io.File;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Manager{ 
	

	protected ParentCompetitorList allMembers;
	public Manager(){
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
		CompetitorListGUI gui= new CompetitorListGUI(allMembers);
		//gui.setVisible(true);
		System.out.println("ended");
	 }
	
}

