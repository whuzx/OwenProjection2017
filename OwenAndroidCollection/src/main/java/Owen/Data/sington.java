package Owen.Data;

import android.util.Log;

public class sington {
	
	private static int a=0;
	
	private sington()
	{
		a++;
	}
	
	static sington instance;
	public static sington getSingtonInstance()
	{
		if(instance==null)
			instance=new sington();
		
		Log.e("Sigton ", Integer.toString(a));
		return instance;		
	}
	
}
