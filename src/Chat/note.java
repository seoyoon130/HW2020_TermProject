package Chat;

import java.io.Serializable;

public class note implements Serializable{

	String sender;
	String receiver;
	String content;
	String send_time;
	
	public note(String sender,String content)
	{
		this.sender=sender;
		this.content=content;
	}
	public note()
	{
		
	}
	public note(String sender,String receiver,String content,String send_time)
	{
		this.sender=sender;
		this.receiver=receiver;
		this.content=content;
		this.send_time=send_time;
	}
	public String getsender()
	{
		return sender;
	}
	public String getreceiver()
	{
		return receiver;
	}
	public String getcontent()
	{
		return content;
	}
	public String getsend_time()
	{
		return send_time;
	}
	public String toString()
	{
		return "("+sender+", "+receiver+", "+content+", "+send_time+")";
	}
	
}
