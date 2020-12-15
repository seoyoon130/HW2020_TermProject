package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class search_add_friend extends JFrame {

	private JPanel contentPane;
	JTextField textField;
	//private JTextField textField_1;
	JFrame frame=new JFrame();
	JButton btnNewButton;
	JList search_results;
	JMenuItem add;
	JMenuItem show_profile;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					search_add_friend frame = new search_add_friend();
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
	public search_add_friend() {
		setBackground(new Color(245, 222, 179));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setBounds(100, 0, 500, 550);
		
		JLabel lblNewLabel = new JLabel("Search Friends");
		lblNewLabel.setFont(new Font("Ebrima", Font.BOLD, 25));
		lblNewLabel.setBounds(133, 20, 181, 52);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 240));
		textField.setBounds(50, 85, 345, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton(""); //°Ë»ö ¹öÆ°
		btnNewButton.setBackground(new Color(222, 184, 135));
		btnNewButton.setBounds(405, 85, 50, 40);
		contentPane.add(btnNewButton);
		
		JPopupMenu popupMenu=new JPopupMenu();
		popupMenu.setBounds(-10003, -10207, 65, 33);
		popupMenu.setLabel("");
		
		add=new JMenuItem("Ä£±¸ Ãß°¡");
		add.setBackground(new Color(240, 255, 255));
		add.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		popupMenu.add(add);
		show_profile=new JMenuItem("ÇÁ·ÎÇÊ º¸±â");
		show_profile.setBackground(new Color(240, 255, 255));
		show_profile.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		popupMenu.add(show_profile);
		
		
		
		search_results=new JList();
		
		search_results.setBackground(new Color(253, 245, 230));
		search_results.setFont(new Font("ÇÑÄÄ °íµñ", Font.BOLD, 15));
		search_results.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JScrollPane scrollPane=new JScrollPane(search_results);
		addPopup(search_results,popupMenu);
		scrollPane.setBounds(50, 150, 400, 350);
		contentPane.add(scrollPane);
		
		frame.setContentPane(contentPane);
		frame.setVisible(true);
		
		
	}
	public void show_results(Vector<User> results)
	{
		
		DefaultListModel model=new DefaultListModel();
		for(int i=0;i<results.size();i++)
		{
			model.addElement(results.get(i).getID());
		}
		search_results.setModel(model);
	}
	public static void addPopup(Component c,JPopupMenu popup)
	{
		c.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
					
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
					
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
				//System.out.println("mouseY: "+e.getY());
				//whose_index=whose(e.getY(),panel_index);
				//System.out.println("STATE: "+ON_OFF);
				//whose=ON_OFF+whose_index;
				//System.out.println("whose: "+whose);
			}
		});
	}
	
	
}
