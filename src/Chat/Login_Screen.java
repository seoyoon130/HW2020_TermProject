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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login_Screen extends JFrame {

	private JPanel contentPane;
	JTextField ID_textfield;
	JPasswordField PW_passwordfield;
	JButton Login_button;
	JButton Join_Member_button;
	JFrame frame=new JFrame();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Screen frame = new Login_Screen();
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
	public Login_Screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100,100,400,550);
		frame.setResizable(false);
		
		JLabel ID_label = new JLabel("ID");
		ID_label.setFont(new Font("Consolas", Font.BOLD, 23));
		ID_label.setBounds(50, 200, 32, 41);
		contentPane.add(ID_label);
		
		JLabel PW_label = new JLabel("PW");
		PW_label.setFont(new Font("Consolas", Font.BOLD, 23));
		PW_label.setBounds(50, 270, 32, 41);
		contentPane.add(PW_label);
		
		ID_textfield = new JTextField();
		ID_textfield.setBackground(new Color(255, 245, 238));
		ID_textfield.setBounds(100, 198, 240, 40);
		contentPane.add(ID_textfield);
		ID_textfield.setColumns(10);
		
		Login_button = new JButton("Login");
		Login_button.setBackground(new Color(222, 184, 135));
		
		Login_button.setFont(new Font("Dialog", Font.PLAIN, 20));
		Login_button.setBounds(206, 375, 150, 41);
		contentPane.add(Login_button);
		
		Join_Member_button = new JButton("Join Member");
		Join_Member_button.setBackground(new Color(222, 184, 135));
		Join_Member_button.setFont(new Font("Dialog", Font.PLAIN, 20));
		Join_Member_button.setBounds(31, 375, 150, 41);
		contentPane.add(Join_Member_button);
		
		PW_passwordfield = new JPasswordField();
		PW_passwordfield.setBackground(new Color(255, 245, 238));
		PW_passwordfield.setBounds(100, 270, 240, 40);
		contentPane.add(PW_passwordfield);
		
		frame.setContentPane(contentPane);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void didnt_put_id_or_pw()
	{
		JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요!");
	}
	public void close()
	{
		frame.setVisible(false);
	}
	public void caution_invalid()
	{
		JOptionPane.showMessageDialog(null, "없는 회원 정보");
	}
}
