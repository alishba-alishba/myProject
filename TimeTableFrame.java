package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.MainApplication;
import com.model.Lesson;
import com.model.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.SwingConstants;
import java.awt.Font;

public class TimeTableFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblTimeTable;
	static TimeTableFrame frame ;
	 private List<Lesson> lessons=new ArrayList<Lesson>();
	
	/**
	 * Launch the application.
	 */
	public static void init(List<Lesson> lessons) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new TimeTableFrame(lessons);
					
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
	public TimeTableFrame(List<Lesson> lessons) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
				new StudentDashboardFrame().init();
			}
		});
		
		
		
		
		this.lessons=lessons;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 911, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 875, 344);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lesson Time Table");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(322, 11, 257, 46);
		panel.add(lblNewLabel);
		
		JScrollPane scrPnlTimeTable = new JScrollPane();
		scrPnlTimeTable.setBounds(20, 68, 845, 246);
		panel.add(scrPnlTimeTable);
		
		tblTimeTable = new JTable();
		setLessons();
		scrPnlTimeTable.setViewportView(tblTimeTable);
		
		JButton btnBookLesson = new JButton("Book Lesson");
		btnBookLesson.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBookLesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			
			int row= tblTimeTable.getSelectedRow();
			if(row >=0) {
			
									
					long id=Long.parseLong(tblTimeTable.getValueAt(row, 0).toString());
					
					
					for (Lesson lesson : MainApplication.upcommingLessons) {
						
						if(id==lesson.getId()) {
							if(lesson.isSeatAvailable()) {
								
								boolean flag=true;
								for(long stdLessonId:MainApplication.activeStudent.getBookedLessonsId()) {
								
									Lesson stdLesson=new Lesson();
									for(Lesson tempLesson:MainApplication.upcommingLessons) {
										if(lesson.getId()==stdLessonId) {
											stdLesson=lesson;
											break;
										}
									}
									
									if(lesson.getId()==stdLessonId) {
										 JOptionPane.showMessageDialog(frame," Student already booked this lesson !");
										 flag=false;
									}
									else if(lesson.getDate().equalsIgnoreCase(stdLesson.getDate()) && lesson.getTiming().equalsIgnoreCase(stdLesson.getTiming())) {
										JOptionPane.showMessageDialog(frame," Time clashed with already booked lesson !");  
										flag=false;
									}
									
								}
								if(flag) {
									MainApplication.activeStudent.addBookedLessonsId(lesson.getId());
									lesson.setStudents(lesson.getStudents()+1);
									JOptionPane.showMessageDialog(frame," Lesson booked successfully !"); 
									setLessons();
									scrPnlTimeTable.setViewportView(tblTimeTable);
								}
								
							}
							else {
								 JOptionPane.showMessageDialog(frame,"No Seat Available !");  
							}
							
						}
					}
					
					
			}
			else {
				 JOptionPane.showMessageDialog(frame,"Select a row !");  
			}
			
			}
		});
		btnBookLesson.setBounds(20, 26, 150, 31);
		panel.add(btnBookLesson);
	}
	
	
	
	private void setLessons() {

		
		Vector data1 = new Vector();
		try {
			
			for (Lesson lesson : this.lessons) {
				Vector rowLesson = new Vector();
				rowLesson.addElement(lesson.getId());
				rowLesson.addElement(lesson.getExercise().getName());
				rowLesson.addElement(lesson.getExercise().getPrice() );
				rowLesson.addElement(lesson.getDate() );
				rowLesson.addElement(lesson.getDay() );
				rowLesson.addElement(lesson.getTiming() );
				rowLesson.addElement(4-lesson.getStudents() );
				
				data1.addElement(rowLesson);
			}

	

			Vector columnNames = new Vector();
			columnNames.addElement(" ID ");
			columnNames.addElement(" EXERCISE ");
			columnNames.addElement(" PRICE");
			columnNames.addElement(" DATE ");
			columnNames.addElement(" DAY ");
			columnNames.addElement(" TIMMING ");
			columnNames.addElement(" AVAILABLE SEATS  ");

			tblTimeTable = new JTable(data1, columnNames);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
}
