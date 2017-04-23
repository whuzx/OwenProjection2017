package com.MyAndroidCollection.xml;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.MyAndroidCollection.R;

public class XmlParse extends Activity implements OnClickListener {
	
	private static final String BOOKS_PATH = "/sdcard/book.xml";   
	private Button createBtn,domBtn,xmlpullBtn;   
	private TextView mTextView; 
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmllayout);
		setupViews();
	}

	private void setupViews() {
		 mTextView = (TextView)findViewById(R.id.result);  
		 createBtn=(Button) findViewById(R.id.createbtn1);
		 domBtn=(Button) findViewById(R.id.dombtn2);
		 xmlpullBtn=(Button) findViewById(R.id.dxmlpullbtn2);
		 createBtn.setOnClickListener(this);
		 domBtn.setOnClickListener(this);
		 xmlpullBtn.setOnClickListener(this);
		

		
	}

	@Override
	public void onClick(View v) {
		if(v==createBtn)
		{
			createXmlFile();
			//Test();
			usesdcardTest();
			// Test();
		}
		else if(v==domBtn)
		{
			domParseXml();
		}
		else if(v==xmlpullBtn)
		{
			XmlPullParser();
		}
	}

	private void XmlPullParser() {
		String res = "本结果是通过XmlPullParse解析:" + "\n";
		Log.e("XmlPullParser", res);
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = factory.newPullParser();
			Log.e("XmlPullParser1", "0000");
			xmlPullParser.setInput(Thread.currentThread()
					.getContextClassLoader().getResourceAsStream("book.xml"),
					"UTF-8");
			Log.e("XmlPullParser1", "1111");

			int eventType = xmlPullParser.getEventType();

			try {
				while (eventType != XmlPullParser.END_DOCUMENT) {
					String nodeName = xmlPullParser.getName();
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if ("bookname".equals(nodeName)) {
							res += "书名: " + xmlPullParser.nextText() + " ";
						} else if ("bookauthor".equals(nodeName)) {
							res += "作者: " + xmlPullParser.nextText() + "\n";
						}
						break;
					default:
						break;
					}
					eventType = xmlPullParser.next();
				}
			} catch (IOException e) {
				e.printStackTrace();
				Log.e("IOException", e.getMessage());
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			Log.e("XmlPullParserException", e.getMessage());
		}

		mTextView.setText(res);

	}

	private void domParseXml() {
		File file = new File(BOOKS_PATH);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = db.parse(file);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element root = doc.getDocumentElement();
		NodeList books = root.getElementsByTagName("book");
		String res = "本结果是通过dom解析:" + "\n";
		for (int i = 0; i < books.getLength(); i++) {
			Element book = (Element) books.item(i);
			Element bookname = (Element) book.getElementsByTagName("bookname")
					.item(0);
			Element bookauthor = (Element) book.getElementsByTagName(
					"bookauthor").item(0);
			res += "书名: " + bookname.getFirstChild().getNodeValue() + " "
					+ "作者: " + bookauthor.getFirstChild().getNodeValue() + "\n";
		}

		mTextView.setText(res);

	}

	private void createXmlFile() {
		
		
		
		File linceseFile = new File(BOOKS_PATH);
		Log.e("Xml path:", BOOKS_PATH);
		try {
			linceseFile.createNewFile();
		} catch (IOException e) {
			Log.e("IOException", "exception in createNewFile() method");
		}
		FileOutputStream fileos = null;
		try {
			//FileOutputStream aFileOutputStream=new FileOutputStream(BOOKS_PATH,false);
			fileos = new FileOutputStream(linceseFile);
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", "can't create FileOutputStream");
		}
		XmlSerializer serializer = Xml.newSerializer();
		try {
			serializer.setOutput(fileos, "UTF-8");
			serializer.startDocument(null, true);
			serializer.startTag(null, "books");
			for (int i = 0; i < 3; i++) {
				serializer.startTag(null, "book");
				serializer.startTag(null, "bookname");
				serializer.text("Android教程" + i);
				serializer.endTag(null, "bookname");
				serializer.startTag(null, "bookauthor");
				serializer.text("Frankie" + i);
				serializer.endTag(null, "bookauthor");
				serializer.endTag(null, "book");
			}

			serializer.endTag(null, "books");
			serializer.endDocument();
			serializer.flush();
			fileos.close();
		} catch (Exception e) {
			Log.e("Exception", "error occurred while creating xml file");
		}
		Toast.makeText(getApplicationContext(), "创建xml文件成功!",
				Toast.LENGTH_SHORT).show();

	}

	private void Test() {
		// TODO Auto-generated method stub

		String tString="<books><book><bookname>Android0</bookname><bookauthor>Frankie0</bookauthor></book><book><bookname>Android1</bookname><bookauthor>Frankie1</bookauthor></book><book><bookname>Android2</bookname><bookauthor>Frankie2</bookauthor></book></books>";
		FileOutputStream fileOutputStream=null;
		try {
			fileOutputStream=this.openFileOutput("book.xml",MODE_WORLD_WRITEABLE);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fileOutputStream.write(tString.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void usesdcardTest()
	{
		String sDStateString = android.os.Environment.getExternalStorageState(); 
		Log.e("sdcard", sDStateString);
		if (sDStateString.equals(android.os.Environment.MEDIA_MOUNTED)) { 
			Log.e("sdcard", "MEDIA_MOUNTED");
			try {  
				  
		        // 获取扩展存储设备的文件目录  
		        File SDFile = android.os.Environment  
		                .getExternalStorageDirectory();  
		  
		        // 打开文件  
		        File myFile = new File(SDFile.getAbsolutePath()  
		                + File.separator + "book0.txt");  
		  
		        Log.e("sdcard path", SDFile.getAbsolutePath());
		        // 判断是否存在,不存在则创建  
		        if (!myFile.exists()) {  
		            myFile.createNewFile();  
		        }  
		  
		        // 写数据  
		        String szOutText = "Hello, World!";  
		        FileOutputStream outputStream = new FileOutputStream(myFile);  
		        
		        outputStream.write(szOutText.getBytes());  
		        outputStream.close();  
		  
		    } catch (Exception e) {  
		        // TODO: handle exception  
		    }// end of try  
		  
		}// end of if(MEDIA_MOUNTED)  
		else if (sDStateString.endsWith(android.os.Environment.MEDIA_MOUNTED_READ_ONLY)) {  
		  
			Log.e("sdcard", "MEDIA_MOUNTED_READ_ONLY");
		    // 获取扩展存储设备的文件目录  
		    File SDFile = android.os.Environment.getExternalStorageDirectory();  
		  
		    // 创建一个文件  
		    File myFile = new File(SDFile.getAbsolutePath() + File.separator  
		            + "book0.xml");  
		  
		    // 判断文件是否存在  
		    if (myFile.exists()) {  
		        try {  
		  
		            // 读数据  
		            FileInputStream inputStream = new FileInputStream(myFile);  
		            byte[] buffer = new byte[1024];  
		            inputStream.read(buffer);  
		            inputStream.close();  
		  
		        } catch (Exception e) {  
		            // TODO: handle exception  
		        }// end of try  
		    }// end of if(myFile)  
		}// end of if(MEDIA_MOUNTED_READ_ONLY)  
		// end of func  


		}
	}


