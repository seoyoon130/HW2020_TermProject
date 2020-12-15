//최종본
package Chat;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import Chat.User;

import Chat.note;

import java.util.*;

public class JDBC {
	
	private static Connection connection=null;
	private static PreparedStatement pstmt=null;
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/chatting?allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&useSSL=false";
	private static String user="root"; //db user id
	private static String pass="akdlsqlvotmdnjem";//db user password

	public JDBC()
	{
		
		try
		{
			//1.JDBC드라이버 로딩- MySQL JDBC 드라이버의 Driver Class 로딩
			Class.forName(driver);
			//2.Connection 생성 -.getConnection(연결문자열,DB-ID,DB-pw)
			connection=DriverManager.getConnection(url,user,pass);
			System.out.println("jdbc 연결성공");
		}
		catch(ClassNotFoundException e1)
		{
			System.out.println("[JDBC Connector Driver 오류:"+e1.getMessage()+"]");
		} 
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
		
	}
	
	public boolean login(String id,String pw)
	{
		System.out.println("<JDBC>login");
		boolean checking=true;
		ResultSet rs=null;
		String select_id_SQL="select max(id) id from user where id=";
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		String datestr=sdf.format(cal.getTime());
		String state="off";
		String encryption_pw="";
		//3.Statement객체 생성
		try 
		{
			pstmt=connection.prepareStatement(select_id_SQL+"'"+id+"'");
			rs=pstmt.executeQuery();
			String result="";
			
			while(rs.next())
			{
				result=rs.getString("id");
			}
			if(result!=null)
			{
				String select_pw_SQL="select pw from user where id=";
				pstmt=connection.prepareStatement(select_pw_SQL+"'"+id+"'");
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					result=rs.getString("pw");
					//System.out.println("pw: "+result);
				}
				for(int i=0;i<pw.length();i++)
				{
					char c=pw.charAt(i);
					int num=(int)c+5;
					encryption_pw=encryption_pw+(char)num;
				}
				if(encryption_pw.equals(result))
				{
					System.out.println(">>>>>>>>>login id: "+id);
					String update_SQL="update user set login=?,state=? where id=";
					pstmt=connection.prepareStatement(update_SQL+"'"+id+"'");
					state="on";
					pstmt.setString(1, datestr);
					pstmt.setString(2, state);
					int res=pstmt.executeUpdate();
					if(res>0)
					{
						System.out.println("처리완료");
					}
					checking=true;
				}
				else
					checking=false;
			}
			else
				checking=false;
		} 
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
		
		return checking;
	}
	public String check_duplication(String id)
	{
		System.out.println("<JDBC>check_duplication");
		String select_SQL="select max(id) id from user where id=";
		ResultSet rs=null;
		String result="";
		try
		{
			pstmt=connection.prepareStatement(select_SQL+"'"+id+"'");
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				result=rs.getString("id");
			}
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
		
		return result;
	}
	public void join_membership(String name,String phone,String id,String pw,String nickname,String email,String birth,String homepage)
	{
		System.out.println("<JDBC>join_membership");
		String insert_SQL="insert into user(name,phone,id,pw,nickname,email,birth,homepage)"
				+ "values(?,?,?,?,?,?,?,?)";
		
		String encryption_pw="";
		for(int i=0;i<pw.length();i++)
		{
			char c=pw.charAt(i);
			int num=(int)c+5;
			encryption_pw=encryption_pw+(char)num;
		}
		try
		{
			pstmt=connection.prepareStatement(insert_SQL);
			pstmt.setString(1,name);
			pstmt.setString(2,phone);
			pstmt.setString(3, id);
			pstmt.setString(4,encryption_pw);
			pstmt.setString(5, nickname);
			pstmt.setString(6, email);
			pstmt.setString(7, birth);
			pstmt.setString(8, homepage);
			int res=pstmt.executeUpdate();
			if(res>0)
			{
				System.out.println("처리 완료");
			}
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
		
	}
	public int number_of_friends(String user_id)
	{
		System.out.println("<JDBC>number_of_friends");
		String select_SQL="select count(friend_id) as f_s from friend where user_id=?";
		ResultSet rs;
		//String friend_size="";
		int friend_size=0;
		try
		{
			pstmt=connection.prepareStatement(select_SQL);
			pstmt.setString(1, user_id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				friend_size=rs.getInt("f_s");
			}
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
		System.out.println("->friend_size: "+friend_size);
		
		return friend_size;
	}
	public Vector friend(String user_id)
	{
		
		System.out.println("<JDBC>friend");
		String select_SQL="select friend_id,nickname,state,message,name,phone,email,birth,homepage,login,logout from friend,user where user_id=? and friend_id=id";
		ResultSet rs;
		String id,state,message,name,phone,email,birth,homepage,login,logout,nickname;
		//String[] friend=new String[friend_size];
		Vector list=new Vector<User>();
		//int i=0;
		try
		{
			pstmt=connection.prepareStatement(select_SQL);
			pstmt.setString(1,user_id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				
				id=rs.getString("friend_id");
				nickname=rs.getString("nickname");
				state=rs.getString("state");
				message=rs.getString("message");
				name=rs.getString("name");
				phone=rs.getString("phone");
				email=rs.getString("email");
				birth=rs.getString("birth");
				homepage=rs.getString("homepage");
				login=rs.getString("login");
				logout=rs.getString("logout");
				System.out.println("->friend_id: "+id+" state: "+state);
				//System.out.println(">>>friend id: "+id+" state: "+state+" message: "+message);
				//System.out.println("name: "+name+" phone: "+phone+" email: "+email+" birth: "+birth+" homepage: "+homepage);
				//System.out.println("login: "+login+" logout: "+logout);
				
				//Friend friend=new Friend(id,state,message);
				//friend.setid(rs.getString("friend_id"));
				//friend.setstate(rs.getString("state"));
				//String friend_id=rs.getString("friend_id");
				//String state=rs.getString("state");
				//System.out.println("friend id: "+friend_id+" state: "+state);
				list.add(new User(name,phone,id,nickname,email,birth,homepage,login,logout,state,message));
				
			}
			
			
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
		//System.out.println("<list>");
		
		return list;
	}
	
	public User myinfo(String id)
	{
		
		String nickname,message,name,phone,email,birth,homepage,login,logout;
		User my=new User();
		
		System.out.println("<JDBC> my info");
		System.out.println("->id: "+id);
		System.out.println("==============================================================");
		my.setID(id);
		String select_SQL="select nickname,message,name,phone,email,birth,homepage from user where id=?";
		ResultSet rs=null;
		try
		{
			pstmt=connection.prepareStatement(select_SQL);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				nickname=rs.getString("nickname");
				my.setNickname(nickname);
				
				message=rs.getString("message");
				my.setMessage(message);
				
				name=rs.getString("name");
				my.setName(name);
				
				phone=rs.getString("phone");
				my.setPhone(phone);
				
				email=rs.getString("email");
				my.setEmail(email);
				
				birth=rs.getString("birth");
				my.setBirth(birth);
				
				homepage=rs.getString("homepage");
				my.setHomepage(homepage);
				
				//System.out.println("IN JDBC name: "+name);
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
		/*
		System.out.println("id: "+my.getid());
		System.out.println("nickname: "+my.getnickname());
		System.out.println("message: "+my.getmessage());
		System.out.println("name: "+my.getname());
		System.out.println("phone: "+my.getphone());
		System.out.println("email: "+my.getemail());
		System.out.println("birth: "+my.getbirth());
		System.out.println("homepage: "+my.gethomepage());
		System.out.println("######################################");
		*/
		return my;
	}
	public void logout(String id)
	{
		System.out.println("<JDBC>logout");
		String update_SQL="update user set logout=?,state=? where id=";
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		String datestr=sdf.format(cal.getTime());
		String state="off";
		try
		{
			pstmt=connection.prepareStatement(update_SQL+"'"+id+"'");
			state="off";
			pstmt.setString(1, datestr);
			pstmt.setString(2, state);
			int res=pstmt.executeUpdate();
			if(res>0)
			{
				System.out.println("처리완료");
			}
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
	}
	public void update_myinfo(User changed_my_info,String user_id)
	{
		int res=0;
		String update_SQL="update user set name=?,message=?,nickname=?,phone=?,homepage=?,birth=?,email=? where id=";
		try
		{
			pstmt=connection.prepareStatement(update_SQL+"'"+user_id+"'");
			pstmt.setString(1, changed_my_info.getName());
			pstmt.setString(2, changed_my_info.getMessage());
			pstmt.setString(3,changed_my_info.getNickname());
			pstmt.setString(4, changed_my_info.getPhone());
			pstmt.setString(5, changed_my_info.getHomepage());
			pstmt.setString(6, changed_my_info.getBirth());
			pstmt.setString(7, changed_my_info.getEmail());
			res=pstmt.executeUpdate();
			if(res>0)
			{
				System.out.println("처리완료");
			}
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
		//return res;
	}
	public void sendNote(String sender,String receiver,String content,String send_time)
	{
		System.out.println("sender: "+sender+"receiver: "+receiver+" content: "+content+" send_time: "+send_time);
		String insert_SQL="insert into note(sender,receiver,content,send_time) values(?,?,?,?)";
		try
		{
			pstmt=connection.prepareStatement(insert_SQL);
			pstmt.setString(1,sender);
			pstmt.setString(2,receiver);
			pstmt.setString(3, content);
			pstmt.setString(4, send_time);
			int res=pstmt.executeUpdate();
			if(res>0)
			{
				System.out.println("처리 완료");
			}
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
	}
	public Vector search_friends_results(String search)
	{
		System.out.println("<JDBC> search_friends_results");
		Vector<User> results=new Vector<User>();
		String select_SQL="select id,nickname,state,message,name,phone,email,birth,homepage,login,logout from user where id like ?";
		ResultSet rs=null;
		String id="",nickname="",state="",message="",name="",phone="",email="",birth="",homepage="",login="",logout="";
		try
		{
			pstmt=connection.prepareStatement(select_SQL);
			pstmt.setString(1,"%"+search+"%");
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				id=rs.getString("id");
				System.out.println("id: "+id);
				nickname=rs.getString("nickname");
				System.out.println("nickname: "+nickname);
				state=rs.getString("state");
				System.out.println("state: "+state);
				message=rs.getString("message");
				name=rs.getString("name");
				phone=rs.getString("phone");
				email=rs.getString("email");
				birth=rs.getString("birth");
				homepage=rs.getString("homepage");
				login=rs.getString("login");
				logout=rs.getString("logout");
				
				results.add(new User(name,phone,id,nickname,email,birth,homepage,login,logout,state,message));
				
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
		return results;
	}
	public void addFriend(String user_id,String friend_id)
	{
		System.out.println("user_id: "+user_id+" friend_id:"+friend_id);
		String insert_SQL="insert into friend(user_id,friend_id) values(?,?)";
		try
		{
			pstmt=connection.prepareStatement(insert_SQL);
			pstmt.setString(1,user_id);
			pstmt.setString(2,friend_id);
			
			int res=pstmt.executeUpdate();
			if(res>0)
			{
				System.out.println("처리 완료");
			}
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
	}
	public Vector show_notes(String id)
	{
		System.out.println("<JDBC> show_notes");
		Vector<note> notes=new Vector<note>();
		String select_SQL="select sender,receiver,content,send_time from note where receiver=?";
		ResultSet rs;
		String sender, receiver, content,send_time;
		//String[] friend=new String[friend_size];
		//ArrayList<Friend> list=new ArrayList<Friend>();
		//int i=0;
		try
		{
			pstmt=connection.prepareStatement(select_SQL);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				sender=rs.getString("sender");
				receiver=rs.getString("receiver");
				content=rs.getString("content");
				send_time=rs.getString("send_time");
				
				notes.add(new note(sender,receiver,content,send_time));
				
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
		
		return notes;
	}
	public void delete_note(String sender,String receiver,String note,String time)
	{
		System.out.println("<JDBC> delete_note");
		System.out.println("sender: "+sender);
		System.out.println("receiver: "+receiver);
		System.out.println("note: "+note);
		String delete_SQL="delete from note where sender=? and content=? and receiver=? and send_time=?";
		
		try
		{
			pstmt=connection.prepareStatement(delete_SQL);
			pstmt.setString(1, sender);
			pstmt.setString(2, note);
			pstmt.setString(3, receiver);
			pstmt.setString(4, time);
			int res=pstmt.executeUpdate();
			if(res>0)
			{
				System.out.println("처리 완료");
			}
		}
		catch(SQLException e)
		{
			System.out.println("[SQL Error:"+e.getMessage()+"]");
		}
	}
	
}
