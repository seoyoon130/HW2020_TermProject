package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class Invitation_Screen extends JFrame {

	private JPanel contentPane;
	JFrame frame=new JFrame();
	DefaultListModel model;
	JList friend_list;
	JButton Invite_button;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Invitation_Screen frame = new Invitation_Screen();
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
	public Invitation_Screen() {
		setBackground(new Color(255, 235, 205));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		frame.setBounds(100, 100, 450, 450);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Invite_your_friends_label = new JLabel("Invite Your Friends");
		Invite_your_friends_label.setFont(new Font("Consolas", Font.BOLD, 23));
		Invite_your_friends_label.setBounds(93, 10, 253, 55);
		contentPane.add(Invite_your_friends_label);
		
		friend_list=new JList();
		friend_list.setBackground(new Color(255, 228, 181));
		friend_list.setFont(new Font("ÇÑÄÄ °íµñ", Font.BOLD, 15));
		friend_list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane(friend_list);
		scrollPane.setBounds(12, 88, 412, 270);
		contentPane.add(scrollPane);
		
		Invite_button = new JButton("Invite");
		Invite_button.setBackground(new Color(255, 228, 181));
		Invite_button.setFont(new Font("Consolas", Font.BOLD, 18));
		Invite_button.setBounds(152, 368, 143, 35);
		contentPane.add(Invite_button);
		
		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}
	public void friends(Vector<User> list)
	{
		model=new DefaultListModel();
		for(int i=0;i<list.size();i++)
		{
			model.addElement(list.get(i).getID());
		}
		friend_list.setModel(model);
	}
}
