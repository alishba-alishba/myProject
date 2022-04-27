package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.MainApplication;
import com.model.Student;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeFrame extends JFrame {

	private JPanel contentPane;
	HomeFrame frame;
	Student student=new Student();
	/**
	 * Launch the application.
	 */
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new HomeFrame();
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
	public HomeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 559, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnAdminDashboard = new JButton("View Reports");
		btnAdminDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ReportFrame().init();
			}
		});
		btnAdminDashboard.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdminDashboard.setBounds(37, 68, 215, 106);
		panel.add(btnAdminDashboard);
		
		JButton btnStudentDashboard = new JButton("Student Dashboard");
		btnStudentDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String stdId=JOptionPane.showInputDialog(frame,"Enter student ID"); 

			
				if(student.isStudentExist(stdId)) {
					
					setVisible(false);
					new StudentDashboardFrame().init();
				}
				else {
					
						JOptionPane.showMessageDialog(frame,"please enter a valid Student id !");
					
				}
			}
		});
		btnStudentDashboard.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStudentDashboard.setBounds(314, 68, 208, 106);
		panel.add(btnStudentDashboard);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(286, 35, 18, 178);
		panel.add(separator);
	}
	
	
}
