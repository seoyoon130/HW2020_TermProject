package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import Chat.note;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JMenuItem;

public class My_Notes_Screen extends JFrame {

	private JPanel contentPane;
	JList notes_list;
	static JFrame frame=new JFrame();
	JMenuItem delete;
	JMenuItem show_send_time;
	static JMenuItem note_time;
	static JPopupMenu show_note_time;
	JButton refresh;
	DefaultListModel model;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					My_Notes_Screen frame = new My_Notes_Screen();
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
	public My_Notes_Screen() {
		setBackground(new Color(255, 235, 205));
		setBounds(600, 100, 450, 450);
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setBounds(600, 100, 450, 450);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel Notes_box_label = new JLabel("Notes Box");
		Notes_box_label.setFont(new Font("Consolas", Font.BOLD, 23));
		Notes_box_label.setBounds(148, 10, 120, 57);
		contentPane.add(Notes_box_label);
		
		refresh=new JButton("Refresh");
		refresh.setBounds(340,68,82,25);
		contentPane.add(refresh);
		
		JPopupMenu popupMenu=new JPopupMenu();
		popupMenu.setBounds(-10003, -10207, 65, 33);
		popupMenu.setLabel("");
		
		delete=new JMenuItem("»èÁ¦");
		delete.setBackground(new Color(240, 255, 255));
		delete.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		popupMenu.add(delete);
		show_send_time=new JMenuItem("Àü¼Û ½Ã°£ º¸±â");
		show_send_time.setBackground(new Color(240, 255, 255));
		show_send_time.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		popupMenu.add(show_send_time);
		
		
		
		
		notes_list = new JList();
		notes_list.setBackground(new Color(255, 245, 238));
		notes_list.setFont(new Font("ÇÑÄÄ °íµñ", Font.BOLD, 15));
		notes_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addPopup(notes_list,popupMenu);
		
		JLabel From_label = new JLabel("From");
		From_label.setFont(new Font("Calibri Light", Font.BOLD, 20));
		From_label.setBounds(24, 73, 83, 21);
		contentPane.add(From_label);
		
		JLabel Notes_label = new JLabel("Notes");
		Notes_label.setFont(new Font("Calibri Light", Font.BOLD, 20));
		Notes_label.setBounds(110, 73, 83, 21);
		contentPane.add(Notes_label);
		JScrollPane scrollPane = new JScrollPane(notes_list);
		scrollPane.setBounds(12, 94, 412, 310);
		contentPane.add(scrollPane);
		
		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}
	
	public void notes(Vector<note> list)
	{
		model=new DefaultListModel();
		for(int i=0;i<list.size();i++)
		{
			System.out.println("from: "+list.get(i).getsender()+" receiver_id: "+list.get(i).getreceiver()+" content: "+list.get(i).getcontent()+" time: "+list.get(i).getsend_time());
		}
		for(int i=0;i<list.size();i++)
		{
			model.addElement(list.get(i).getsender()+"       "+list.get(i).getcontent());
			
		}
		
		notes_list.setModel(model);
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
				
			}
		});
	}
	public static void close()
	{
		frame.setVisible(false);
	}
}
