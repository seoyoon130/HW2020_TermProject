package Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.net.*;
import java.text.SimpleDateFormat;

import Chat.User;
import Chat.note;
import Chat.Change_my_info;
import Chat.search_add_friend;

public class Client {

	String ServerAddress;
	int port_number;
	String id;
	String pw;
	Scanner in;
	PrintWriter out;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Socket socket;
	Vector<User> ON=new Vector<User>();
	Vector<User> OFF=new Vector<User>();
	Vector<User> friend_list=new Vector<User>();
	Vector<User> search_results=new Vector<User>();
	static HashMap<Integer,Chatting_room_Screen> Rooms=new HashMap<>();
	Vector<note> my_notes=new Vector<note>();
	User myInfo=new User();
	Room_multi_thread room_multi=new Room_multi_thread();
	
	Join_Member_Screen join_member;
	Friend_list_main friend_list_screen=new Friend_list_main();
	Login_Screen login;
	Send_Note_Screen send_note_screen;
	Profile_Screen profile;
	search_add_friend search_add;
	make_chattingroom_show_list to_make_chattingroom;
	//Chatting_room_Screen chatting_screen;
	
	
	public Client()
	{
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
	}
	public static void main(String[] args) throws Exception
	{
		Client c=new Client();
		c.run();
	}
	
	public void run()  throws IOException, ClassNotFoundException
	{
		try
		{
			socket=new Socket(ServerAddress,port_number);
			in=new Scanner(socket.getInputStream());
			out=new PrintWriter(socket.getOutputStream(),true);
			oos=new ObjectOutputStream(socket.getOutputStream());
			ois=new ObjectInputStream(socket.getInputStream());
			System.out.println("connected");
			
			login=new Login_Screen();
			login.Login_button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("login button");
					
					id=login.ID_textfield.getText();
					pw=new String(login.PW_passwordfield.getPassword());
					System.out.println("ID: "+id+" PW: "+pw);
					if(id.equals("")||pw.equals(""))
					{
						login.didnt_put_id_or_pw();
						System.out.println("invalid");
					}
					else
					{
						System.out.println("valid id: "+id+" pw: "+pw);
						String outString="LOGIN "+id+"&"+pw;
						out.println("LOGIN "+id+"&"+pw);
					}
					
				}
			});
			login.Join_Member_button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
					join_member=new Join_Member_Screen();
					
					join_member.complete_button.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							if(join_member.checking_duplication==1)
							{
								out.println("NEWMEMBERSHIP");
								out.println(join_member.ID_textfield.getText());
								out.println(join_member.PW_passwordfield.getPassword());
								out.println(join_member.Phone_textfield.getText());
								out.println(join_member.Name_textfield.getText());
								out.println(join_member.Nickname_textfield.getText());
								out.println(join_member.Email_textfield.getText());
								out.println(join_member.Birth_textfield.getText());
								out.println(join_member.Homepage_textfield.getText());
		
							}
							else if(join_member.checking_duplication==0)
							{
								join_member.caution_didnt_check_duplication(); 
							}
						}
					});
					join_member.check_duplicate_button.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							out.println("CHECK ID DUPLICATION "+join_member.ID_textfield.getText());
							join_member.checking_duplication=1;
						}
					});
					
				}
				
			});
			
			while(in.hasNextLine())
			{
				String input=in.nextLine();
				System.out.println("From Server: "+input);
				
				if(input.startsWith("ACCOUNTACCEPTED"))
				{
					login.close();
					out.println("REQUEST MAINSCREEN");
					friend_list_screen=new Friend_list_main();
				}
				else if(input.startsWith("INVALIDACCOUNT"))
				{
					login.caution_invalid();
				}
				else if(input.startsWith("SUCCESS"))
				{
					JOptionPane.showMessageDialog(join_member, "회원가입 성공!",null,JOptionPane.INFORMATION_MESSAGE);
				}
				else if(input.startsWith("MAINSCREEN"))
				{
					int size=Integer.parseInt(input.substring(11));
					int distinguish_elements;
					ON.clear();
					OFF.clear();
					friend_list.clear();
					distinguish_elements=0;
					
					System.out.println("-----------내 정보-----------------");
					
				
					for(int i=0;i<size;i++)
					{
						input=in.nextLine();
						System.out.println("input>"+input);
						//System.out.println("==================================");
						//System.out.println(input);
						//System.out.println("==================================");
						
						distinguish_elements=0;
						String id="",state="",message="",name="",phone="",email="",birth="",homepage="",login="",logout="",nickname="";
						if(i==0)
						{
							
							System.out.println("-----------내 정보-----------------");
							StringTokenizer st=new StringTokenizer(input,"&");
							while(st.hasMoreTokens())
							{
								if(distinguish_elements==0)
								{
									id=st.nextToken();
									myInfo.setID(id);
									System.out.println("id: "+id);
									//friend.setid(st.nextToken());
									distinguish_elements++;
								}
								else if(distinguish_elements==1)
								{
									message=st.nextToken();
									myInfo.setMessage(message);
									//friend.setmessage(st.nextToken());
									distinguish_elements++;
								}
								else if(distinguish_elements==2)
								{
									name=st.nextToken();
									myInfo.setName(name);
									System.out.println("name: "+name);
									//System.out.println("!@!@!@!@!@!name: "+myInfo.getname());
									distinguish_elements++;
								}
								else if(distinguish_elements==3)
								{
									phone=st.nextToken();
									myInfo.setPhone(phone);
									System.out.println("phone: "+phone);
									distinguish_elements++;
								}
								else if(distinguish_elements==4)
								{
									email=st.nextToken();
									myInfo.setEmail(email);
									System.out.println("email: "+email);
									distinguish_elements++;
								}
								else if(distinguish_elements==5)
								{
									birth=st.nextToken();
									myInfo.setBirth(birth);
									System.out.println("birth: "+birth);
									distinguish_elements++;
								}
								else if(distinguish_elements==6)
								{
									homepage=st.nextToken();
									myInfo.setHomepage(homepage);
									System.out.println("homepage: "+homepage);
									distinguish_elements++;
								}
								else if(distinguish_elements==7)
								{
									nickname=st.nextToken();
									myInfo.setNickname(nickname);
									System.out.println("nickname: "+nickname);
									distinguish_elements++;
								}
								
							}
						}
						else
						{
							StringTokenizer st=new StringTokenizer(input,"&");
							while(st.hasMoreTokens())
							{
								if(distinguish_elements==0)
								{
									state=st.nextToken();
									
										//friend.setid(st.nextToken());
									distinguish_elements++;
								}
								else if(distinguish_elements==1)
								{
									id=st.nextToken();
									//friend.setmessage(st.nextToken());
									distinguish_elements++;
								}
								else if(distinguish_elements==2)
								{
									message=st.nextToken();
									//System.out.println("name: "+name);
									distinguish_elements++;
								}
								else if(distinguish_elements==3)
								{
									name=st.nextToken();
									//System.out.println("phone: "+phone);
									distinguish_elements++;
								}
								else if(distinguish_elements==4)
								{
									phone=st.nextToken();
									//System.out.println("email: "+email);
									distinguish_elements++;
								}
								else if(distinguish_elements==5)
								{
									email=st.nextToken();
									//System.out.println("birth: "+birth);
										distinguish_elements++;
									}
									else if(distinguish_elements==6)
									{
										birth=st.nextToken();
										//System.out.println("homepage: "+homepage);
										distinguish_elements++;
									}
									else if(distinguish_elements==7)
									{
										homepage=st.nextToken();
										//System.out.println("login: "+login);
										distinguish_elements++;
									}
									else if(distinguish_elements==8)
									{
										login=st.nextToken();
										//System.out.println("logout: "+logout);
										distinguish_elements++;
									}
									else if(distinguish_elements==9)
									{
										logout=st.nextToken();
										//System.out.println("nickname: "+nickname);
										distinguish_elements++;
									}
									else if(distinguish_elements==10)
									{
										nickname=st.nextToken();
										//System.out.println("nickname: "+nickname);
										distinguish_elements++;
									}
							}
							friend_list.add(new User(name,phone,id,nickname,email,birth,homepage,login,logout,state,message));
						}
					}
					
					/*
					//myInfo=(User)ois.readObject();
					
					Object objectmyInfo=ois.readObject();
					//System.out.println(objectmyInfo.toString());
				//System.out.println(myInfo.toString());
					myInfo=(User)objectmyInfo;
					System.out.println(myInfo.toString());
					friend_list.clear();
					ON.clear();
					//Object objectFriend_list=ois.readObject();
					//friend_list=(ArrayList<User>)ois.readObject();
					
					friend_list=(Vector<User>)ois.readObject();
					for(int i=0;i<friend_list.size();i++)
					{
						System.out.println(">"+friend_list.get(i).toString());
					}
					*/
					
					//friend_list_screen.initialization();
					
					friend_list_screen.close_update_before();
					friend_list_screen=new Friend_list_main(myInfo,friend_list);
					for(int i=0;i<friend_list.size();i++)
					{
						if(friend_list.get(i).getState().equals("on"))
						{
							ON.add(friend_list.get(i));
						}
						else
						{
							OFF.add(friend_list.get(i));
						}
					}
					friend_list_screen.btnNewButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							game369 mygame=new game369();
						}
					});
					//로그아웃
					friend_list_screen.btnNewButton_5.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							int result=JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?");
							if(result==JOptionPane.CLOSED_OPTION)
							{
								
							}
							else if(result==JOptionPane.YES_OPTION)
							{
								out.println("LOGOUT "+myInfo.getID());
								try {
									oos.close();
									out.close();
									in.close();
									ois.close();
									
								} catch (IOException e1) {
									
									e1.printStackTrace();
								}
								
								
								friend_list_screen.close();
								System.exit(0);
								
							}
						}
					});
					//친구 검색,추가
					friend_list_screen.btnNewButton_3.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							search_add=new search_add_friend();
							//친구 검색
							search_add.btnNewButton.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									out.println("SEARCH "+search_add.textField.getText());
								}
							});
							//친구 추가
							search_add.add.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									String want_to_add_friend=search_add.search_results.getSelectedValue().toString();
									System.out.println("want to add friend: "+want_to_add_friend);
									out.println("ADD "+myInfo.getID()+"&"+want_to_add_friend);
								}
							});
							//검색한 친구 프로필 보기
							search_add.show_profile.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									String friend_id=search_add.search_results.getSelectedValue().toString();
									User friend=null;
									for(int i=0;i<search_results.size();i++)
									{
										if(search_results.get(i).getID().equals(friend_id))
										{
											friend=search_results.get(i);
											break;
										}
									}
									Profile_Screen searched_friend_profile=new Profile_Screen(friend);
								}
							});
						}
					});
					//채팅방 만들기
					friend_list_screen.btnNewButton_4.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							to_make_chattingroom=new make_chattingroom_show_list();
							to_make_chattingroom.friends_list(ON);
							to_make_chattingroom.btnNewButton.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									int[] selected_friends=to_make_chattingroom.friends.getSelectedIndices();
									Vector<String> want_to_invite=new Vector<String>();
									out.println("CHAT REQUEST "+myInfo.getID());
									for(int i=0;i<selected_friends.length;i++)
									{
										want_to_invite.add(ON.get(i).getID());
									}
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
					//쪽지함 보기
					friend_list_screen.my_menuItem_1.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							out.println("SHOW NOTES "+myInfo.getID());
						}
					});
					//내 정보 변경
					friend_list_screen.my_menuItem_2.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							System.out.println("내정보 변경");
							Change_my_info change_info=new Change_my_info(myInfo);
							//내정보 변경 완료 버튼
							change_info.completed_button.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									System.out.println("completed button");
									System.out.println("<<<<<<<<<<<>>>><><<<<<<<<id: "+myInfo.getID());
									System.out.println("name: "+change_info.name_textfield.getText());
									System.out.println("nickname: "+change_info.nickname_textfield.getText());
									System.out.println("message: "+change_info.message_textfield.getText());
									System.out.println("phone: "+change_info.phone_textfield.getText());
									System.out.println("birth: "+change_info.birth_textfield.getText());
									System.out.println("homepage: "+change_info.homepage_textfield.getText());
									System.out.println("email: "+change_info.email_textfield.getText());
									
									myInfo.setName(change_info.name_textfield.getText());
									myInfo.setNickname(change_info.nickname_textfield.getText());
									myInfo.setMessage(change_info.message_textfield.getText());
									myInfo.setPhone(change_info.phone_textfield.getText());
									myInfo.setBirth(change_info.birth_textfield.getText());
									myInfo.setHomepage(change_info.homepage_textfield.getText());
									myInfo.setEmail(change_info.email_textfield.getText());
									
									System.out.println("send to SERVER");
									//System.out.println("CHANGEMYINFO "+id+"&"+change_info.name_textfield.getText()+"&"+change_info.message_textfield.getText()+"&"+change_info.nickname_textfield.getText()+"&"
									//+change_info.phone_textfield.getText()+"&"+change_info.homepage_textfield.getText()+"&"+change_info.birth_textfield.getText()+"&"+change_info.email_textfield.getText());
									try {
										out.println("CHANGE MY INFO "+myInfo.getID());
										
										oos.writeObject(myInfo);
										oos.flush();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
				 						e1.printStackTrace();
									}
									//out.println("CHANGEMYINFO "+id+"&"+change_info.name_textfield.getText()+"&"+change_info.message_textfield.getText()+"&"+change_info.nickname_textfield.getText()+"&"
									//+change_info.phone_textfield.getText()+"&"+change_info.homepage_textfield.getText()+"&"+change_info.birth_textfield.getText()+"&"+change_info.email_textfield.getText());
									
					 				change_info.close();
								}
							});
						}
					});
					
					//쪽지보내기
					friend_list_screen.friend_menuItem_1.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							System.out.println("In client: "+friend_list_screen.receiver_id);
							send_note_screen=new Send_Note_Screen(myInfo.getID(),friend_list_screen.receiver_id);
							send_note_screen.Send_button.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									note Note;
									Calendar cal=Calendar.getInstance();
									SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
									String datestr=sdf.format(cal.getTime());
									Note=new note(myInfo.getID(),friend_list_screen.receiver_id,send_note_screen.content_textfield.getText(),datestr);
									send_note_screen.content_textfield.setText("");
									out.println("SEND NOTE "+friend_list_screen.receiver_id);
									try {
										
										oos.writeObject(Note);
										oos.flush();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
						}
					});
					//친구 프로필 보기
					friend_list_screen.friend_menuItem_4.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							System.out.println("In client: "+friend_list_screen.receiver_id);
							User friend = null;
							for(int i=0;i<friend_list.size();i++)
							{
								if(friend_list_screen.receiver_id.equals(friend_list.get(i).getID()))
								{
									friend=friend_list.get(i);
									break;
								}
							
							}
							profile=new Profile_Screen(friend);
						}
					});
					//채팅 요청 - 1:1
					friend_list_screen.friend_menuItem_3.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							if(friend_list_screen.whose.startsWith("ON"))
							{
								Vector<String> requested=new Vector<>();
								requested.add(friend_list_screen.receiver_id);
								out.println("CHAT REQUEST "+myInfo.getID());
								
								
								try {
									
									oos.writeObject(requested);
									oos.flush();
								} catch (IOException e1) {
									
									e1.printStackTrace();
								}
								
							}
							else if(friend_list_screen.whose.startsWith("OFF"))
							{
								JOptionPane.showMessageDialog(friend_list_screen, "OFF 상태! 채팅 불가!",null,JOptionPane.INFORMATION_MESSAGE);
							}
							
						}
					});
					
					
					
				}
				else if(input.startsWith("AVAILABLE"))
				{
					join_member.available_id();
				}
				else if(input.startsWith("UNAVAILABLE"))
				{
					join_member.caution_id_duplicate();
				}
				else if(input.startsWith("SEARCH RESULTS"))
				{
					search_results.clear();
					
					search_results=(Vector<User>)ois.readObject();
					search_add.show_results(search_results);
				}
				//채팅 요청을 받음.
				else if(input.startsWith("REQUEST FOR A CHAT"))
				{
					StringTokenizer st=new StringTokenizer(input.substring(19)," ");
					int which_element=0;
					String founder="";
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
						}
					}
					//System.out.println("my id: "+myInfo.getID());
					int result=JOptionPane.showConfirmDialog(null, founder+"(으)로부터 채팅 요청이 왔습니다. 수락하시겠습니까?",null,JOptionPane.YES_NO_OPTION);
					//System.out.println("my id: "+myInfo.getID());
					if(result==JOptionPane.CLOSED_OPTION||result==JOptionPane.NO_OPTION)
					{
						out.println("REJECTED CHAT REQUEST "+myInfo.getID()+" "+room_id);
					}
					else if(result==JOptionPane.YES_OPTION)
					{
						System.out.println("요청 수락! myinfo:"+myInfo.getID());
						out.println("ACCEPTED CHAT REQUEST "+myInfo.getID()+" "+room_id);
						Chatting_room_Screen chatting_screen=new Chatting_room_Screen(room_id,myInfo.getID(),out,oos,ON);
						Rooms.put(room_id,chatting_screen);
					}
				}
				//개설자로서 채팅방 개설 시도 시 일단은 채팅방 안에 들어가 있음. 하지만 아직 비활성화 
				else if(input.startsWith("MAKE CHAT ROOM"))
				{
					int room_id=Integer.parseInt(input.substring(15));
					Chatting_room_Screen chatting_screen=new Chatting_room_Screen(room_id,myInfo.getID(),out,oos,ON); //**
					Rooms.put(room_id,chatting_screen);
				}
				//채팅방 개설 실패!
				else if(input.startsWith("FAILED TO OPEN CHAT ROOM"))
				{
					int room_id=Integer.parseInt(input.substring(25));
					Chatting_room_Screen chatting_screen=Rooms.get(room_id); //**
					JOptionPane.showMessageDialog(chatting_screen, "채팅 요청 수락 인원이 0명으로 채팅방 개설 실패!",null,JOptionPane.INFORMATION_MESSAGE);
					chatting_screen.close();
					Rooms.remove(room_id);
				}
				else if(input.startsWith("CHAT ROOM OPEN SUCCESSFULLY"))
				{
					int room_id=Integer.parseInt(input.substring(28));
					Chatting_room_Screen chatting_screen=Rooms.get(room_id);
					//room_multi.new_room(chatting_screen,out,oos,ON);
					//room_pool.execute(new Handler());
					/*
					chatting_screen.send_button.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							System.out.println("send button clicked id:"+id+" room_id:"+chatting_screen.room_id);
							String message=Rooms.get(chatting_screen.room_id).message_textfield.getText();
							System.out.println("message: "+message);
							out.println("MESSAGE "+message+"\\\\"+chatting_screen.room_id+"\\\\"+myInfo.getID());
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
									out.println("INVITE FRIEND TO CHAT ROOM "+myInfo.getID()+"\\\\"+chatting_screen.room_id);
									
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
							System.out.println("left: "+id);
							out.println("LEFT "+id+" "+chatting_screen.room_id);
							Rooms.remove(chatting_screen.room_id);
							chatting_screen.close();
						}
					});
					*/
				}
				else if(input.startsWith("ENTER MESSAGE"))
				{
					String message=input.substring(14);
					StringTokenizer st=new StringTokenizer(message,"\\\\");
					int which_element=0;
					String content="";
					int room_id=0;
					while(st.hasMoreTokens())
					{
						if(which_element==0)
						{
							content=st.nextToken();
							which_element++;
						}
						else if(which_element==1)
						{
							room_id=Integer.parseInt(st.nextToken());
						}
					}
					Chatting_room_Screen chatting_screen=Rooms.get(room_id);
					chatting_screen.textArea.append(content+"\n");
				}
				else if(input.startsWith("LEFT MESSAGE"))
				{
					String message=input.substring(13);
					System.out.println(message);
					StringTokenizer st=new StringTokenizer(message,"\\\\");
					int which_element=0;
					String content="";
					int room_id=0;
					while(st.hasMoreTokens())
					{
						if(which_element==0)
						{
							content=st.nextToken();
							System.out.println("content: "+content);
							which_element++;
						}
						else if(which_element==1)
						{
							room_id=Integer.parseInt(st.nextToken());
							System.out.println("room_id: "+room_id);
						}
					}
					Chatting_room_Screen chatting_screen=Rooms.get(room_id);
					chatting_screen.textArea.append("<"+content+">"+"\n");
				}
				else if(input.startsWith("MESSAGE"))
				{
					String message=input.substring(8);
					StringTokenizer st=new StringTokenizer(message,"\\\\");
					int which_element=0;
					String content="";
					String from="";
					int room_id=0;
					while(st.hasMoreTokens())
					{
						if(which_element==0)
						{
							content=st.nextToken();
							which_element++;
						}
						else if(which_element==1)
						{
							room_id=Integer.parseInt(st.nextToken());
							which_element++;
						}
						else if(which_element==2)
						{
							from=st.nextToken();
						}
					}
					Chatting_room_Screen chatting_screen=Rooms.get(room_id);
					chatting_screen.textArea.append("<"+from+">   "+content+"\n");
				}
				else if(input.startsWith("CLOSE THE CHAT ROOM"))
				{
					int room_id=Integer.parseInt(input.substring(20));
					Chatting_room_Screen chatting_screen=Rooms.get(room_id);
					chatting_screen.close();
				}
				else if(input.startsWith("MY NOTES"))
				{
					System.out.println("here1");
					
					my_notes=(Vector<note>)ois.readObject();
					System.out.println("here2");
					for(int i=0;i<my_notes.size();i++)
					{
						System.out.println("from- "+my_notes.get(i).getsender()+" content: "+my_notes.get(i).getcontent());
					}
					My_Notes_Screen my_notes_screen=new My_Notes_Screen();
					my_notes_screen.notes(my_notes);
					
					//쪽지 삭제
					my_notes_screen.delete.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							//String want_to_delete_note=my_notes_screen.notes_list.getSelectedValue().toString();
							//out.println("DELETE "+want_to_delete_note+" "+myInfo.getID());
							int want_to_delete_note_index=my_notes_screen.notes_list.getSelectedIndex();
							out.println("DELETE");
							try {
								
								oos.writeObject(my_notes.get(want_to_delete_note_index));
								oos.flush();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					});
					//쪽지 전송 시간 보기
					my_notes_screen.show_send_time.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							int selected_note_index=my_notes_screen.notes_list.getSelectedIndex();
							Note_Send_time_Screen note_time_screen=new Note_Send_time_Screen(my_notes.get(selected_note_index));
						}
					});
					my_notes_screen.refresh.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							my_notes_screen.close();
							
							out.println("SHOW NOTES "+id);
						}
					});
				}
			}
			
			
			
			
			
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
}

	

