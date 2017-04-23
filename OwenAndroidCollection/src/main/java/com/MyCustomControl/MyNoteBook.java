package com.MyCustomControl;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;

import com.MyAndroidCollection.R;

public class MyNoteBook extends EditText{
	Context mContext;
	Paint paint; 
	public MyNoteBook(Context context)
	{
		super(context);
		mContext=context;
		paint=new Paint();
		
		
	}
	public MyNoteBook(Context context,AttributeSet attrs)
	{
		super(context,attrs);
		
		mContext=context;
		paint=new Paint();
	     

	    TypedArray a = context.obtainStyledAttributes(attrs,   
	                R.styleable.MyNoteBook);   
	           
	        int textColor = a.getColor(R.styleable.MyNoteBook_textColor1, 
	                0XFFFFFFFF);   
	        float textSize = a.getDimension(R.styleable.MyNoteBook_textSize1, 24);   
	           
	        paint.setTextSize(textSize);   
	        paint.setColor(textColor);   
	           
	        a.recycle();  
	        
		
	}
	
	public MyNoteBook(Context context,AttributeSet attrs,int defStyles)
	{
		super(context,attrs,defStyles);
		mContext=context;
	}
	
	protected void onDraw(Canvas canvas)
	{
		
		WindowManager wm=(WindowManager)mContext.getSystemService("window");
		int windowWidth=wm.getDefaultDisplay().getWidth();
		int windowHeight=wm.getDefaultDisplay().getHeight();
		
		//Paint paint=new Paint();
		paint.setStyle(Paint.Style.FILL);
		//paint.setColor(Color.BLUE);
		
		int paddingTop=getPaddingTop();
		int paddingBottom = getPaddingBottom();
		
		int scrollY=getScrollY();
		int scrollX= getScrollX()+windowWidth;
		
		 int innerHeight   = scrollY + getHeight() - paddingTop - paddingBottom;
		 int lineHeight    = getLineHeight();
		 int baseLine      = scrollY + (lineHeight - (scrollY % lineHeight));

		  int x = 8;
		  while (baseLine < innerHeight) {
		    //canvas.drawBitmap(line, x, baseLine + paddingTop, paint); 
		    canvas.drawLine(x, baseLine + paddingTop,scrollX, baseLine + paddingTop, paint);
		    baseLine += lineHeight;
		  }
//		
//        //画一个矩形,前俩个是矩形左上角坐标，后面俩个是右下角坐标   
       // canvas.drawRect(new Rect(100, 100, 200, 200), paint);   
        //paint.setColor(Color.RED);   
//        //绘制文字   
        canvas.drawText("haha", 10,10, paint);   
		super.onDraw(canvas);
         
	}
	
	public boolean onPreDraw()
	{
		Log.i("MyControl","onPreDraw");
		return true;
	}

}
