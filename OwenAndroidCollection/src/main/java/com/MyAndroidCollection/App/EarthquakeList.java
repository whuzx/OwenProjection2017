package com.MyAndroidCollection.App;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.MyAndroidCollection.R;

public class EarthquakeList extends Activity {
  
  ListView list;
  EarthQuakeInfo selectedQuake;

  ArrayAdapter<EarthQuakeInfo> adapter;
  ArrayList<EarthQuakeInfo> infoList = new ArrayList<EarthQuakeInfo>();
  
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    
    setContentView(R.layout.earthquake); //设置用户界面

    list = (ListView)this.findViewById(R.id.list);
    
    //设置ListView的内容为infoList
    adapter = new ArrayAdapter<EarthQuakeInfo>(this,android.R.layout.simple_list_item_1,infoList);
    //设置ListView的适配器为adapter
    list.setAdapter(adapter);
 
    getInfo(); //获得infoList的具体内容，这样ListView才有地震信息可以显示
  }
  
  /** 刷新数据，获得最新地址信息 */
  private void getInfo() {
	  // 获得XML
	  URL url;
	  try {
		  
		 	Properties prop = System.getProperties();
			prop.setProperty("http.proxyHost", "172.29.8.29");
			prop.setProperty("http.proxyPort", "80");
	             
			
	    String feed ="http://earthquake.usgs.gov/eqcenter/catalogs/1day-M2..xml";
	    	//getString(R.string.feed);
	    url = new URL(feed);
	         
	    URLConnection connection = url.openConnection(); 
	       
	    HttpURLConnection httpConnection = (HttpURLConnection)connection; 
	    int responseCode = httpConnection.getResponseCode(); 

	    if (responseCode == HttpURLConnection.HTTP_OK) { 
	      InputStream in = httpConnection.getInputStream(); 
	          
	      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
	      DocumentBuilder db = dbfactory.newDocumentBuilder();

	      // 解析地震feed
	      Document dom = db.parse(in);      
	      Element docEle = dom.getDocumentElement();
	        
	      // 清空旧的地震信息
	      infoList.clear();
	          
	      // 获得地震信息的列表
	      NodeList nl = docEle.getElementsByTagName("entry");
	      if (nl != null && nl.getLength() > 0) {
	        for (int i = 0 ; i < nl.getLength(); i++) {
	          Element entry = (Element)nl.item(i);
	          Element title = (Element)entry.getElementsByTagName("title").item(0);
	          Element geo = (Element)entry.getElementsByTagName("georss:point").item(0);
	          Element when = (Element)entry.getElementsByTagName("updated").item(0);

	          String details = title.getFirstChild().getNodeValue();
	          String point = geo.getFirstChild().getNodeValue();
	          String date = when.getFirstChild().getNodeValue();  
	          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
	          Date qdate = new GregorianCalendar(0,0,0).getTime();
	          try {
	            qdate = sdf.parse(date);
	          } catch (ParseException e) {
	            e.printStackTrace();
	          }

	          String[] location = point.split(" ");
	          Location loc = new Location("dummyGPS");
	          loc.setLatitude(Double.parseDouble(location[0]));
	          loc.setLongitude(Double.parseDouble(location[1]));

	          String magnitudeString = details.split(" ")[1];
	          int end =  magnitudeString.length()-1;
	          double magnitude = Double.parseDouble(magnitudeString.substring(0, end));
	              
	          details = details.split(",")[1].trim();
	              
	          EarthQuakeInfo info = new EarthQuakeInfo(qdate, details, loc, magnitude);

	          // 处理新的地震信息
	          newEntry(info);
	        }
	      }
	    }
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
  }

  //新的地震信息
  private void newEntry(EarthQuakeInfo info) {
	  // 将新的地址信息条目加入列表中
	  infoList.add(info);

	  // 通知array adapter
	  adapter.notifyDataSetChanged();
	}
 //地震信息类
  public class EarthQuakeInfo {
      public Date date;
      public String details;
      public Location location;
      public double magnitude;
      
      public EarthQuakeInfo(Date d, String de, Location loc, double mag) {
        date = d;
        details = de;
        location = loc;
        magnitude = mag;
      }

      @Override
      public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'hh:mm:ss");
        return sdf.format(date) 
            + "\n 里氏" 
            + magnitude 
            + "级 \n " 
            + details;
      }
    }
}