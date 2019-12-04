import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/** Class of CompetitorList GUI
 * provides constructors to build GUIS, manage ActionPerformed and related functionality.	 
 */
public class CompetitorListGUI extends JFrame implements ActionListener 
{
	ParentCompetitorList competitorList;
	JFrame mainGUI;
	JFrame popUpGUI;
	ArrayList<JFrame>pops=new ArrayList<JFrame>();
	MenuBar mb; 
	JMenu m1, m4, m11, m12, m13, m41, m42;
	JPanel searchPanel, sportsPanel, panelSouth, panelCentre, basketballPanel, kendoPanel, tableTennisPanel, volleyballPanel;
	JButton shortDetails, search, close, help, b1, b2, b4, k1, k2, k4, t1, t2, t4, v1, v2, v4, basketball, kendoka, tabletennis, volleyball, best;
	JLabel membershipNumber, title, explain, score;
	JTextField searchField, alterScores;
	String alter="";
	int member=0;
	ArrayList<CompetitorListGUI> guiList = new ArrayList<CompetitorListGUI>();
	
	private static String popUpSport = "Kendo";
	private static String popUpSortKey = "ID";
		
	/** Constructor CompetitorListGUI
	 *  creates the main gui frame
	 * @param list The competitor array list
	 */
	public CompetitorListGUI(ParentCompetitorList list){
		
		competitorList=list;
		JFrame mainGUI = new JFrame("JADA Sports Management");
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setLocation(400,200);
		mainGUI.getContentPane().add(BorderLayout.SOUTH, buildSouth());
		mainGUI.getContentPane().add(BorderLayout.NORTH, buildMenu());
		mainGUI.getContentPane().add(BorderLayout.CENTER, buildCentre());
		mainGUI.pack();
		mainGUI.setVisible(true);	
	}
	
	/** KillMe()
	 * Makes the associated object not visible.
	 * Perhaps KillMe is an overstatement. 
	 * @param No parameter
	 */	
	public void KillMe()
	{
		popUpGUI.setVisible(false);
	}
	
	/** CompetitorListGUI2
	 * Creates the popUpGUI as JFrame, filling its content with:
	 * North pane - a list of radio buttons built in northPanel()
	 * Centre - using reportPane() 
	 * @param ParentCompetitorList popUpList, String sport, String sort
	 * 	 Sport relates to which sport the pop-up is for, sort is the key the pop-up is sorted on
	 */
	private void CompetitorListGUI2(ParentCompetitorList popUpList, String sport, String sort)
	{	
		competitorList = popUpList;
		popUpGUI = new JFrame("More Details on " + sport);
		popUpGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUpGUI.setLocation(500,300);
		popUpGUI.getContentPane().add(BorderLayout.NORTH, northPanel());
		popUpGUI.add(BorderLayout.CENTER, reportPane(popUpList, sort));
		popUpGUI.pack();
		popUpGUI.setVisible(true);
		pops.add( popUpGUI );
	}
	
	/** Constructor CompetitorListGUI
	 * creates the pop up gui frame
	 * @param numberList The competitor array list
	 * @param num parameter to differentiate from the main gui constructor using overloading
	 */
	public CompetitorListGUI(ParentCompetitorList numberList, int num)
	{	
		competitorList = numberList;
		JFrame searchGUI = new JFrame("Search for a competitor by ID" );
		searchGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchGUI.setLocation(500,300);
		searchGUI.getContentPane().add(BorderLayout.NORTH, getSearchPanel(num));
		searchGUI.pack();
		searchGUI.setVisible(true);
	}
	
	/** buildMenu
	 * creates the menubar with the necessary menu items and adds action listeners
	 * @return the menubar with all menu items
	 */
	public JMenuBar buildMenu()
	{
        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m4 = new JMenu("Help");
        m4.addActionListener(this);
        mb.add(m1);
        mb.add(m4);
        
        JMenuItem m11 = new JMenuItem("Read Input File");
        m11.addActionListener(this);
        m11.setActionCommand("Read File"); 
        m1.add(m11);
        JMenuItem m12 = new JMenuItem("Write Report to File");
        m12.addActionListener(this);
        m12.setActionCommand("Report Written to File");
        m1.add(m12);
        JMenuItem m13 = new JMenuItem("Exit");
        m13.addActionListener(this);
        m13.setActionCommand("Exit");
        m13.setToolTipText("Exit without writing report");
        m1.add(m13);
 
        JMenuItem m41 = new JMenuItem("About JADA Sports Management Software");
        m41.addActionListener(this);
        m41.setActionCommand("About");
        m4.add(m41);
        JMenuItem m42 = new JMenuItem("Help"); 
        m42.addActionListener(this);
        m42.setActionCommand("Help");
        m4.add(m42);
        
        return mb;
	}
	
	/**buildSouth
	 * creates the south panel with the necessary buttons and adds action listeners
	 * @return south panel
	 */
 	public JPanel buildSouth()
	{
 		
 		JButton best = new JButton("Best Overall Competitor");
        best.addActionListener(this);
        best.setActionCommand("Best overall report");
       
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JButton shortD = new JButton("All Short Details");
        JButton search = new JButton("Search for competitor");
        JButton close = new JButton("Close");
        panel.add(shortD); 
        panel.add(best);
        panel.add(search);
        panel.add(new JLabel("          "));		// spaces pad out the close button to the RHS
        panel.add(close);
        shortD.addActionListener(this);
        shortD.setActionCommand("Short details");
        shortD.setToolTipText("Click here for short details of all competitors");
        search.addActionListener(this);
        search.setActionCommand("Search");
        search.setToolTipText("Click here to search a competitor");
        close.addActionListener(this);
        close.setActionCommand("Close");
        close.setToolTipText("Click here to close a window and write competitor list to an output file");
        return panel;
	}
	
 	/**buildSport
 	 * builds the sport panel with the necessary buttons for the specified competitor
 	 * based on the value of the sport parameter 
 	 * Performs actions common to all 4 button stacks
 	 * @param sportPanel the JPanel with the buttons
 	 * @param col the background colour of the panel
 	 * @param sport the sport for which the panel is being designed
 	 * @param b1 first button 	
 	 * @param b2 second button 
 	 * @param b4 third button
 	 * @param b5 forth button
 	 * @return sportPanel with all the buttons aligned as specified
 	 */
	public static JPanel buildSport(JPanel sportPanel, Color col, String sport, JButton b1, JButton b2, JButton b4, JButton b5)
	{
		// Builds a stack of buttons plus heading
		sportPanel.setLayout(new BoxLayout(sportPanel, BoxLayout.Y_AXIS));
		sportPanel.setSize(800/3, (400 - 100));
		
		sportPanel.setBackground( col );
        JLabel title = new JLabel(sport);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		b5.setAlignmentX(Component.CENTER_ALIGNMENT);
		sportPanel.add(Box.createRigidArea(new Dimension(0,10)));
		sportPanel.add(title);
		sportPanel.add(Box.createRigidArea(new Dimension(0,20)));
		sportPanel.add(b1);
		sportPanel.add(Box.createRigidArea(new Dimension(0,10)));
		sportPanel.add(b2);
		sportPanel.add(Box.createRigidArea(new Dimension(0,10)));
		sportPanel.add(b4);
		sportPanel.add(Box.createRigidArea(new Dimension(0,10)));
		sportPanel.add(Box.createRigidArea(new Dimension(0,10)));
		sportPanel.add(b5);
		sportPanel.add(Box.createRigidArea(new Dimension(0,5)));
		sportPanel.add(Box.createRigidArea(new Dimension(201,5)));
		return sportPanel;
	}    	
	
	/**buildCentre
	 * creates the center panel 
	 * @return center panel with 1 panel for each of the sports 
	 */
	public JPanel buildCentre()
	{
		// Builds the centre panel of 4 sports
        JPanel cPanel = new JPanel();
        cPanel.setLayout(new FlowLayout());   
		JPanel basketP = new JPanel();
		JButton b1 = new JButton("Full Statistics");
		b1.setToolTipText("Full report of baskelball players");
		b1.addActionListener(this);
		b1.setActionCommand("Full report b");
		JButton b2 = new JButton("Short Details");
		b2.setToolTipText("Short details of baskelball players");
		b2.addActionListener(this);
		b2.setActionCommand("Short details b");
		JButton b4 = new JButton("Highest score");
		b4.addActionListener(this);
		b4.setActionCommand("Write report b");
		b4.setToolTipText("Write a statistical report to a output file");
		JButton basketball = new JButton("Competitor sorter");
		basketball.addActionListener(this);
		basketball.setActionCommand("Options b");
		basketball.setToolTipText("Click here for more options for basketball players");
		buildSport(basketP, Color.GREEN, "Basketball", b1, b2, b4, basketball);
		
		JPanel kendoP = new JPanel();
        JButton k1 = new JButton("Full Statistics");
        k1.addActionListener(this);
        k1.setActionCommand("Full report k");
        k1.setToolTipText("Full report of kendo players");
		JButton k2 = new JButton("Short Details");
		k2.addActionListener(this);
		k2.setActionCommand("Short details k");
		k2.setToolTipText("Short details of kendo players");
		JButton k4 = new JButton("Highest score");
		k4.addActionListener(this);
		k4.setActionCommand("Write report k");
		k4.setToolTipText("Write a statistical report to a output file");
		JButton kendoka = new JButton("Competitor sorter");
		kendoka.addActionListener(this);
		kendoka.setActionCommand("Options k");
		kendoka.setToolTipText("Click here for more options for kendoka");
		buildSport(kendoP, Color.GRAY , "Kendo", k1, k2, k4, kendoka);
		
		JPanel tableP = new JPanel();
        JButton t1 = new JButton("Full Statistics");
        t1.addActionListener(this);
        t1.setActionCommand("Full report t");
        t1.setToolTipText("Full report of table tennis players");
		JButton t2 = new JButton("Short Details");
		t2.addActionListener(this);
		t2.setActionCommand("Short details t");
		t2.setToolTipText("Short details of table tennis players");
		JButton t4 = new JButton("Highest score");
		t4.addActionListener(this);
		t4.setActionCommand("Write report t");
		t4.setToolTipText("Write a statistical report to a output file");
		JButton tabletennis = new JButton("Competitor sorter");
		tabletennis.addActionListener(this);
		tabletennis.setActionCommand("Options t");
		tabletennis.setToolTipText("Click here for more options for table tennis competitor group");
		buildSport(tableP, Color.RED, "Table-Tennis", t1, t2, t4, tabletennis);
		
		JPanel volleyP = new JPanel();
        JButton v1 = new JButton("Full Statistics");
        v1.addActionListener(this);
        v1.setActionCommand("Full report v");
        v1.setToolTipText("Full report of table volleyball players");
		JButton v2 = new JButton("Short Details");
		v2.addActionListener(this);
		v2.setActionCommand("Short details v");
		v2.setToolTipText("Short details of volleyball players");
		JButton v4 = new JButton("Highest score");
		v4.addActionListener(this);
		v4.setActionCommand("Write report v");
		
		v4.setToolTipText("Write a statistical report to a output file");
		JButton volleyball = new JButton("Competitor sorter");
		volleyball.addActionListener(this);
		volleyball.setActionCommand("Options v");
		volleyball.setToolTipText("Click here for more options for volleyball players");
		buildSport(volleyP, Color.CYAN, "Volleyball", v1, v2, v4, volleyball);
		
		cPanel.add(basketP);
		cPanel.add(kendoP);
		cPanel.add(tableP);
		cPanel.add(volleyP);

		return cPanel;
	}   
	
	@Override
	/**actionPerformed
	 * performs the required functions based on the value generated by the action listener
	 * @params e The keyword of the action event as read by the action listener 
	 */
	public void actionPerformed(ActionEvent e) 
	{
	
		String action = e.getActionCommand();
		
		if ((competitorList.getCompetitorCount() == 0) & !( (action.equals("Read File")) || (action.equals("Close") ) || (action.equals("About")) || (action.equals("Help")) || (action.equals("Exit") ) ))	
		{ JOptionPane.showMessageDialog(mainGUI, "Load the data form the file using File->Read Input File." ); }
		else
			{	
			
			if (action.equals("About"))
				{
					JOptionPane.showMessageDialog(null, about());
				}
			else if (action.equals("Help"))
				{
					JOptionPane.showMessageDialog(null, help());
				}
			else if (action.equals("Exit"))
			{
				System.exit(0);
			}
			else if(action.equals("Read File"))
			{
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to read the file?", "Message", JOptionPane.YES_NO_OPTION );
				if(result == JOptionPane.YES_OPTION) {
					competitorList.readFile("ArkCompetitorInput.txt");
					competitorList.readFile("Input File Davids Updated.dat");
					competitorList.readFile("Volleyball_Input_data.txt");
					competitorList.readFile("AgzCompetitors.txt");
				}
			}
			else if(action.equals("Report Written to File"))
			{
				if(competitorList.getCompetitorCount() == 0){
					JOptionPane.showMessageDialog(mainGUI, "Load the data form the file using File->Read Input File." );
				}
				else{
				String report = competitorList.getallMembers();
				String highestScore = competitorList.getHighestScore();
				competitorList.writeToFile("Output.txt", report, highestScore);
				JOptionPane.showMessageDialog(null, "Report has been written to the file 'Output.txt'");
				}
			}
			else if(action.equals("Close"))
			{
				if(competitorList.getCompetitorCount() == 0){
					JOptionPane.showMessageDialog(mainGUI, "The program is terminated.\nTo get the report written into the 'Output.txt' file load the input files using File->Read Input File." );
				}
				else{
					String report = competitorList.getallMembers();
					String highestScore = competitorList.getHighestScore();
					competitorList.writeToFile("Output.txt", report, highestScore);
					JOptionPane.showMessageDialog(null, "The program is terminated and the report has been written to the file 'Output.txt'");
				}
				System.exit(0);
				
			}
			else if(action.equals("Short details"))
			{
					JTextArea shortDetailstext = new JTextArea(competitorList.getallMembers());
					JScrollPane shortDetails = new JScrollPane(shortDetailstext);
					shortDetailstext.setLineWrap(true);
					shortDetailstext.setWrapStyleWord(true);
					shortDetailstext.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
					shortDetails.setPreferredSize(new Dimension(371,400));
					JOptionPane.showMessageDialog(mainGUI,shortDetails, "Short details of all competitors",JOptionPane.PLAIN_MESSAGE);
			}
			else if(action.equals("Full report b"))
				{	JOptionPane.showMessageDialog(null, makeReportGUI(competitorList.getBasketballStats()) );	}
			else if(action.equals("Full report k"))
				{	JOptionPane.showMessageDialog(null, makeReportGUI(competitorList.getKendoStatistics()) );	}
			else if(action.equals("Full report t"))
				{	JOptionPane.showMessageDialog(null, makeReportGUI(competitorList.getTableTennisStat()) );	}
			else if(action.equals("Full report v"))
				{	JOptionPane.showMessageDialog(null, makeReportGUI(competitorList.getVolleyballStat()) );	}
			else if(action.equals("Short details b"))
				{	JOptionPane.showMessageDialog(mainGUI, competitorList.getallBasketball());	}
			else if(action.equals("Short details k"))
				{	JOptionPane.showMessageDialog(null, makeReportGUI(competitorList.getDavidKendokaFullDetails("score")) );}
			else if(action.equals("Short details t"))
				{	JOptionPane.showMessageDialog(mainGUI, competitorList.getallTableTennis());	}
			else if(action.equals("Short details v"))
				{	JOptionPane.showMessageDialog(mainGUI, competitorList.getallVolleyball());}
	
			else if(action.equals("Write report b"))
				{	JOptionPane.showMessageDialog(null,  competitorList.getallBasketballScoreReport());}
			else if(action.equals("Write report k"))
				{	JOptionPane.showMessageDialog(null,  competitorList.getallDavidKendokaScoreReport() );}
			else if(action.equals("Write report t"))
				{	JOptionPane.showMessageDialog(null,  competitorList.getallTableTennisScoreReport());	}
			else if(action.equals("Write report v"))
				{	JOptionPane.showMessageDialog(null,  competitorList.getallVolleyballScoreReport()); }
	
			else if(action.equals("Best overall report"))
				{	JOptionPane.showMessageDialog(null, competitorList.getHighestScore());
				}
			else if(action.equals("Basketball stats"))
				{	JOptionPane.showMessageDialog(null, competitorList.getBasketballStats());}
			else if(action.equals("Options b"))
			{
				popUpSport = "Basketball";
				popUpSortKey = "ID";  						// default option when entering Popup
				buildOutGUIs(popUpSortKey, popUpSport);
			}
			else if(action.equals("Options k"))
			{
				popUpSport = "Kendo";
				popUpSortKey = "ID";  						// default option when entering Popup
				buildOutGUIs(popUpSortKey, popUpSport);
			}
			else if(action.equals("Options t"))
			{
				popUpSport = "Tabletennis";
				popUpSortKey = "ID";  						// default option when entering Popup
				buildOutGUIs(popUpSortKey, popUpSport);
			}
			else if(action.equals("Options v"))
			{
				popUpSport = "Volleyball";
				popUpSortKey = "ID";  						// default option when entering Popup
				buildOutGUIs(popUpSortKey, popUpSport);
			}
			else if (action.equals("Sort by Age"))
			{
				popUpSortKey = "age";
				killPopUp();
				buildOutGUIs(popUpSortKey, popUpSport);
			}
			else if (action.equals("Sort by Dan"))
			{
				popUpSortKey = "dan";
				killPopUp();
				buildOutGUIs(popUpSortKey, popUpSport);
			}
			else if (action.equals("Sort by Overall Score"))
			{
				popUpSortKey = "score";
				killPopUp();
				buildOutGUIs(popUpSortKey, popUpSport);
			}
			else if (action.equals("Sort by ID"))
			{
				popUpSortKey = "ID";
				killPopUp();
				buildOutGUIs(popUpSortKey, popUpSport);
			}	
			else if (action.equals("Sort by Name"))
			{
				popUpSortKey = "name";
				killPopUp();
				buildOutGUIs(popUpSortKey, popUpSport);
			}	
			else if	(action.equals("Sort by Position"))
			{
				popUpSortKey = "position";
				killPopUp();
				buildOutGUIs(popUpSortKey, popUpSport);
			}		
			else if	(action.equals("Sort by Nationality"))
			{
				popUpSortKey = "nationality";
				killPopUp();
				buildOutGUIs(popUpSortKey, popUpSport);
			}		
			else if	(action.equals("Sort by Level"))
			{
				popUpSortKey = "level";
				killPopUp();
				buildOutGUIs(popUpSortKey, popUpSport);
			}	
			
			else if (action.equals("Search"))
				{	CompetitorListGUI searchGUI= new CompetitorListGUI(competitorList, 1);	}
			else if (action.equals("View Score full details"))
			{
				try {
					if(searchField.getText().length() >0) {
						member = Integer.parseInt(searchField.getText().trim());
						JOptionPane.showMessageDialog(null, competitorList.findFullDetails(member));
					}
					else{
						JOptionPane.showMessageDialog(null, "Please enter valid id.");
					}
				}
				catch(NumberFormatException ioe){
					JOptionPane.showMessageDialog(null, "Invalid entry: Please input valid number.\nMakes sure there are no spaces or commas between the scores and only enter numerical characters.\nTry again.");
				}
			}
			else if (action.equals("Alter Score")) 
			{
				try {
					if(searchField.getText().length() >0 ) {
						member = Integer.parseInt(searchField.getText().trim());
						if(alterScores.getText().length() >0 & alterScores.getText().length() == 5) {
								alter = alterScores.getText().trim();
								int [] newScore= new int[alter.length()];
								for(int i=0; i<alter.length(); i++){
								newScore[i]=Integer.parseInt(alter.substring(i,i+1));
							}
							competitorList.setScoreArray(newScore, member);
							JOptionPane.showMessageDialog(null, "The new overall score for the competitor number " + member + " is " + competitorList.getOverallScore(member));
							String  report = "";
							for(int scoreIndex = 0; scoreIndex < newScore.length; scoreIndex++)
						 	{
								 report += newScore[scoreIndex] + " " ;
						 	}
						 }
						else{
							JOptionPane.showMessageDialog(null, "Please enter exactly 5 digits in the alter score input field.\nMakes sure there are no spaces or commas between the scores and only enter numerical characters." );
						}
					}
				}
				catch(NumberFormatException ioe){
					JOptionPane.showMessageDialog(null, "Invalid entry: Please input valid number.\nMakes sure there are no spaces or commas between the scores and only enter numerical characters.\nTry again.");
				}
			}
			else if (action.equals("Search score"))
			{
				try {
					if(searchField.getText().length() >0) {
						member = Integer.parseInt(searchField.getText().trim());
						JOptionPane.showMessageDialog(mainGUI, competitorList.findShortDetails(member));
					}
					else{
						JOptionPane.showMessageDialog(null, "Please enter valid id.\nMakes sure there are no spaces or commas between the scores and only enter numerical characters.\nTry again.");
					}
				}
				catch(NumberFormatException ioe){
					JOptionPane.showMessageDialog(null, "Invalid entry: Please input valid number.\nMakes sure there are no spaces or commas between the scores and only enter numerical characters.\nTry again.");
				}
			}	
		}
	}
	
	/** killPopUp()
	 * Method to hide unused GUI from display
	 * @param no parameters 
	 * 	 
	 */
	private void killPopUp() 
	{
		for (int i=0; i<pops.size(); ++i)
		{
			pops.get(i).setVisible( false );
		}
		guiList.removeAll(guiList);
	}
	
	/** about()
	 * Method to provide information about application
	 * @param no parameters 
	 * 	 
	 */
	public String about()
	{
		String aboutJADA = "";
		aboutJADA += "\n";
		aboutJADA += "This excellent Sports Membership software was written by:";
		aboutJADA += "\n";
		aboutJADA += "Jessica Yip\n";
		aboutJADA += "Agne Zainyte\n";
		aboutJADA += "David Spacey\n";
		aboutJADA += "Akshay Rajieve Krishnan\n";
		aboutJADA += "\n";
		aboutJADA += "Our Software is free to use, but donations and high marks are appreciated.";
		
		return aboutJADA;
	}

	/** help()
	 * Method to provide help information
	 * @param no parameters 
	 * 	 
	 */
	public String help()
	{
		String help = "";
		help += "\n";
		help += "Search our extensive Help database.";
		help += "\n";
				
		return help;
	}
	
	/** makeReportGUI
	 * puts a text string report into a JTextArea 
	 * @param report
	 * @return
	 */
	public JScrollPane makeReportGUI(String report)
	{
		JTextArea text = new JTextArea( report );
		text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		JScrollPane scrollPane = new JScrollPane(text);
		return scrollPane;
	}
	
	/**getSearchPanel
	 * creates search panel for the search popup gui
	 * @param num arbitrary constant to distinguish from main gui 
	 * @return search panel with necessary buttons and input text fields
	 */
	public JPanel getSearchPanel(int num){
			JPanel sPanel = new JPanel();
			sPanel.setLayout(new FlowLayout());
			ButtonGroup click = new ButtonGroup();
			
			JLabel label = new JLabel("Enter Membership Number");
	        searchField = new JTextField(3); // accepts upto 3 characters
	        sPanel.add(label); // Components Added using Flow Layout
	        sPanel.add(searchField);
	        searchField.addActionListener(this);
	        searchField.setToolTipText("Enter the Id of the competitor. Make sure there are no spaces or commas and only enter numerical characters.");
	        searchField.setEditable(true);
			
	        JButton search = new JButton("Search");
	        search.addActionListener(this);
	        sPanel.add(search);
	        search.setActionCommand("Search score");
	        
			JButton vs = new JButton("View full details");
			vs.addActionListener(this);
			vs.setActionCommand("View Score full details");
			sPanel.add(vs);
			click.add(vs);
			
			JLabel alter = new JLabel("Enter new scores");
	        alterScores = new JTextField(5); // accepts upto 5 characters
	        sPanel.add(alter); // Components Added using Flow Layout
	        sPanel.add(alterScores);
	        alterScores.addActionListener(this);
	        alterScores.setToolTipText("Enter the new scores. Makes sure there are no spaces or commas between the scores and only enter numerical characters.");
	        alterScores.setEditable(true);
			
			JButton as = new JButton("Alter Score");
			as.addActionListener(this);
			as.setActionCommand("Alter Score");
			sPanel.add(as);
			click.add(as);
			
			sPanel.setVisible(true);
			return sPanel;
	}
	
	/** reportPane
	 * Creates the body of the sorted report for the Competitor Sorter
	 * @param ParentCompetitorList popUpList, String sortkey
	 * @return panel - being a panel populated with a sport specific sorted report
	 * These indicate the list of members we are working with and the key to sort on.
	 */
	public JTextArea reportPane(ParentCompetitorList popUpList, String sortkey)
	{
		String report = "";
		switch (popUpSport)
		{
			case "Kendo": 
				report = popUpList.getDavidKendokaFullDetails(sortkey);
				break;
			case "Basketball":
				report = popUpList.getBasketballFullDetails(sortkey);
				break;
			case "Tabletennis":
				report = popUpList.getTableTennisFullDetails(sortkey);
				break;
			case "Volleyball":
				report = popUpList.getVolleyballFullDetails(sortkey);
				break;
		}
		JTextArea panel = new JTextArea( report );
		panel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		return panel;
	}
	
	/** northPanel()
	 * Creates the radio buttons header, with 2 common buttons and
	 * a mix of others which are sport dependent.
	 * @param Takes no parameters, but actively uses local/private
	 * @return panelN - a panel consisting of sport specific sort radio buttons
	 * instance variables of popUpsport and popUpSortKey to customise output
	 */
	public JPanel northPanel()
	{
	JPanel panelN = new JPanel();
	panelN.setLayout(new FlowLayout());
	ButtonGroup group = new ButtonGroup();
	
	JLabel explain = new JLabel("Sort By:   ");
	panelN.add(explain);
	
	JLabel label0 = new JLabel("ID");
	JRadioButton rbutton0 = new JRadioButton();
	rbutton0.addActionListener(this);
	rbutton0.setActionCommand("Sort by ID");
	panelN.add(label0);
	panelN.add(rbutton0);
	group.add(rbutton0);
	if (popUpSortKey == "ID" ) {rbutton0.setSelected(true);}	// set default
	
	JLabel label1 = new JLabel("Overall Score");
	JRadioButton rbutton1 = new JRadioButton();
	rbutton1.addActionListener(this);
	rbutton1.setActionCommand("Sort by Overall Score");
	panelN.add(label1);
	panelN.add(rbutton1);
	group.add(rbutton1);
	if (popUpSortKey == "score" ) {rbutton1.setSelected(true);} 					

	switch(popUpSport)
		{
		case "Kendo":
			JLabel klabel2 = new JLabel("Dan Grade");
			JRadioButton krbutton2 = new JRadioButton();
			krbutton2.addActionListener(this);
			krbutton2.setActionCommand("Sort by Dan");
			panelN.add(klabel2);
			panelN.add(krbutton2);
			group.add(krbutton2);
			if (popUpSortKey == "dan" ) {krbutton2.setSelected(true);}
			
			JLabel label3 = new JLabel("Age");
			JRadioButton rbutton3 = new JRadioButton();
			rbutton3.addActionListener(this);
			rbutton3.setActionCommand("Sort by Age");
			panelN.add(label3);
			panelN.add(rbutton3);
			group.add(rbutton3);
			if (popUpSortKey == "age" ) {rbutton3.setSelected(true);}
			break;
		case "Basketball":
			JLabel blabel3 = new JLabel("Name");
			JRadioButton brbutton3 = new JRadioButton();
			brbutton3.addActionListener(this);
			brbutton3.setActionCommand("Sort by Name");
			panelN.add(blabel3);
			panelN.add(brbutton3);
			group.add(brbutton3);
			if (popUpSortKey == "name" ) {brbutton3.setSelected(true);}
						
			JLabel blabel2 = new JLabel("Level");
			JRadioButton brbutton2 = new JRadioButton();
			brbutton2.addActionListener(this);
			brbutton2.setActionCommand("Sort by Level");
			panelN.add(blabel2);
			panelN.add(brbutton2);
			group.add(brbutton2);
			if (popUpSortKey == "level" ) {brbutton2.setSelected(true);}
			break;
			
		case "Tabletennis":
			JLabel tlabel2 = new JLabel("Nationality");
			JRadioButton trbutton2 = new JRadioButton();
			trbutton2.addActionListener(this);
			trbutton2.setActionCommand("Sort by Nationality");
			panelN.add(tlabel2);
			panelN.add(trbutton2);
			group.add(trbutton2);
			if (popUpSortKey == "nationality" ) {trbutton2.setSelected(true);}
			break;
		case "Volleyball":
			JLabel vlabel2 = new JLabel("Position");
			JRadioButton vrbutton2 = new JRadioButton();
			vrbutton2.addActionListener(this);
			vrbutton2.setActionCommand("Sort by Position");
			panelN.add(vlabel2);
			panelN.add(vrbutton2);
			group.add(vrbutton2);
			if (popUpSortKey == "position" ) {vrbutton2.setSelected(true);}
						
			JLabel vlabel3 = new JLabel("Name");
			JRadioButton vrbutton3 = new JRadioButton();
			vrbutton3.addActionListener(this);
			vrbutton3.setActionCommand("Sort by Name");
			panelN.add(vlabel3);
			panelN.add(vrbutton3);
			group.add(vrbutton3);
			if (popUpSortKey == "name" ) {vrbutton3.setSelected(true);}
			break;
		}
	return panelN;
	}
	
	/** buildOutGUIs
	 * pCalls CompetitorListGUI2 to create pop up competitor sorter
	 * @param String popUpSport, popUpSortKey 
	 * 	 
	 */
	public void buildOutGUIs(String popUpSortKey, String popUpSport)
	{	CompetitorListGUI2(competitorList, popUpSport, popUpSortKey );	}
	
}

