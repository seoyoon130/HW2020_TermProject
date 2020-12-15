package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class make_chattingroom_show_list extends JFrame {

	private JPanel contentPane;
	JList friends;
	JFrame frame=new JFrame();
	JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					make_chattingroom_show_list frame = new make_chattingroom_show_list();
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
	public make_chattingroom_show_list() {
		setBackground(new Color(255, 250, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setBounds(100, 0, 450, 450);
		JLabel lblNewLabel = new JLabel("Choose Friends");
		lblNewLabel.setBounds(135, 30, 151, 42);
		lblNewLabel.setFont(new Font("Nirmala UI", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("CREATE");
		btnNewButton.setBounds(164, 371, 107, 32);
		btnNewButton.setBackground(new Color(255, 250, 250));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		contentPane.add(btnNewButton);
		
		friends=new JList();
		friends.setBackground(new Color(253, 245, 230));
		friends.setFont(new Font("ÇÑÄÄ °íµñ", Font.BOLD, 15));
		
		friends.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(friends);
		scrollPane.setBounds(12, 92, 412, 257);
		contentPane.add(scrollPane);
		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}
	
	public void friends_list(Vector<User> list)
	{
		System.out.println("<ON>");
		for(int i=0;i<list.size();i++)
		{
			System.out.println("ON line friend id: "+list.get(i).getID());
			
		}
		DefaultListModel model=new DefaultListModel();
		for(int i=0;i<list.size();i++)
		{
			model.addElement(list.get(i).getID());
		}
		friends.setModel(model);
	}
}
