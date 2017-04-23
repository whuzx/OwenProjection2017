package com.MyAndroidCollection.App.Fragment;

/***
 * 为了允许一个Fragment跟包含他的Activity通信，我们可以在Fragment类中定义一个接口，
 * 并且在Activity内实现。Fragment在onAttach()函数里面捕获接口实现，并且调用接口方法和Activity通信。
 * （上面都说了Fragment间只能通过Activity进行通信了。）


 * @author z0020101
 *
 */
public interface HwIDListener {
	  public void onEnterPassword(int position);
	  public void onFindPassword(int position);
	  public void onCancel();
	  public void onOK();
	  
	  
	  
	  
	  
	  
	  
}
