package Chat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Chat.User;
//import Chat.note;
import Chat.Server.Handler;


public class Server {

	
	//private static HashMap<String,ObjectOutputStream> ON=new HashMap<>();//채팅 참여자,채팅 참여자의 outputstream
	private static Hashtable<String,ObjectOutputStream> ON_oos=new Hashtable<>();
	private static Hashtable<String,PrintWriter> ON_out=new Hashtable<>();
	private static Hashtable<Integer,Room> Rooms=new Hashtable<>();
	//private static ArrayList<String> ON_id=new ArrayList<>();
	private static JDBC db=new JDBC();
	//private static ArrayList<note> notes=new ArrayList<note>();
	static Vector<note> myNotes=new Vector<note>();
	static User myInfo;
	static int room_id=0;
	public static void main(String[] args) throws Exception
	{
		System.out.println("The chat new_server is RUNNING...");
		ExecutorService pool = Executors.newFixedThreadPool(1000);
		
		String ServerAddress="";
		int port_number=0;
		try
		{
			FileReader fr=new FileReader("C:\\Users\\lmslm\\eclipse-workspace/serverinfo.dat");
			BufferedReader br=new BufferedReader(fr);
			String line="";
			int ip_or_port=0;
			
			while((line=br.readLine())!=null)
			{
				StringTokenizer token=new StringTokenizer(line," ");
				while(token.hasMoreTokens())
				{
					if(ip_or_port==0)
					{
						ServerAddress=token.nextToken();
						ip_or_port=1;
					}
					else if(ip_or_port==1)
					{
						port_number=Integer.parseInt(token.nextToken());
						ip_or_port=0;
					}
				}
				
			}
			System.out.println("Address: "+ServerAddress+"Port: "+port_number);
			
			br.close();
		}
		catch(FileNotFoundException e)
		{
			ServerAddress="127.0.0.1";
			port_number=1234;
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		
		try (ServerSocket listener = new ServerSocket(port_number)) {
			while (true) {
				pool.execute(new Handler(listener.accept()));
			}
		}
	}
	public static class Handler implements Runnable
	{
		private Scanner in;
		private PrintWriter out;
		private Socket socket;
		
		public Handler(Socket socket)
		{
			this.socket=socket;
		}
		@Override
		public void run() {
			try
			{
				in=new Scanner(socket.getInputStream());
				out=new PrintWriter(socket.getOutputStream(),true);
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
				System.out.println("<<<NEW Client Connected>>>");
				
				
				while(in.hasNext())
				{
					String input=in.nextLine();
					System.out.println("From Client: "+input);
					if(input.startsWith("LOGIN"))
					{
						
						Login(input,oos,out);
					}
					else if(input.startsWith("REQUEST MAINSCREEN"))
					{
						send_Main_Screen();
					}
					else if(input.startsWith("CHECK ID DUPLICATION"))
					{
						String input_id=input.substring(21);
						String duplication="";//null이면 중복아이디 없음.
						duplication=db.check_duplication(input_id);
						if(duplication==null)
						{
							out.println("AVAILABLE");
							out.flush();
						}
						else
						{
							out.println("UNAVAILABLE");
							out.flush();
							
						}
					}
					else if(input.startsWith("NEWMEMBERSHIP"))
					{
						String id=in.nextLine();
						String pw=in.nextLine();
						String phone=in.nextLine();
						String name=in.nextLine();
						String nickname=in.nextLine();
						String email=in.nextLine();
						String birth=in.nextLine();
						String homepage=in.nextLine();
						
						db.join_membership(name,phone,id,pw,nickname,email,birth,homepage);
						out.println("SUCCESS");
					}
					else if(input.startsWith("LOGOUT"))
					{
						String logout_id=input.substring(7);
						db.logout(logout_id);
						ON_out.remove(logout_id);
						ON_oos.remove(logout_id);
						//ON_id.remove(logout_id);
						
						send_Main_Screen();
					}
					else if(input.startsWith("CHANGE MY INFO"))
					{
						String user_id=input.substring(15);
						
						User changed_my_info=(User)ois.readObject();
						db.update_myinfo(changed_my_info,user_id);
						send_Main_Screen();
					}
					else if(input.startsWith("SEND NOTE"))
					{
						String receiver=input.substring(10);
						
						note Note=(note)ois.readObject();
						db.sendNote(Note.getsender(), Note.getreceiver(), Note.getcontent(), Note.getsend_time());
					}
					else if(input.startsWith("SEARCH")) //친구 검색
					{
						String search_id=input.substring(7);
						Vector<User> results=new Vector<User>();
						results=db.search_friends_results(search_id);
						out.println("SEARCH RESULTS");
						out.flush();
						oos.writeObject(results);
						oos.flush();
					}
					else if(input.startsWith("ADD")) //친구 추가
					{
						StringTokenizer st=new StringTokenizer(input.substring(4),"&");
						int elements=0;
						String friend_id="";
						String user_id="";
						
						while(st.hasMoreTokens())
						{
							if(elements==0)
							{
								user_id=st.nextToken();
								elements++;
							}
							else if(elements==1)
							{
								friend_id=st.nextToken();
								elements++;
							}
							
						}
						System.out.println("user_id: "+user_id+" friend_id: "+friend_id);
						db.addFriend(user_id,friend_id);
						send_Main_Screen();
					}
					else if(input.startsWith("SHOW NOTES"))
					{
						String myid=input.substring(11);
						
						myNotes=db.show_notes(myid);
						out.println("MY NOTES");
						out.flush();
						oos.writeObject(myNotes);
						oos.flush();
					}
					else if(input.startsWith("DELETE"))
					{
						
						note want_to_delete=(note)ois.readObject();
						//StringTokenizer st=new StringTokenizer(input.substring(7)," ");
						System.out.println("want to delete note content: "+want_to_delete.getcontent());
						db.delete_note(want_to_delete.getsender(),want_to_delete.getreceiver(),want_to_delete.getcontent(),want_to_delete.getsend_time());
					}
					else if(input.startsWith("CHAT REQUEST"))
					{
						Vector<String> requested=new Vector<>();
						
						requested=(Vector<String>)ois.readObject();
						for(int i=0;i<requested.size();i++)
						{
							System.out.println("채팅 요청 받을 사람들: "+requested.get(i));
						}
						Hashtable<String,PrintWriter> founder_stream=new Hashtable<>();
						Hashtable<String,PrintWriter> requested_stream=new Hashtable<>();
						
						String founder=input.substring(13);
						founder_stream.put(founder, ON_out.get(founder));
						for(int i=0;i<requested.size();i++)
						{
							requested_stream.put(requested.get(i),ON_out.get(requested.get(i)));
						}
						room_id++;
						Room room=new Room(founder,requested,founder_stream,requested_stream,room_id);
						Rooms.put(room_id,room);
					}
					else if(input.startsWith("ACCEPTED CHAT REQUEST")) //채팅 요청 수락
					{
						StringTokenizer st=new StringTokenizer(input.substring(22)," ");
						int which_element=0;
						String participant="";
						int room_id=0;
						while(st.hasMoreTokens())
						{
							if(which_element==0)
							{
								participant=st.nextToken();
								which_element++;
							}
							else if(which_element==1)
							{
								room_id=Integer.parseInt(st.nextToken());
							}
						}
						Room room=Rooms.get(room_id);
						room.room_activate(participant,"ACCEPT");
					}
					else if(input.startsWith("LEFT"))
					{
						System.out.println(input.substring(5));
						StringTokenizer st=new StringTokenizer(input.substring(5)," ");
						int which_element=0;
						String left_id="";
						int room_id=0;
						while(st.hasMoreTokens())
						{
							if(which_element==0)
							{
								left_id=st.nextToken();
								which_element++;
							}
							else if(which_element==1)
							{
								room_id=Integer.parseInt(st.nextToken());
							}
						}
						Room room=Rooms.get(room_id);
						room.remove(left_id);
						room.broadcast(room.participants,"LEFT MESSAGE "+left_id+"님이 퇴장하셨습니다."+"\\\\"+room_id);
					}
					else if(input.startsWith("REJECTED CHAT REQUEST")) //채팅 요청 거절
					{
						StringTokenizer st=new StringTokenizer(input.substring(21)," ");
						int which_element=0;
						String reject_chat="";
						int room_id=0;
						while(st.hasMoreTokens())
						{
							if(which_element==0)
							{
								reject_chat=st.nextToken();
								which_element++;
							}
							else if(which_element==1)
							{
								room_id=Integer.parseInt(st.nextToken());
							}
						}
						Room room=Rooms.get(room_id);
						room.room_activate(reject_chat,"REJECT");
						
					}
					else if(input.startsWith("MESSAGE"))
					{
						StringTokenizer st=new StringTokenizer(input.substring(8),"\\\\");
						int which_element=0;
						String message="";
						int room_id=0;
						String from="";
						while(st.hasMoreTokens())
						{
							if(which_element==0)
							{
								message=st.nextToken();
								which_element++;
							}
							else if(which_element==1)
							{
								room_id=Integer.parseInt(st.nextToken());
								break;
							}
							
						}
						Room room=Rooms.get(room_id);
						room.broadcast(room.participants,input);
					}
					else if(input.startsWith("INVITE FRIEND TO CHAT ROOM"))
					{
						
						Vector<String> want_to_invite=(Vector<String>)ois.readObject();
						StringTokenizer st=new StringTokenizer(input.substring(27),"\\\\");
						int which_element=0;
						String founder=" ";
						int room_id=0;
						while(st.hasMoreTokens())
						{
							if(which_element==0)
							{
								founder=st.nextToken();
								which_element++;
							}
							else if(which_element==1)
							{
								room_id=Integer.parseInt(st.nextToken());
								break;
							}
						}
						Room room=Rooms.get(room_id);
						Hashtable<String,PrintWriter> additional_invite=new Hashtable<>();
						for(int i=0;i<want_to_invite.size();i++)
						{
							System.out.println("invited: "+want_to_invite.get(i));
							additional_invite.put(want_to_invite.get(i),ON_out.get(want_to_invite.get(i)));
							room.requested_chat_stream.put(want_to_invite.get(i),ON_out.get(want_to_invite.get(i)));
						}
						
						room.broadcast(additional_invite, "REQUEST FOR A CHAT "+founder+" "+room_id);
						
					}
					
					
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			finally
			{
				
				
				
			}
			
		}
	}
	public static void Login(String input,ObjectOutputStream oos,PrintWriter out)
	{
		
		StringTokenizer st=new StringTokenizer(input.substring(6),"&");
		int which_element=0;
		String id="",pw="";
		while(st.hasMoreTokens())
		{
			if(which_element==0)
			{
				id=st.nextToken();
				which_element++;
			}
			else if(which_element==1)
			{
				pw=st.nextToken();
			}
		}
		
		boolean valid_account=db.login(id, pw);
		
		if(valid_account==true)
		{
			System.out.println("here");
			ON_oos.put(id, oos);
			ON_out.put(id,out);
			//ON_id.add(id);
			out.println("ACCOUNTACCEPTED");
			out.flush();
		}
		else if(valid_account==false)
		{
			out.println("INVALIDACCOUNT");
			out.flush();
		}
	}
	
	
	public static void send_Main_Screen() throws IOException
	{
		for(java.util.Map.Entry<String,PrintWriter> entry:ON_out.entrySet())
		{
			PrintWriter writer=entry.getValue();
			String output_id=entry.getKey();
			ObjectOutputStream oos=ON_oos.get(output_id);
			System.out.println("==========output id: "+output_id+"==============");
			
			int friend_size=db.number_of_friends(output_id);
			Vector<User> list=new Vector<User>();
			list=db.friend(output_id);
			myInfo=db.myinfo(output_id);
			int size=list.size()+1;
			writer.println("MAINSCREEN "+size);
			writer.flush();
			
			
			for(int i=0;i<list.size()+1;i++)
			{
				if(i==0)
				{
					
					writer.println(myInfo.getID()+"&"+myInfo.getMessage()+"&"+myInfo.getName()+"&"+myInfo.getPhone()+"&"+myInfo.getEmail()+"&"+myInfo.getBirth()+"&"+myInfo.getHomepage()+"&"+myInfo.getNickname());
				}
				else
				{
					writer.println(list.get(i-1).getState()+"&"+list.get(i-1).getID()+"&"+list.get(i-1).getMessage()+"&"+list.get(i-1).getName()+"&"+list.get(i-1).getPhone()+"&"+list.get(i-1).getEmail()+"&"+list.get(i-1).getBirth()+"&"+list.get(i-1).getHomepage()+"&"+list.get(i-1).getLogin()+"&"+list.get(i-1).getLogout()+"&"+list.get(i-1).getNickname());
				}
			}
		
			//oos.writeObject(myInfo);
			//oos.flush();
			
			//oos.writeObject(list);
			//oos.flush();
			
				
			
		}
	}
	
}
