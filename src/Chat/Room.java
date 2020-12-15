package Chat;

import java.util.*;
import java.io.*;
public class Room {
	
	
	String founder;
	Vector<String> requested_chat=new Vector<>();
	Hashtable<String,PrintWriter> founder_stream=new Hashtable<>(); //개설자
	Hashtable<String,PrintWriter> requested_chat_stream=new Hashtable<>();
	Hashtable<String,PrintWriter> participants=new Hashtable<>();
	Hashtable<String,PrintWriter> additional_requested=new Hashtable<>();
	Hashtable<String,PrintWriter> except_me=new Hashtable<>();
	int room_id; //서버가 할당
	int requested_size=0;
	int accepted_size=0;
	public Room(String founder,Vector<String> requested_chat,Hashtable<String,PrintWriter> founder_stream,Hashtable<String,PrintWriter> requested_chat_stream,int room_id)
	{
		this.founder=founder;
		this.requested_chat=requested_chat;
		this.founder_stream=founder_stream;
		this.requested_chat_stream=requested_chat_stream;
		this.room_id=room_id;
		this.requested_size=requested_chat.size();
		
		room_setting();
	}
	
	public void room_setting()
	{
		/*
		 * 개설자한테는 room_id 전송-> 채팅방 안에는 들어가있게, 하지만 아직 채팅방 비활성화->participants에 개설자 먼저 저장
		 * 참여자들한테 chat request message 전송 -chat request message(room_id,개설자)
		 */
		System.out.println("개설자: "+founder);
		PrintWriter out=founder_stream.get(founder);
		out.println("MAKE CHAT ROOM "+room_id);
		participants.put(founder,out); //개설자는 참여자 리스트에 넣어놓기
		
		broadcast(requested_chat_stream,"REQUEST FOR A CHAT "+founder+" "+room_id);
		/*
		for(java.util.Map.Entry<String,PrintWriter> entry:requested_chat_stream.entrySet())
		{
			PrintWriter writer=entry.getValue();
			String id=entry.getKey();
			System.out.println("채팅 요청 받은 사람: "+id);
			writer.println("REQUEST FOR A CHAT "+founder+" "+room_id);
		}
		*/
		
	}
	public void room_activate(String id,String accept_or_reject)
	{
		/*
		 * 채팅방 활성화 - participants에 참여자 id와 해당 stream 저장
		 * 요청 수락한 사람 수가 0명이면 채팅방 개설 실패-> 채팅방 비활성화 메세지 전송(유일한 participants인 개설자에게)
		 */
		if(accept_or_reject.equals("ACCEPT"))
		{
			broadcast(participants,"ENTER MESSAGE "+id+"님이 입장하셨습니다."+"\\\\"+room_id);
			accepted_size++;
			PrintWriter out=requested_chat_stream.get(id);
			participants.put(id,out);
			
			
		}
		else if(accept_or_reject.equals("REJECT"))
		{
			System.out.println("reject");
		}
		
		
		if(accepted_size>0)
		{
			broadcast(participants,"CHAT ROOM OPEN SUCCESSFULLY "+room_id);
		}
		else if(accepted_size==0)
		{
			PrintWriter out=founder_stream.get(founder);
			out.println("FAILED TO OPEN CHAT ROOM "+room_id);
		}
		
	}
	public void broadcast(Hashtable<String,PrintWriter> Client,String message)
	{
		/*
		 * participants들에게 message broadcast
		 */
		System.out.println("<<ROOM>");
		System.out.println("-room id: "+room_id);
		System.out.println("-founder: "+founder);
		System.out.println("-message: "+message);
		for(java.util.Map.Entry<String,PrintWriter> entry:Client.entrySet())
		{
			
			String id=entry.getKey();
			System.out.println("-client id: "+id);
		}
		for(java.util.Map.Entry<String,PrintWriter> entry:Client.entrySet())
		{
			PrintWriter writer=entry.getValue();
			String id=entry.getKey();
			System.out.println("client id: "+id);
			writer.println(message);
		}
	}
	public void remove(String left_id)
	{
		System.out.println("left id: "+left_id);
		participants.remove(left_id);
		if(participants.size()==1)
		{
			broadcast(participants,"CLOSE THE CHAT ROOM "+room_id);
		}
	}

	
	

}
