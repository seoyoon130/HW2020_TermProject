package Chat;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class Chatting_room_Screen extends JFrame{

	private JPanel contentPane;
	JTextField message_textfield;
	static int room_id;
	JButton send_button;
	JTextArea textArea;
	JFrame frame=new JFrame();
	JButton invitation_button;
	JButton exit;
	static String myID="";
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chatting_room_Screen frame = new Chatting_room_Screen(room_id);
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
	public Chatting_room_Screen(int room_id,String id,PrintWriter out,ObjectOutputStream oos,Vector<User> ON) {
		this.room_id=room_id;
		this.myID=id;
		frame.setTitle("room_id: "+room_id+" id: "+id);
		setBackground(new Color(245, 245, 220));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 605);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setBounds(100,100,500,605);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 500, 380, 60);
		contentPane.add(scrollPane);
		
		message_textfield = new JTextField();
		message_textfield.setBackground(new Color(253, 245, 230));
		scrollPane.setViewportView(message_textfield);
		message_textfield.setColumns(10);
		
		send_button = new JButton("SEND");
		send_button.setFont(new Font("Consolas", Font.BOLD, 16));
		send_button.setBounds(404, 500, 70, 60);
		send_button.setBackground(new Color(250, 240, 230));
		send_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("send button clicked id:"+myID+" room_id:"+room_id);
				String message=message_textfield.getText();
				System.out.println("message: "+message);
				out.println("MESSAGE "+message+"\\\\"+room_id+"\\\\"+myID);
				message_textfield.setText("");
			}
		});
		contentPane.add(send_button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 53, 462, 437);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(255, 250, 240));
		scrollPane_1.setViewportView(textArea);
		
		invitation_button = new JButton("+");
		invitation_button.setBackground(new Color(245, 222, 179));
		invitation_button.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
		invitation_button.setBounds(380, 4, 45, 40);
		invitation_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Invitation_Screen invitation_screen=new Invitation_Screen();
				invitation_screen.friends(ON);
				
				invitation_screen.Invite_button.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						@SuppressWarnings("deprecation")
						List selected_friends=invitation_screen.friend_list.getSelectedValuesList();
						Vector<String> want_to_invite=new Vector<String>();
						for(int i=0;i<selected_friends.size();i++)
						{
							
							want_to_invite.add((String) selected_friends.get(i));
							System.out.println("초대된 사람: "+want_to_invite.get(i));
						}
						out.println("INVITE FRIEND TO CHAT ROOM "+myID+"\\\\"+room_id);
						
						try {
							
							oos.writeObject(want_to_invite);
							oos.flush();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
			
		});
		contentPane.add(invitation_button);
		
		exit = new JButton("-");
		exit.setBackground(new Color(245, 222, 179));
		exit.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
		exit.setBounds(422, 4, 45, 40);
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("left: "+myID);
				out.println("LEFT "+myID+" "+room_id);
				Client.Rooms.remove(room_id);
				close();
			}
		});
		contentPane.add(exit);
		
		JLabel chatting_label = new JLabel("Chatting...");
		chatting_label.setFont(new Font("Consolas", Font.BOLD, 20));
		chatting_label.setBounds(30, 10, 380, 40);
		contentPane.add(chatting_label);
		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}
	public void close()
	{
		frame.setVisible(false);
	}
}
