import java.io.File;
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

	private ParentCompetitorList allMembers;
	public Manager ()
		{ allMembers = new ParentCompetitorList();}
	public void run() { 
allMembers.readFile("Input File Davids Updated.dat");
allMembers.readFile("Volleyball_Input_data.txt");
allMembers.readFile("AgzCompetitors.txt");
allMembers.readFile("ArkCompetitorInput.txt");
}
}
