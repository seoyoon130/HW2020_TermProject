package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Join_Member_Screen extends JFrame {

	private JPanel contentPane;
	JTextField ID_textfield;
	JTextField Name_textfield;
	JTextField Email_textfield;
	JTextField Phone_textfield;
	JTextField Homepage_textfield;
	JTextField Birth_textfield;
	JTextField Nickname_textfield;
	JPasswordField PW_passwordfield;
	JButton check_duplicate_button;
	JButton complete_button;
	JFrame frame=new JFrame();
	int checking_duplication;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join_Member_Screen frame = new Join_Member_Screen();
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
	public Join_Member_Screen() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 196));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setBounds(100,100,500,600);
		
		JLabel lblNewLabel = new JLabel("Join Membership");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 26));
		lblNewLabel.setBounds(130, 40, 222, 56);
		contentPane.add(lblNewLabel);
		
		check_duplicate_button = new JButton("\u2714");
		check_duplicate_button.setFont(new Font("Lucida Sans", Font.PLAIN, 10));
		check_duplicate_button.setForeground(new Color(250, 240, 230));
		check_duplicate_button.setBackground(new Color(222, 184, 135));
		check_duplicate_button.setBounds(389, 102, 46, 41);
		contentPane.add(check_duplicate_button);
		
		JLabel ID_label = new JLabel("ID");
		ID_label.setFont(new Font("Consolas", Font.PLAIN, 18));
		ID_label.setBounds(70, 110, 70, 40);
		contentPane.add(ID_label);
		
		JLabel PW_label = new JLabel("PW");
		PW_label.setFont(new Font("Consolas", Font.PLAIN, 18));
		PW_label.setBounds(70, 155, 71, 40);
		contentPane.add(PW_label);
		
		JLabel Name_label = new JLabel("Name");
		Name_label.setFont(new Font("Consolas", Font.PLAIN, 18));
		Name_label.setBounds(70, 200, 71, 40);
		contentPane.add(Name_label);
		
		JLabel Email_label = new JLabel("Email");
		Email_label.setFont(new Font("Consolas", Font.PLAIN, 18));
		Email_label.setBounds(70, 245, 71, 40);
		contentPane.add(Email_label);
		
		JLabel Phone_label = new JLabel("Phone");
		Phone_label.setFont(new Font("Consolas", Font.PLAIN, 18));
		Phone_label.setBounds(70, 290, 71, 40);
		contentPane.add(Phone_label);
		
		JLabel Homepage_label = new JLabel("Homepage");
		Homepage_label.setFont(new Font("Consolas", Font.PLAIN, 18));
		Homepage_label.setBounds(70, 335, 95, 40);
		contentPane.add(Homepage_label);
		
		JLabel Nickname_label = new JLabel("Nickname");
		Nickname_label.setFont(new Font("Consolas", Font.PLAIN, 18));
		Nickname_label.setBounds(70, 430, 95, 40);
		contentPane.add(Nickname_label);
		
		JLabel Birth_label = new JLabel("Birth");
		Birth_label.setFont(new Font("Consolas", Font.PLAIN, 18));
		Birth_label.setBounds(70, 380, 95, 40);
		contentPane.add(Birth_label);
		
		complete_button = new JButton("Completed!");
		complete_button.setBackground(new Color(222, 184, 135));
		complete_button.setFont(new Font("Consolas", Font.PLAIN, 19));
		complete_button.setBounds(180, 490, 141, 40);
		contentPane.add(complete_button);
		
		ID_textfield = new JTextField();
		ID_textfield.setBackground(new Color(255, 235, 205));
		ID_textfield.setBounds(160, 108, 200, 30);
		ID_textfield.setText("Please Check duplicate!");
		contentPane.add(ID_textfield);
		ID_textfield.setColumns(10);
		
		Name_textfield = new JTextField();
		Name_textfield.setBackground(new Color(255, 235, 205));
		Name_textfield.setColumns(10);
		Name_textfield.setBounds(160, 205, 200, 30);
		contentPane.add(Name_textfield);
		
		Email_textfield = new JTextField();
		Email_textfield.setBackground(new Color(255, 235, 205));
		Email_textfield.setColumns(10);
		Email_textfield.setBounds(160, 250, 200, 30);
		contentPane.add(Email_textfield);
		
		Phone_textfield = new JTextField();
		Phone_textfield.setBackground(new Color(255, 235, 205));
		Phone_textfield.setColumns(10);
		Phone_textfield.setBounds(160, 293, 200, 30);
		Phone_textfield.setText("ex:010-1234-5678");
		contentPane.add(Phone_textfield);
		
		Homepage_textfield = new JTextField();
		Homepage_textfield.setBackground(new Color(255, 235, 205));
		Homepage_textfield.setColumns(10);
		Homepage_textfield.setBounds(160, 340, 200, 30);
		contentPane.add(Homepage_textfield);
		
		Birth_textfield = new JTextField();
		Birth_textfield.setBackground(new Color(255, 235, 205));
		Birth_textfield.setColumns(10);
		Birth_textfield.setBounds(160, 385, 200, 30);
		Birth_textfield.setText("ex:20000101");
		contentPane.add(Birth_textfield);
		
		Nickname_textfield = new JTextField();
		Nickname_textfield.setBackground(new Color(255, 235, 205));
		Nickname_textfield.setColumns(10);
		Nickname_textfield.setBounds(160, 435, 200, 30);
		contentPane.add(Nickname_textfield);
		
		PW_passwordfield = new JPasswordField();
		PW_passwordfield.setBackground(new Color(255, 235, 205));
		PW_passwordfield.setBounds(160, 160, 200, 30);
		contentPane.add(PW_passwordfield);
		
		frame.setContentPane(contentPane);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void caution_id_duplicate()
	{
		JOptionPane.showMessageDialog(null, "아이디 중복!");
	}
	public void caution_didnt_check_duplication()
	{
		JOptionPane.showMessageDialog(null, "아이디 중복 체크 하세요!");
	}
	public void available_id()
	{
		JOptionPane.showMessageDialog(null,"아이디 사용 가능!");
	}

}

