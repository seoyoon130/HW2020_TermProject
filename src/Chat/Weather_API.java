package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Weather_API {

	String tmn=""; //�������
      String tmx=""; //�ְ���
      String base_date;
      String now;
	public Weather_API() throws IOException {
		Date date_now=new Date(System.currentTimeMillis());
		SimpleDateFormat fourteen_format=new SimpleDateFormat("yyyyMMddHHmmss");
		now=fourteen_format.format(date_now);
		base_date=now.substring(0,8);
		System.out.println("���� ��¥:"+base_date);
		int date=Integer.parseInt(base_date)-1;
		base_date=Integer.toString(date);
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst"); ///*URL
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=HWWcc7ydy6e5YT2AQb5sVwkGOATdTjIQhPRVfqXoNTJsTTfBBpDBHpY23to4K1dC1fwEo5rcBq3coBKL%2B7qidQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); ///*��������ȣ
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("71", "UTF-8")); //�� ������ ��� ��
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); ///*��û�ڷ�����(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(base_date, "UTF-8")); //��ǥ��¥�� ������¥ 23�� ��ǥ�� �ϰ� 71���� ����� �������� ���� 3�ú��� ���� 21�ñ����� ���� ���� �� ����
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("2300", "UTF-8")); ///*23�� ��ǥ*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("63", "UTF-8"));// /*�������� X ��ǥ��*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("125", "UTF-8")); ///*���������� Y ��ǥ��*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result=sb.toString();
      
        
        try
        {
        	JSONParser parser=new JSONParser();
  	        JSONObject obj=(JSONObject)parser.parse(result);
  	        //response Ű�� ������ ������ �Ľ�
  	        JSONObject response=(JSONObject)obj.get("response");
  	        //response�κ��� body ã��
  	        JSONObject body=(JSONObject)response.get("body");
  	        //body�κ��� itemsã��
  	        JSONObject parse_items=(JSONObject)body.get("items");
  	        //items�κ��� itemlist�ޱ� -item list�� array�� �Ǿ�����
  	        JSONArray parse_item=(JSONArray) parse_items.get("item");
  	        String category;
  	      
  	        JSONObject weather;//parse_item�� �迭�����̱� ������ �ϳ��� �����͸� �����ö� ���
  	        
  	        String day="";
  	        String time="";
  	        
  	        for(int i=0;i<parse_item.size();i++)
  	        {
  	        	weather=(JSONObject)parse_item.get(i);
  	        	String fcstValue=weather.get("fcstValue").toString();
  	        	String fcstDate=weather.get("fcstDate").toString(); //������¥
  	        	String fcstTime=weather.get("fcstTime").toString();//�����ð�
  	        	category=weather.get("category").toString();
  	        	if(!day.equals(fcstDate))
  	        	{
  	        		day=fcstDate;
  	        	}
  	        	if(!time.equals(fcstTime))
  	        	{
  	        		time=fcstTime;
  	        		//System.out.println("���� ��¥/�ð�-"+day+"/"+time);
  	        	}
  	        	if(category.equals("TMN"))
	  	        {
	  	        	category="��ħ �������";
	  	        		
	  	        	fcstValue=fcstValue+"��";
	  	      		tmn=fcstValue;
	  	        }
	  	        else if(category.equals("TMX"))
	  	       	{
	  	       		category="�� �ְ���";
	  	      		fcstValue=fcstValue+"��";
	  	       		tmx=fcstValue;
	  	       	}
  	        	
  	        	//System.out.print("\t"+ category+" : ");
  				//System.out.println(fcstValue);
  				
  	  	        }
  	        System.out.println("������ ��ħ �������: "+tmn+" ������ �� �ְ���: "+tmx);
  	     }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
  	        	
        }
        
	public String toString()
	{
		return now.substring(0,8)+">"+"������ ��ħ �������: "+tmn+", ������ �� �ְ���: "+tmx;
	}
	}


