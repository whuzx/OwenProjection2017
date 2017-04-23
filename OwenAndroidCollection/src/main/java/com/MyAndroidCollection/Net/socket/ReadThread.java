package com.MyAndroidCollection.Net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.util.Log;

public class ReadThread extends Thread{

	Socket socket = null;
	public ReadThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		BufferedReader reader = null;
		try{
			
			reader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
	

			String readStr = null;
			while(null != (readStr = reader.readLine())){
				msgdepose(readStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != reader){
				try{
					reader.close();
					reader = null;
				}catch (Exception e1) {}
			} 
			exit();
		}
	}
	void exit(){
		if(null != socket){
			try{
				socket.close();
			}catch (Exception e2) {}
		}
	}
	void msgdepose(String msg){
		//System.out.println(msg);
		Log.d("ReadThread:",msg);
	}

}
