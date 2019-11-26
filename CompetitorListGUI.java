import java.awt.*;
import javax.swing.*;

public class CompetitorListGUI extends JFrame {
	private ParentCompetitorList competitorList;
	JFrame mainGUI;
	JMenuBar menuBar;
	JMenu m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23, m24, m25, m26, m27, m28, m29, m30, m31, m32, m33, m34, m35, m36, m37, m38, m39, m40, m41, m42;
	JPanel sportsPanel, panelSouth, panelCentre, basketballPanel, kendoPanel, tableTennisPanel, volleyballPanel;
	JButton shortDetails, search, delete, close, b1, b2, b3, b4, k1, k2, k3, k4, t1, t2, t3, t4, v1, v2, v3, v4;
	JLabel membershipNumber, title;
	JTextField searchField;
	
	//gridofbuttons frame???
	
	public CompetitorListGUI(){

		// construct grid of buttons???
		JFrame mainGUI = new JFrame("JADA Sports Management");
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setLocation(400,200);
		mainGUI.getContentPane().add(BorderLayout.SOUTH, buildSouth());
		mainGUI.getContentPane().add(BorderLayout.NORTH, buildMenu());
		mainGUI.getContentPane().add(BorderLayout.CENTER, buildCentre());
		mainGUI.pack();
		mainGUI.setVisible(true);	
		System.out.println("gui works");
	
		
	}
	
	public static JMenuBar buildMenu()
	{
        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Edit");
        JMenu m3 = new JMenu("Report");
        JMenu m4 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        
        JMenuItem m11 = new JMenuItem("Read Input File");
        JMenuItem m12 = new JMenuItem("Save Members to File");
        JMenuItem m13 = new JMenuItem("Exit");
//        m11.addActionListener(this);
//        m12.addActionListener(this);
//        m13.addActionListener(this);
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        
        JMenuItem m21 = new JMenuItem("Add a Member");
        JMenuItem m22 = new JMenuItem("Delete a Member");
        JMenuItem m23 = new JMenuItem("Extend a Member to another Sport");
//        m21.addActionListener(this);
//        m22.addActionListener(this);
//        m23.addActionListener(this);
        m2.add(m21);
        m2.add(m22);
        m2.add(m23);
        
        JMenuItem m31 = new JMenuItem("Report Best Overall Competitor");
        JMenuItem m32 = new JMenuItem("Report Basket Ball Statistics");
        JMenuItem m33 = new JMenuItem("Report Kendo Statistics");
        JMenuItem m34 = new JMenuItem("Report Table Tennis Statistics");
        JMenuItem m35 = new JMenuItem("Report Volley Ball Statistics");
//        m31.addActionListener(this);
//        m32.addActionListener(this);
//        m33.addActionListener(this);
//        m34.addActionListener(this);
//        m35.addActionListener(this);
        m3.add(m31);
        m3.add(m32);
        m3.add(m33);
        m3.add(m34);
        m3.add(m35);
        
        JMenuItem m41 = new JMenuItem("About JADA Sports Management Software");
        JMenuItem m42 = new JMenuItem("Help");
//        m41.addActionListener(null);
//        m42.addActionListener(this);
        m4.add(m41);
        m4.add(m42);
        
        return mb;
	}
	
 	public static JPanel buildSouth()
	{
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JButton shortD = new JButton("All Short Details");
        JLabel label = new JLabel("Enter Membership Number");
        JTextField mn = new JTextField(3); // accepts upto 3 characters
        JButton search = new JButton("Search");
        JButton delete = new JButton("Delete");
        JButton close = new JButton("Close");
        panel.add(shortD); 
        panel.add(label); // Components Added using Flow Layout
        panel.add(mn);
        panel.add(search);
        panel.add(delete);
        panel.add(new JLabel("          "));		// spaces pad out the close button to the RHS
        panel.add(close);
//        shortD.addActionListener(this);
//        search.addActionListener(this);
//        delete.addActionListener(this);`
//        close.addActionListener(this);
        
        return panel;
	}
	
	public static JPanel buildSport(JPanel sportPanel, Color col, String sport, JButton b1, JButton b2, JButton b3, JButton b4)
	{
		// Builds a stack of buttons plus heading
		sportPanel.setLayout(new BoxLayout(sportPanel, BoxLayout.Y_AXIS));
		sportPanel.setSize(800/3, (400 - 100));
		sportPanel.setBackground( col );
        JLabel title = new JLabel(sport);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		sportPanel.add(title);
		sportPanel.add(b1);
		sportPanel.add(b2);
		sportPanel.add(b3);
		sportPanel.add(b4);
//		b1.addActionListener(this);
//		b2.addActionListener(this);
//		b3.addActionListener(this);
//		b4.addActionListener(this);
		return sportPanel;
	}    	
	
	public static JPanel buildCentre()
	{
		// Builds the centre panel of 4 sports
        JPanel cPanel = new JPanel();
        cPanel.setLayout(new FlowLayout());;
        
		JPanel basketP = new JPanel();
		JButton b1 = new JButton("Full Statistics");
		JButton b2 = new JButton("Short Details");
		JButton b3 = new JButton("Write Members to File"); 
		JButton b4 = new JButton("Write report to File");
		buildSport(basketP, Color.GREEN, "Basket Ball", b1, b2, b3, b4);
		
		JPanel kendoP = new JPanel();
        JButton k1 = new JButton("Full Statistics");
		JButton k2 = new JButton("Short Details");
		JButton k3 = new JButton("Write members to file");
		JButton k4 = new JButton("Write report to file");
		buildSport(kendoP, Color.GRAY , "Kendo", k1, k2, k3, k4);
		
		JPanel tableP = new JPanel();
        JButton t1 = new JButton("Full Statistics");
		JButton t2 = new JButton("Short Details");
		JButton t3 = new JButton("Write members to file");
		JButton t4 = new JButton("Write report to file");
		buildSport(tableP, Color.RED, "Table Tennis", t1, t2, t3, t4);
		
		JPanel volleyP = new JPanel();
        JButton v1 = new JButton("Full Statistics");
		JButton v2 = new JButton("Short Details");
		JButton v3 = new JButton("Write members to file");
		JButton v4 = new JButton("Write report to file");
		buildSport(volleyP, Color.CYAN, "Volley Ball", v1, v2, v3, v4);
		
		cPanel.add(basketP);
		cPanel.add(kendoP);
		cPanel.add(tableP);
		cPanel.add(volleyP);

		return cPanel;
	}   
	
	
}
