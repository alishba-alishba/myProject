package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.MainApplication;
import com.model.Review;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReviewFormFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtReview;
	ReviewFormFrame frame ;
	/**
	 * Launch the application.
	 */
	public void init(long lessonId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new ReviewFormFrame(lessonId);
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
	public ReviewFormFrame(long lessonId) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				new ReviewFrame().init();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lesson ID:- "+lessonId);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(242, 11, 162, 27);
		panel.add(lblNewLabel);
		
		JLabel lblRating = new JLabel("Select Rating");
		lblRating.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRating.setBounds(10, 61, 162, 27);
		panel.add(lblRating);
		
		JLabel lblNewLabel_1_1 = new JLabel("Write Review");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 114, 162, 27);
		panel.add(lblNewLabel_1_1);
		
		String ratings[]= {"1: Very dissatisfied","2: Dissatisfied","3: Ok","4: Satisfied","5: Very Satisfied"};
		JComboBox cmbRating = new JComboBox(ratings);
		cmbRating.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbRating.setBounds(198, 66, 162, 32);
		panel.add(cmbRating);
		
		txtReview = new JTextField();
		txtReview.setBounds(198, 119, 162, 66);
		panel.add(txtReview);
		txtReview.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubmit.setBounds(99, 196, 261, 32);
		panel.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainApplication.reviewList.add(new Review(lessonId, MainApplication.activeStudent.getId(), txtReview.getText(), cmbRating.getSelectedIndex()+1));
				 JOptionPane.showMessageDialog(frame," Review Updated  !");
				setVisible(false);
				new ReviewFrame().init();
				
			}
		});
		
		
	}
}
