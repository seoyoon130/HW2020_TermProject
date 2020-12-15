package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class Profile_Screen extends JFrame {

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
					Profile_Screen frame = new Profile_Screen();
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
	public Profile_Screen(User friend) {
		setBackground(new Color(250, 240, 230));
		setBounds(100, 100, 500, 560);
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBackground(new Color(250, 240, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setBounds(100,100,500,560);
		
		JLabel Profile_label = new JLabel("Profile");
		Profile_label.setFont(new Font("Consolas", Font.BOLD, 25));
		Profile_label.setBounds(188, 10, 100, 50);
		contentPane.add(Profile_label);
		
		JLabel ID_label = new JLabel("ID:");
		ID_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		ID_label.setBounds(30, 65, 78, 35);
		contentPane.add(ID_label);
		
		JLabel State_label = new JLabel("State:");
		State_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		State_label.setBounds(30, 105, 78, 35);
		contentPane.add(State_label);
		
		JLabel Message_label = new JLabel("Message:");
		Message_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		Message_label.setBounds(30, 145, 95, 35);
		contentPane.add(Message_label);
		
		JLabel Name_label = new JLabel("Name:");
		Name_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		Name_label.setBounds(30, 185, 78, 35);
		contentPane.add(Name_label);
		
		JLabel Phone_label = new JLabel("Phone:");
		Phone_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		Phone_label.setBounds(30, 225, 78, 35);
		contentPane.add(Phone_label);
		
		JLabel Email_label = new JLabel("Email:");
		Email_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		Email_label.setBounds(30, 265, 78, 35);
		contentPane.add(Email_label);
		
		JLabel Birth_label = new JLabel("Birth:");
		Birth_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		Birth_label.setBounds(30, 345, 78, 35);
		contentPane.add(Birth_label);
		
		JLabel Homepage_label = new JLabel("Homepage:");
		Homepage_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		Homepage_label.setBounds(30, 385, 114, 35);
		contentPane.add(Homepage_label);
		
		JLabel Login_label = new JLabel("Last Login:");
		Login_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		Login_label.setBounds(30, 425, 126, 35);
		contentPane.add(Login_label);
		
		JLabel Logout_label = new JLabel("Last Logout:");
		Logout_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		Logout_label.setBounds(30, 465, 137, 35);
		contentPane.add(Logout_label);
		
		JLabel Nickname_label = new JLabel("Nickname:");
		Nickname_label.setFont(new Font("DialogInput", Font.BOLD, 18));
		Nickname_label.setBounds(30, 305, 114, 35);
		contentPane.add(Nickname_label);
		
		JLabel ID = new JLabel(friend.getID());
		ID.setFont(new Font("Dialog", Font.BOLD, 18));
		ID.setBounds(179, 65, 295, 35);
		contentPane.add(ID);
		
		JLabel State = new JLabel(friend.getState());
		State.setFont(new Font("Dialog", Font.BOLD, 18));
		State.setBounds(179, 105, 295, 35);
		contentPane.add(State);
		
		JLabel Message = new JLabel(friend.getMessage());
		Message.setFont(new Font("Dialog", Font.BOLD, 18));
		Message.setBounds(179, 145, 295, 35);
		contentPane.add(Message);
		
		JLabel Name = new JLabel(friend.getName());
		Name.setFont(new Font("Dialog", Font.BOLD, 18));
		Name.setBounds(179, 185, 295, 35);
		contentPane.add(Name);
		
		JLabel Phone = new JLabel(friend.getPhone());
		Phone.setFont(new Font("Dialog", Font.BOLD, 18));
		Phone.setBounds(179, 225, 295, 35);
		contentPane.add(Phone);
		
		JLabel Email = new JLabel(friend.getEmail());
		Email.setFont(new Font("Dialog", Font.BOLD, 18));
		Email.setBounds(179, 265, 295, 35);
		contentPane.add(Email);
		
		JLabel Nickname = new JLabel(friend.getNickname());
		Nickname.setFont(new Font("Dialog", Font.BOLD, 18));
		Nickname.setBounds(179, 305, 295, 35);
		contentPane.add(Nickname);
		
		JLabel Birth = new JLabel(friend.getBirth());
		Birth.setFont(new Font("Dialog", Font.BOLD, 18));
		Birth.setBounds(179, 345, 295, 35);
		contentPane.add(Birth);
		
		JLabel Homepage = new JLabel(friend.getHomepage());
		Homepage.setFont(new Font("Dialog", Font.BOLD, 18));
		Homepage.setBounds(179, 385, 295, 35);
		contentPane.add(Homepage);
		
		JLabel Login = new JLabel(friend.getLogin());
		Login.setFont(new Font("Dialog", Font.BOLD, 18));
		Login.setBounds(179, 425, 295, 35);
		contentPane.add(Login);
		
		JLabel Logout = new JLabel(friend.getLogout());
		Logout.setFont(new Font("Dialog", Font.BOLD, 18));
		Logout.setBounds(179, 465, 295, 35);
		contentPane.add(Logout);
		
		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}
}
