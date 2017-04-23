package com.MyAndroidCollection.task;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import android.os.Bundle;

public class Future2Task extends FutureTask<Bundle> {

	public Future2Task(Callable<Bundle> callable) {
		super(callable);
		
		


	
		
	}
	


}

class PrivateAccount implements Callable {   
    Integer totalMoney;   
/*  
    @Override  
    public Object call() throws Exception {   
        Thread.sleep(000);   
        totalMoney = new Integer(new Random().nextInt(10000));   
        System.out.println("您当前有" + totalMoney + "在您的私有账户中");   
        return totalMoney;   
    }*/

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
     
  }
