package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.MainApplication;
import com.model.Lesson;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentDashboardFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public void init() {
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDashboardFrame frame = new StudentDashboardFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentDashboardFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				new HomeFrame().init();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 887, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 92, 851, 360);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search Time Table and Book Lesson");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(46, 11, 427, 37);
		panel.add(lblNewLabel);
		
		JRadioButton rdbtnDay = new JRadioButton("Search by day");
		rdbtnDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		rdbtnDay.setBounds(46, 66, 135, 23);
		panel.add(rdbtnDay);
		
		JRadioButton rdbtnExercise = new JRadioButton("Search by exercise");
		rdbtnExercise.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		rdbtnExercise.setBounds(339, 66, 153, 23);
		panel.add(rdbtnExercise);
		
		ButtonGroup buttonGroup=new ButtonGroup(); 
		buttonGroup.add(rdbtnDay);
		buttonGroup.add(rdbtnExercise);
		
		JPanel pnlSearch = new JPanel();
		pnlSearch.setVisible(false);
		pnlSearch.setBounds(46, 105, 603, 66);
		panel.add(pnlSearch);
		pnlSearch.setLayout(null);
		
		JLabel lblSearchOption = new JLabel("Select Day");
		lblSearchOption.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearchOption.setBounds(10, 15, 136, 26);
		pnlSearch.add(lblSearchOption);
		
		JComboBox cmbSearchItem = new JComboBox();
		cmbSearchItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbSearchItem.setBounds(188, 13, 179, 28);
		pnlSearch.add(cmbSearchItem);
		
		JButton btnSearch = new JButton("Get Time Table");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnSearch.setBounds(429, 13, 131, 30);
		pnlSearch.add(btnSearch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 851, 53);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 841, 38);
		panel_1.add(menuBar);
		
		JMenuItem mnuViewBooking = new JMenuItem("VIew Booked Lesson");
		
		mnuViewBooking.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnuViewBooking);
		
		JMenuItem mnuReview = new JMenuItem("Review");
		mnuReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ReviewFrame().init();
			}
		});
		mnuReview.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnuReview);
		
		
		
		rdbtnExercise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSearchOption.setText("Select Exercise");
				cmbSearchItem.removeAllItems();
				String[] exercises= {"Aquacise","Body Blitz","Box Fit","Yoga","Zumba"};
				
				for (String exercise : exercises) {
					cmbSearchItem.addItem((Object)exercise);;
				}
			
				
				pnlSearch.setVisible(true);
				
			
			}
		});
		
		rdbtnDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSearchOption.setText("Select Day");
				cmbSearchItem.removeAllItems();
				cmbSearchItem.addItem((Object)"Saturday");
				cmbSearchItem.addItem((Object)"Sunday");
				
				pnlSearch.setVisible(true);
				
				
			}
		});
		
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Lesson> lessons=new ArrayList<Lesson>();
				if(rdbtnDay.isSelected()) {
					
					for (Lesson lesson : MainApplication.upcommingLessons) {
						
						if(lesson.getDay().equalsIgnoreCase(cmbSearchItem.getSelectedItem().toString())) {
							lessons.add(lesson);
						}
							
					}
				}
				else if(rdbtnExercise.isSelected()) {
					
					for (Lesson lesson : MainApplication.upcommingLessons) {
						
						if(lesson.getExercise().getName().equalsIgnoreCase(cmbSearchItem.getSelectedItem().toString())) {
							lessons.add(lesson);
						}
							
					}
				}
				setVisible(false);
				TimeTableFrame.init(lessons);

			}
		});
		
		mnuViewBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new ViewBookingFrame().init();
			}
		});
		
	}
}
