package com.MyAndroidCollection.App;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.MyAndroidCollection.R;

public class CurrentWeather extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        
       	
/*    	Properties prop = System.getProperties();
		prop.setProperty("http.proxyHost", "172.29.8.29");
		prop.setProperty("http.proxyPort", "80");*/
             
        Button submit = (Button) findViewById(R.id.btn);
        submit.setOnClickListener(new OnClickListener() {
        	@Override
            public void onClick(View arg0) {
        		/*从google上获得图标*/
                try {
                	/*获取用户输入的城市名称*/
                	String city = ((EditText) findViewById(R.id.input))
                    .getText().toString();
                	
                	/*组成URL字符串*/
                	//中文：http://www.google.com/ig/api?hl=zh-cn&weather=
                	//英文：http://www.google.com/ig/api?weather=
                	String queryString = "http://www.google.com/ig/api?weather="
                    + city;
                	/*将可能的空格替换为"%20"*/
                	URL aURL = new URL(queryString.replace(" ", "%20"));
                                        
                	 /* 从SAXParserFactory获取SAXParser*/
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    SAXParser sp = spf.newSAXParser();

                    /* 从SAXParser得到XMLReader*/
                    XMLReader xr = sp.getXMLReader();

                    /*
                     * 创建GoogleWeatherHandler，以便解析XML内容
                     */
                    GoogleWeatherHandler gwh = new GoogleWeatherHandler();
                    xr.setContentHandler(gwh);

                    /* 解析XML文件内容 */
                    xr.parse(new InputSource(aURL.openStream()) );

                    
                    TextView tv1 = (TextView)findViewById(R.id.tem);
                    tv1.setText("温度：" + gwh.getCurrentTemp() + "摄氏度");
                    
                    TextView tv2 = (TextView)findViewById(R.id.weather);
                    tv2.setText(gwh.getCurrentCondition());
                    
                    TextView tv3 = (TextView)findViewById(R.id.hum);
                    tv3.setText(""+ gwh.getCurrentHum() );
                    
                    //HTTP
                    URL iconURL = new URL("http://www.google.com"+ gwh.getIconURL());
                	URLConnection conn = iconURL.openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    //设置icon
                    ImageView iv = (ImageView)findViewById(R.id.iconOfWeather);
                    Bitmap bm = null;
                    bm = BitmapFactory.decodeStream(bis);
                    iv.setImageBitmap(bm);
                    
                    //全局数据  赋值
                    MyApp myApp=(MyApp) getApplication();
                    myApp.setmBitmap(bm);
                    
                    bis.close();
                    is.close();
               } catch (Exception e) {
                    Log.e("error",e.toString());
               }
        	}//end of onClick
        });//end of SetClick
    }
}       
       
 