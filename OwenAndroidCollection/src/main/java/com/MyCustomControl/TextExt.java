package com.MyCustomControl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextExt extends LinearLayout {

	private TextView tv;
	private ImageButton ib;
	private onExtClickListener clickListener;// 有这个实例

	public TextExt(Context context, String text, int imgres) {
		super(context);
		this.setOrientation(VERTICAL);
		tv = new TextView(context);
		tv.setText(text);
		addView(tv, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));

		ib = new ImageButton(context);
		ib.setImageResource(imgres);
		ib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (clickListener != null) {
					clickListener.onclickListenr(getText());// 触发事件
					clickListener.onclickListenr1(v, getText());
				}
			}
		});
		addView(ib, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
	}

	public void setText(String text) {
		tv.setText(text);
	}

	public String getText() {
		return tv.getText().toString();
	}

	public void setImage(int res) {
		ib.setImageResource(res);
	}

	public Drawable getDrawable() {
		return ib.getDrawable();
	}

	public void setDrawable(Drawable dd) {
		ib.setImageDrawable(dd);
	}

	public void setOnExtClickListener(onExtClickListener click) {
		this.clickListener = click;
	}

}
