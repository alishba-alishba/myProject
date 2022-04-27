package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.MainApplication;
import com.model.Lesson;
import com.model.Student;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewBookingFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblViewBooking;
	ViewBookingFrame frame;
	 
	 
	/**
	 * Launch the application.
	 */
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new ViewBookingFrame();
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
	public ViewBookingFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				new StudentDashboardFrame().init();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 848, 436);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrPnlViewBooking = new JScrollPane();
		scrPnlViewBooking.setBounds(26, 77, 798, 348);
		panel_1.add(scrPnlViewBooking);
		
		tblViewBooking = new JTable();
		setLessons();
		scrPnlViewBooking.setViewportView(tblViewBooking);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				int row= tblViewBooking.getSelectedRow();
				if(row >=0) {
				
										
						long id=Long.parseLong(tblViewBooking.getValueAt(row, 0).toString());
						
						
						MainApplication.activeStudent.getBookedLessonsId().remove(id);
						
						for(Lesson lesson:MainApplication.upcommingLessons) {
							if(lesson.getId()==id) {
						
								lesson.setStudents(lesson.getStudents()-1);
								JOptionPane.showMessageDialog(frame," Lesson removed successfully !"); 
								setLessons();
								scrPnlViewBooking.setViewportView(tblViewBooking);
								break;
							}
						}
						
				
						
						
					
				}
				else {
					 JOptionPane.showMessageDialog(frame,"Select a row !");  
				}
				
				
				
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemove.setBounds(26, 31, 122, 35);
		panel_1.add(btnRemove);
		
		JButton btnBookNew = new JButton("Book New Lesson");
		btnBookNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new StudentDashboardFrame().init();
			}
		});
		btnBookNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBookNew.setBounds(181, 31, 164, 35);
		panel_1.add(btnBookNew);
	}
	
private void setLessons() {

		List<Lesson> lessons=getBookedLessons();
	
		Vector data1 = new Vector();
		try {
			
			for (Lesson lesson : lessons) {
				Vector rowLesson = new Vector();
				rowLesson.addElement(lesson.getId());
				rowLesson.addElement(lesson.getExercise().getName());
				rowLesson.addElement(lesson.getExercise().getPrice() );
				rowLesson.addElement(lesson.getDate() );
				rowLesson.addElement(lesson.getDay() );
				rowLesson.addElement(lesson.getTiming() );
				
				data1.addElement(rowLesson);
			}

	

			Vector columnNames = new Vector();
			columnNames.addElement(" ID ");
			columnNames.addElement(" EXERCISE ");
			columnNames.addElement(" PRICE");
			columnNames.addElement(" DATE ");
			columnNames.addElement(" DAY ");
			columnNames.addElement(" TIMMING ");

			tblViewBooking = new JTable(data1, columnNames);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public List<Lesson> getBookedLessons() {
		List<Lesson> bookedLessons=new ArrayList<Lesson>();
		for (long stdLessonId : MainApplication.activeStudent.getBookedLessonsId()) {
			
			for(Lesson lesson:MainApplication.upcommingLessons) {
				if(lesson.getId()==stdLessonId) {
					bookedLessons.add(lesson);
				}
			}
			
		}
		return bookedLessons;
	}
	
}
