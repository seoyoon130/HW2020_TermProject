package Chat;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Change_my_info extends JFrame{

	private JPanel contentPane;
	JTextField name_textfield;
	JTextField message_textfield;
	JTextField nickname_textfield;
	JTextField phone_textfield;
	JTextField homepage_textfield;
	JTextField birth_textfield;
	JTextField email_textfield;
	JButton completed_button;
	
	JFrame frame=new JFrame();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myINFO frame = new myINFO();
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
	public Change_my_info(User myInfo ) {
		System.out.println("myINFO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 240, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setBounds(100, 0, 500, 550);
		JLabel lblNewLabel = new JLabel("      Change My Information");
		lblNewLabel.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblNewLabel.setBounds(50, 20, 400, 70);
		contentPane.add(lblNewLabel);
		
		
		JLabel name_label = new JLabel("\uC774\uB984:");
		name_label.setFont(new Font("³ª´®°íµñ", Font.BOLD, 17));
		name_label.setBounds(25, 130, 100, 30);
		contentPane.add(name_label);
		
		JLabel message_label = new JLabel("\uC624\uB298\uC758 \uD55C\uB9C8\uB514:");
		message_label.setFont(new Font("³ª´®°íµñ", Font.BOLD, 17));
		message_label.setBounds(25, 180, 118, 30);
		contentPane.add(message_label);
		
		JLabel nickname_label = new JLabel("\uBCC4\uBA85:");
		nickname_label.setFont(new Font("³ª´®°íµñ", Font.BOLD, 17));
		nickname_label.setBounds(25, 230, 66, 30);
		contentPane.add(nickname_label);
		
		JLabel phone_label = new JLabel("\uC804\uD654\uBC88\uD638:");
		phone_label.setFont(new Font("³ª´®°íµñ", Font.BOLD, 17));
		phone_label.setBounds(25, 280, 91, 30);
		contentPane.add(phone_label);
		
		JLabel homepage_label = new JLabel("\uD648\uD398\uC774\uC9C0 \uC8FC\uC18C:");
		homepage_label.setFont(new Font("³ª´®°íµñ", Font.BOLD, 17));
		homepage_label.setBounds(25, 330, 118, 30);
		contentPane.add(homepage_label);
		
		JLabel birth_label = new JLabel("\uC0DD\uB144\uC6D4\uC77C: ");
		birth_label.setFont(new Font("³ª´®°íµñ", Font.BOLD, 17));
		birth_label.setBounds(25, 380, 118, 30);
		contentPane.add(birth_label);
		
		JLabel email_label = new JLabel("\uC774\uBA54\uC77C: ");
		email_label.setFont(new Font("³ª´®°íµñ", Font.BOLD, 17));
		email_label.setBounds(25, 430, 118, 30);
		contentPane.add(email_label);
		
		name_textfield = new JTextField();
		name_textfield.setText(myInfo.getName());
		name_textfield.setBackground(new Color(255, 250, 240));
		name_textfield.setBounds(150, 130, 197, 30);
		contentPane.add(name_textfield);
		name_textfield.setColumns(10);
		
		message_textfield = new JTextField();
		message_textfield.setText(myInfo.getMessage());
		message_textfield.setColumns(10);
		message_textfield.setBackground(new Color(255, 250, 240));
		message_textfield.setBounds(150, 180, 197, 30);
		contentPane.add(message_textfield);
		
		nickname_textfield = new JTextField();
		nickname_textfield.setText(myInfo.getNickname());
		nickname_textfield.setColumns(10);
		nickname_textfield.setBackground(new Color(255, 250, 240));
		nickname_textfield.setBounds(150, 230, 197, 30);
		contentPane.add(nickname_textfield);
		
		phone_textfield = new JTextField();
		phone_textfield.setText(myInfo.getPhone());
		phone_textfield.setColumns(10);
		phone_textfield.setBackground(new Color(255, 250, 240));
		phone_textfield.setBounds(150, 280, 197, 30);
		contentPane.add(phone_textfield);
		
		homepage_textfield = new JTextField();
		homepage_textfield.setText(myInfo.getHomepage());
		homepage_textfield.setColumns(10);
		homepage_textfield.setBackground(new Color(255, 250, 240));
		homepage_textfield.setBounds(150, 330, 197, 30);
		contentPane.add(homepage_textfield);
		
		birth_textfield = new JTextField();
		birth_textfield.setText(myInfo.getBirth());
		birth_textfield.setColumns(10);
		birth_textfield.setBackground(new Color(255, 250, 240));
		birth_textfield.setBounds(150, 380, 197, 30);
		contentPane.add(birth_textfield);
		
		email_textfield = new JTextField();
		email_textfield.setText(myInfo.getEmail());
		email_textfield.setColumns(10);
		email_textfield.setBackground(new Color(255, 250, 240));
		email_textfield.setBounds(150, 430, 197, 30);
		contentPane.add(email_textfield);
		
		completed_button = new JButton("completed");
		completed_button.setBackground(new Color(255, 250, 240));
		completed_button.setBounds(142, 480, 163, 23);
		contentPane.add(completed_button);
		
		frame.setContentPane(contentPane);
		frame.setVisible(true);
		
	}
	public void close()
	{
		frame.setVisible(false);
	}
}
