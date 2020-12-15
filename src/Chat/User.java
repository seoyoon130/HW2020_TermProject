package Chat;

import java.io.Serializable;

public class User  implements Serializable{
	
	String id,state,message,name,phone,email,birth,homepage,login,logout,nickname;

	public User(String name,String phone,String id,String nickname,String email,String birth,String homepage,String login,String logout,String state,String message)
	{
		this.id=id;
		this.state=state;
		this.message=message;
		this.name=name;
		this.phone=phone;
		this.email=email;
		this.birth=birth;
		this.homepage=homepage;
		this.login=login;
		this.logout=logout;
		this.nickname=nickname;
	}
	public User()
	{
		
	}
	public String getID()
	{
		return id;
	}
	public void setID(String id)
	{
		this.id=id;
	}
	public String getState()
	{
		return state;
	}
	
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message=message;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname=nickname;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
	}
	public String getLogin()
	{
		return login;
	}
	public String getLogout()
	{
		return logout;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public String getHomepage()
	{
		return homepage;
	}
	public void setHomepage(String homepage)
	{
		this.homepage=homepage;
	}
	public String getBirth()
	{
		return birth;
	}
	public void setBirth(String birth)
	{
		this.birth=birth;
	}
	public String toString()
	{
		return "("+id+", "+state+", "+message+", "+name+", "+phone+", "+email+", "+birth+", "
				+homepage+", "+login+", "+logout+", "+nickname+")";
	}
}
