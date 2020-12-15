package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Note_Send_time_Screen extends JFrame {

	private JPanel contentPane;
	JFrame frame=new JFrame();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Note_Send_time_Screen frame = new Note_Send_time_Screen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	public Note_Send_time_Screen(note selected) {
		setBackground(new Color(255, 250, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 300, 120);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setBounds(500, 100, 300, 120);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel time_label = new JLabel("<TIME>");
		time_label.setFont(new Font("Consolas", Font.BOLD, 20));
		time_label.setBounds(108, 10, 81, 39);
		contentPane.add(time_label);
		
		JLabel time = new JLabel(selected.getsend_time());
		time.setFont(new Font("Corbel", Font.BOLD, 19));
		time.setBounds(55, 40, 200, 32);
		contentPane.add(time);
		
		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}
	
}
