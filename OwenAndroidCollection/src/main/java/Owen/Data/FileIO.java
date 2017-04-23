package Owen.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FileIO extends Activity {
    
    final String FILE_PATH = 
        "/data/data/com.studio.android.chp9.ex1/";
    final String FILE_NAME = "luyou.txt"; 
    final String TAG = "I/O";
    final String TEXT_ENCODING = "UTF-8";
    
    File file;
    FileOutputStream out;
    FileInputStream in;
    TextView tv;
    String display;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
        	
            
            //创建文件
            file = new File(FILE_NAME);
            file.createNewFile();
            
            //打开文件file的OutputStream
            out = new FileOutputStream(file);
            String infoToWrite = "纸上得来终觉浅，绝知此事要躬行";
            //将字符串转换成byte数组写入文件
            out.write(infoToWrite.getBytes());
            //关闭文件file的OutputStream
            out.close();
            
            //打开文件file的InputStream
            in = new FileInputStream(file);
            //将文件内容全部读入到byte数组
            int length = (int)file.length();
            byte[] temp = new byte[length];
            in.read(temp, 0, length);
            //将byte数组用UTF-8编码并存入display字符串中
            display =  EncodingUtils.getString(temp,TEXT_ENCODING);
            //关闭文件file的InputStream
            in.close();
        } catch (IOException e) {
            //将出错信息打印到Logcat
            Log.e(TAG, e.toString());
            this.finish();
        }
        
        //将读出的字符串用TextView显示到主界面
        tv = new TextView(this);
        tv.setText(display);
        setContentView(tv);
    }
}