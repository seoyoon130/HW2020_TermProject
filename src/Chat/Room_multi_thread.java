package Chat;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Room_multi_thread
{
	ExecutorService pool = Executors.newFixedThreadPool(1000);
	
	public void new_room(Chatting_room_Screen chatting_room,PrintWriter out,ObjectOutputStream oos,Vector<User> ON)
	{
		
		
		pool.execute(new Handler(chatting_room,out,oos,ON));
	}
	public static class Handler implements Runnable
	{
		static Hashtable<Integer,Chatting_room_Screen> Screens=new Hashtable<>();
		Chatting_room_Screen chatting_screen;
		PrintWriter out;
		ObjectOutputStream oos;
		Vector<User> ON;
		
		public Handler(Chatting_room_Screen chatting_screen,PrintWriter out,ObjectOutputStream oos,Vector<User> ON)
		{
			this.chatting_screen=chatting_screen;
			this.out=out;
			this.oos=oos;
			this.ON=ON;
		}
		public void run()
		{
			chatting_screen.send_button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("send button clicked id:"+chatting_screen.myID+" room_id:"+chatting_screen.room_id);
					String message=chatting_screen.message_textfield.getText();
					System.out.println("message: "+message);
					out.println("MESSAGE "+message+"\\\\"+chatting_screen.room_id+"\\\\"+chatting_screen.myID);
					chatting_screen.message_textfield.setText("");
				}
			});
			chatting_screen.invitation_button.addActionListener(new ActionListener()
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
							out.println("INVITE FRIEND TO CHAT ROOM "+chatting_screen.myID+"\\\\"+chatting_screen.room_id);
							
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
			chatting_screen.exit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("left: "+chatting_screen.myID);
					out.println("LEFT "+chatting_screen.myID+" "+chatting_screen.room_id);
					Client.Rooms.remove(chatting_screen.room_id);
					chatting_screen.close();
				}
			});
		}
	}
}

