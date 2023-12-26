import java.awt.BorderLayout;
import java.awt.EventQueue;
 
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Map.Entry;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.SystemColor;
 
 
public class MainFrame extends JFrame {
 
	private static final long serialVersionUID = 1L;
	public JPanel Parent_Panel;
	public JTabbedPane All_Tabs_Panel;
	public JPanel Recommendations_Tab;
	public JPanel Scrolling_Pane_Holder_Panel;
 
	public JButton Data_Entry_Nav_Btn;
	public JButton Recommendations_Nav_Btn;
	public JButton Progress_Nav_Btn;
 
	public JPanel Popup_Information_Panel;
	public JLabel Rec_Name = new JLabel("");
	public JTextArea Rec_Desc = new JTextArea();
	public JTextArea Rec_Note = new JTextArea();
 
	public JComboBox Exercise_Done_Btn;
	public JTextField Reps_Done_TextField;
	public JTextField Sets_Done_TextField;
	public JTextField Weight_Done_TextField;
 
	public HashMap<String, HashMap<String, String>> Exercise_Categories = new HashMap<String, HashMap<String, String>>();
	public HashMap<String, String[]> Exercise_Sets = new HashMap<>();
	public HashMap<String, String[]> Exercise_Reps = new HashMap<>();
	public HashMap<String, String[]> Exercise_Weights = new HashMap<>();
	public HashMap<String, String> Exercise_Notes = new HashMap<>();
	public HashMap<String, String> exercises;
 
 
	//Mine
	public JPanel Profile_Tab;
	public JPanel submitPanel;
 
	private JPanel userProfilePanel;
	private JComboBox<String> genderComboBox;
	private JTextField weightTextField;
	private JComboBox<String> experienceComboBox;
	private JComboBox<String> muscleGoalComboBox;  // New dropdown for Muscle Goal
	private JComboBox<String> weightGoalComboBox;  // New dropdown for Weight Goal
	private JButton submitButton;
	private JButton editButton;
 
	// Global variables to store user information
	private String userGender = "";
	private double userWeight;
	private String userExperience = "";
	private String userMuscleGoal = "";  // Store Muscle Goal
	private String userWeightGoal = "";  // Store Weight Goal
	private int mode = 0;
	
	private JComboBox<String> dateComboBox;
    private JTabbedPane mainTabbedPane;
    private HashMap<String, String> entryDataMap;
    private JPanel dailySummary;
    private JPanel weeklySummary;
    private JPanel monthlySummary;
    private JPanel allSummary;
    
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
 
	public MainFrame() 
	{
 
		//1st Make Parent_Panel
		make_Parent_Panel();
 
		//2nd Make All_Tabs_Panel
		make_All_Tabs_Panel();
 
		//3rd Make Home_Tab
		make_Home_Tab();
 
		//4th Make Data_Entry_Tab()
		make_Data_Entry_Tab();
 
		//5th Make Recommendations_Tab and add to All_Tabs_Panel
		make_Recommendations_Tab();
 
		//6th Make Progress_Tab();
		make_Progress_Tab();
 
		//7th Make Profile_Tab();
		make_Profile_Tab();
 
		All_Tabs_Panel.setEnabledAt(1, false);
		All_Tabs_Panel.setEnabledAt(2, false);
		All_Tabs_Panel.setEnabledAt(3, false);
 
		//DATABASE OF STUF
		// Barbell Bicep Curl
		Exercise_Sets.put("Barbell Bicep Curl", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"4",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"4"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Barbell Bicep Curl", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"10-12",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"8-10",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"8-10",    // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"8-10"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Barbell Bicep Curl", new String[] {
				"20lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"20lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"20lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"20lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"20lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"20lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"25lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"25lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"25lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"25lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"25lbs",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"25lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		// Dumbbell Hammer Curl
		Exercise_Sets.put("Dumbbell Hammer Curl", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"4",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"4"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Dumbbell Hammer Curl", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (more reps for flexibility)
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (more reps for flexibility)
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (more reps for flexibility)
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Dumbbell Hammer Curl", new String[] {
				"20lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"20lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"20lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"15lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (slightly lower weight)
				"15lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (slightly lower weight)
				"15lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (slightly lower weight)
				"25lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"25lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"25lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"20lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (slightly lower weight)
				"20lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (slightly lower weight)
				"20lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (slightly lower weight)
		});
 
		Exercise_Sets.put("Preacher Tricep Dips", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"4",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"4"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Preacher Tricep Dips", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More reps for female-specific improvements)
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More reps for female-specific improvements)
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More reps for female-specific improvements)
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Slightly fewer reps compared to male experts)
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Slightly fewer reps compared to male experts)
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Slightly fewer reps compared to male experts)
		});
 
		Exercise_Weights.put("Preacher Tricep Dips", new String[] {
				"25lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"25lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"25lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"20lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Slightly lower weights for females)
				"20lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Slightly lower weights for females)
				"20lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Slightly lower weights for females)
				"30lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"30lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"30lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"25lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Slightly lower weights for females)
				"25lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Slightly lower weights for females)
				"25lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Slightly lower weights for females)
		});
 
		// Tricep Pushdown
		Exercise_Sets.put("Tricep Pushdown", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Tricep Pushdown", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Tricep Pushdown", new String[] {
				"25lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"25lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"25lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"20lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"20lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"20lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"30lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"30lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"30lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"25lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"25lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"25lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Skull Crushers
		Exercise_Sets.put("Skull Crushers", new String[] {
				"3",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Skull Crushers", new String[] {
				"10-12",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12-15",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12-15",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",    // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",    // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",    // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Skull Crushers", new String[] {
				"30lbs",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"30lbs",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"30lbs",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"25lbs",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"25lbs",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"25lbs",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"40lbs",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"40lbs",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"40lbs",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"35lbs",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"35lbs",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"35lbs"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Standing Military Press
		Exercise_Sets.put("Standing Military Press", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Standing Military Press", new String[] {
				"6-8",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"6-8",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"6-8",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"8-10", // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"8-10", // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"8-10", // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4-6",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4-6",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4-6",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"6-8",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"6-8",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"6-8"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Standing Military Press", new String[] {
				"40lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"40lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"40lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"30lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"30lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"30lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"50lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"50lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"50lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"40lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"40lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"40lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Lateral Raises
		Exercise_Sets.put("Lateral Raises", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Lateral Raises", new String[] {
				"12-15",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"15-20",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"15-20",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"15-20",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"15-20",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"15-20",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"15-20",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"20-25",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"20-25",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"20-25"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Lateral Raises", new String[] {
				"8lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"8lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"8lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"5lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"5lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"5lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"10lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"10lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"10lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"8lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"8lbs",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"8lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Bent Over Reverse Flyes
		Exercise_Sets.put("Bent Over Reverse Flyes", new String[] {
				"3",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Bent Over Reverse Flyes", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Bent Over Reverse Flyes", new String[] {
				"15lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"15lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"15lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"20lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"20lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"20lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"15lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"15lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"15lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Incline Bench Press
		Exercise_Sets.put("Incline Bench Press", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Increased for female-specific improvements)
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Increased for female-specific improvements)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Increased for female-specific improvements)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Increased for female-specific improvements)
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Increased for female-specific improvements)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Increased for female-specific improvements)
		});
 
		Exercise_Reps.put("Incline Bench Press", new String[] {
				"8-10",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight (Slightly higher for female-specific improvements)
				"8-10",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight (Slightly higher for female-specific improvements)
				"8-10",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight (Slightly higher for female-specific improvements)
				"10-12",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"6-8",   // Mode 6: Male Expert - Gain Muscle and Lose Weight (Slightly higher for female-specific improvements)
				"6-8",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight (Slightly higher for female-specific improvements)
				"6-8",   // Mode 8: Male Expert - Gain Muscle and Gain Weight (Slightly higher for female-specific improvements)
				"8-10",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"8-10",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"8-10"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Incline Bench Press", new String[] {
				"135lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight (Slightly lower for female-specific improvements)
				"135lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight (Slightly lower for female-specific improvements)
				"135lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight (Slightly lower for female-specific improvements)
				"95lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"95lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"95lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"165lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight (Slightly lower for female-specific improvements)
				"165lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight (Slightly lower for female-specific improvements)
				"165lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight (Slightly lower for female-specific improvements)
				"115lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"115lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"115lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Incline Dumbbell Flyes
		Exercise_Sets.put("Incline Dumbbell Flyes", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"4",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Incline Dumbbell Flyes", new String[] {
				"8-10",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"8-10",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"8-10",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"6-8",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"6-8",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"10-12", // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"6-8",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"6-8",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"6-8",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4-6",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"4-6",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"8-10"  // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Incline Dumbbell Flyes", new String[] {
				"25lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"25lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"25lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"20lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"20lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"15lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"30lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"30lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"30lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"25lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"25lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"20lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Incline Push-Ups
		Exercise_Sets.put("Incline Push-Ups", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More sets for female-specific exercises)
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More sets for female-specific exercises)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More sets for female-specific exercises)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More sets for female-specific exercises)
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More sets for female-specific exercises)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More sets for female-specific exercises)
		});
 
		Exercise_Reps.put("Incline Push-Ups", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More reps for female-specific exercises)
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More reps for female-specific exercises)
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More reps for female-specific exercises)
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More reps for female-specific exercises)
				"10-12",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More reps for female-specific exercises)
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More reps for female-specific exercises)
		});
 
		Exercise_Weights.put("Incline Push-Ups", new String[] {
				"None",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight (No additional weight)
				"None",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight (No additional weight)
				"None",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight (No additional weight)
				"None",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (No additional weight)
				"None",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (No additional weight)
				"None",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (No additional weight)
				"None",  // Mode 6: Male Expert - Gain Muscle and Lose Weight (No additional weight)
				"None",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight (No additional weight)
				"None",  // Mode 8: Male Expert - Gain Muscle and Gain Weight (No additional weight)
				"None",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (No additional weight)
				"None",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (No additional weight)
				"None"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (No additional weight)
		});
 
 
		// Decline Bench Press
		Exercise_Sets.put("Decline Bench Press", new String[] {
				"4",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"5",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"5",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"5",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Decline Bench Press", new String[] {
				"8-10",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"8-10",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"8-10",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"10-12",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"6-8",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"6-8",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"6-8",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"8-10",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"8-10"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Decline Bench Press", new String[] {
				"75lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"75lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"75lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"50lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"55lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"60lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"95lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"100lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"105lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"60lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"65lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"70lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Decline Dumbbell Flyes
		Exercise_Sets.put("Decline Dumbbell Flyes", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Decline Dumbbell Flyes", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Decline Dumbbell Flyes", new String[] {
				"25lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"25lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"25lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"20lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"20lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"20lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"30lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"30lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"30lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"25lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"25lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"25lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Chest Dips
		Exercise_Sets.put("Chest Dips", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Chest Dips", new String[] {
				"8-10",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"8-10",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"8-10",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"6-8",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"6-8",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"6-8",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"6-8",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"6-8",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"6-8",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4-6",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"4-6",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"4-6"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Chest Dips", new String[] {
				"None",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"None",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"None",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"None",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"None",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"None"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Bent-Over Rows
		Exercise_Sets.put("Bent-Over Rows", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More sets for physique and flexibility)
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More sets for physique and flexibility)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More sets for physique and flexibility)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More sets for physique and flexibility)
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More sets for physique and flexibility)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More sets for physique and flexibility)
		});
 
		Exercise_Reps.put("Bent-Over Rows", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More reps for physique and flexibility)
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More reps for physique and flexibility)
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More reps for physique and flexibility)
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More reps for physique and flexibility)
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More reps for physique and flexibility)
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More reps for physique and flexibility)
		});
 
		Exercise_Weights.put("Bent-Over Rows", new String[] {
				"30lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"30lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"30lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"25lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Lower weight for females)
				"25lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Lower weight for females)
				"25lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Lower weight for females)
				"35lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"35lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"35lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"30lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Lower weight for females)
				"30lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Lower weight for females)
				"30lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Lower weight for females)
		});
 
 
		// Face Pulls
		Exercise_Sets.put("Face Pulls", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Face Pulls", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Face Pulls", new String[] {
				"20lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"20lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"20lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"15lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"15lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"15lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"25lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"25lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"25lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"20lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"20lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"20lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Deadlift
		Exercise_Sets.put("Deadlift", new String[] {
				"3",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"4",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"4"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Deadlift", new String[] {
				"6-8",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"6-8",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"6-8",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"5-7",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"5-7",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"5-7",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4-6",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4-6",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4-6",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"3-5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"3-5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"3-5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Deadlift", new String[] {
				"150lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"150lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"150lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"120lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"120lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"120lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"180lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"180lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"180lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"140lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"140lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"140lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Seated Cable Rows
		Exercise_Sets.put("Seated Cable Rows", new String[] {
				"3",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"4",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Seated Cable Rows", new String[] {
				"8-10",    // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"8-10",    // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"8-10",    // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"10-12",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"10-12",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12-15",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"6-8",     // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"6-8",     // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"6-8",     // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"8-10",    // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"8-10",    // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Seated Cable Rows", new String[] {
				"100lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"100lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"100lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"80lbs",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"80lbs",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"60lbs",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"120lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"120lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"120lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"100lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"100lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"80lbs"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Hyperextensions
		Exercise_Sets.put("Hyperextensions", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"5",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"5",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"6",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"5",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"6",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"7",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"6"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Hyperextensions", new String[] {
				"12-15",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"15-20",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"15-20",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"15-20",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"10-12",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"10-12",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"12-15",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"12-15",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"12-15"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Hyperextensions", new String[] {
				"10lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"8lbs",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"8lbs",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"8lbs",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"15lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"15lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"15lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"12lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"12lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"12lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Pull-Ups (No weights, set weight to 0)
		Exercise_Sets.put("Pull-Ups", new String[] {
				"3",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Pull-Ups", new String[] {
				"6-8",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"6-8",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"6-8",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"6-8",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"6-8",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"8-10",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12", // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12", // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"  // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Pull-Ups", new String[] {
				"None", // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"None", // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"None", // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"None", // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"None", // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"None", // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"None", // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"None", // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"None", // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"None", // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"None",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"None"  // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Lat Pulldowns
		Exercise_Sets.put("Lat Pulldowns", new String[] {
				"3",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Lat Pulldowns", new String[] {
				"10-12",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12-15",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12-15",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",    // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",    // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",    // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Lat Pulldowns", new String[] {
				"100lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"100lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"100lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"80lbs",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"80lbs",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"80lbs",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"120lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"120lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"120lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"100lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"100lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"100lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Crunches (No weights, set weight to 0)
		Exercise_Sets.put("Crunches", new String[] {
				"3",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight (more sets for female physique)
				"4",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (more sets for female physique)
				"4",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight (more sets for female physique)
				"4",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",   // Mode 9: Female Expert - Gain Muscle and Lose Weight (more sets for female physique)
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (more sets for female physique)
				"5"    // Mode 11: Female Expert - Gain Muscle and Gain Weight (more sets for female physique)
		});
 
		Exercise_Reps.put("Crunches", new String[] {
				"15-20",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"15-20",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"15-20",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"20-25",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight (more reps for female strength)
				"20-25",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (more reps for female strength)
				"20-25",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight (more reps for female strength)
				"20-25",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"20-25",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"20-25",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"25-30",   // Mode 9: Female Expert - Gain Muscle and Lose Weight (more reps for female strength)
				"25-30",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (more reps for female strength)
				"25-30"    // Mode 11: Female Expert - Gain Muscle and Gain Weight (more reps for female strength)
		});
 
		Exercise_Weights.put("Crunches", new String[] {
				"None",    // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"None",    // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"None",    // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"None",    // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"None",    // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"None",    // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"None",    // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"None",    // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"None",    // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"None",    // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"None",    // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"None"     // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Leg Raises (No weights, set weight to 0)
		Exercise_Sets.put("Leg Raises", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Leg Raises", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Leg Raises", new String[] {
				"None",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"None",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"None",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"None",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"None",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"None"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Russian Twists (No weights, set weight to 0)
		Exercise_Sets.put("Russian Twists", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Russian Twists", new String[] {
				"12-15",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"15-20",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"15-20",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"15-20",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"10-12",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"10-12",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"12-15",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"12-15",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"12-15"  // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Russian Twists", new String[] {
				"None",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"None",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"None",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"None",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"None",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"None"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Romanian Deadlift
		Exercise_Sets.put("Romanian Deadlift", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (more focus on flexibility)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (more focus on flexibility)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (more focus on flexibility)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (more focus on flexibility)
		});
 
		Exercise_Reps.put("Romanian Deadlift", new String[] {
				"8-10",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"8-10",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"8-10",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (more reps for physique)
				"10-12",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (more reps for physique)
				"6-8",    // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"6-8",    // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"6-8",    // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"6-8",    // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (more reps for physique)
				"8-10"    // Mode 11: Female Expert - Gain Muscle and Gain Weight (more reps for physique)
		});
 
		Exercise_Weights.put("Romanian Deadlift", new String[] {
				"70lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"70lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"70lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"60lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (slightly lower weights)
				"60lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (slightly lower weights)
				"60lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (slightly lower weights)
				"90lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"90lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"90lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"80lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (slightly lower weights)
				"80lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (slightly lower weights)
				"80lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (slightly lower weights)
		});
 
 
		// Lying Leg Curls
		Exercise_Sets.put("Lying Leg Curls", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Increased for female-specific exercises)
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Increased for female-specific exercises)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Increased for female-specific exercises)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Increased for female-specific exercises)
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Increased for female-specific exercises)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Increased for female-specific exercises)
		});
 
		Exercise_Reps.put("Lying Leg Curls", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Increased for female-specific exercises)
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Increased for female-specific exercises)
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Increased for female-specific exercises)
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Increased for female-specific exercises)
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Increased for female-specific exercises)
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Increased for female-specific exercises)
		});
 
		Exercise_Weights.put("Lying Leg Curls", new String[] {
				"40lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"40lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"40lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"30lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Slightly lower for female-specific exercises)
				"30lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Slightly lower for female-specific exercises)
				"30lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Slightly lower for female-specific exercises)
				"50lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"50lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"50lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"40lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Slightly lower for female-specific exercises)
				"40lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Slightly lower for female-specific exercises)
				"40lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Slightly lower for female-specific exercises)
		});
 
 
		// Barbell Squats
		Exercise_Sets.put("Barbell Squats", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (increased sets for improved physique and flexibility)
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (increased sets for improved physique and flexibility)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (increased sets for improved physique and flexibility)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (increased sets for improved physique and flexibility)
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (increased sets for improved physique and flexibility)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (increased sets for improved physique and flexibility)
		});
 
		Exercise_Reps.put("Barbell Squats", new String[] {
				"8-10",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"8-10",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"8-10",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"10-12",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (increased reps for improved physique and flexibility)
				"10-12",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (increased reps for improved physique and flexibility)
				"10-12",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (increased reps for improved physique and flexibility)
				"10-12",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"10-12",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"10-12",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"12-15",   // Mode 9: Female Expert - Gain Muscle and Lose Weight (increased reps for improved physique and flexibility)
				"12-15",    // Mode 10: Female Expert - Gain Muscle and Maintain Weight (increased reps for improved physique and flexibility)
				"12-15"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (increased reps for improved physique and flexibility)
		});
 
		Exercise_Weights.put("Barbell Squats", new String[] {
				"100lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"100lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"100lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"80lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (lower weights for females)
				"80lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (lower weights for females)
				"80lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (lower weights for females)
				"120lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"120lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"120lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"100lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (lower weights for females)
				"100lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (lower weights for females)
				"100lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (lower weights for females)
		});
 
 
		// Leg Press Machine
		Exercise_Sets.put("Leg Press Machine", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"4",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"4"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Leg Press Machine", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Leg Press Machine", new String[] {
				"180lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"180lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"180lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"140lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"140lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"140lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"220lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"220lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"220lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"160lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"160lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"160lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Barbell Hip Thrusts
		Exercise_Sets.put("Barbell Hip Thrusts", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More reps and sets for female physique)
				"5",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More reps and sets for female physique)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More reps and sets for female physique)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"5",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More reps and sets for female physique)
				"6",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More reps and sets for female physique)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More reps and sets for female physique)
		});
 
		Exercise_Reps.put("Barbell Hip Thrusts", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More reps for flexibility)
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More reps for flexibility)
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More reps for flexibility)
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More reps for flexibility)
				"10-12",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More reps for flexibility)
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More reps for flexibility)
		});
 
		Exercise_Weights.put("Barbell Hip Thrusts", new String[] {
				"50lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"50lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"50lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"40lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Slightly lower weight for females)
				"40lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Slightly lower weight for females)
				"40lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Slightly lower weight for females)
				"60lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"60lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"60lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"50lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Slightly lower weight for females)
				"50lbs",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Slightly lower weight for females)
				"50lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Slightly lower weight for females)
		});
 
 
		// Lunges
		Exercise_Sets.put("Lunges", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"5",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"5",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"6",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"7"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Lunges", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"15-20",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"10-12",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"12-15",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"15-20"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Lunges", new String[] {
				"40lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"40lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"40lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"25lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"30lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"35lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"50lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"50lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"60lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"35lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"40lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"45lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Glute Bridges
		Exercise_Sets.put("Glute Bridges", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"5",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"5",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"6",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Glute Bridges", new String[] {
				"12-15",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"15-20",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"15-20",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"20-25",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"15-20",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"10-12",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"12-15",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"10-12",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"15-20",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"20-25",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"15-20"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Glute Bridges", new String[] {
				"None",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"None",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"None",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"None",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"None",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"None"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Standing Calf Raises
		Exercise_Sets.put("Standing Calf Raises", new String[] {
				"4",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (more sets for female physique)
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (more sets for female physique)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (more sets for female physique)
				"5",  // Mode 6: Male Expert - Gain Muscle and Lose Weight (more sets for calf development)
				"5",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight (more sets for calf development)
				"5",  // Mode 8: Male Expert - Gain Muscle and Gain Weight (more sets for calf development)
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (more sets for calf development)
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (more sets for calf development)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (more sets for calf development)
		});
 
		Exercise_Reps.put("Standing Calf Raises", new String[] {
				"12-15",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"15-20",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (more reps for female physique)
				"15-20",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (more reps for female physique)
				"15-20",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (more reps for female physique)
				"10-12",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"10-12",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"12-15",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"12-15",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"12-15"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Standing Calf Raises", new String[] {
				"60lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"60lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"60lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"45lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (lower weight for female)
				"45lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (lower weight for female)
				"45lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (lower weight for female)
				"80lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight (higher weight for calf development)
				"80lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight (higher weight for calf development)
				"80lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight (higher weight for calf development)
				"65lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (higher weight for calf development)
				"65lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (higher weight for calf development)
				"65lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (higher weight for calf development)
		});
 
 
		// Seated Calf Raises
		Exercise_Sets.put("Seated Calf Raises", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More emphasis on lower body)
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More emphasis on lower body)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More emphasis on lower body)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More emphasis on lower body)
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More emphasis on lower body)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More emphasis on lower body)
		});
 
		Exercise_Reps.put("Seated Calf Raises", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More reps for toning)
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More reps for toning)
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More reps for toning)
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More reps for toning)
				"10-12",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More reps for toning)
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More reps for toning)
		});
 
		Exercise_Weights.put("Seated Calf Raises", new String[] {
				"50lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"50lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"50lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"40lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Lower weights for beginners)
				"40lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Lower weights for beginners)
				"40lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Lower weights for beginners)
				"60lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"60lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"60lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"50lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Lower weights for beginners)
				"50lbs",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Lower weights for beginners)
				"50lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Lower weights for beginners)
		});
 
 
		// Donkey Calf Raises
		Exercise_Sets.put("Donkey Calf Raises", new String[] {
				"4",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"5",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"5",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"5",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"5",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"5",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"5",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"6",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"6",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"6"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Donkey Calf Raises", new String[] {
				"12-15",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"15-20",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"15-20",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"15-20",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"15-20",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"15-20",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"15-20",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"20-25",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"20-25",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"20-25"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Donkey Calf Raises", new String[] {
				"40lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"40lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"40lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"35lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"35lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"35lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"50lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"50lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"50lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"45lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"45lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"45lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Bicep Curls
		Exercise_Sets.put("Bicep Curls", new String[] {
				"3",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More emphasis on flexibility)
				"4",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More emphasis on flexibility)
				"4",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More emphasis on flexibility)
				"4",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",   // Mode 9: Female Expert - Gain Muscle and Lose Weight (More emphasis on flexibility)
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More emphasis on flexibility)
				"5"    // Mode 11: Female Expert - Gain Muscle and Gain Weight (More emphasis on flexibility)
		});
 
		Exercise_Reps.put("Bicep Curls", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More reps for toning)
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More reps for toning)
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More reps for toning)
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More reps for toning)
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More reps for toning)
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More reps for toning)
		});
 
		Exercise_Weights.put("Bicep Curls", new String[] {
				"25lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"25lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"25lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"15lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Slightly lower weight)
				"15lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Slightly lower weight)
				"15lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Slightly lower weight)
				"30lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"30lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"30lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"20lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Slightly lower weight)
				"20lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Slightly lower weight)
				"20lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Slightly lower weight)
		});
 
 
		// Tricep Dips
		Exercise_Sets.put("Tricep Dips", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More sets for physique)
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More sets for physique)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More sets for physique)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight (More sets for physique)
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight (More sets for physique)
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight (More sets for physique)
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More sets for physique)
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More sets for physique)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More sets for physique)
		});
 
		Exercise_Reps.put("Tricep Dips", new String[] {
				"8-10",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"8-10",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"8-10",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"10-12",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More reps for flexibility)
				"10-12",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More reps for flexibility)
				"10-12",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More reps for flexibility)
				"10-12",  // Mode 6: Male Expert - Gain Muscle and Lose Weight (More reps for flexibility)
				"10-12",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight (More reps for flexibility)
				"10-12",  // Mode 8: Male Expert - Gain Muscle and Gain Weight (More reps for flexibility)
				"12-15",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More reps for flexibility)
				"12-15",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More reps for flexibility)
				"12-15"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More reps for flexibility)
		});
 
		Exercise_Weights.put("Tricep Dips", new String[] {
				"None",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight (None exercise)
				"None",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight (None exercise)
				"None",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight (None exercise)
				"None",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (None exercise)
				"None",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (None exercise)
				"None",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (None exercise)
				"None",  // Mode 6: Male Expert - Gain Muscle and Lose Weight (None exercise)
				"None",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight (None exercise)
				"None",  // Mode 8: Male Expert - Gain Muscle and Gain Weight (None exercise)
				"None",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (None exercise)
				"None",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (None exercise)
				"None"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (None exercise)
		});
 
 
 
		//----------
 
		// Push-Ups
		Exercise_Sets.put("Push-Ups", new String[] {
				"3",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight (more sets for physique)
				"4",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (more sets for physique)
				"4",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight (more sets for physique)
				"4",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",   // Mode 9: Female Expert - Gain Muscle and Lose Weight (more sets for physique)
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight (more sets for physique)
				"5"    // Mode 11: Female Expert - Gain Muscle and Gain Weight (more sets for physique)
		});
 
		Exercise_Reps.put("Push-Ups", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (more reps for physique)
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (more reps for physique)
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (more reps for physique)
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (more reps for physique)
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (more reps for physique)
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (more reps for physique)
		});
 
		Exercise_Weights.put("Push-Ups", new String[] {
				"None",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight (bodyweight)
				"None",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight (bodyweight)
				"None",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight (bodyweight)
				"None",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (bodyweight)
				"None",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (bodyweight)
				"None",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (bodyweight)
				"None",  // Mode 6: Male Expert - Gain Muscle and Lose Weight (bodyweight)
				"None",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight (bodyweight)
				"None",  // Mode 8: Male Expert - Gain Muscle and Gain Weight (bodyweight)
				"None",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (bodyweight)
				"None",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (bodyweight)
				"None"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (bodyweight)
		});
 
 
		// Bench Press
		Exercise_Sets.put("Bench Press", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Bench Press", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Bench Press", new String[] {
				"125lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"125lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"125lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"95lbs",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"95lbs",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"95lbs",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"155lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"155lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"155lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"115lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"115lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"115lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Plank
		Exercise_Sets.put("Plank", new String[] {
				"3",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"3",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"5",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"4"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Plank", new String[] {
				"30-45 seconds",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"30-45 seconds",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"30-45 seconds",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"45-60 seconds",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"45-60 seconds",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"45-60 seconds",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"45-60 seconds",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"60-75 seconds",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"60-75 seconds",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"60-75 seconds",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"75-90 seconds",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"75-90 seconds"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Plank", new String[] {
				"None",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"None",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"None",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"None",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"None",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"None",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"None",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"None",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"None",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"None",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"None",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"None"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Hanging Leg Raises
		Exercise_Sets.put("Hanging Leg Raises", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"5",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"5",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"5",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"6",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"6"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Hanging Leg Raises", new String[] {
				"12-15",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"12-15",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"12-15",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"15-18",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"15-18",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"15-18",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"10-12",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"10-12",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"10-12",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"12-15",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"12-15",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"12-15"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Hanging Leg Raises", new String[] {
				"None",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"None",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"None",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"None",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"None",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"None"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Running
		Exercise_Sets.put("Running", new String[] {
				"4",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"4",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"5",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"5",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"5",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"5",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"5",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"5",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"6",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"6",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"6"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Running", new String[] {
				"20-30 minutes",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"20-30 minutes",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"20-30 minutes",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"30-40 minutes",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"30-40 minutes",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"30-40 minutes",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"30-40 minutes",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"30-40 minutes",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"30-40 minutes",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"40-50 minutes",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"40-50 minutes",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"40-50 minutes"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Running", new String[] {
				"None",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"None",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"None",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"None",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"None",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"None"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Cycling
		Exercise_Sets.put("Cycling", new String[] {
				"60 minutes",   // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"60 minutes",   // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"60 minutes",   // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"60 minutes",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"60 minutes",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"60 minutes",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"75 minutes",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"75 minutes",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"75 minutes",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"75 minutes",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"75 minutes",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"75 minutes"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Cycling", new String[] {
				"Moderate Intensity",     // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"Moderate Intensity",     // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"Moderate Intensity",     // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"Moderate Intensity",     // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"Moderate Intensity",     // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"Moderate Intensity",     // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"High Intensity",         // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"High Intensity",         // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"High Intensity",         // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"High Intensity",         // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"High Intensity",         // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"High Intensity"          // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Cycling", new String[] {
				"None",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"None",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"None",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"None",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"None",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"None",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"None",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"None",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"None"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
		// Jump Rope
		Exercise_Sets.put("Jump Rope", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Females focus more on cardio)
				"3",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Females focus more on cardio)
				"3",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Females focus more on cardio)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Females focus more on cardio)
				"4",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Females focus more on cardio)
				"4"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Females focus more on cardio)
		});
 
		Exercise_Reps.put("Jump Rope", new String[] {
				"10-15 minutes",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight (More cardio)
				"10-15 minutes",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight (More cardio)
				"10-15 minutes",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight (More cardio)
				"15-20 minutes",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Females focus more on cardio)
				"15-20 minutes",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Females focus more on cardio)
				"15-20 minutes",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Females focus more on cardio)
				"15-20 minutes",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"15-20 minutes",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"15-20 minutes",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"20-25 minutes",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Females focus more on cardio)
				"20-25 minutes",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Females focus more on cardio)
				"20-25 minutes"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Females focus more on cardio)
		});
 
		Exercise_Weights.put("Jump Rope", new String[] {
				"None",           // Mode 0: Male Beginner - Gain Muscle and Lose Weight (None exercise)
				"None",           // Mode 1: Male Beginner - Gain Muscle and Maintain Weight (None exercise)
				"None",           // Mode 2: Male Beginner - Gain Muscle and Gain Weight (None exercise)
				"None",           // Mode 3: Female Beginner - Gain Muscle and Lose Weight (None exercise)
				"None",           // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (None exercise)
				"None",           // Mode 5: Female Beginner - Gain Muscle and Gain Weight (None exercise)
				"None",           // Mode 6: Male Expert - Gain Muscle and Lose Weight (None exercise)
				"None",           // Mode 7: Male Expert - Gain Muscle and Maintain Weight (None exercise)
				"None",           // Mode 8: Male Expert - Gain Muscle and Gain Weight (None exercise)
				"None",           // Mode 9: Female Expert - Gain Muscle and Lose Weight (None exercise)
				"None",           // Mode 10: Female Expert - Gain Muscle and Maintain Weight (None exercise)
				"None"            // Mode 11: Female Expert - Gain Muscle and Gain Weight (None exercise)
		});
 
		//Preacher Curl
		Exercise_Sets.put("Preacher Curl", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"3",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"3",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (More emphasis on female physique)
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (More emphasis on female physique)
				"4",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (More emphasis on female physique)
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"4",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"4",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"5",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More emphasis on female physique)
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More emphasis on female physique)
				"5"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More emphasis on female physique)
		});
 
		Exercise_Reps.put("Preacher Curl", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"12-15",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Higher reps for female flexibility)
				"12-15",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Higher reps for female flexibility)
				"12-15",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Higher reps for female flexibility)
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"10-12",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (More balanced reps)
				"10-12",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (More balanced reps)
				"10-12"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (More balanced reps)
		});
 
		Exercise_Weights.put("Preacher Curl", new String[] {
				"25lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"25lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"25lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"20lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight (Slightly lower weight for females)
				"20lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight (Slightly lower weight for females)
				"20lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight (Slightly lower weight for females)
				"30lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"30lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"30lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"25lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight (Slightly lower weight for females)
				"25lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight (Slightly lower weight for females)
				"25lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight (Slightly lower weight for females)
		});
 
		Exercise_Sets.put("Dumbbell Flyes", new String[] {
				"3",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"5",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"3",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"4",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"5",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"4",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"5",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"6",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"4",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"5",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"6"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Reps.put("Dumbbell Flyes", new String[] {
				"10-12",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"10-12",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"10-12",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"8-10",   // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"8-10",   // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"8-10",   // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"8-10",   // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"8-10",   // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"8-10",   // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"8-10"    // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
		Exercise_Weights.put("Dumbbell Flyes", new String[] {
				"25lbs",  // Mode 0: Male Beginner - Gain Muscle and Lose Weight
				"25lbs",  // Mode 1: Male Beginner - Gain Muscle and Maintain Weight
				"25lbs",  // Mode 2: Male Beginner - Gain Muscle and Gain Weight
				"20lbs",  // Mode 3: Female Beginner - Gain Muscle and Lose Weight
				"20lbs",  // Mode 4: Female Beginner - Gain Muscle and Maintain Weight
				"20lbs",  // Mode 5: Female Beginner - Gain Muscle and Gain Weight
				"30lbs",  // Mode 6: Male Expert - Gain Muscle and Lose Weight
				"30lbs",  // Mode 7: Male Expert - Gain Muscle and Maintain Weight
				"30lbs",  // Mode 8: Male Expert - Gain Muscle and Gain Weight
				"25lbs",  // Mode 9: Female Expert - Gain Muscle and Lose Weight
				"25lbs",  // Mode 10: Female Expert - Gain Muscle and Maintain Weight
				"25lbs"   // Mode 11: Female Expert - Gain Muscle and Gain Weight
		});
 
 
	}
 
	public void make_Parent_Panel() 
	{
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 700);
		Parent_Panel = new JPanel();
		Parent_Panel.setBackground(Color.WHITE);
		Parent_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
 
		setContentPane(Parent_Panel);
		Parent_Panel.setLayout(null);
	}
 
	public void make_All_Tabs_Panel() 
	{
		All_Tabs_Panel = new JTabbedPane(JTabbedPane.TOP);
		All_Tabs_Panel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		All_Tabs_Panel.setBounds(0, 0, 338, 665);
		Parent_Panel.add(All_Tabs_Panel);
	}
 
	public void make_Home_Tab() 
	{
		JPanel Home_Tab = new JPanel();
		Home_Tab.setBackground(new Color(140, 198, 255));
		All_Tabs_Panel.addTab("Home", null, Home_Tab, null);
		Home_Tab.setLayout(null);
 
 
 
		Data_Entry_Nav_Btn = new JButton("Data Entry");
		Data_Entry_Nav_Btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Data_Entry_Nav_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				All_Tabs_Panel.setSelectedIndex(1);
			}
		});
		Data_Entry_Nav_Btn.setBounds(80, 431, 172, 39);
		Home_Tab.add(Data_Entry_Nav_Btn);
		Data_Entry_Nav_Btn.setEnabled(false);
 
		Recommendations_Nav_Btn = new JButton("Exercises");
		Recommendations_Nav_Btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Recommendations_Nav_Btn.setActionCommand("Exercises");
		Recommendations_Nav_Btn.setMargin(new Insets(2, 2, 2, 2));
		Recommendations_Nav_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				All_Tabs_Panel.setSelectedIndex(2);
			}
		});
		Recommendations_Nav_Btn.setBounds(80, 352, 172, 39);
		Home_Tab.add(Recommendations_Nav_Btn);
		Recommendations_Nav_Btn.setEnabled(false);
 
		Progress_Nav_Btn = new JButton("Progress");
		Progress_Nav_Btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Progress_Nav_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				All_Tabs_Panel.setSelectedIndex(3);
			}
		});
		Progress_Nav_Btn.setBounds(80, 510, 172, 39);
		Home_Tab.add(Progress_Nav_Btn);
		Progress_Nav_Btn.setEnabled(false);
 
		JButton Profile_Nav_Btn = new JButton("Profile");
		//Profile_Nav_Btn.setBackground(Color.RED);
		Profile_Nav_Btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Profile_Nav_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				All_Tabs_Panel.setSelectedIndex(4);
			}
		});
		Profile_Nav_Btn.setBounds(80, 273, 172, 39);
		Home_Tab.add(Profile_Nav_Btn);
 
		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Renzo Svartz\\Pictures\\Saved Pictures\\app2.png"));
		lblNewLabel_4.setBounds(80, 28, 172, 169);
		Home_Tab.add(lblNewLabel_4);
 
		JLabel lblNewLabel_4_2 = new JLabel("Potential Energy");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_4_2.setBounds(59, 222, 212, 29);
		Home_Tab.add(lblNewLabel_4_2);
 
	}
 
	public void make_Data_Entry_Tab() 
	{
 
		JPanel dataEntryTabPanel = new JPanel(null);
        dataEntryTabPanel.setBackground(new Color(255, 255, 255));
        JPanel dateSelection = new JPanel();
        dateSelection.setBackground(new Color(255, 255, 255));
        dateSelection.setLayout(null);
        dateComboBox = new JComboBox<>();
        dateComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateComboBox.setBounds(228, 98, 252, 54);
        dateSelection.add(dateComboBox);
 
        JTextArea txtrPleaseSelectA = new JTextArea();
        txtrPleaseSelectA.setBackground(new Color(255, 255, 255));
        txtrPleaseSelectA.setEditable(false);
        txtrPleaseSelectA.setFont(new Font("Poor Richard", Font.PLAIN, 34));
        txtrPleaseSelectA.setText("Please select a date ");
        txtrPleaseSelectA.setBounds(228, 33, 252, 54);
        dateSelection.add(txtrPleaseSelectA);
 
        // Create a HashMap to store data
        entryDataMap = new HashMap<>();
 
        List<String> dateList = generateDateList(10);
        for (int i = dateList.size()-1; i >= 0; i--) {
            dateComboBox.addItem(dateList.get(i));
        }
 
        JTabbedPane dateTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        dateTabbedPane.setBounds(2, 0, 850, 670);
 
        dateComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDate = (String) dateComboBox.getSelectedItem();
                if (selectedDate != null) {
                    addNewDateTab(dateTabbedPane, selectedDate);
                }
            }
        });
 
        //add the nested tabs
        getContentPane().setLayout(null);
        dateTabbedPane.add("Date Selection", dateSelection);
        dataEntryTabPanel.add(dateTabbedPane);
        //mainTabbedPane.add("Data Entry", dataEntryTabPanel);
        //getContentPane().add(mainTabbedPane);
        All_Tabs_Panel.addTab("Entry", null, dataEntryTabPanel, null);
	}
	
	private static List<String> generateDateList(int days) {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 
        Calendar calendar = Calendar.getInstance();
 
        calendar.add(Calendar.DAY_OF_MONTH, (-1 * days) + 1);
 
        for (int i = 0; i < days; i++) { 
            dateList.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
 
        return dateList;
    }
 
    private void addNewDateTab(JTabbedPane dateTabbedPane, String date) {
        JTextField textField1;
        JTextField textField2;
        JTextField textField3;
        JTextField textField4;
        JTextField textField5;
        JTextField textField6;
        JPanel newTabPanel = new JPanel();
        newTabPanel.setLayout(null);
 
        JLabel label1 = new JLabel("Calories for the Day:");
        label1.setBounds(20, 20, 120, 20);
        newTabPanel.add(label1);
 
        textField1 = new JTextField();
        textField1.setBounds(160, 20, 150, 30);
        newTabPanel.add(textField1);
 
        JLabel label2 = new JLabel("Current Weight:");
        label2.setBounds(20, 60, 120, 20);
        newTabPanel.add(label2);
 
        textField2 = new JTextField();
        textField2.setBounds(160, 60, 150, 30);
        newTabPanel.add(textField2);
 
        JLabel label3 = new JLabel("Protein (g):");
        label3.setBounds(20, 100, 120, 20);
        newTabPanel.add(label3);
 
        textField3 = new JTextField();
        textField3.setBounds(160, 100, 150, 30);
        newTabPanel.add(textField3);
 
        JLabel label4 = new JLabel("Carbs (g):");
        label4.setBounds(20, 140, 120, 20);
        newTabPanel.add(label4);
 
        textField4 = new JTextField();
        textField4.setBounds(160, 140, 150, 30);
        newTabPanel.add(textField4);
 
        JLabel label5 = new JLabel("Fat (g):");
        label5.setBounds(20, 180, 120, 20);
        newTabPanel.add(label5);
 
        textField5 = new JTextField();
        textField5.setBounds(160, 180, 150, 30);
        newTabPanel.add(textField5);
 
        JLabel label6 = new JLabel("Workout Type:");
        label6.setBounds(20, 220, 120, 20);
        newTabPanel.add(label6);
 
        textField6 = new JTextField();
        textField6.setBounds(160, 220, 150, 30);
        newTabPanel.add(textField6);
 
        // Add a button to save the input
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(80, 275, 150, 30);
        newTabPanel.add(saveButton);
 
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Store the input into the HashMap
                String input1 = textField1.getText();
                String input2 = textField2.getText();
                String input3 = textField3.getText();
                String input4 = textField4.getText();
                String input5 = textField5.getText();
                String input6 = textField6.getText();
 
                // Create a string to store all four inputs
                String inputData = input1 + " " + input2 + " " + input3 + " " + input4 + " " + input5 + " " + input6;
 
                entryDataMap.put(date, inputData);
            }
        });
 
        dateTabbedPane.addTab(date, newTabPanel);
    }
 
	public void make_Progress_Tab() 
	{
		JPanel Progress_Tab = new JPanel();
		Progress_Tab.setBackground(new Color(238, 238, 238));
		All_Tabs_Panel.addTab("Progress", null, Progress_Tab, null);
		Progress_Tab.setLayout(null);
	}
 
	public void ask_Profile_Tab()
	{
		userProfilePanel.removeAll();
 
	    // Create components for user profile (you can adjust the preferred size as needed)
	    genderComboBox = new JComboBox<String>(new String[]{"Male", "Female"});
	    genderComboBox.setBackground(Color.WHITE);
	    genderComboBox.setBounds(new Rectangle(156, 11, 156, 22));
	    genderComboBox.setMaximumSize(new Dimension(60, 20));
	    weightTextField = new JTextField(10);
	    weightTextField.setBackground(Color.WHITE);
	    weightTextField.setBounds(156, 44, 156, 22);
	    weightTextField.setPreferredSize(new Dimension(150, 25)); // Adjust the width and height
	    experienceComboBox = new JComboBox<String>(new String[]{"Beginner", "Advanced"});
	    experienceComboBox.setBackground(Color.WHITE);
	    experienceComboBox.setBounds(156, 77, 156, 22);
	    muscleGoalComboBox = new JComboBox<String>(new String[]{"Gain", "Maintain"});
	    muscleGoalComboBox.setBackground(Color.WHITE);
	    muscleGoalComboBox.setBounds(156, 110, 156, 22);
	    weightGoalComboBox = new JComboBox<String>(new String[]{"Lose", "Maintain", "Gain"});
	    weightGoalComboBox.setBackground(Color.WHITE);
	    weightGoalComboBox.setBounds(156, 143, 156, 22);
	    editButton = new JButton("Edit");
	    userProfilePanel.setLayout(null);
 
	    // Initial user input components
	    JLabel label_1 = new JLabel("Gender:");
	    label_1.setBounds(0, 11, 156, 22);
	    userProfilePanel.add(label_1);
	    userProfilePanel.add(genderComboBox);
	    JLabel label_2 = new JLabel("Weight (lbs):");
	    label_2.setBounds(0, 44, 156, 22);
	    userProfilePanel.add(label_2);
	    userProfilePanel.add(weightTextField);
	    JLabel label_3 = new JLabel("Experience:");
	    label_3.setBounds(0, 77, 156, 22);
	    userProfilePanel.add(label_3);
	    userProfilePanel.add(experienceComboBox);
	    JLabel label_4 = new JLabel("Muscle Goal:");
	    label_4.setBounds(0, 110, 156, 22);
	    userProfilePanel.add(label_4);
	    userProfilePanel.add(muscleGoalComboBox);
	    JLabel label_5 = new JLabel("Weight Goal:");
	    label_5.setBounds(0, 143, 156, 22);
	    userProfilePanel.add(label_5);
	    userProfilePanel.add(weightGoalComboBox);
	    Profile_Tab.add(userProfilePanel);
	    Profile_Tab.revalidate();
		Profile_Tab.repaint();
	    
	    JLabel lblNewLabel = new JLabel("");
	    lblNewLabel.setBackground(new Color(140, 198, 255));
	    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Renzo Svartz\\Pictures\\Saved Pictures\\app3.png"));
	    lblNewLabel.setBounds(80, 227, 172, 142);
	    userProfilePanel.add(lblNewLabel);
	    
	    JTextPane txtpnEmpowerYourPotential = new JTextPane();
	    txtpnEmpowerYourPotential.setEditable(false);
	    StyledDocument documentStyle = txtpnEmpowerYourPotential.getStyledDocument();
	    SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
	    StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
	    documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
	    txtpnEmpowerYourPotential.setFont(new Font("Tahoma", Font.ITALIC, 18));
	    txtpnEmpowerYourPotential.setText("Empower your potential    one muscle at a time");
	    txtpnEmpowerYourPotential.setBackground(new Color(140, 198, 255));
	    txtpnEmpowerYourPotential.setBounds(58, 404, 232, 95);
	    userProfilePanel.add(txtpnEmpowerYourPotential);
	    submitButton = new JButton("Submit");
	    submitButton.setBounds(90, 498, 135, 22);
	    userProfilePanel.add(submitButton);
	    submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
 
	    // Action listener for the Submit button
	    submitButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	            userGender = (String) genderComboBox.getSelectedItem();
	            try 
	            {
	                userWeight = Double.parseDouble(weightTextField.getText());
	            } 
	            catch (NumberFormatException ex) 
	            {
	                userWeight = 0.0; // Default value if parsing fails
	            }
 
	            userExperience = (String) experienceComboBox.getSelectedItem();
	            userMuscleGoal = (String) muscleGoalComboBox.getSelectedItem();
	            userWeightGoal = (String) weightGoalComboBox.getSelectedItem();
 
	            // Display the user's information with the Edit button
	            //Profile_Tab.remove(submitPanel);
	            edit_Profile_Tab();
 
	            Data_Entry_Nav_Btn.setEnabled(true);
	            Recommendations_Nav_Btn.setEnabled(true);
	            Progress_Nav_Btn.setEnabled(true);
	            All_Tabs_Panel.setEnabledAt(1, true);
	    		All_Tabs_Panel.setEnabledAt(2, true);
	    		All_Tabs_Panel.setEnabledAt(3, true);
	    		make_Scrolling_Pane(exercises, true);
	        }
	    });
	}
 
	public void make_Profile_Tab() 
	{
	    Profile_Tab = new JPanel();
	    Profile_Tab.setBackground(new Color(140, 198, 255));
	    All_Tabs_Panel.addTab("Profile", null, Profile_Tab, null);
	    Profile_Tab.setBorder(new EmptyBorder(10, 10, 10, 10));
	    Profile_Tab.setLayout(null);
 
	    JLabel profileHeader = new JLabel("Profile");
	    profileHeader.setBounds(10, 10, 130, 14);
	    profileHeader.setHorizontalAlignment(SwingConstants.LEFT);
	    Profile_Tab.add(profileHeader);
 
	    userProfilePanel = new JPanel();
	    userProfilePanel.setBackground(new Color(140, 198, 255));
	    userProfilePanel.setBounds(10, 24, 313, 531);
 
	    ask_Profile_Tab();
 
	    make_Exercise_Sizes();
	}
 
	// Display the user's information with an Edit button
	private void edit_Profile_Tab() 
	{
		userProfilePanel.removeAll();
		userProfilePanel.setLayout(null);
 
	    // Create components for user profile (you can adjust the preferred size as needed)
	    JLabel genderResult = new JLabel(userGender);
	    genderResult.setBounds(new Rectangle(156, 11, 156, 22));
	    userProfilePanel.add(genderResult);
 
	    JLabel weightResult = new JLabel(String.valueOf(userWeight) + "lbs");
	    weightResult.setBounds(156, 44, 156, 22);
	    userProfilePanel.add(weightResult);
 
	    JLabel experienceResult = new JLabel(userExperience);
	    experienceResult.setBounds(156, 77, 156, 22);
	    userProfilePanel.add(experienceResult);
 
	    JLabel muscleGoalResult = new JLabel(userMuscleGoal);
	    muscleGoalResult.setBounds(156, 110, 156, 22);
	    userProfilePanel.add(muscleGoalResult);
 
	    JLabel weightGoalResult = new JLabel(userWeightGoal);
	    weightGoalResult.setBounds(156, 143, 156, 22);
	    userProfilePanel.add(weightGoalResult);
 
	    // Initial user input components
	    JLabel label_1 = new JLabel("Gender:");
	    label_1.setBounds(0, 11, 156, 22);
	    userProfilePanel.add(label_1);
	    JLabel label_2 = new JLabel("Weight (lbs):");
	    label_2.setBounds(0, 44, 156, 22);
	    userProfilePanel.add(label_2);
	    JLabel label_3 = new JLabel("Experience:");
	    label_3.setBounds(0, 77, 156, 22);
	    userProfilePanel.add(label_3);
	    JLabel label_4 = new JLabel("Muscle Goal:");
	    label_4.setBounds(0, 110, 156, 22);
	    userProfilePanel.add(label_4);
	    JLabel label_5 = new JLabel("Weight Goal:");
	    label_5.setBounds(0, 143, 156, 22);
	    userProfilePanel.add(label_5);
 
	  
	    JLabel lblNewLabel = new JLabel("");
	    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Renzo Svartz\\Pictures\\Saved Pictures\\app3.png"));
	    lblNewLabel.setBounds(80, 227, 172, 142);
	    userProfilePanel.add(lblNewLabel);
	    
	    JTextPane txtpnEmpowerYourPotential = new JTextPane();
	    StyledDocument documentStyle = txtpnEmpowerYourPotential.getStyledDocument();
	    SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
	    StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
	    documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
	    txtpnEmpowerYourPotential.setFont(new Font("Tahoma", Font.ITALIC, 18));
	    txtpnEmpowerYourPotential.setBackground(new Color(140, 198, 255));
	    txtpnEmpowerYourPotential.setText("Empower your potential    one muscle at a time");
	    txtpnEmpowerYourPotential.setBounds(58, 404, 232, 95);
	    userProfilePanel.add(txtpnEmpowerYourPotential);
	    
	    submitButton = new JButton("Edit");
	    submitButton.setBounds(90, 498, 135, 22);
	    userProfilePanel.add(submitButton);
	    submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    Profile_Tab.revalidate();
		Profile_Tab.repaint();
 
	    // Action listener for the Submit button
	    submitButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	userProfilePanel.removeAll();
	            ask_Profile_Tab();
 
	        }
	    });

	}
 
	public void make_Recommendations_Tab() 
	{
 
		Recommendations_Tab = new JPanel();
		Recommendations_Tab.setBackground(new Color(140, 198, 255));
		All_Tabs_Panel.addTab("Exercises", null, Recommendations_Tab, null);
		Recommendations_Tab.setLayout(null);
 
		Scrolling_Pane_Holder_Panel = new JPanel(new GridBagLayout());
		Scrolling_Pane_Holder_Panel.setMaximumSize(new Dimension(292, 2147483647));
		Scrolling_Pane_Holder_Panel.setBounds(20, 55, 292, 433);
 
        make_Exercise_Categories();
 
        make_Exercise_Sizes();
 
        make_Daily_Workout("Full Body");
 
		make_Settings_Panel();
 
		make_Data_Entry_Panel();
 
		make_Popup_Information_Panel();
 
	}
 
 
	//if if if mode == 0 through 6
	public void make_Exercise_Sizes() 
	{
		if (userGender.equals("Male")) {
		    if (userExperience.equals("Beginner")) {
		        if (userMuscleGoal.equals("Gain")) {
		            if (userWeightGoal.equals("Lose")) {
		                mode = 0;
		            } else if (userWeightGoal.equals("Maintain")) {
		                mode = 1;
		            } else if (userWeightGoal.equals("Gain")) {
		                mode = 2;
		            }
		        }
		    } else if (userExperience.equals("Expert")) {
		        if (userMuscleGoal.equals("Gain")) {
		            if (userWeightGoal.equals("Lose")) {
		                mode = 6;
		            } else if (userWeightGoal.equals("Maintain")) {
		                mode = 7;
		            } else if (userWeightGoal.equals("Gain")) {
		                mode = 8;
		            }
		        }
		    }
		} else if (userGender.equals("Female")) {
		    if (userExperience.equals("Beginner")) {
		        if (userMuscleGoal.equals("Gain")) {
		            if (userWeightGoal.equals("Lose")) {
		                mode = 3;
		            } else if (userWeightGoal.equals("Maintain")) {
		                mode = 4;
		            } else if (userWeightGoal.equals("Gain")) {
		                mode = 5;
		            }
		        }
		    } else if (userExperience.equals("Expert")) {
		        if (userMuscleGoal.equals("Gain")) {
		            if (userWeightGoal.equals("Lose")) {
		                mode = 9;
		            } else if (userWeightGoal.equals("Maintain")) {
		                mode = 10;
		            } else if (userWeightGoal.equals("Gain")) {
		                mode = 11;
		            }
		        }
		    }
		}
 
 
 
		//Exercise_Sizes.put("Barbell Bicep Curl", "Small");
	}
 
	public void make_Exercise_Categories() 
	{
 
 
		Exercise_Categories.put("Triceps", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Tricep Dips", "Tricep dips are an effective bodyweight exercise for targeting the triceps. To perform this exercise, find parallel bars or a sturdy surface, like the edge of a bench or a set of parallel dip bars. Place your hands shoulder-width apart on the bars, with your arms fully extended and feet on the ground. Lower your body by bending your elbows until your upper arms are parallel to the ground or slightly below. Push through your palms to return to the starting position.");
	    exercises.put("Tricep Pushdown", "The tricep pushdown is a popular isolation exercise that targets the triceps. Attach a straight or V-bar attachment to a high pulley on a cable machine. Stand facing the machine, grasp the bar with an overhand grip, and position your elbows at your sides. Push the bar down by extending your elbows and fully straightening your arms, then return to the starting position. Keep your upper arms stationary throughout the movement.");
	    exercises.put("Skull Crushers", "Skull crushers, also known as lying tricep extensions, are a great exercise to isolate the triceps. To perform this exercise, lie on a flat bench with a barbell or dumbbells in your hands. Extend your arms straight up over your chest. Slowly lower the weight by bending your elbows while keeping them pointed upward and close to your head. Once the weight is close to your forehead, reverse the movement by extending your arms to return to the starting position.");
	    Exercise_Categories.get("Triceps").putAll(exercises);
 
		Exercise_Categories.put("Shoulders", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Standing Military Press", "The standing military press is a compound exercise that primarily targets the deltoid muscles. To perform this exercise, stand with your feet shoulder-width apart and hold a barbell or dumbbells at shoulder height, with your palms facing forward. Press the weight overhead by extending your arms, and then lower it back to shoulder height. Keep your core engaged and maintain proper posture throughout the movement. Can be done with either Barbell or Dumbbell.");
        exercises.put("Lateral Raises", "Lateral raises are excellent for targeting the lateral or side head of the deltoid muscles. To perform this exercise, stand or sit with a dumbbell in each hand at your sides. With a slight bend in your elbows, raise the weights to the side until your arms are parallel to the ground. Lower the weights back down to your sides slowly to complete one repetition. Can be done with either Cable or Dumbbell.");
        exercises.put("Bent Over Reverse Flyes", "Bent over reverse flyes work the rear deltoid muscles, helping to balance the shoulder development. To perform this exercise, bend at the hips to a 45-degree angle, holding a dumbbell in each hand with your palms facing each other. Keeping a slight bend in your elbows, raise your arms out to the sides until they are parallel to the ground. Lower the weights back down with control. Can be done with either Cable or Dumbbell.");
        Exercise_Categories.get("Shoulders").putAll(exercises);
 
        Exercise_Categories.put("Biceps", new HashMap<String, String>());
		exercises = new HashMap<>();
        exercises.put("Barbell Bicep Curl", "The barbell bicep curl is a classic exercise for building bicep strength and size. To perform this exercise, stand with your feet shoulder-width apart, grasp a barbell with an underhand grip (palms facing up), and let it hang at arm's length in front of your thighs. Keeping your upper arms stationary, exhale as you curl the barbell up to shoulder level while contracting your biceps. Inhale as you slowly lower the barbell back to the starting position.");
        exercises.put("Dumbbell Hammer Curl", "The dumbbell hammer curl targets the biceps brachii as well as the brachialis and brachioradialis muscles. Stand or sit with a dumbbell in each hand, palms facing your torso (neutral grip). Keep your upper arms stationary and exhale as you curl the weights while contracting your biceps. Inhale as you lower the dumbbells back down to your sides.");
        exercises.put("Preacher Curl", "The preacher curl is performed using a specialized bench or machine with an angled pad to isolate the biceps. Position your upper arms against the preacher bench pad and grip a barbell or dumbbells with an underhand grip. Slowly curl the weight upwards, exhaling as you contract your biceps. Inhale as you lower the weight back to the starting position. This exercise minimizes cheating and focuses on the biceps' peak.");
        Exercise_Categories.get("Biceps").putAll(exercises);
 
		Exercise_Categories.put("Upper Back", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Pull-Ups", "Pull-ups are a classic exercise for developing the upper back. Find a sturdy overhead bar and grasp it with an overhand grip (palms facing away from you) or a neutral grip (palms facing each other). Hang freely with your arms fully extended, then pull your body up towards the bar until your chin is above the bar. Lower your body back down with control. Pull-ups primarily target the lats, traps, and rear deltoids.");
        exercises.put("Bent-Over Rows", "Bent-over rows are excellent for targeting the upper back, including the rhomboids and trapezius muscles. To perform this exercise, stand with a barbell or dumbbells in your hands, feet shoulder-width apart. Bend at the hips to create a slight forward lean, keeping your back straight. Pull the weight towards your lower ribcage, squeezing your shoulder blades together, and then lower the weight back down.");
        exercises.put("Face Pulls", "Face pulls help strengthen the upper back and rear deltoids. Set up a rope attachment on a cable machine at shoulder height. Stand facing the machine, grasp the rope with an overhand grip, and step back a few feet. Pull the rope towards your face, separating the ends as you do so. Focus on squeezing your shoulder blades together and bringing the rope towards your nose.");
        Exercise_Categories.get("Upper Back").putAll(exercises);
 
        Exercise_Categories.put("Middle/Lower Back", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Deadlift", "The deadlift is a compound exercise that targets the entire back, including the middle and lower back. To perform a deadlift, stand in front of a barbell with your feet hip-width apart. Bend at your hips and knees to lower your body and grasp the barbell with an overhand or mixed grip. Lift the bar by extending your hips and knees, and then lower it back to the ground. Deadlifts are excellent for strengthening the erector spinae muscles in the lower back.");
        exercises.put("Seated Cable Rows", "Seated cable rows are great for targeting the middle and lower back. Sit down at a cable row machine, place your feet on the platform, and grasp the handle with an overhand grip. Sit up straight and pull the handle toward your lower abdomen while squeezing your shoulder blades together. Return the handle to the starting position with control.");
        exercises.put("Hyperextensions", "Hyperextensions (or Back Extensions) work the erector spinae and lower back muscles. You can perform this exercise on a hyperextension bench. Position yourself face down with your hips resting on the pad and your upper body hanging off the bench. Cross your arms over your chest or place your hands behind your head. Lift your upper body by contracting your lower back muscles and then lower it back down.");
        Exercise_Categories.get("Middle/Lower Back").putAll(exercises);
 
		Exercise_Categories.put("Lats", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Pull-Ups", "Pull-ups are one of the best exercises for targeting the lats. Find a sturdy overhead bar and grasp it with an overhand grip (palms facing away from you) or a neutral grip (palms facing each other). Hang freely with your arms fully extended, then pull your body up towards the bar until your chin is above the bar. Lower your body back down with control. Pull-ups are a highly effective exercise for building wide, well-defined lats.");
        exercises.put("Lat Pulldowns", "Lat pulldowns are a machine-based exercise that effectively targets the lats. Sit down at a lat pulldown machine, grasp the bar with a wide overhand grip, and position your thighs securely under the leg pads. Pull the bar down towards your chest, keeping your torso upright and your elbows pointed down and back. Squeeze your lats at the bottom of the movement, then release the bar with control.");
        exercises.put("Bent-Over Rows", "Bent-over rows are a compound exercise that works not only the middle and lower back but also the lats. To perform this exercise, stand with a barbell or dumbbells in your hands, feet shoulder-width apart. Bend at the hips to create a slight forward lean, keeping your back straight. Pull the weight towards your lower ribcage, squeezing your shoulder blades together, and then lower the weight back down. The wide grip emphasizes the lats. Can be done with either Barbell or Dumbbell.");
        Exercise_Categories.get("Lats").putAll(exercises);
 
		Exercise_Categories.put("Upper Chest", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Incline Bench Press", "The incline bench press is a classic exercise for emphasizing the upper chest. To perform this exercise, set an adjustable bench to an incline of around 30 to 45 degrees. Lie down on the bench, grasp the barbell or dumbbells, and lower them to your upper chest while keeping your elbows at a 45-degree angle to your torso. Push the weight back up to the starting position. Can be done with either Barbell or Dumbbell.");
        exercises.put("Incline Dumbbell Flyes", "Incline dumbbell flyes are excellent for isolating the upper chest. Using an adjustable bench set to an incline, lie down and hold a dumbbell in each hand with your palms facing each other. Keep a slight bend in your elbows and open your arms wide, lowering the dumbbells to the sides until you feel a stretch in your chest. Return to the starting position by squeezing your chest.");
        exercises.put("Incline Push-Ups", "Incline push-ups are a bodyweight exercise that can be adapted to different fitness levels. To target the upper chest, perform push-ups with your hands placed on an elevated surface like a bench or a step. Position your body at an angle with your feet on the ground and your hands on the elevated surface. Lower your chest towards the surface and then push back up to the starting position.");
        Exercise_Categories.get("Upper Chest").putAll(exercises);
 
 
 
		Exercise_Categories.put("Lower Chest", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Decline Bench Press", "The decline bench press is a key exercise for targeting the lower chest. Use a bench set to a decline position (feet higher than your head). Lie on the bench and grasp a barbell or dumbbells. Lower the weight to your lower chest and then press it back up. This movement puts more emphasis on the lower part of the pectoral muscles. Can be done with either Barbell or Dumbbell.");
        exercises.put("Decline Dumbbell Flyes", "Decline dumbbell flyes help isolate the lower chest. Lie on a decline bench with a dumbbell in each hand. Begin with your arms extended and slightly bent at the elbows. Open your arms wide and lower the dumbbells in an arcing motion until you feel a stretch in your lower chest. Bring the dumbbells back together above your chest to complete the rep.");
        exercises.put("Chest Dips", "Chest dips can be modified to target the lower chest by leaning your upper body forward during the exercise. Using parallel bars, stand between them and lean forward. Keep your feet off the ground and lower your body while bending your elbows. Push your body back up to the starting position. This variation shifts more of the workload to the lower chest.");
        Exercise_Categories.get("Lower Chest").putAll(exercises);
 
		Exercise_Categories.put("Abs", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Crunches", "Crunches are a classic exercise for targeting the upper abdominal muscles. To perform a basic crunch, lie on your back with your knees bent and your feet flat on the floor. Place your hands behind your head or cross them over your chest. Contract your abs and lift your upper body off the ground, curling your shoulders toward your knees. Lower your upper body back down with control. Avoid pulling on your neck to prevent strain.");
        exercises.put("Leg Raises", "Leg raises are effective for working the lower abdominal muscles. Lie on your back with your legs extended. Place your hands under your hips for support or hold onto a bench or a sturdy object. Lift your legs off the ground while keeping them straight, then slowly lower them back down without letting your feet touch the ground. This exercise primarily targets the lower abs.");
        exercises.put("Russian Twists", "Russian twists are a great exercise for working the oblique muscles on the sides of your abdomen. Sit on the ground with your knees bent and your feet flat. Lean back slightly and lift your feet off the ground. Hold a weight or a medicine ball with both hands and twist your torso to one side, touching the weight to the ground beside your hip. Return to the center and twist to the other side. This exercise engages the oblique muscles.");
        Exercise_Categories.get("Abs").putAll(exercises);
 
		Exercise_Categories.put("Hamstrings", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Deadlift", "The deadlift is a compound exercise that targets not only the lower back but also the hamstrings, glutes, and other muscles in the posterior chain. To perform a deadlift, stand with your feet hip-width apart in front of a barbell. Bend at your hips and knees to lower your body and grasp the barbell with an overhand or mixed grip. Lift the bar by extending your hips and knees, and then lower it back to the ground. Deadlifts are excellent for strengthening the hamstrings and improving overall posterior chain development.");
        exercises.put("Romanian Deadlift", "The Romanian deadlift is a variation of the traditional deadlift that places a greater emphasis on the hamstrings and less on the lower back. To perform this exercise, stand with a barbell or dumbbells in your hands, feet hip-width apart. Keep a slight bend in your knees and hinge at your hips, pushing your hips back while lowering the weight down your thighs. Keep the bar close to your body and lower it until you feel a stretch in your hamstrings. Return to the upright position by extending your hips.");
        exercises.put("Lying Leg Curls", "Lying leg curls are isolation exercises that specifically target the hamstrings. Lie face down on a leg curl machine or a bench with a dumbbell between your feet. Curl your legs upward, bending at the knees, until you feel a strong contraction in your hamstrings. Slowly lower your legs back down to the starting position.");
        Exercise_Categories.get("Hamstrings").putAll(exercises);
 
		Exercise_Categories.put("Quads", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Barbell Squats", "Barbell squats are a compound exercise that targets the quadriceps along with several other lower body muscles. To perform this exercise, stand with your feet shoulder-width apart and a barbell resting on your upper back. Lower your body by bending your knees and hips, keeping your back straight. Descend until your thighs are parallel to the ground or lower, and then push back up to the starting position.");
        exercises.put("Leg Press Machine", "The leg press machine is an excellent exercise for isolating the quadriceps. Sit on the machine and place your feet shoulder-width apart on the platform. Push the weight away by extending your knees while keeping your back against the seat. Slowly lower the weight back down until your knees are at a 90-degree angle, then press it back up.");
        exercises.put("Lunges", "Lunges are a versatile exercise that works the quads, along with the glutes and hamstrings. To perform lunges, stand with your feet together and take a step forward with one foot. Bend both knees until they form 90-degree angles. The back knee should hover just above the ground. Push off the front foot to return to the starting position, and then alternate legs for each repetition.");
        Exercise_Categories.get("Quads").putAll(exercises);
 
		Exercise_Categories.put("Glutes", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Barbell Hip Thrusts", "Barbell hip thrusts are a great exercise for isolating and strengthening the glutes. Sit on the floor with your upper back against a bench and a barbell across your hips. Roll the barbell over your legs and rest it on your hips. Bend your knees and keep your feet flat on the ground. Push through your heels to lift your hips off the ground until your body forms a straight line from your shoulders to your knees. Squeeze your glutes at the top and then lower your hips back down.");
		exercises.put("Lunges", "Lunges, as mentioned earlier in the \"Quads\" section, are excellent for targeting the glutes in addition to working the quads and hamstrings. To perform lunges, step forward with one foot and lower your body by bending both knees. The back knee should hover just above the ground. Push off the front foot to return to the starting position and alternate legs for each repetition.");
		exercises.put("Glute Bridges", "Glute bridges are a bodyweight exercise that effectively targets the glutes. Lie on your back with your knees bent and your feet flat on the ground, hip-width apart. Place your arms at your sides. Push through your heels to lift your hips off the ground, creating a straight line from your shoulders to your knees. Squeeze your glutes at the top and then lower your hips back down.");
		Exercise_Categories.get("Glutes").putAll(exercises);
 
		Exercise_Categories.put("Calves", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Standing Calf Raises", "Standing calf raises are one of the most common exercises to target the calf muscles. You can perform this exercise on a calf raise machine or with dumbbells. Stand on the machine's platform or with your feet hip-width apart, with your toes and balls of your feet on a raised surface (e.g., a block or calf raise machine step). Push through the balls of your feet to raise your heels as high as possible, flexing your calf muscles at the top. Lower your heels back down below the level of the raised surface for a full stretch.");
		exercises.put("Seated Calf Raises", "Seated calf raises target the soleus muscles, which are part of the calf complex. Sit on a seated calf raise machine or on a bench with your knees bent at a 90-degree angle and a calf block or step beneath your toes. Place the balls of your feet on the calf block or step, and then raise your heels as high as you can by pushing through the balls of your feet. Lower your heels down for a full stretch of the calf muscles.");
		exercises.put("Donkey Calf Raises", "Donkey calf raises are performed on a specialized machine where you lean forward and your training partner or a spotter adds weight to a lever, allowing you to raise your heels. If you don't have access to a machine, you can also do this exercise with a training partner sitting on your back. Keep your knees slightly bent and raise your heels as high as possible, then lower them for a full stretch of the calf muscles.");
		Exercise_Categories.get("Calves").putAll(exercises);
 
		Exercise_Categories.put("Arms", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Bicep Curls", "Bicep curls are effective for targeting the biceps. You can perform them with a barbell or dumbbells. Stand with your feet shoulder-width apart and hold the barbell or dumbbells with an underhand grip (palms facing up). Keeping your upper arms stationary, curl the weights toward your shoulders by bending your elbows. Lower the weights back to the starting position. This exercise primarily works the biceps.");
		exercises.put("Tricep Dips", "Tricep dips target the triceps, but they also engage the chest and shoulders. Find parallel bars or a sturdy surface, like the edge of a bench or a set of parallel dip bars. Place your hands on the bars with your palms facing down and let your body hang. Bend your elbows to lower your body, and then push through your palms to raise your body back up to the starting position. This exercise primarily works the triceps.");
		exercises.put("Push-Ups", "Push-ups are a versatile bodyweight exercise that work the chest, shoulders, and triceps. Begin in a plank position with your hands slightly wider than shoulder-width apart. Lower your body by bending your elbows, keeping them close to your body, until your chest nearly touches the ground. Push your body back up to the starting position. You can modify push-ups by changing hand positions, like diamond push-ups for more tricep emphasis, or wide-grip push-ups for more chest emphasis.");
		Exercise_Categories.get("Arms").putAll(exercises);
		//--
		Exercise_Categories.put("Upper Body", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Bench Press", "The bench press is a compound exercise that primarily targets the chest (pectorals) but also engages the shoulders and triceps. Lie on a flat bench and grasp a barbell with an overhand grip. Lower the barbell to your chest and then press it back up to the starting position. This exercise is a staple for building chest strength and size.");
		exercises.put("Pull-Ups", "Pull-ups target the back muscles (especially the lats) and the biceps. Find a sturdy overhead bar and grasp it with an overhand grip (palms facing away from you). Hang freely with your arms fully extended, then pull your body up towards the bar until your chin is above the bar. Lower your body back down with control. Pull-ups are excellent for building a strong upper back and biceps.");
		exercises.put("Standing Military Press", "The standing military press is an exercise that focuses on the shoulders, particularly the deltoid muscles, but it also engages the triceps. Stand with your feet shoulder-width apart and hold a barbell or dumbbells at shoulder height with an overhand grip. Press the weight overhead by extending your arms, and then lower it back to shoulder height. This exercise helps develop strong and well-rounded shoulder muscles.");
		Exercise_Categories.get("Upper Body").putAll(exercises);
 
		Exercise_Categories.put("Lower Body/Legs", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Barbell Squats", "Barbell squats are one of the best exercises for targeting the quadriceps, hamstrings, and glutes. Stand with your feet shoulder-width apart and a barbell on your upper back. Lower your body by bending your knees and hips, keeping your back straight. Descend until your thighs are parallel to the ground or lower, and then push back up to the starting position. This exercise is excellent for building lower body strength and size.");
		exercises.put("Deadlift", "Deadlifts work the hamstrings, glutes, lower back, and even the quadriceps. Stand with your feet hip-width apart in front of a barbell. Bend at your hips and knees to lower your body and grasp the barbell with an overhand or mixed grip. Lift the bar by extending your hips and knees, and then lower it back to the ground. Deadlifts are a highly effective exercise for overall lower body and posterior chain development.");
		exercises.put("Standing Calf Raises", "Standing calf raises target the calf muscles (gastrocnemius). You can perform them on a calf raise machine or with dumbbells. Stand with your feet hip-width apart, and place the balls of your feet on a raised surface. Push through the balls of your feet to lift your heels as high as possible, flexing your calf muscles at the top. Lower your heels back down for a full stretch of the calf muscles.");
		Exercise_Categories.get("Lower Body/Legs").putAll(exercises);
 
		Exercise_Categories.put("Chest", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Bench Press", "The bench press is a compound exercise that primarily targets the entire chest (pectoral muscles). Lie on a flat bench and grasp a barbell with an overhand grip. Lower the barbell to your chest and then press it back up to the starting position. This exercise is a fundamental choice for building chest strength and size.");
		exercises.put("Incline Bench Press", "Incline bench presses focus on the upper chest. Lie on an incline bench set at about a 30 to 45-degree angle and perform the bench press with a barbell or dumbbells. This exercise helps develop the upper portion of the chest for a well-rounded chest appearance.");
		exercises.put("Dumbbell Flyes", "Dumbbell flyes are excellent for isolating the chest muscles and providing a good stretch. Lie on a flat bench with a dumbbell in each hand. Begin with your arms extended and slightly bent at the elbows. Open your arms wide and lower the dumbbells to the sides until you feel a stretch in your chest. Return to the center by squeezing your chest muscles. Dumbbell flyes help create a balanced chest development.");
		Exercise_Categories.get("Chest").putAll(exercises);
 
		Exercise_Categories.put("Back", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Pull-Ups", "Pull-ups are a fantastic exercise for targeting the lats and upper back. Find a sturdy overhead bar and grasp it with an overhand grip (palms facing away from you). Hang freely with your arms fully extended, then pull your body up towards the bar until your chin is above the bar. Lower your body back down with control. Pull-ups are excellent for building a strong upper back.");
		exercises.put("Bent-Over Rows", "Bent-over rows work the middle and upper back muscles, as well as the lats. Stand with a barbell or dumbbells in your hands, feet shoulder-width apart. Bend at the hips to create a slight forward lean, keeping your back straight. Pull the weight towards your lower ribcage, squeezing your shoulder blades together, and then lower the weight back down. This exercise helps develop the upper and middle back.");
		exercises.put("Deadlift", "Deadlifts are a compound exercise that targets the lower back, along with the hamstrings, glutes, and other muscles in the posterior chain. To perform a deadlift, stand with your feet hip-width apart in front of a barbell. Bend at your hips and knees to lower your body and grasp the barbell with an overhand or mixed grip. Lift the bar by extending your hips and knees, and then lower it back to the ground. Deadlifts are highly effective for overall lower back and posterior chain development.");
		Exercise_Categories.get("Back").putAll(exercises);
 
		Exercise_Categories.put("Core", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Plank", "The plank is a fundamental core-strengthening exercise. Begin in a push-up position with your forearms on the ground and your elbows directly below your shoulders. Keep your body in a straight line from head to heels, engaging your core muscles to maintain this position. Hold the plank for as long as you can while keeping good form. This exercise targets the entire core.");
		exercises.put("Russian Twists", "Russian twists are great for targeting the oblique muscles on the sides of your abdomen. Sit on the ground with your knees bent and your feet flat. Lean back slightly and lift your feet off the ground. Hold a weight or a medicine ball with both hands and twist your torso to one side, touching the weight to the ground beside your hip. Return to the center and twist to the other side. This exercise engages the oblique muscles.");
		exercises.put("Hanging Leg Raises", "Hanging leg raises focus on the lower abdominals. Hang from a pull-up bar with your arms fully extended. Raise your legs while keeping them straight until they are parallel to the ground, and then lower them back down. This exercise requires core stability and strength to lift and control the legs.");
		Exercise_Categories.get("Core").putAll(exercises);
 
		Exercise_Categories.put("Cardio", new HashMap<String, String>());
		exercises = new HashMap<>();
		exercises.put("Running", "Running is a highly effective and convenient cardiovascular exercise. You can run outdoors, on a treadmill, or on a track. It helps improve endurance, burns a significant number of calories, and strengthens your leg muscles. You can vary the intensity and duration of your runs to suit your fitness level and goals.");
		exercises.put("Cycling", "Cycling, whether on a stationary bike or a regular bicycle, is a low-impact cardio exercise that's gentle on the joints. It works your leg muscles and is great for improving cardiovascular fitness. You can vary the resistance and speed to make your cycling sessions more challenging.");
		exercises.put("Jump Rope", "Jumping rope is a simple yet highly effective cardio exercise. It's portable, affordable, and can be done almost anywhere. Jumping rope increases your heart rate, improves coordination, and works the lower body. You can vary the speed and style of jumping to change the intensity of the workout.");
		Exercise_Categories.get("Cardio").putAll(exercises);
	}
 
	public void make_Daily_Workout(String type) 
	{
		exercises = new HashMap<>();
 
		if (type.equals("Full Body")) //1 Upper Body, 1 Lower Body, 1 Core, 1 Cardio
		{
			//get 1 upper body
			String randomKey;
			String randomValue;
			int randomIndex;
			HashMap<String, String> workouts;
			List<String> keyList;
			// Create a Random object
		    Random random = new Random();
 
		    //get 1 upper body
		    workouts = Exercise_Categories.get("Chest");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		  //get 1 upper body
		    workouts = Exercise_Categories.get("Back");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		    workouts = Exercise_Categories.get("Arms");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		    //get 1 lower body
		    workouts = Exercise_Categories.get("Lower Body/Legs");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
		}
 
		else if (type.equals("Push Day")) //Have Chest and Triceps
		{
			//get 1 upper body
			String randomKey;
			String randomValue;
			int randomIndex;
			HashMap<String, String> workouts;
			List<String> keyList;
			// Create a Random object
		    Random random = new Random();
 
			//get 1 upper body
		    workouts = Exercise_Categories.get("Upper Chest");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		  //get 1 upper body
		    workouts = Exercise_Categories.get("Lower Chest");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		    //get 1 lower body
		    workouts = Exercise_Categories.get("Triceps");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		    workouts = Exercise_Categories.get("Shoulders");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
		}
 
		else if (type.equals("Pull Day")) //Has Back and Biceps
		{
			//get 1 upper body
			String randomKey;
			String randomValue;
			int randomIndex;
			HashMap<String, String> workouts;
			List<String> keyList;
			// Create a Random object
		    Random random = new Random();
 
			//get 1 upper body
		    workouts = Exercise_Categories.get("Upper Back");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		    //get 1 lower body
		    workouts = Exercise_Categories.get("Middle/Lower Back");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		    workouts = Exercise_Categories.get("Lats");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		    workouts = Exercise_Categories.get("Biceps");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
		}
 
		else if (type.equals("Leg Day")) //Has Legs and Core/Abs
		{
			//get 1 upper body
			String randomKey;
			String randomValue;
			int randomIndex;
			HashMap<String, String> workouts;
			List<String> keyList;
			// Create a Random object
		    Random random = new Random();
 
			//get 1 upper body
		    workouts = Exercise_Categories.get("Hamstrings");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		    //get 1 lower body
		    workouts = Exercise_Categories.get("Quads");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		    workouts = Exercise_Categories.get("Glutes");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
 
		    workouts = Exercise_Categories.get("Calves");
			keyList = new ArrayList<>(workouts.keySet());
		    // Generate a random index
		    randomIndex = random.nextInt(keyList.size());
		    // Get the random key
		    randomKey = keyList.get(randomIndex);
		    // Get the corresponding value from the HashMap
		    randomValue = workouts.get(randomKey);
		    exercises.put(randomKey, randomValue);
		}
 
 
	}
 
	public void make_Popup_Information_Panel() 
	{
		//Popup Information Panel
		Popup_Information_Panel = new JPanel();
		Popup_Information_Panel.setBackground(new Color(140, 198, 255));
		Popup_Information_Panel.setBounds(10, 5, 313, 621);
		Recommendations_Tab.add(Popup_Information_Panel);
		Recommendations_Tab.getComponent(2).setVisible(false);
		Popup_Information_Panel.setLayout(null);
		JButton Back_Btn = new JButton("Back");
		Back_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
 
				Recommendations_Tab.getComponent(0).setVisible(true);
		      	Recommendations_Tab.getComponent(1).setVisible(true);
		      	Recommendations_Tab.getComponent(2).setVisible(false);
		      	Recommendations_Tab.getComponent(3).setVisible(true);
		      	Exercise_Notes.put(Rec_Name.getText(), Rec_Note.getText());
			}
		});
		Back_Btn.setBounds(173, 17, 130, 22);
		Popup_Information_Panel.add(Back_Btn);
		Rec_Name.setBounds(10, 17, 160, 22);
		Popup_Information_Panel.add(Rec_Name);
		Rec_Desc.setEditable(false);
		Rec_Desc.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Rec_Desc.setBackground(Color.WHITE);
		Rec_Desc.setWrapStyleWord(true);
		Rec_Desc.setLineWrap(true);
		Rec_Desc.setAlignmentY(Component.TOP_ALIGNMENT);
		Rec_Desc.setAlignmentX(Component.LEFT_ALIGNMENT);
		Rec_Desc.setBounds(10, 50, 293, 277);
		Popup_Information_Panel.add(Rec_Desc);
 
		JLabel Note = new JLabel("Note:");
		Note.setBounds(10, 332, 160, 22);
		Popup_Information_Panel.add(Note);
 
		Rec_Note = new JTextArea();
		Rec_Note.setBackground(Color.WHITE);
		Rec_Note.setBounds(10, 354, 293, 252);
		Popup_Information_Panel.add(Rec_Note);
	}
 
	public void Replace_Information(String repl_name, String repl_desc)
	{
		Popup_Information_Panel.remove(Rec_Name);
		Popup_Information_Panel.remove(Rec_Desc);
		Popup_Information_Panel.remove(Rec_Note);
 
		Rec_Name = new JLabel(repl_name);
		Rec_Name.setBounds(10, 17, 160, 22);
 
		Popup_Information_Panel.add(Rec_Name);
		Popup_Information_Panel.add(Rec_Desc);
		Popup_Information_Panel.add(Rec_Note);
 
		Popup_Information_Panel.repaint();
		Popup_Information_Panel.revalidate();
	}
 
	public void make_Data_Entry_Panel() 
	{
		JPanel Data_Entry_Panel = new JPanel();
		Data_Entry_Panel.setBackground(new Color(140, 198, 255));
		Data_Entry_Panel.setBounds(20, 487, 292, 150);
		Recommendations_Tab.add(Data_Entry_Panel);
		Data_Entry_Panel.setLayout(null);
 
		JButton Record_Exercise_Done_Btn = new JButton("Record Exercise Done");
		Record_Exercise_Done_Btn.setMargin(new Insets(2, 0, 2, 0));
		Record_Exercise_Done_Btn.setBounds(0, 5, 135, 22);
		Data_Entry_Panel.add(Record_Exercise_Done_Btn);
 
		JButton Undo_Last_Recording_Btn = new JButton("Undo Last Recording");
		Undo_Last_Recording_Btn.setMargin(new Insets(2, 2, 2, 2));
		Undo_Last_Recording_Btn.setBounds(163, 5, 129, 22);
		Data_Entry_Panel.add(Undo_Last_Recording_Btn);
 
		Reps_Done_TextField = new JTextField();
		Reps_Done_TextField.setBackground(Color.WHITE);
		Reps_Done_TextField.setBounds(163, 59, 129, 22);
		Data_Entry_Panel.add(Reps_Done_TextField);
		Reps_Done_TextField.setColumns(10);
 
		Sets_Done_TextField = new JTextField();
		Sets_Done_TextField.setBackground(Color.WHITE);
		Sets_Done_TextField.setBounds(163, 86, 129, 22);
		Data_Entry_Panel.add(Sets_Done_TextField);
		Sets_Done_TextField.setColumns(10);
 
		JLabel Reps_Done_Label = new JLabel("Repititions:");
		Reps_Done_Label.setBounds(0, 59, 129, 22);
		Data_Entry_Panel.add(Reps_Done_Label);
 
		JLabel Sets_Done_Label = new JLabel("Sets:");
		Sets_Done_Label.setBounds(0, 86, 129, 22);
		Data_Entry_Panel.add(Sets_Done_Label);
 
		Exercise_Done_Btn = new JComboBox();
		Exercise_Done_Btn.setBackground(Color.WHITE);
		Exercise_Done_Btn.setToolTipText("");
		Exercise_Done_Btn.setModel(new DefaultComboBoxModel(new String[] {"Barbell Bicep Curl", "Barbell Hip Thrusts", "Barbell Squats", "Bench Press", "Bent Over Reverse Flyes", "Bent-Over Rows", "Bicep Curls", "Chest Dips", "Cycling", "Deadlift", "Decline Bench Press", "Decline Dumbbell Flyes", "Donkey Calf Raises", "Dumbbell Flyes", "Dumbbell Hammer Curl", "Face Pulls", "Glute Bridges", "Hanging Leg Raises", "Hyperextensions", "Incline Bench Press", "Incline Dumbbell Flyes", "Incline Push-Ups", "Jump Rope", "Lat Pulldowns", "Leg Press Machine", "Leg Raises", "Lateral Raises", "Lunges", "Lying Leg Curls", "Plank", "Preacher Curl", "Pull-Ups", "Push-Ups", "Romanian Deadlift", "Running", "Russian Twists", "Seated Calf Raises", "Seated Cable Rows", "Skull Crushers", "Standing Calf Raises", "Standing Military Press", "Tricep Dips", "Tricep Pushdown"}));
		Exercise_Done_Btn.setBounds(163, 32, 129, 22);
		Data_Entry_Panel.add(Exercise_Done_Btn);
 
		JLabel Weight_Done_Label = new JLabel("Weight:"); //BRUH
		Weight_Done_Label.setBounds(0, 113, 129, 22);
		Data_Entry_Panel.add(Weight_Done_Label);
 
		Weight_Done_TextField = new JTextField();
		Weight_Done_TextField.setBackground(Color.WHITE);
		Weight_Done_TextField.setBounds(163, 113, 129, 20);
		Data_Entry_Panel.add(Weight_Done_TextField);
		Weight_Done_TextField.setColumns(10);
 
 
		JLabel Exercise_Done_Dropdown_Label = new JLabel("Exercise Done:");
		Exercise_Done_Dropdown_Label.setBounds(0, 32, 129, 22);
		Data_Entry_Panel.add(Exercise_Done_Dropdown_Label);
	}
 
	public void make_Settings_Panel() 
	{
		JPanel Settings_Panel = new JPanel();
		Settings_Panel.setBackground(new Color(140, 198, 255));
		Settings_Panel.setBounds(10, 5, 313, 44);
		Recommendations_Tab.add(Settings_Panel);
		Settings_Panel.setLayout(null);
 
		//Daily or All Label
		JLabel Daily_or_All_Label = new JLabel("Daily or All Exercises");
		Daily_or_All_Label.setBounds(10, 0, 130, 14);
		Settings_Panel.add(Daily_or_All_Label);
		//Muscle Focus Label
		JLabel Muscle_Focus_Label = new JLabel("Workout Type");
		Muscle_Focus_Label.setBounds(173, 0, 130, 14);
		Settings_Panel.add(Muscle_Focus_Label);
 
		//Muscle Focus Button
		JComboBox Muscle_Focus_Btn = new JComboBox();
		Muscle_Focus_Btn.setBackground(Color.WHITE);
		Muscle_Focus_Btn.setSelectedItem(null);
		Muscle_Focus_Btn.addItem("Full Body");
		Muscle_Focus_Btn.addItem("Push Day");
		Muscle_Focus_Btn.addItem("Pull Day");
		Muscle_Focus_Btn.addItem("Leg Day");
		Muscle_Focus_Btn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(Muscle_Focus_Btn.getSelectedItem() == null)
				{
					;
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Biceps"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
				    exercises = Exercise_Categories.get("Biceps");
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if (Muscle_Focus_Btn.getSelectedItem().equals("Triceps"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
			        exercises.put("Tricep Dips", "Tricep dips are an effective bodyweight exercise for targeting the triceps. To perform this exercise, find parallel bars or a sturdy surface, like the edge of a bench or a set of parallel dip bars. Place your hands shoulder-width apart on the bars, with your arms fully extended and feet on the ground. Lower your body by bending your elbows until your upper arms are parallel to the ground or slightly below. Push through your palms to return to the starting position.");
			        exercises.put("Tricep Pushdown", "(Cable Machine) \n The tricep pushdown is a popular isolation exercise that targets the triceps. Attach a straight or V-bar attachment to a high pulley on a cable machine. Stand facing the machine, grasp the bar with an overhand grip, and position your elbows at your sides. Push the bar down by extending your elbows and fully straightening your arms, then return to the starting position. Keep your upper arms stationary throughout the movement.");
			        exercises.put("Skull Crushers", "Skull crushers, also known as lying tricep extensions, are a great exercise to isolate the triceps. To perform this exercise, lie on a flat bench with a barbell or dumbbells in your hands. Extend your arms straight up over your chest. Slowly lower the weight by bending your elbows while keeping them pointed upward and close to your head. Once the weight is close to your forehead, reverse the movement by extending your arms to return to the starting position.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if (Muscle_Focus_Btn.getSelectedItem().equals("Shoulders"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
			        exercises.put("Standing Military Press", "(Barbell or Dumbbell) \n The standing military press is a compound exercise that primarily targets the deltoid muscles. To perform this exercise, stand with your feet shoulder-width apart and hold a barbell or dumbbells at shoulder height, with your palms facing forward. Press the weight overhead by extending your arms, and then lower it back to shoulder height. Keep your core engaged and maintain proper posture throughout the movement.");
			        exercises.put("Lateral Raises", "(Dumbbell or Cable) \n Lateral raises are excellent for targeting the lateral or side head of the deltoid muscles. To perform this exercise, stand or sit with a dumbbell in each hand at your sides. With a slight bend in your elbows, raise the weights to the side until your arms are parallel to the ground. Lower the weights back down to your sides slowly to complete one repetition.");
			        exercises.put("Bent Over Reverse Flyes", "(Dumbbell or Cable) \n Bent over reverse flyes work the rear deltoid muscles, helping to balance the shoulder development. To perform this exercise, bend at the hips to a 45-degree angle, holding a dumbbell in each hand with your palms facing each other. Keeping a slight bend in your elbows, raise your arms out to the sides until they are parallel to the ground. Lower the weights back down with control.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Upper Chest"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
			        exercises.put("Incline Bench Press", "(Barbell or Dumbbell) \n The incline bench press is a classic exercise for emphasizing the upper chest. To perform this exercise, set an adjustable bench to an incline of around 30 to 45 degrees. Lie down on the bench, grasp the barbell or dumbbells, and lower them to your upper chest while keeping your elbows at a 45-degree angle to your torso. Push the weight back up to the starting position.");
			        exercises.put("Incline Dumbbell Flyes", "Incline dumbbell flyes are excellent for isolating the upper chest. Using an adjustable bench set to an incline, lie down and hold a dumbbell in each hand with your palms facing each other. Keep a slight bend in your elbows and open your arms wide, lowering the dumbbells to the sides until you feel a stretch in your chest. Return to the starting position by squeezing your chest.");
			        exercises.put("Incline Push-Ups", "Incline push-ups are a bodyweight exercise that can be adapted to different fitness levels. To target the upper chest, perform push-ups with your hands placed on an elevated surface like a bench or a step. Position your body at an angle with your feet on the ground and your hands on the elevated surface. Lower your chest towards the surface and then push back up to the starting position.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Lower Chest"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
			        exercises.put("Decline Bench Press", "(Barbell or Dumbbell) \n The decline bench press is a key exercise for targeting the lower chest. Use a bench set to a decline position (feet higher than your head). Lie on the bench and grasp a barbell or dumbbells. Lower the weight to your lower chest and then press it back up. This movement puts more emphasis on the lower part of the pectoral muscles.");
			        exercises.put("Decline Dumbbell Flyes", "Decline dumbbell flyes help isolate the lower chest. Lie on a decline bench with a dumbbell in each hand. Begin with your arms extended and slightly bent at the elbows. Open your arms wide and lower the dumbbells in an arcing motion until you feel a stretch in your lower chest. Bring the dumbbells back together above your chest to complete the rep.");
			        exercises.put("Chest Dips", "(Leaning Forward) \n Chest dips can be modified to target the lower chest by leaning your upper body forward during the exercise. Using parallel bars, stand between them and lean forward. Keep your feet off the ground and lower your body while bending your elbows. Push your body back up to the starting position. This variation shifts more of the workload to the lower chest.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Upper Back"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
			        exercises.put("Pull-Ups", "Pull-ups are a classic exercise for developing the upper back. Find a sturdy overhead bar and grasp it with an overhand grip (palms facing away from you) or a neutral grip (palms facing each other). Hang freely with your arms fully extended, then pull your body up towards the bar until your chin is above the bar. Lower your body back down with control. Pull-ups primarily target the lats, traps, and rear deltoids.");
			        exercises.put("Bent-Over Rows", "(Barbell or Dumbbell) \n Bent-over rows are excellent for targeting the upper back, including the rhomboids and trapezius muscles. To perform this exercise, stand with a barbell or dumbbells in your hands, feet shoulder-width apart. Bend at the hips to create a slight forward lean, keeping your back straight. Pull the weight towards your lower ribcage, squeezing your shoulder blades together, and then lower the weight back down.");
			        exercises.put("Face Pulls", "(Cable Machine) \n Face pulls help strengthen the upper back and rear deltoids. Set up a rope attachment on a cable machine at shoulder height. Stand facing the machine, grasp the rope with an overhand grip, and step back a few feet. Pull the rope towards your face, separating the ends as you do so. Focus on squeezing your shoulder blades together and bringing the rope towards your nose.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Middle/Lower Back"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
			        exercises.put("Deadlift", "The deadlift is a compound exercise that targets the entire back, including the middle and lower back. To perform a deadlift, stand in front of a barbell with your feet hip-width apart. Bend at your hips and knees to lower your body and grasp the barbell with an overhand or mixed grip. Lift the bar by extending your hips and knees, and then lower it back to the ground. Deadlifts are excellent for strengthening the erector spinae muscles in the lower back.");
			        exercises.put("Seated Cable Rows", "Seated cable rows are great for targeting the middle and lower back. Sit down at a cable row machine, place your feet on the platform, and grasp the handle with an overhand grip. Sit up straight and pull the handle toward your lower abdomen while squeezing your shoulder blades together. Return the handle to the starting position with control.");
			        exercises.put("Hyperextensions", "(Back Extensions) \n Hyperextensions work the erector spinae and lower back muscles. You can perform this exercise on a hyperextension bench. Position yourself face down with your hips resting on the pad and your upper body hanging off the bench. Cross your arms over your chest or place your hands behind your head. Lift your upper body by contracting your lower back muscles and then lower it back down.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Lats"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
			        exercises.put("Pull-Ups", "Pull-ups are one of the best exercises for targeting the lats. Find a sturdy overhead bar and grasp it with an overhand grip (palms facing away from you) or a neutral grip (palms facing each other). Hang freely with your arms fully extended, then pull your body up towards the bar until your chin is above the bar. Lower your body back down with control. Pull-ups are a highly effective exercise for building wide, well-defined lats.");
			        exercises.put("Lat Pulldowns", "(Cable Machine) \n Lat pulldowns are a machine-based exercise that effectively targets the lats. Sit down at a lat pulldown machine, grasp the bar with a wide overhand grip, and position your thighs securely under the leg pads. Pull the bar down towards your chest, keeping your torso upright and your elbows pointed down and back. Squeeze your lats at the bottom of the movement, then release the bar with control.");
			        exercises.put("Bent-Over Rows", "(Barbell or Dumbbell) \n Bent-over rows are a compound exercise that works not only the middle and lower back but also the lats. To perform this exercise, stand with a barbell or dumbbells in your hands, feet shoulder-width apart. Bend at the hips to create a slight forward lean, keeping your back straight. Pull the weight towards your lower ribcage, squeezing your shoulder blades together, and then lower the weight back down. The wide grip emphasizes the lats.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Abs"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
			        exercises.put("Crunches", "(Upper Abs) \n Crunches are a classic exercise for targeting the upper abdominal muscles. To perform a basic crunch, lie on your back with your knees bent and your feet flat on the floor. Place your hands behind your head or cross them over your chest. Contract your abs and lift your upper body off the ground, curling your shoulders toward your knees. Lower your upper body back down with control. Avoid pulling on your neck to prevent strain.");
			        exercises.put("Leg Raises", "(Lower Abs) \n Leg raises are effective for working the lower abdominal muscles. Lie on your back with your legs extended. Place your hands under your hips for support or hold onto a bench or a sturdy object. Lift your legs off the ground while keeping them straight, then slowly lower them back down without letting your feet touch the ground. This exercise primarily targets the lower abs.");
			        exercises.put("Russian Twists", "(Oblique Muscles) \n Russian twists are a great exercise for working the oblique muscles on the sides of your abdomen. Sit on the ground with your knees bent and your feet flat. Lean back slightly and lift your feet off the ground. Hold a weight or a medicine ball with both hands and twist your torso to one side, touching the weight to the ground beside your hip. Return to the center and twist to the other side. This exercise engages the oblique muscles.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Hamstrings"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
			        exercises.put("Deadlift", "The deadlift is a compound exercise that targets not only the lower back but also the hamstrings, glutes, and other muscles in the posterior chain. To perform a deadlift, stand with your feet hip-width apart in front of a barbell. Bend at your hips and knees to lower your body and grasp the barbell with an overhand or mixed grip. Lift the bar by extending your hips and knees, and then lower it back to the ground. Deadlifts are excellent for strengthening the hamstrings and improving overall posterior chain development.");
			        exercises.put("Romanian Deadlift", "The Romanian deadlift is a variation of the traditional deadlift that places a greater emphasis on the hamstrings and less on the lower back. To perform this exercise, stand with a barbell or dumbbells in your hands, feet hip-width apart. Keep a slight bend in your knees and hinge at your hips, pushing your hips back while lowering the weight down your thighs. Keep the bar close to your body and lower it until you feel a stretch in your hamstrings. Return to the upright position by extending your hips.");
			        exercises.put("Lying Leg Curls", "(Machine or Dumbbell) \n Lying leg curls are isolation exercises that specifically target the hamstrings. Lie face down on a leg curl machine or a bench with a dumbbell between your feet. Curl your legs upward, bending at the knees, until you feel a strong contraction in your hamstrings. Slowly lower your legs back down to the starting position.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Quads"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
			        exercises.put("Barbell Squats", "Barbell squats are a compound exercise that targets the quadriceps along with several other lower body muscles. To perform this exercise, stand with your feet shoulder-width apart and a barbell resting on your upper back. Lower your body by bending your knees and hips, keeping your back straight. Descend until your thighs are parallel to the ground or lower, and then push back up to the starting position.");
			        exercises.put("Leg Press Machine", "The leg press machine is an excellent exercise for isolating the quadriceps. Sit on the machine and place your feet shoulder-width apart on the platform. Push the weight away by extending your knees while keeping your back against the seat. Slowly lower the weight back down until your knees are at a 90-degree angle, then press it back up.");
			        exercises.put("Lunges", "Lunges are a versatile exercise that works the quads, along with the glutes and hamstrings. To perform lunges, stand with your feet together and take a step forward with one foot. Bend both knees until they form 90-degree angles. The back knee should hover just above the ground. Push off the front foot to return to the starting position, and then alternate legs for each repetition.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Glutes"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
					exercises.put("Barbell Hip Thrusts", "Barbell hip thrusts are a great exercise for isolating and strengthening the glutes. Sit on the floor with your upper back against a bench and a barbell across your hips. Roll the barbell over your legs and rest it on your hips. Bend your knees and keep your feet flat on the ground. Push through your heels to lift your hips off the ground until your body forms a straight line from your shoulders to your knees. Squeeze your glutes at the top and then lower your hips back down.");
					exercises.put("Lunges", "Lunges, as mentioned earlier in the \"Quads\" section, are excellent for targeting the glutes in addition to working the quads and hamstrings. To perform lunges, step forward with one foot and lower your body by bending both knees. The back knee should hover just above the ground. Push off the front foot to return to the starting position and alternate legs for each repetition.");
					exercises.put("Glute Bridges", "Glute bridges are a bodyweight exercise that effectively targets the glutes. Lie on your back with your knees bent and your feet flat on the ground, hip-width apart. Place your arms at your sides. Push through your heels to lift your hips off the ground, creating a straight line from your shoulders to your knees. Squeeze your glutes at the top and then lower your hips back down.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Calves"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
					exercises.put("Standing Calf Raises", "(Machine or Dumbbells) \n Standing calf raises are one of the most common exercises to target the calf muscles. You can perform this exercise on a calf raise machine or with dumbbells. Stand on the machine's platform or with your feet hip-width apart, with your toes and balls of your feet on a raised surface (e.g., a block or calf raise machine step). Push through the balls of your feet to raise your heels as high as possible, flexing your calf muscles at the top. Lower your heels back down below the level of the raised surface for a full stretch.");
					exercises.put("Seated Calf Raises", "(Machine or Dumbbells) \n Seated calf raises target the soleus muscles, which are part of the calf complex. Sit on a seated calf raise machine or on a bench with your knees bent at a 90-degree angle and a calf block or step beneath your toes. Place the balls of your feet on the calf block or step, and then raise your heels as high as you can by pushing through the balls of your feet. Lower your heels down for a full stretch of the calf muscles.");
					exercises.put("Donkey Calf Raises", "(Machine or Assisted) \n Donkey calf raises are performed on a specialized machine where you lean forward and your training partner or a spotter adds weight to a lever, allowing you to raise your heels. If you don't have access to a machine, you can also do this exercise with a training partner sitting on your back. Keep your knees slightly bent and raise your heels as high as possible, then lower them for a full stretch of the calf muscles.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Arms"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
					exercises.put("Bicep Curls", "(Barbell or Dumbbell) \n Bicep curls are effective for targeting the biceps. You can perform them with a barbell or dumbbells. Stand with your feet shoulder-width apart and hold the barbell or dumbbells with an underhand grip (palms facing up). Keeping your upper arms stationary, curl the weights toward your shoulders by bending your elbows. Lower the weights back to the starting position. This exercise primarily works the biceps.");
					exercises.put("Tricep Dips", "Tricep dips target the triceps, but they also engage the chest and shoulders. Find parallel bars or a sturdy surface, like the edge of a bench or a set of parallel dip bars. Place your hands on the bars with your palms facing down and let your body hang. Bend your elbows to lower your body, and then push through your palms to raise your body back up to the starting position. This exercise primarily works the triceps.");
					exercises.put("Push-Ups", "Push-ups are a versatile bodyweight exercise that work the chest, shoulders, and triceps. Begin in a plank position with your hands slightly wider than shoulder-width apart. Lower your body by bending your elbows, keeping them close to your body, until your chest nearly touches the ground. Push your body back up to the starting position. You can modify push-ups by changing hand positions, like diamond push-ups for more tricep emphasis, or wide-grip push-ups for more chest emphasis.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Upper Body"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
					exercises.put("Bench Press", "The bench press is a compound exercise that primarily targets the chest (pectorals) but also engages the shoulders and triceps. Lie on a flat bench and grasp a barbell with an overhand grip. Lower the barbell to your chest and then press it back up to the starting position. This exercise is a staple for building chest strength and size.");
					exercises.put("Pull-Ups", "Pull-ups target the back muscles (especially the lats) and the biceps. Find a sturdy overhead bar and grasp it with an overhand grip (palms facing away from you). Hang freely with your arms fully extended, then pull your body up towards the bar until your chin is above the bar. Lower your body back down with control. Pull-ups are excellent for building a strong upper back and biceps.");
					exercises.put("Standing Military Press", "(Barbell or Dumbbell) \n The standing military press is an exercise that focuses on the shoulders, particularly the deltoid muscles, but it also engages the triceps. Stand with your feet shoulder-width apart and hold a barbell or dumbbells at shoulder height with an overhand grip. Press the weight overhead by extending your arms, and then lower it back to shoulder height. This exercise helps develop strong and well-rounded shoulder muscles.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Lower Body/Legs"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
					exercises.put("Barbell Squats", "Barbell squats are one of the best exercises for targeting the quadriceps, hamstrings, and glutes. Stand with your feet shoulder-width apart and a barbell on your upper back. Lower your body by bending your knees and hips, keeping your back straight. Descend until your thighs are parallel to the ground or lower, and then push back up to the starting position. This exercise is excellent for building lower body strength and size.");
					exercises.put("Deadlift", "Deadlifts work the hamstrings, glutes, lower back, and even the quadriceps. Stand with your feet hip-width apart in front of a barbell. Bend at your hips and knees to lower your body and grasp the barbell with an overhand or mixed grip. Lift the bar by extending your hips and knees, and then lower it back to the ground. Deadlifts are a highly effective exercise for overall lower body and posterior chain development.");
					exercises.put("Standing Calf Raises", "(Machine or Dumbbells) \n Standing calf raises target the calf muscles (gastrocnemius). You can perform them on a calf raise machine or with dumbbells. Stand with your feet hip-width apart, and place the balls of your feet on a raised surface. Push through the balls of your feet to lift your heels as high as possible, flexing your calf muscles at the top. Lower your heels back down for a full stretch of the calf muscles.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Chest"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
					exercises.put("Bench Press", "The bench press is a compound exercise that primarily targets the entire chest (pectoral muscles). Lie on a flat bench and grasp a barbell with an overhand grip. Lower the barbell to your chest and then press it back up to the starting position. This exercise is a fundamental choice for building chest strength and size.");
					exercises.put("Incline Bench Press", "Incline bench presses focus on the upper chest. Lie on an incline bench set at about a 30 to 45-degree angle and perform the bench press with a barbell or dumbbells. This exercise helps develop the upper portion of the chest for a well-rounded chest appearance.");
					exercises.put("Dumbbell Flyes", "Dumbbell flyes are excellent for isolating the chest muscles and providing a good stretch. Lie on a flat bench with a dumbbell in each hand. Begin with your arms extended and slightly bent at the elbows. Open your arms wide and lower the dumbbells to the sides until you feel a stretch in your chest. Return to the center by squeezing your chest muscles. Dumbbell flyes help create a balanced chest development.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Back"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
					exercises.put("Pull-Ups", "Pull-ups are a fantastic exercise for targeting the lats and upper back. Find a sturdy overhead bar and grasp it with an overhand grip (palms facing away from you). Hang freely with your arms fully extended, then pull your body up towards the bar until your chin is above the bar. Lower your body back down with control. Pull-ups are excellent for building a strong upper back.");
					exercises.put("Bent-Over Rows", "(Barbell or Dumbbell) \n Bent-over rows work the middle and upper back muscles, as well as the lats. Stand with a barbell or dumbbells in your hands, feet shoulder-width apart. Bend at the hips to create a slight forward lean, keeping your back straight. Pull the weight towards your lower ribcage, squeezing your shoulder blades together, and then lower the weight back down. This exercise helps develop the upper and middle back.");
					exercises.put("Deadlift", "Deadlifts are a compound exercise that targets the lower back, along with the hamstrings, glutes, and other muscles in the posterior chain. To perform a deadlift, stand with your feet hip-width apart in front of a barbell. Bend at your hips and knees to lower your body and grasp the barbell with an overhand or mixed grip. Lift the bar by extending your hips and knees, and then lower it back to the ground. Deadlifts are highly effective for overall lower back and posterior chain development.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Core"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
					exercises.put("Plank", "The plank is a fundamental core-strengthening exercise. Begin in a push-up position with your forearms on the ground and your elbows directly below your shoulders. Keep your body in a straight line from head to heels, engaging your core muscles to maintain this position. Hold the plank for as long as you can while keeping good form. This exercise targets the entire core.");
					exercises.put("Russian Twists", "Russian twists are great for targeting the oblique muscles on the sides of your abdomen. Sit on the ground with your knees bent and your feet flat. Lean back slightly and lift your feet off the ground. Hold a weight or a medicine ball with both hands and twist your torso to one side, touching the weight to the ground beside your hip. Return to the center and twist to the other side. This exercise engages the oblique muscles.");
					exercises.put("Hanging Leg Raises", "Hanging leg raises focus on the lower abdominals. Hang from a pull-up bar with your arms fully extended. Raise your legs while keeping them straight until they are parallel to the ground, and then lower them back down. This exercise requires core stability and strength to lift and control the legs.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Cardio"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
					//Add recommendations
					HashMap<String, String> exercises = new HashMap<>();
					exercises.put("Running", "Running is a highly effective and convenient cardiovascular exercise. You can run outdoors, on a treadmill, or on a track. It helps improve endurance, burns a significant number of calories, and strengthens your leg muscles. You can vary the intensity and duration of your runs to suit your fitness level and goals.");
					exercises.put("Cycling", "Cycling, whether on a stationary bike or a regular bicycle, is a low-impact cardio exercise that's gentle on the joints. It works your leg muscles and is great for improving cardiovascular fitness. You can vary the resistance and speed to make your cycling sessions more challenging.");
					exercises.put("Jump Rope", "Jumping rope is a simple yet highly effective cardio exercise. It's portable, affordable, and can be done almost anywhere. Jumping rope increases your heart rate, improves coordination, and works the lower body. You can vary the speed and style of jumping to change the intensity of the workout.");
 
			        make_Scrolling_Pane(exercises, false);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Push Day"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
 
					//Add recommendations
					make_Daily_Workout("Push Day");
 
			        make_Scrolling_Pane(exercises, true);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Pull Day"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
 
					//Add recommendations
					make_Daily_Workout("Pull Day");
 
			        make_Scrolling_Pane(exercises, true);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Leg Day"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
 
					//Add recommendations
					make_Daily_Workout("Leg Day");
 
			        make_Scrolling_Pane(exercises, true);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
				else if(Muscle_Focus_Btn.getSelectedItem().equals("Full Body"))
				{
					Scrolling_Pane_Holder_Panel.remove(0);
 
					//Add recommendations
					make_Daily_Workout("Full Body");
 
			        make_Scrolling_Pane(exercises, true);
			        Scrolling_Pane_Holder_Panel.repaint();
			        Scrolling_Pane_Holder_Panel.revalidate();
				}
 
			}
		});
		Muscle_Focus_Btn.setBounds(173, 17, 130, 22);
		Settings_Panel.add(Muscle_Focus_Btn);
		//Daily or All Exercises Button
		JComboBox Daily_AllEx_Btn = new JComboBox();
		Daily_AllEx_Btn.setBackground(Color.WHITE);
		Daily_AllEx_Btn.setModel(new DefaultComboBoxModel(new String[] {"Daily Exercises", "All Exercises"}));
		Daily_AllEx_Btn.setBounds(10, 17, 130, 22);
		Settings_Panel.add(Daily_AllEx_Btn);
		Daily_AllEx_Btn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (Daily_AllEx_Btn.getSelectedItem().equals("All Exercises"))
				{
					Muscle_Focus_Btn.removeAllItems();
					Muscle_Focus_Btn.setSelectedItem(null);
					Muscle_Focus_Btn.addItem("Biceps");
					Muscle_Focus_Btn.addItem("Triceps");
					Muscle_Focus_Btn.addItem("Shoulders");
					Muscle_Focus_Btn.addItem("Upper Chest");
					Muscle_Focus_Btn.addItem("Lower Chest");
					Muscle_Focus_Btn.addItem("Upper Back");
					Muscle_Focus_Btn.addItem("Middle/Lower Back");
					Muscle_Focus_Btn.addItem("Lats");
					Muscle_Focus_Btn.addItem("Abs");
					Muscle_Focus_Btn.addItem("Hamstrings");
					Muscle_Focus_Btn.addItem("Glutes");
					Muscle_Focus_Btn.addItem("Quads");
					Muscle_Focus_Btn.addItem("Calves");
					Muscle_Focus_Btn.addItem("Arms");
					Muscle_Focus_Btn.addItem("Upper Body");
					Muscle_Focus_Btn.addItem("Lower Body/Legs");
					Muscle_Focus_Btn.addItem("Chest");
					Muscle_Focus_Btn.addItem("Back");
					Muscle_Focus_Btn.addItem("Core");
					Muscle_Focus_Btn.addItem("Cardio");
				}
				else if (Daily_AllEx_Btn.getSelectedItem().equals("Daily Exercises"))
				{
					Muscle_Focus_Btn.removeAllItems();
					Muscle_Focus_Btn.setSelectedItem(null);
					Muscle_Focus_Btn.addItem("Full Body");
					Muscle_Focus_Btn.addItem("Push Day");
					Muscle_Focus_Btn.addItem("Pull Day");
					Muscle_Focus_Btn.addItem("Leg Day");
				}
 
			}
		});
 
	}
 
	public void make_Scrolling_Pane(HashMap<String, String> exercises, boolean show_replace_btn)
    {
 
        //Scrolling List Panel
        JPanel Changing_List_of_Exercises = new JPanel(new GridBagLayout());
        Changing_List_of_Exercises.setBackground(new Color(140, 198, 255));
        Changing_List_of_Exercises.setMaximumSize(new Dimension(292, 2147483647));
        //GBC
        GridBagConstraints changing_list_gbc = new GridBagConstraints();
        changing_list_gbc.gridx = 0;
        changing_list_gbc.gridy = 0;
        changing_list_gbc.fill = GridBagConstraints.BOTH;
 
        //JScrollPane holds the To Do List JPanel
        JScrollPane Scrolling_Pane = new JScrollPane(Changing_List_of_Exercises);
        Scrolling_Pane.setMaximumSize(new Dimension(292, 32767));
        //Scrolling_Pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
 
        //GBC
        GridBagConstraints scrolling_pane_gbc = new GridBagConstraints();
        scrolling_pane_gbc.weightx = 1.0;
        scrolling_pane_gbc.gridx = 0;
        scrolling_pane_gbc.gridy = 0;
        scrolling_pane_gbc.weighty = 1.0;
        scrolling_pane_gbc.fill = GridBagConstraints.BOTH;
 
        //redistribute extra vertical space
        for (Component rec : Changing_List_of_Exercises.getComponents())
        {
            Changing_List_of_Exercises.remove(rec);
        }
 
        //add exercises
        for (Entry<String, String> exercise : exercises.entrySet())
        {
        	add_Exercises(exercise, Changing_List_of_Exercises, show_replace_btn);
        }
 
        Scrolling_Pane_Holder_Panel.add(Scrolling_Pane, scrolling_pane_gbc);
 
        Recommendations_Tab.add(Scrolling_Pane_Holder_Panel);
    }
 
	public void add_Exercises(Entry<String, String> exercise, JPanel Changing_List_of_Exercises, boolean show_replace_btn) 
    {
		//CHEESE
		//Adding a new Recommendation
        JPanel exercise_object = make_Exercises(exercise.getKey(), exercise.getValue(), Changing_List_of_Exercises, show_replace_btn);
    	GridBagConstraints gbc = new GridBagConstraints();
 
        //redistribute extra vertical space
        for (Component rec_i : Changing_List_of_Exercises.getComponents())
        {
            gbc.gridx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.weighty = 0;
            Changing_List_of_Exercises.remove(rec_i);
            Changing_List_of_Exercises.add(rec_i, gbc);
        }
 
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; 
 
        Changing_List_of_Exercises.add(exercise_object, gbc);
        Changing_List_of_Exercises.revalidate();
        Changing_List_of_Exercises.repaint();
    }
 
	//might have to add variable for Exercise_Category (String Exercise_Category, String exercise_name, String exercise_description, JPanel Changing_List_of_Exercises) 
	public JPanel make_Exercises(String exercise_name, String exercise_description, JPanel Changing_List_of_Exercises, boolean show_replace_btn) 
    {
		//maybe here if mode == beginner reps = this. or reps = that.
 
		JPanel Exercise_Panel = new JPanel();
		Exercise_Panel.setBackground(new Color(203, 230, 255));
		Exercise_Panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.CYAN, Color.BLUE));
		Exercise_Panel.setBounds(20, 11, 293, 87);
 
		JLabel Exercise_Name_Label = new JLabel(exercise_name);
 
		JLabel label = new JLabel("");
 
		JLabel label_1 = new JLabel("");
 
		JLabel label_2 = new JLabel("");
 
		JLabel label_3 = new JLabel("");
 
		//if mode == beginner
		JLabel Reps_Label;
		JLabel Sets_Label;
		JLabel Weight_Label;
 
		if (show_replace_btn)
		{
			Reps_Label = new JLabel("Repititions: " + Exercise_Reps.get(exercise_name)[mode]);//+ Exercise_Reps.get(exercise_name)[mode]
 
			Sets_Label = new JLabel("Sets: " + Exercise_Sets.get(exercise_name)[mode]); //+ Exercise_Sets.get(exercise_name)[mode]
 
			Weight_Label = new JLabel("Weight: " + Exercise_Weights.get(exercise_name)[mode]);// + Exercise_Weights.get(exercise_name)[mode]
		}
		else
		{
			Reps_Label = new JLabel("");// + Exercise_Sets.(exercise_name)[mode]);
 
			Sets_Label = new JLabel("");// + Exercise_Reps.(exercise_name)[mode]);
 
			Weight_Label = new JLabel("");// + + Exercise_Weights.(exercise_name)[mode]);
		}
 
		JButton Set_to_Record_Btn = new JButton("Set To Record");
		Set_to_Record_Btn.setIconTextGap(0);
		Set_to_Record_Btn.setMargin(new Insets(2, 2, 2, 2));
		Set_to_Record_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exercise_Done_Btn.setSelectedItem(Exercise_Name_Label.getText());
			}
		});
		if (!show_replace_btn)
		{
			Set_to_Record_Btn.setBorderPainted(false);
			Set_to_Record_Btn.setBackground(new Color(0, 0, 0, 0));
			Set_to_Record_Btn.setForeground(new Color(0, 0, 0, 0));
			Set_to_Record_Btn.setText("");
			Set_to_Record_Btn.setVisible(false);
			Set_to_Record_Btn.setEnabled(false);
		}
 
		JButton Replace_Btn = new JButton("Replace");
		Replace_Btn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
 
				HashMap<String, String> replacement_workouts = null;
 
				for (HashMap<String, String> categories: Exercise_Categories.values())
				{
					if (categories.containsKey(exercise_name))
					{
						replacement_workouts = categories;
						break;
					}
				}
 
				List<String> keyList = new ArrayList<>(replacement_workouts.keySet());
 
			    // Create a Random object
			    Random random = new Random();
 
			    String randomKey;
			    String randomValue;
 
			    do 
			    {
			    	// Generate a random index
				    int randomIndex = random.nextInt(keyList.size());
 
				    // Get the random key
				    randomKey = keyList.get(randomIndex);
 
				    // Get the corresponding value from the HashMap
				    randomValue = replacement_workouts.get(randomKey);
			    }
			    while (randomKey.equals(Exercise_Name_Label.getText()));
 
			    Rec_Name.setText(randomKey);
			    Rec_Desc.setText(randomValue);
			    Rec_Note.setText(Exercise_Notes.getOrDefault(randomKey, ""));
			    Reps_Label.setText("Repititions: " + Exercise_Reps.get(randomKey)[mode]);//+ Exercise_Reps.get(exercise_name)[mode])
				Sets_Label.setText("Sets: " + Exercise_Sets.get(randomKey)[mode]); //+ Exercise_Sets.get(exercise_name)[mode]
				Weight_Label.setText("Weight: " + Exercise_Weights.get(randomKey)[mode]);// + Exercise_Weights.get(exercise_name)[mode]
				Exercise_Name_Label.setText(randomKey);
				Replace_Information(randomKey, randomValue);
			}
		});
		Replace_Btn.setMargin(new Insets(2, 2, 2, 2));
		if (!show_replace_btn)
		{
			Replace_Btn.setBorderPainted(false);
			Replace_Btn.setBackground(new Color(0, 0, 0, 0));
			Replace_Btn.setForeground(new Color(0, 0, 0, 0));
			Replace_Btn.setText("");
			Replace_Btn.setVisible(false);
			Replace_Btn.setEnabled(false);
 
		}
 
		JButton Help_Btn = new JButton("Information");
		Help_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recommendations_Tab.getComponent(0).setVisible(false);
            	Recommendations_Tab.getComponent(1).setVisible(false);
            	Recommendations_Tab.getComponent(2).setVisible(true);
            	Recommendations_Tab.getComponent(3).setVisible(false);
            	Rec_Name.setText(Exercise_Name_Label.getText());
 
            	HashMap<String, String> workouts = new HashMap<>();
            	for (HashMap<String, String> categories: Exercise_Categories.values())
				{
					if (categories.containsKey(Exercise_Name_Label.getText()))
					{
						workouts = categories;
						break;
					}
				}
 
            	Rec_Desc.setText(workouts.get(Exercise_Name_Label.getText()));
            	Rec_Note.setText(Exercise_Notes.getOrDefault(Exercise_Name_Label.getText(), ""));
			}
		});
		Help_Btn.setMargin(new Insets(2, 2, 2, 2));
 
		if (show_replace_btn)
		{
			GroupLayout gl_Exercise_Panel = new GroupLayout(Exercise_Panel);
			gl_Exercise_Panel.setHorizontalGroup(
				gl_Exercise_Panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_Exercise_Panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_Exercise_Panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_Exercise_Panel.createSequentialGroup()
								.addComponent(Exercise_Name_Label)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(label_1)
								.addGap(102)
								.addComponent(label)
								.addGap(11)
								.addComponent(label_2)
								.addGap(4)
								.addComponent(label_3))
							.addGroup(gl_Exercise_Panel.createSequentialGroup()
								.addGroup(gl_Exercise_Panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(Reps_Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(Set_to_Record_Btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_Exercise_Panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(Sets_Label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(Replace_Btn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_Exercise_Panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(Weight_Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(Help_Btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								)))
			);
			gl_Exercise_Panel.setVerticalGroup(
				gl_Exercise_Panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_Exercise_Panel.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_Exercise_Panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_Exercise_Panel.createSequentialGroup()
								.addGroup(gl_Exercise_Panel.createParallelGroup(Alignment.LEADING)
									.addComponent(label_1)
									.addComponent(label)
									.addComponent(label_2)
									.addComponent(label_3))
								.addGap(56))
							.addGroup(gl_Exercise_Panel.createSequentialGroup()
								.addComponent(Exercise_Name_Label)
								.addGap(8)
								.addGroup(gl_Exercise_Panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(Reps_Label)
									.addComponent(Sets_Label)
									.addComponent(Weight_Label))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_Exercise_Panel.createParallelGroup(Alignment.LEADING)
									.addComponent(Set_to_Record_Btn)
									.addGroup(gl_Exercise_Panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(Help_Btn)
										.addComponent(Replace_Btn)))
								.addContainerGap())))
			);
			Exercise_Panel.setLayout(gl_Exercise_Panel);
		}
 
		else
		{
 
			GroupLayout gl_panel = new GroupLayout(Exercise_Panel);
			gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(Exercise_Name_Label)
							.addComponent(Help_Btn))
						.addContainerGap(120, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(Exercise_Name_Label)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(Help_Btn)
						.addContainerGap())
			);
			Exercise_Panel.setLayout(gl_panel);
		}
 
 
		return Exercise_Panel;
 
 
    }
}