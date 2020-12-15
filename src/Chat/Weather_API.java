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

	String tmn=""; //최저기온
      String tmx=""; //최고기온
      String base_date;
      String now;
	public Weather_API() throws IOException {
		Date date_now=new Date(System.currentTimeMillis());
		SimpleDateFormat fourteen_format=new SimpleDateFormat("yyyyMMddHHmmss");
		now=fourteen_format.format(date_now);
		base_date=now.substring(0,8);
		System.out.println("오늘 날짜:"+base_date);
		int date=Integer.parseInt(base_date)-1;
		base_date=Integer.toString(date);
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst"); ///*URL
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=HWWcc7ydy6e5YT2AQb5sVwkGOATdTjIQhPRVfqXoNTJsTTfBBpDBHpY23to4K1dC1fwEo5rcBq3coBKL%2B7qidQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); ///*페이지번호
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("71", "UTF-8")); //한 페이지 결과 수
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); ///*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(base_date, "UTF-8")); //발표날짜를 어제날짜 23시 발표로 하고 71개의 결과를 가져오면 오늘 3시부터 내일 21시까지의 값을 받을 수 있음
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("2300", "UTF-8")); ///*23시 발표*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("63", "UTF-8"));// /*예보지점 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("125", "UTF-8")); ///*예보지점의 Y 좌표값*/
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
  	        //response 키를 가지고 데이터 파싱
  	        JSONObject response=(JSONObject)obj.get("response");
  	        //response로부터 body 찾기
  	        JSONObject body=(JSONObject)response.get("body");
  	        //body로부터 items찾기
  	        JSONObject parse_items=(JSONObject)body.get("items");
  	        //items로부터 itemlist받기 -item list는 array로 되어있음
  	        JSONArray parse_item=(JSONArray) parse_items.get("item");
  	        String category;
  	      
  	        JSONObject weather;//parse_item은 배열형태이기 때문에 하나씩 데이터를 가져올때 사용
  	        
  	        String day="";
  	        String time="";
  	        
  	        for(int i=0;i<parse_item.size();i++)
  	        {
  	        	weather=(JSONObject)parse_item.get(i);
  	        	String fcstValue=weather.get("fcstValue").toString();
  	        	String fcstDate=weather.get("fcstDate").toString(); //예보날짜
  	        	String fcstTime=weather.get("fcstTime").toString();//예보시간
  	        	category=weather.get("category").toString();
  	        	if(!day.equals(fcstDate))
  	        	{
  	        		day=fcstDate;
  	        	}
  	        	if(!time.equals(fcstTime))
  	        	{
  	        		time=fcstTime;
  	        		//System.out.println("예보 날짜/시간-"+day+"/"+time);
  	        	}
  	        	if(category.equals("TMN"))
	  	        {
	  	        	category="아침 최저기온";
	  	        		
	  	        	fcstValue=fcstValue+"℃";
	  	      		tmn=fcstValue;
	  	        }
	  	        else if(category.equals("TMX"))
	  	       	{
	  	       		category="낮 최고기온";
	  	      		fcstValue=fcstValue+"℃";
	  	       		tmx=fcstValue;
	  	       	}
  	        	
  	        	//System.out.print("\t"+ category+" : ");
  				//System.out.println(fcstValue);
  				
  	  	        }
  	        System.out.println("오늘의 아침 최저기온: "+tmn+" 오늘의 낮 최고기온: "+tmx);
  	     }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
  	        	
        }
        
	public String toString()
	{
		return now.substring(0,8)+">"+"오늘의 아침 최저기온: "+tmn+", 오늘의 낮 최고기온: "+tmx;
	}
	}


