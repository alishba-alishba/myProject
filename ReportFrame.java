package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.MainApplication;
import com.model.Lesson;
import com.model.Report2;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReportFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblReport1;
	private JTable tblReport2;
	private JLabel lblHighestIncome;
	/**
	 * Launch the application.
	 */
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportFrame frame = new ReportFrame();
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
	public ReportFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				new HomeFrame().init();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 865, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 829, 423);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Report 1", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 804, 373);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Report 1");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(203, 11, 313, 50);
		panel_2.add(lblNewLabel);
		
		JScrollPane scrPnlReport1 = new JScrollPane();
		scrPnlReport1.setBounds(10, 87, 784, 275);
		panel_2.add(scrPnlReport1);
		
		tblReport1 = new JTable();
		setReportOne();
		scrPnlReport1.setViewportView(tblReport1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Report 2", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Report 2");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(218, 11, 279, 41);
		panel_1.add(lblNewLabel_1);
		
		lblHighestIncome = new JLabel("Exercise with highest income is :- ");
		lblHighestIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHighestIncome.setBounds(10, 73, 804, 53);
		panel_1.add(lblHighestIncome);
		
		JScrollPane scrPnlExercise = new JScrollPane();
		scrPnlExercise.setBounds(131, 137, 578, 247);
		panel_1.add(scrPnlExercise);
		
		tblReport2 = new JTable();
		setReportTwo();
		scrPnlExercise.setViewportView(tblReport2);
	}
	
	
	private void setReportTwo() {
		
		List<String> exerciseList=new ArrayList<String>();
		List<Report2> report2List=new ArrayList<Report2>();
		for(Lesson atdLesson:MainApplication.attantedLessons) {
			String exercise=atdLesson.getExercise().getName();
			long price=atdLesson.getExercise().getPrice();
			if(exerciseList.contains(exercise)) {
				for(Report2 report2:report2List) {
					if(report2.getExerciseName().equalsIgnoreCase(exercise)) {
						report2.setLessons(1);
						report2.setTotalIncome(price);
						break;
					}
				}
			}
			else {
				exerciseList.add(exercise);
				report2List.add(new Report2(exercise, 1, price));
			}
		}
		String exercice[]=calHighestIncome(report2List);
		lblHighestIncome.setText("Exercise with highest income is : "
							+exercice[1]+"  Total income : "+exercice[0]);
		
		Vector data1 = new Vector();
		try {
			
			for (Report2 report2 : report2List) {
				
				Vector rowLesson = new Vector();
				rowLesson.addElement(report2.getExerciseName());
				rowLesson.addElement(report2.getLessons() );
				
				data1.addElement(rowLesson);
			}

			

			Vector columnNames = new Vector();
			
			columnNames.addElement(" EXERCISE ");
			columnNames.addElement(" No of Lesson ");

			tblReport2 = new JTable(data1, columnNames);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private String[] calHighestIncome(List<Report2> report2List) {
		double maxPrice=0;
		String exerciseName="";
		
		for (Report2 report2 : report2List) {
			double income=report2.getTotalIncome();
			if(maxPrice<income) {
				maxPrice=income;
				exerciseName=report2.getExerciseName();
			}
			
		}
		String strMaxPrice=maxPrice+"";
		String exercise[]={strMaxPrice,exerciseName};;
		return exercise;
	}
	
	private void setReportOne() {

	
		Vector data1 = new Vector();
		try {
			
			for (Lesson lesson : MainApplication.attantedLessons) {
				
				Vector rowLesson = new Vector();
				rowLesson.addElement(lesson.getExercise().getName());
				rowLesson.addElement(lesson.getDate() );
				rowLesson.addElement(lesson.getDay() );
				rowLesson.addElement(lesson.getTiming() );
				rowLesson.addElement(lesson.getStdAttend());
				rowLesson.addElement(calAvgRating(lesson.getTotalRating(),lesson.getStdAttend()));
				
				data1.addElement(rowLesson);
			}

	

			Vector columnNames = new Vector();
			
			columnNames.addElement(" EXERCISE ");
			columnNames.addElement(" DATE ");
			columnNames.addElement(" DAY ");
			columnNames.addElement(" TIMMING ");
			columnNames.addElement(" STUDENTS  ");
			columnNames.addElement(" AVG. RATING ");

			tblReport1 = new JTable(data1, columnNames);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private double calAvgRating(int totalRating,int stdAttend) {
		
		double avgRating=totalRating/stdAttend;
		
		int precision=2;
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(avgRating * scale) / scale;
	}
}
