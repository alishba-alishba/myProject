package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.MainApplication;
import com.model.Lesson;

import javax.swing.JLabel;
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
import javax.swing.JInternalFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReviewFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblReview;
	ReviewFrame frame ;
	/**
	 * Launch the application.
	 */
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new ReviewFrame();
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
	public ReviewFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				new StudentDashboardFrame().init();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 856, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 820, 389);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Submit your review here");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(239, 11, 314, 56);
		panel.add(lblNewLabel);
		
		JScrollPane scrPnlReview = new JScrollPane();
		scrPnlReview.setBounds(10, 111, 800, 267);
		panel.add(scrPnlReview);
		
		tblReview = new JTable();
		setLessons();
		scrPnlReview.setViewportView(tblReview);
		
		JButton btnSubmitReview = new JButton("Submit Review");
		
		btnSubmitReview.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmitReview.setBounds(632, 68, 166, 33);
		panel.add(btnSubmitReview);
		
		btnSubmitReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row= tblReview.getSelectedRow();
				if(row>=0) {
					long id=Long.parseLong(tblReview.getValueAt(row, 0).toString());
					
					setVisible(false);
					new ReviewFormFrame(id).init(id);
				}
				else {
					 JOptionPane.showMessageDialog(frame,"Select a row !");  
				}
			}
		});
		
	}
	
	
	
	private void setLessons() {

		List<Lesson> lessons=getAttendedLessons();
	
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

			tblReview = new JTable(data1, columnNames);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public List<Lesson> getAttendedLessons() {
		List<Lesson> attendedLessons=new ArrayList<Lesson>();
		for (long stdLessonId : MainApplication.activeStudent.getAttendedLessonsId()) {
			
			for(Lesson lesson:MainApplication.attantedLessons) {
				if(lesson.getId()==stdLessonId) {
					attendedLessons.add(lesson);
				}
			}
			
		}
		return attendedLessons;
	}
}
