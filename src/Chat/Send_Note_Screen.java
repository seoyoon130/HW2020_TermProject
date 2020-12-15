package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Send_Note_Screen extends JFrame {

	private JPanel contentPane;
	JTextField content_textfield;
	JButton Send_button;
	JFrame frame=new JFrame();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Send_Note_Screen frame = new Send_Note_Screen();
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
	public Send_Note_Screen(String sender,String receiver) {
		System.out.println("in Send_Note_Screen sender: "+sender+" receiver: "+receiver);
		setBackground(new Color(255, 228, 181));
		System.out.println("here1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("here2");
		setBounds(100, 100, 450, 200);
		System.out.println("here3");
		contentPane = new JPanel();
		System.out.println("here4");
		contentPane.setBackground(new Color(255, 228, 181));
		System.out.println("here5");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 200);
		
		JLabel Sending_note_label = new JLabel("Sending Note...");
		Sending_note_label.setBounds(131, 20, 177, 39);
		Sending_note_label.setFont(new Font("Consolas", Font.BOLD, 20));
		contentPane.add(Sending_note_label);
		
		JLabel receiver_sender_label = new JLabel(sender+"->"+receiver);
		receiver_sender_label.setBounds(12, 76, 180, 23);
		receiver_sender_label.setFont(new Font("ÇÑÄÄ °íµñ", Font.BOLD, 18));
		contentPane.add(receiver_sender_label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 109, 325, 44);
		contentPane.add(scrollPane);
		
		content_textfield = new JTextField();
		content_textfield.setBackground(new Color(255, 248, 220));
		scrollPane.setViewportView(content_textfield);
		content_textfield.setColumns(10);
		
		Send_button = new JButton("SEND");
		Send_button.setBackground(new Color(245, 222, 179));
		Send_button.setFont(new Font("Consolas", Font.BOLD, 17));
		Send_button.setBounds(349, 109, 81, 44);
		contentPane.add(Send_button);
		
		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}
}
