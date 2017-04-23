package com.MyAndroidCollection.Net.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class WriteThread extends Thread{

	Socket socket = null;
	public WriteThread(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		OutputStreamWriter write = null;
		try{
			write = new OutputStreamWriter(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				write.write(reader.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != write){
				try{
					write.close();
				}catch (Exception e1) {}
			} 
			if(null != socket){
				try{
					socket.close();
				} catch (Exception e2) {}
			}
		}
	}

}
