package Chat;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Panel;
import java.awt.PopupMenu;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.MenuItem;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.Canvas;
import java.awt.Scrollbar;

public class Friend_list_main extends JFrame {
	

	private JPanel contentPane;
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
					Friend_list_main frame = new Friend_list_main(12,10);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	*/
	public Friend_list_main()
	{
		
	}
	public void close_update_before()
	{
		frame.setVisible(false);
		ON.clear();
		OFF.clear();
	}
	public void initialization()
	{
		
		frame.setVisible(false);
		on_page_index=0;
		off_page_index=0;
		
		
	}
	private int on_page_index=0;
	private int off_page_index=0;
	//private int now_page;
	int i=0;
	JMenuItem my_menuItem_1; //쪽지함
	JMenuItem my_menuItem_2;//내 정보 변경
	JMenuItem my_menuItem_3;
	JMenuItem friend_menuItem_1;//쪽지 보내기
	JMenuItem friend_menuItem_2;//파일 전송
	JMenuItem friend_menuItem_3;//대화요청
	JMenuItem friend_menuItem_4;//프로필 보기
	
	JPanel my_info;
	JButton btnNewButton_5;
	JButton btnNewButton_3;
	JButton btnNewButton_4;
	int ON_panel_size;
	int OFF_panel_size;
	JPanel[] ON_PANEL;
	JPanel[] OFF_PANEL;
	
	static ArrayList<User> ON=new ArrayList<>();
	static ArrayList<User> OFF=new ArrayList<>();
	//void initialize(int ON_size,int OFF_size)
	public Friend_list_main(User myInfo,Vector<User> friend_list) throws IOException 
	{
		frame.setTitle(myInfo.getID());
		System.out.println("my id: "+myInfo.getID());
		System.out.println("friend_size; "+friend_list.size());
		for(int i=0;i<friend_list.size();i++)
		{
			System.out.println(friend_list.get(i).toString());
			if(friend_list.get(i).getState().equals("on"))
			{
				ON.add(friend_list.get(i));
				System.out.println("*ON* "+friend_list.get(i).getID());
			}
			else if(friend_list.get(i).getState().equals("off"))
			{
				OFF.add(friend_list.get(i));
				System.out.println("*OFF* "+friend_list.get(i).getID());
			}
		}
		
		//frame=new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		//////setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 0, 500, 605);
		
		
		System.out.println("<ON_size> "+ON.size());
		System.out.println("<OFF_size> "+OFF.size());
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(176, 196, 222));
		contentPane.setSize(500,605);
		/////////setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//친구추가,친구 검색,채팅방 생성 버튼이 있는 panel - 상단바
		JPanel top_bar_panel = new JPanel(); 
		top_bar_panel.setBackground(new Color(255, 222, 173));
		top_bar_panel.setBounds(0, 0, 484, 40);
		top_bar_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(top_bar_panel);
		top_bar_panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("  CHAT CHAT");
		lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(0, 3, 164, 46);
		top_bar_panel.add(lblNewLabel_2);
		
		//ImageIcon img=new ImageIcon(Friend_list_main.class.getResource("/friend_list/img/219846755.jpg"));
		
		
		btnNewButton = new JButton("");// 게임
		btnNewButton.setIcon(new ImageIcon("./icon/game.png"));
		btnNewButton.setBackground(new Color(255, 239, 213));
		btnNewButton.setBounds(287, 1, 46, 38);
		top_bar_panel.add(btnNewButton);
		
		
		btnNewButton_3 = new JButton("");//친구 검색,추가
		btnNewButton_3.setBounds(333, 1, 46, 38);
		btnNewButton_3.setBackground(new Color(255, 239, 213));
		btnNewButton_3.setIcon(new ImageIcon("./icon/search.png"));
		//btnNewButton_3.setIcon(img);
		top_bar_panel.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("");//채팅방 만들기
		btnNewButton_4.setBounds(379, 1, 46, 38);
		btnNewButton_4.setIcon(new ImageIcon("./icon/plus.png"));
		btnNewButton_4.setBackground(new Color(255, 239, 213));
		top_bar_panel.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("");//로그아웃
		btnNewButton_5.setBounds(425, 1, 46, 38);
		btnNewButton_5.setIcon(new ImageIcon("./icon/logout.png"));
		btnNewButton_5.setBackground(new Color(255, 239, 213));
		top_bar_panel.add(btnNewButton_5);
		
		
		
		//공공데이터
		JPanel public_data_panel = new JPanel(); 
		
		public_data_panel.setBackground(new Color(255, 222, 173));
		public_data_panel.setBounds(0, 496, 484, 70);
		contentPane.add(public_data_panel);
		public_data_panel.setLayout(null);
		
		Weather_API weather=new Weather_API();
		JLabel lblNewLabel = new JLabel("Today's Weather");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 18));
		lblNewLabel.setBounds(20, 15, 373, 21);
		public_data_panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(50, 40, 370, 21);
		public_data_panel.add(lblNewLabel_3);
		lblNewLabel_3.setText(weather.toString());

		
		//MY INFO panel , MY INFO라고 써져 있는 곳
		JPanel MYINFO_panel = new JPanel(); 
		MYINFO_panel.setBackground(new Color(255, 248, 220));
		MYINFO_panel.setBounds(0, 41, 484, 30);
		MYINFO_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(MYINFO_panel);
		MYINFO_panel.setLayout(null);
				
		JLabel my_info_label = new JLabel("MY INFO");
		my_info_label.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		my_info_label.setBounds(12, 3, 155, 30);
		MYINFO_panel.add(my_info_label);
				
		//내 정보 오른쪽 마우스 클릭 시 나타나는 팝업 메뉴(쪽지함 보기,내정보 변경 가능)
		JPopupMenu popupMenu_my=new JPopupMenu();
		popupMenu_my.setBounds(-10003, -10207, 65, 33);
		popupMenu_my.setLabel("");
			
		my_menuItem_1=new JMenuItem("쪽지함");
		my_menuItem_1.setBackground(new Color(240, 255, 255));
		my_menuItem_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		popupMenu_my.add(my_menuItem_1);
			
		my_menuItem_2=new JMenuItem("내 정보 변경");
		my_menuItem_2.setBackground(new Color(240, 255, 255));
		my_menuItem_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		popupMenu_my.add(my_menuItem_2);
		
		//내 프로필 띄우는 panel
		my_info = new JPanel(); 
		my_info.setBackground(new Color(255, 228, 181));
		my_info.setBounds(0, 71, 484, 40);
		my_info.setLayout(null);
		my_info.setBorder(new LineBorder(new Color(0, 0, 0)));
		addMyPopup(my_info,popupMenu_my);
		contentPane.add(my_info);
		
		JLabel my_id_label = new JLabel(myInfo.getID());
		my_id_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		my_id_label.setBounds(10, 5, 112, 30);
		my_info.add(my_id_label);
		
		JLabel my_message_label = new JLabel(myInfo.getMessage());
		my_message_label.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
		my_message_label.setBounds(200, 5, 272, 30);
		my_info.add(my_message_label);
		
		
		
		
		// ON이라고 써져 있는 곳 (친구 목록을 넘겨서 볼 수 있는 버튼 존재)
		JPanel ON_panel = new JPanel(); 
		ON_panel.setBackground(new Color(255, 248, 220));
		ON_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		ON_panel.setBounds(0, 111, 484, 30);
		contentPane.add(ON_panel);
		ON_panel.setLayout(null);
		
		JButton ON_button_left = new JButton("<");
		ON_button_left.setBackground(new Color(255, 245, 238));
		ON_button_left.setFont(new Font("Arial Black", Font.BOLD, 10));
		ON_button_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ON_button_left.setBounds(392, 2, 45, 25);
		ON_panel.add(ON_button_left);
		
		
		JButton ON_button_right = new JButton(">");
		ON_button_right.setBackground(new Color(255, 245, 238));
		ON_button_right.setFont(new Font("Arial Black", Font.BOLD, 10));
		ON_button_right.setBounds(432, 2, 45, 25);
		ON_panel.add(ON_button_right);
		
		JLabel lblNewLabel_1 = new JLabel("ON");
		lblNewLabel_1.setBounds(12, 3, 52, 30);
		ON_panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		
		
		//친구 목록 오른쪽 마우스 클릭 시 나타나는 팝업 메뉴
		JPopupMenu popupMenu_friend=new JPopupMenu(); 
		popupMenu_friend.setBounds(-10003, -10207, 65, 33);
		popupMenu_friend.setLabel("");
		
		
		friend_menuItem_1=new JMenuItem("쪽지보내기");
		friend_menuItem_1.setBackground(new Color(240, 255, 255));
		friend_menuItem_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		popupMenu_friend.add(friend_menuItem_1);
		
		friend_menuItem_2=new JMenuItem("파일전송");
		friend_menuItem_2.setBackground(new Color(240, 255, 255));
		friend_menuItem_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		popupMenu_friend.add(friend_menuItem_2);
		
		friend_menuItem_3=new JMenuItem("대화요청");
		friend_menuItem_3.setBackground(new Color(240, 255, 255));
		friend_menuItem_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		popupMenu_friend.add(friend_menuItem_3);
		
		friend_menuItem_4=new JMenuItem("프로필보기");
		friend_menuItem_4.setBackground(new Color(240, 255, 255));
		friend_menuItem_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		popupMenu_friend.add(friend_menuItem_4);
		
		//ON 상태인 친구들 목록 띄우는 패널
		ON_panel_size=(int) Math.ceil((double)ON.size()/4);
		System.out.println("ON_panel_size: "+ON_panel_size);
		
		ON_PANEL=new JPanel[ON_panel_size];
		JPanel[] ON_LIST_PANEL=new JPanel[ON_panel_size*4];
		JLabel[] ON_id=new JLabel[ON.size()];
		JLabel[] ON_message=new JLabel[ON.size()];
		if(ON.size()>0)
		{
			for(int i=0;i<ON_panel_size;i++)
			{
				ON_PANEL[i]=new JPanel();
				ON_PANEL[i].setBounds(1,142,484,160);
				ON_PANEL[i].setLayout(null);
				int j=0;
				int list_index=0;
				int start_index=i*4;
				for(int index=start_index;index<start_index+4;index++)
				{
					ON_LIST_PANEL[index]=new JPanel();
					ON_LIST_PANEL[index].setLayout(null);
					ON_LIST_PANEL[index].setBounds(0,j*40,484,40);
					ON_LIST_PANEL[index].setBackground(new Color(255, 239, 213));
					
					if(index<ON.size())
					{
						ON_id[index]=new JLabel();
						ON_id[index].setBounds(10,4,112,30);
						ON_id[index].setFont(new Font("함초롬돋움", Font.BOLD, 20));
						ON_id[index].setText(ON.get(index).getID());
						System.out.println("*****ON->"+ON.get(index).getID());
						ON_LIST_PANEL[index].add(ON_id[index]);
						
						ON_message[index]=new JLabel();
						ON_message[index].setBounds(200,4,272,30);
						ON_message[index].setFont(new Font("함초롬돋움", Font.PLAIN, 20));
						ON_message[index].setText(ON.get(index).getMessage());
						ON_LIST_PANEL[index].add(ON_message[index]);
						list_index++;
					}
					
					ON_PANEL[i].add(ON_LIST_PANEL[index]);
					j++;
					
				}
				contentPane.add(ON_PANEL[i]);
			}
			ON_PANEL[0].setVisible(true);
			for(int i=1;i<ON_panel_size;i++)
			{
				ON_PANEL[i].setVisible(false);
			}
		}
		else
		{
			contentPane.setBackground(new Color(255, 239, 213));
		}
		ON_button_right.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				if(on_page_index==ON_panel_size-1)
				{
					JOptionPane.showMessageDialog(null, "마지막 페이지입니다");
				}
				else
				{
					on_page_index++;
					for(int i=0;i<ON_panel_size;i++)
					{
						if(i==on_page_index)
						{
							ON_PANEL[i].setVisible(true);
						}
						else
						{
							ON_PANEL[i].setVisible(false);
						}
					}
				}
			}
			
		});
		ON_button_left.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				if(on_page_index==0)
				{
					JOptionPane.showMessageDialog(null, "처음 페이지입니다.");
				}
				else
				{
					on_page_index--;
					for(int i=0;i<ON_panel_size;i++)
					{
						if(i==on_page_index)
						{
							ON_PANEL[i].setVisible(true);
						}
						else
						{
							ON_PANEL[i].setVisible(false);
						}
					}
				}
			}
			
		});
		
		for(i=0;i<ON_panel_size;i++)
		{
			addfriendPopup(ON_PANEL[i],popupMenu_friend,i,"ON");
		}
		
		//OFF라고 써져 있는 곳(친구 목록을 넘겨서 볼 수 있는 버튼 존재)
		JPanel OFF_panel = new JPanel(); 
		OFF_panel.setBackground(new Color(255, 248, 220));
		OFF_panel.setLayout(null);
		OFF_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		OFF_panel.setBounds(0, 303, 484, 30);
		contentPane.add(OFF_panel);
		
		JButton OFF_button_left = new JButton("<");
		OFF_button_left.setBackground(new Color(255, 245, 238));
		OFF_button_left.setFont(new Font("Arial Black", Font.BOLD, 10));
		OFF_button_left.setBounds(394, 2, 45, 25);
		OFF_panel.add(OFF_button_left);
		
		JButton OFF_button_right = new JButton(">");
		OFF_button_right.setBackground(new Color(255, 245, 238));
		OFF_button_right.setFont(new Font("Arial Black", Font.BOLD, 10));
		OFF_button_right.setBounds(432, 2, 45, 25);
		OFF_panel.add(OFF_button_right);
		
		JLabel lblNewLabel_1_1 = new JLabel("OFF");
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(12, 3, 52, 30);
		OFF_panel.add(lblNewLabel_1_1);
		
		
		//OFF 상태인 친구들 목록 띄우는 
		OFF_panel_size=(int)Math.ceil((double)OFF.size()/4);
		System.out.println("OFF_panel_size: "+OFF_panel_size);
		OFF_PANEL=new JPanel[OFF_panel_size];
		JPanel[] OFF_LIST_PANEL=new JPanel[OFF_panel_size*4];
		JLabel[] OFF_id=new JLabel[OFF.size()];
		JLabel[] OFF_message=new JLabel[OFF.size()];
		//int list_index = 0;
		if(OFF.size()>0)
		{
			for(int i=0;i<OFF_panel_size;i++)
			{
				OFF_PANEL[i]=new JPanel();
				OFF_PANEL[i].setBounds(0,334,484,160);
				OFF_PANEL[i].setLayout(null);
				int j=0;
				int start_index=i*4;
				for(int index=start_index;index<start_index+4;index++)
				{
					OFF_LIST_PANEL[index]=new JPanel();
					OFF_LIST_PANEL[index].setLayout(null);
					OFF_LIST_PANEL[index].setBounds(0,j*40,484,40);
					OFF_LIST_PANEL[index].setBackground(new Color(255, 239, 213));
					
					if(index<OFF.size())
					{
						OFF_id[index]=new JLabel();
						OFF_LIST_PANEL[index].setLayout(null);
						OFF_id[index].setBounds(10,4,112,30);
						OFF_id[index].setFont(new Font("함초롬돋움", Font.BOLD, 20));
						//OFF_id[index].setBorder(new LineBorder(new Color(255,255,255),3));
						System.out.println("*****OFF->"+OFF.get(index).getID());
						OFF_id[index].setText(OFF.get(index).getID());
						OFF_LIST_PANEL[index].add(OFF_id[index]);
						
						OFF_message[index]=new JLabel();
						OFF_message[index].setBounds(200,4,272,30);
						OFF_message[index].setFont(new Font("함초롬돋움", Font.PLAIN, 20));
						//OFF_message[index].setBorder(new LineBorder(new Color(255,255,255),3));
						OFF_message[index].setText(OFF.get(index).getMessage());
						OFF_LIST_PANEL[index].add(OFF_message[index]);
					}
					OFF_PANEL[i].add(OFF_LIST_PANEL[index]);
					j++;
					
				}
				contentPane.add(OFF_PANEL[i]);
			}
			
			OFF_PANEL[0].setVisible(true);
			for(int i=1;i<OFF_panel_size;i++)
			{
				OFF_PANEL[i].setVisible(false);
			}
		}
		else
		{
			contentPane.setBackground(new Color(255, 239, 213));
		}
		OFF_button_right.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				if(off_page_index==OFF_panel_size-1)
				{
					JOptionPane.showMessageDialog(null, "마지막 페이지입니다");
				}
				else
				{
					off_page_index++;
					for(int i=0;i<OFF_panel_size;i++)
					{
						if(i==off_page_index)
						{
							OFF_PANEL[i].setVisible(true);
						}
						else
						{
							OFF_PANEL[i].setVisible(false);
						}
					}
				}
			}
			
		});
		OFF_button_left.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				if(off_page_index==0)
				{
					JOptionPane.showMessageDialog(null, "처음 페이지입니다.");
				}
				else
				{
					off_page_index--;
					for(int i=0;i<OFF_panel_size;i++)
					{
						if(i==off_page_index)
						{
							OFF_PANEL[i].setVisible(true);
						}
						else
						{
							OFF_PANEL[i].setVisible(false);
						}
					}
				}
			}
			
		});
		for(i=0;i<OFF_panel_size;i++)
		{
			addfriendPopup(OFF_PANEL[i],popupMenu_friend,i,"OFF");
		}
		
		
	
		frame.getContentPane().add(contentPane);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//frame.add(contentPane);
	
		}
		
		
		private static int whose(int y,int panel_index)
		{
			int index=0; // arraylist안에 들어있는 사람 중 몇 번 인덱스의 사람의 정보인지 알기 위한 index
			if(0<=y&&y<40)
			{
				index=0;
			}
			else if(40<=y&&y<80)
			{
				index=1;
			}
			else if(80<=y&&y<120)
			{
				index=2;
			}
			else if(120<=y&&y<160)
			{
				index=3;
			}
			index=4*panel_index+index;
			
			return index;
		}
		static int whose_index=0;
		static String whose;
		static String receiver_id="";
		private static void addfriendPopup(Component component,final JPopupMenu popup,int panel_index,String ON_OFF)
		{
			
			component.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
						//whose_index=whose(e.getY(),panel_index);
					}
				}
				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
						//whose_index=whose(e.getY(),panel_index);
					}
				}
				private void showMenu(MouseEvent e) {
					popup.show(e.getComponent(), e.getX(), e.getY());
					System.out.println("mouseY: "+e.getY());
					whose_index=whose(e.getY(),panel_index);
					//System.out.println("STATE: "+ON_OFF);
					
					whose=ON_OFF+whose_index;
					
					System.out.println("whose: "+whose);
					receiver_id=get_whose_id();
					System.out.println("receiver: "+receiver_id);
				}
			});
			//return whose;
		}
		
		static String get_whose_id()
		{
			String receiver="";
			if(whose.startsWith("ON"))
			{
				whose_index=Integer.parseInt(whose.substring(2));
				receiver=ON.get(whose_index).getID();
				
			}
			else if(whose.startsWith("OFF"))
			{
				whose_index=Integer.parseInt(whose.substring(3));
				receiver=OFF.get(whose_index).getID();
			}
			//System.out.println("in Friend_list_main- receiver: "+receiver_id);
			return receiver;
		}
		private static void addMyPopup(Component component,JPopupMenu myPopup)
		{
			component.addMouseListener(new MouseAdapter()
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
					myPopup.show(e.getComponent(), e.getX(), e.getY());
					
				}
			});
		}
		
		public void close()
		{
			System.out.println("<in GUI LOGOUT>");
			frame.setVisible(false);
		}
		
}

